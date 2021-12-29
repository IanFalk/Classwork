/********************************************************************************************************************************************
 * File: Program3.cpp
 * Author: Ian Falk
 * Modified on: 11 November 2021
 *  by: Ian Falk
 * 
 * Procedures:
 * main             - Creates a data set and provides it to replacement methods. Prints out statistics of each algorithms performance. 
 * clockReplace     - Runs the clock replacement algorithm on a data set, and returns the number of page faults. 
 * FIFOReplace      - Runs the FIFO replacement algorithm on a data set, and returns the number of page faults.
 * LRUReplace       - Runs the LRU replacement algorithm on a data set, and returns the number of page faults.
 * ******************************************************************************************************************************************/
#include <iostream>
#include <string>
#include <random>
#include <deque>
#include <unistd.h>

using namespace std;
const int NUM_OF_EXPERIMENTS = 1000;

/********************************************************************************************************************************************
 * int clockReplace(int wss, int data[])
 * Author: Ian Falk
 * Modified on: 11 November 21
 *  by: Ian Falk
 * Description: Performs the clock replacement algorithm on a provided dataset. 
 *              Returns the number of page faults (after initial frame allocation). 
 * 
 * Parameters: 
 *      clockReplace    O/P     int      The number of page faults that occured. 
 *      wss             I/P     int      The "working set size", or the number of frames. 
 *      data            I/P     int[]    The data set to test the algorithm on. 
 ********************************************************************************************************************************************/
int clockReplace(int wss, int data[]) {
    deque<int> que(wss);                                    //Creates a new deque limited to the size of wss
    int bit[wss] = {0};                                     //Creates an array to track the use bit of each frame. 
    int numOfFaults=0;

    int ptr =0;                                             //Used as the pointer for the "circular buffer"

    for(int i=0; i<NUM_OF_EXPERIMENTS; i++) {               //Loops through each "page" in the data set
        bool contains = false;                              //A variable used to keep track if a page is already in the queue

        //Checks to see if the page 
        // is already in the queue
        for(int j=0; j<wss; j++) {
            if(que.at(j) == data[i]) {
                contains = true;                            
                bit[j] = 1;                                 //Sets the use bit of the frame to 1. 
            }
        }

        if(!contains) {
            while(true) {                                   //Loops until a new page has been loaded into a frame. 

                if(bit[ptr] == 0) {                         //Checks if the current frame is eligble for replacement.
                    que.erase(que.begin()+ptr);             //Removes the current page in the frame being pointed to.
                    que.insert(que.begin()+ptr, data[i]);   //Inserts a new page into the frame being pointed to.

                    numOfFaults++;                          //Increments the number of page faults that have occured.
                    bit[ptr] = 1;                           //Sets the use bit of the frame to 1.

                    if(ptr == wss-1) {                      //If the pointer is at the end of the wss index, 
                        ptr = 0;                            //reset to 0 for "circular" effect.
                    } else {
                        ptr++;                              
                    }
                    break;                                  //Since a new page has been inserted, break out of while loop. 

                } else if(bit[ptr] == 1) {                  //Checks if a page is not eligible for replacement
                    bit[ptr] = 0;                           //After visiting the page, set the use bit to 0. 

                    if(ptr == wss-1) {                      //If the pointer is at the end of the wss index, 
                        ptr = 0;                            //reset to 0 for "circular" effect.
                    } else {
                        ptr++;
                    }
                }
            }
        }

    }

    return numOfFaults-wss;                                 //Returns number of page faults, minus wss
}

/********************************************************************************************************************************************
 * int FIFOReplace(int wss, int data[])
 * Author: Ian Falk
 * Modified on: 11 November 21
 *  by: Ian Falk
 * Description: Performs the First In, First Out (FIFO) replacement algorithm on a provided dataset. 
 *              Returns the number of page faults (after initial frame allocation). 
 * 
 * Parameters: 
 *      FIFOReplace     O/P     int      The number of page faults that occured. 
 *      wss             I/P     int      The "working set size", or the number of frames. 
 *      data            I/P     int[]    The data set to test the algorithm on. 
 ********************************************************************************************************************************************/
int FIFOReplace(int wss, int data[]) {
    deque<int> que(wss);                                    //Creates a new deque limited to the size of wss.
    int numOfFaults=0;

    for(int i=0; i<NUM_OF_EXPERIMENTS; i++) {               //Loops through each "page" in the data set. 
        bool contains = false;                              //A variable used to keep track if a page is already in the queue
        
        //Checks to see if the page 
        // is already in the queue
        for(int j=0; j<wss; j++) {
            if(que.at(j) == data[i]) {
                contains = true;
            }
        }

        if(!contains) {
            que.push_front(data[i]);                        //Pushes a new page to the front of the deque. 
            numOfFaults++;                                  //Increments the number of page faults that have occured. 
        }
    }

    return numOfFaults-wss;                                 //Returns number of page faults, minus wss. 
}

/********************************************************************************************************************************************
 * int LRUReplace(int wss, int data[])
 * Author: Ian Falk
 * Modified on: 11 November 21
 *  by: Ian Falk
 * Description: Performs the Least Recently Used (LRU) replacement algorithm on a provided dataset. 
 *              Returns the number of page faults (after initial frame allocation). 
 * 
 * Parameters: 
 *      LRUReplace      O/P     int      The number of page faults that occured. 
 *      wss             I/P     int      The "working set size", or the number of frames. 
 *      data            I/P     int[]    The data set to test the algorithm on. 
 ********************************************************************************************************************************************/
int LRUReplace(int wss, int data[]) {
    deque<int> que(wss);                                    //Creates a new deque limited to the size of wss.
    int numOfFaults=0;

    for(int i=0; i<NUM_OF_EXPERIMENTS; i++) {               //Loops through each "page" in the data set. 
        bool contains = false;                              //A variable used to keep track if a page is already in the queue. 
        int index = -1;                                     //A variable used to track which frame a page is located. 
        

        //Checks to see if the page 
        // is already in the queue
        for(int j=0; j<wss; j++) {
            if(que.at(j) == data[i]) {
                contains = true;
                index = j;                                  //Sets the index to the current frame. 
            }
        }
        
        //If a page is already in the queue,
        //remove it from its current location,
        //and push it back to the front of the queue.
        if(contains) {
            que.erase(que.begin()+index);
            que.push_front(data[i]);

        //If the page is not in the queue, 
        //push it to the front of the queue. 
        } else {
            que.push_front(data[i]);
            numOfFaults++;                                  //Increments the number of page faults that have occured. 
        }
    }
    
    return numOfFaults-wss;                                 //Returns number of page faults, minus wss. 
    
}

/********************************************************************************************************************************************
 * int main(int argc, char *argv[])
 * Author: Dr. Richard Goodrum
 * Modified on: 11 November 21
 *  by: Ian Falk
 * Description: Suggested program structure for Program 3.  
 *              Generates a random data set, and outputs statistics of different replacement algorithms performance on the data set.  
 * 
 * Parameters: 
 *      main            O/P     int         Status code (not currently used).
 *      argc            I/P     int         The number of arguments on the command line. 
 *      argv            I/P     char *[]    The arguments on the command line.
 *      
 ********************************************************************************************************************************************/
int main(int argc, char *argv[]) {
    int data[NUM_OF_EXPERIMENTS];                           //Creates a new data array to hold 1000 "pages" with random values. 

    normal_distribution<double> distribution(10.0, 2.0);    //A normal distribution generator, with a mean of 10, and stddev of 2. 
    random_device rd;                                       //A new random device, needed for a random gen. 
    default_random_engine generator(rd());                  //Starts the random gen

    int LRUResults[21];                                     //Array used to store the number of page faults from the LRU algorithm.
    int FIFOResults[21];                                    //Array used to store the number of page faults from the FIFO algorithm.
    int clockResults[21];                                   //Array used to store the number of page faults from the Clock algorithm.

    for(int i=0; i<NUM_OF_EXPERIMENTS; i++) {               //Experiments loop

        for(int j=0; j<NUM_OF_EXPERIMENTS; j++) {           //Trace loop

            // Generate a random number from a normal distribution
            // with a mean of ten and a standard deviation of two.
            // There are ten regions which have their own base address.
            data[j] = (10 * ((int) ( j / 100)) ) + distribution(generator);

        }

        //Determine and accumulate the number of page
        // faults for each algorithm based on the current
        // working set size and the current trace. 
        for(int wss = 4; wss <= 20; wss++) {
            LRUResults[wss] += LRUReplace(wss, data);
            FIFOResults[wss] += FIFOReplace(wss, data);
            clockResults[wss] += clockReplace(wss, data);
        }

    }
    
    //Outputs the LRU replacement averages for each working set size. 
    cout << "LRU Replacement Averages: \n";
    for(int wss = 4; wss <= 20; wss++) {
        printf("Working set size: %d, Avg # Of Page Faults: %d \n", wss, LRUResults[wss]/NUM_OF_EXPERIMENTS);
    }

    //Outputs the FIFO replacement averages for each working set size. 
    cout << "\nFIFO Replacement Averages: \n";
    for(int wss = 4; wss <= 20; wss++) {
        printf("Working set size: %d, Avg # Of Page Faults: %d \n", wss, FIFOResults[wss]/NUM_OF_EXPERIMENTS);
    }

    //Outputs the Clock replacement averages for each working set size. 
    cout << "\nClock Replacement Averages: \n";
    for(int wss = 4; wss <= 20; wss++) {
        printf("Working set size: %d, Avg # Of Page Faults: %d \n", wss, clockResults[wss]/NUM_OF_EXPERIMENTS);
    }

}

