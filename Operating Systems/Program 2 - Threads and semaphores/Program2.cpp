/********************************************************************************************************************************************
 * File: Program2.cpp
 * Author: Ian Falk
 * Modified on: 19 October 2021
 *  by: Ian Falk
 * 
 * Procedures:
 * main             -Inits/destroys semaphores, creates/cancels threads, and prints out statistics after 5 min. 
 * philosopher      -Continually read/write semaphores, and calls eat or think when appropriate. 
 * eat_or_think     -sleeps for a random time of [25,49] ms, and returns the entire time spent in the method. 
 * ******************************************************************************************************************************************/
#include <semaphore.h>
#include <chrono>
#include <unistd.h>
#include <pthread.h>
#include <iostream>
#include <thread>

using namespace std;
using namespace std::chrono;


sem_t utensil[5];                                               //Array of 5 sempahores used to represent the available forks on the table. 
sem_t room;                                                     //Semaphore used to limit the num. of phil. in the room at once.
double spent_eating[] = {0,0,0,0,0,0,0,0,0,0};                  //Array that will store the time spent, and num of times, the phil. ate. 
double spent_thinking[] = {0,0,0,0,0,0,0,0,0,0};                //Array that will store the time spent, and num of times, the phil thought.


/********************************************************************************************************************************************
 * double eat_or_think()
 * Author: Ian Falk
 * Modified on: 19 October 2021
 *  by: Ian Falk
 * Description: Generates a random number between 25 and 49, and then sleeps for that number of milliseconds.
 *              Records and returns the entire time spent in the method (not just the time spent sleeping).
 * 
 * Parameters: 
 *      eat_or_think    O/P     double      The amount of time spent in this method.
 ********************************************************************************************************************************************/
double eat_or_think() {
    auto start = high_resolution_clock::now();                  //Gets an accurate start time for eat_or_think().
    long ran = 25+(rand()% (50-25) );                           //Generates a random number [25,49].
    this_thread::sleep_for(milliseconds(ran));                  //Sleeps for a random number of msecs between 25 and 49.
    auto end = high_resolution_clock::now();                    //Gets an accurate end time for eat_or_think().

    return duration_cast<milliseconds>(end-start).count();      //Calculate the time spent eating or thinking.
}

/********************************************************************************************************************************************
 * void* philosopher(void* num) 
 * Author: William Stallings
 * Modified on: 19 October 2021
 *  by: Ian Falk
 * Description: Loops indefinitely. When the phil. is in the room he eats, and when he is outside the room he thinks. 
 * Increments semaphores when it moves into the room, and picks up forks. Decrements semaphores when it puts down the forks and leaves the room.   
 * 
 * Parameters: 
 *      num             I/P     void*       Pointer to the ID of the philosopher. 
 *      philosopher     O/P     void*       Pointer to thread location (not currently used).
 ********************************************************************************************************************************************/
void* philosopher (void* num) {
    int i = *((int *)num);                                      //Converts num to an int pointer, and sets i to the value being pointed at by int* num. 
    while(true) {                                               //Loop forever (until the thread is cancelled)
        spent_thinking[i] += eat_or_think();                    //Think, and store the time spent doing so. 
        spent_thinking[i+5] += 1;                               //Increments the # of times the phil. has thought
        sem_wait(&room);                                        //Enter the room (if there is space)
        sem_wait(&utensil[i]);                                  //Pick up the left fork
        sem_wait(&utensil[(i+1) % 5]);                          //Pick up the right fork
        spent_eating[i] += eat_or_think();                      //Eat, and store the time spent doing so.
        spent_eating[i+5] += 1;                                 //Increments the # of times the phil. has ate. 
        sem_post(&utensil[(i+1) % 5]);                          //Put down the right fork
        sem_post(&utensil[i]);                                  //Put down the left fork
        sem_post(&room);                                        //Leave the room
    }
    return 0;
}

/********************************************************************************************************************************************
 * int main(int argc, char *argv[]) 
 * Author: Ian Falk
 * Modified on: 19 October 2021
 *  by: Ian Falk
 * Description: Creates semaphores and philosopher threads. After 5 minutes, cancels the threads and destroys the semaphores.
 *              It also prints statistics to the console, and records the total running time of the program. 
 * 
 * Parameters: 
 *      argc            I/P     int         The number of arguments on the command line. 
 *      argv            I/P     char *[]    The arguments on the command line.
 *      main            O/P     int         Status code (not currently used).
 ********************************************************************************************************************************************/
int main(int argc, char *argv[]) {
    srand(time(NULL));                                                              //Sets the random generator seed.
    auto start = high_resolution_clock::now();                                      //Stores the time the program begins running

    for(int i=0; i<5; i++) {
        sem_init(&utensil[i], 0, 1);                                                //Inits 5 utensil semaphores with a value of 1. 
    }
    sem_init(&room, 0, 4);                                                          //Inits the room semaphore with a value of 4. 

    pthread_t t[5];                                                                 //Inits 5 thread IDs for the philosophers. 
    
    for(int i=0; i < 5; i++) {
        pthread_create(&t[i], NULL, philosopher, &i);                               //Creates a thread for philosopher #i (with i being 0-5).
        this_thread::sleep_for(microseconds(1));                                    //Provides a brief (1us) delay before creating the next thread.
    }

    this_thread::sleep_for(minutes(5));                                             //Sleeps this thread for 5 minutes. 

    for(int i=0; i<5; i++) {
        pthread_cancel(t[i]);                                                       //Terminates all the philosopher threads. 
    }

    
    for(int i=0; i<5; i++) {
        printf("Philosopher #%d thought %.0f number of times, for a total of %.2f seconds, or %.0f ms.\n",
        i+1,spent_thinking[i+5], spent_thinking[i]/1000, spent_thinking[i]);        //Prints out the num of times the phil. thought, and for how long.
        printf("Philosopher #%d ate %.0f number of times, for a total of %.2f seconds, or %.0f ms.\n\n",
        i+1,spent_eating[i+5], spent_eating[i]/1000, spent_eating[i]);              //Prints out the num of times the phil. ate, and for how long
    }

    for(int i=0; i<5; i++) {
        sem_destroy(&utensil[i]);                                                   //Releases the resources used for the Utensil semaphores.  
    }
    sem_destroy(&room);                                                             //Releases the resources used for the room semaphore. 

    auto end = high_resolution_clock::now();                                        //Stores the time the program stops running.
    cout << "Total Running time: "
    << duration_cast<minutes>(end-start).count() << " minutes.\n";                  //Prints the total time the program was running. 

    return 0;
}
