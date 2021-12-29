/********************************************************************************************************************************************
 * File: Program1.cpp
 * Author: Ian Falk
 * Modified on: 13 September 2021
 *  by: Ian Falk
 * 
 * Procedures:
 * main             -prints system information, and prints results 
 * isMagicSquare    -checks if a 9 digit input qualifies as a "Magic Square".
 * getUserInput     -takes input from the user and stores it in a char array.
 * serverClient     -creates "client" and "server" processes that send data between each other.
 * ******************************************************************************************************************************************/
#include <iostream>
#include <unistd.h>
#include <cstring>
#include <sys/utsname.h>
#include <sys/wait.h>

using namespace std;

/********************************************************************************************************************************************
 * bool isMagicSquare(char numbers[])
 * Author: Ian Falk
 * Modified on: 13 September 2021
 *  by: Ian Falk
 * Description: Converts input data to a 2d array. Iterates horizontally, vertically, and diagonally to verify the matrix is a "Magic Square".
 * 
 * Parameters: 
 *      numbers         I/P   char[9]       The 9 digits to be verified as a magic square.
 *      isMagicSquare   O/P   bool          The result of the input being verified as a magic square
 ********************************************************************************************************************************************/
bool isMagicSquare(char numbers[9]) {
    int sum=0;          //Used to track the current sum of a row/col
    int prevsum=0;      //Used to track the previous sum of a row/col
    int magic[3][3];    //Initializes a 3x3 matrix

    int ptr = 0;    //Used to step through the 1d array numbers[]

    //Iterates left to right, top to bottom, storing values from numbers[] in the matrix. 
    for(int i=0; i<3; i++) {
        for(int j=0; j<3; j++) {
            magic[i][j] = (numbers[ptr]-'0');   //Since numbers[] holds chars, subtracting '0' gets the int value of the chars. 
            ptr++;
        }
    }

    for(int i=0; i<3; i++){         //Loops through each column of magic[][]
        for (int j=0; j<3; j++) {   //Loops through each row of magic[][]
            sum += magic[i][j];     //Sums all the values in this row.
        }
        
        //Checks if the sum of the previous row is equal to the sum of the current row.
        //If the two sums are not equal, then the matrix is not a magic square and returns false. 
        //Skips this comparison on the first row, as prevsum will always be 0. 
        if(prevsum != sum && i>0) {
            return false;
        }
        prevsum = sum;
        sum =0;
    }

    for(int i=0; i<3; i++){         //Loops through each row of magic[][]
        for (int j=0; j<3; j++) {   //Loops through each column of magic[][]
            sum += magic[j][i];     //Sums all the values in this column.
        }
        
        //Checks if the sum of the previous column is equal to the sum of the current column.
        //If the two sums are not equal, then the matrix is not a magic square and returns false. 
        //Skips this comparison on the first column, as prevsum will always be 0. 
        if(prevsum != sum && i>0) {
            return false;
        }
        prevsum = sum;
        sum =0;
    }

    for(int i=0; i<3; i++) {    //Increments i to loop diagonally over the matrix. 
        sum += magic[i][i];     //Sums the values of the matrix diagonal {0,0} -> {1,1} -> {2,2}
    }

    //Checks if the sum of the previous column is equal to the sum of the diagonal.
    //If the two sums are not equal, then the matrix is not a magic square and returns false. 
    if(prevsum != sum) {
            return false;
    }
    prevsum = sum;
    sum =0;

    int k=2;
    for(int q=0; q<3; q++, k--) {   //Increments q, and decrements k, to loop diagonally over the matrix. 
        sum += magic[k][q];         //Sums the values of the matrix diagonal {2,0} -> {1,1} -> {0,2}
    }
    
    //Checks if the sum of the previous diagonal is equal to the sum of the current diagonal.
    //If the two sums are not equal, then the matrix is not a magic square and returns false. 
    if(prevsum != sum) {
            return false;
    }

    return true;    //If the code reaches this point, then the matrix represents a magic square and thus returns true.
}

/********************************************************************************************************************************************
 * char* getUserInput()
 * Author: Ian Falk
 * Modified on: 13 September 2021
 *  by: Ian Falk
 * Description: Gets a string of user input, stores only the first 9 digits of the string into a char array.
 * 
 * Parameters: 
 *      getUserInput   O/P   char*          The pointer to an array of digits the user wants to verify as a magic square
 ********************************************************************************************************************************************/
char* getUserInput() {
    string input;
    static char numbers[9]; //The magic square (and therefore this array) is limited to 9 digits.

    cout << "Please enter 9 numbers you would like to verify as a magic square:\n";
    getline(cin, input);    //Gets the entire next line entered into the terminal
    
    int ptr=0;                                  //Used to track index in numbers[] array
    for(int i=0; i<input.length(); i++) {       //Loops over the entire string the user entered
        if(isdigit(input.at(i)) && ptr <9) {    //Checks if the char at index i of the string is a digit, and that the numbers[] array isn't already filled.
            numbers[ptr] = input.at(i);         //Stores the digit at index i of the string at index ptr of numbers[].
            ptr++;
        }
    }

    //Loops through the numbers array and prints 3 values from it on 3 different lines, 
    //to illustrate the magic square in the terminal.
    cout << "Is this a magic square?\n";
    for(int i=0; i<9; i++) {
        cout << numbers[i] << " ";
        if((i+1)%3 == 0) {
            cout << "\n";
        }
    }
    cout << "\n";

    return numbers;     //Returns the pointer to the array of chars. 
}

/********************************************************************************************************************************************
 * string serverClient() 
 * Author: Ian Falk
 * Modified on: 13 September 2021
 *  by: Ian Falk
 * Description: Creates "server" and "client" processes. Client process sends user input to server. Server verifies if the input represents 
 *              a magic square and returns T/F to the client. Client prints the result. 
 * 
 * Parameters: 
 *      serverClient   O/P   string          The string returned to the client from the server. 
 ********************************************************************************************************************************************/
string serverClient() {
    pid_t cpid;
    int pipe_client[2];     //The server will write info to this pipe, and the client will read from this pipe.
    int pipe_server[2];     //The client will write info to this pipe, and the server will read from this pipe.
    char clientBuf[5];      //The client only needs a buffer of 5, to store either "true" or "false"
    char serverBuf[9];      //The server only needs a buffer of 9, to store the 9 digit sequence to be verified
    char *numbers;

    numbers = getUserInput();

    //If either pipe fails to open, the error is printed to the terminal and the program is stopped. 
    if((pipe(pipe_client) == -1) || (pipe(pipe_server) == -1) ) {
        perror("pipe");
        exit(EXIT_FAILURE);
    }

    cpid = fork();

    //If the fork fails to run, the error is printed to the terminal and the program is stopped.
    if(cpid == -1) {
        perror("fork");
        exit(EXIT_FAILURE);
    }

    if(cpid==0) {                               //If cpid==0, then the process is the child or "Client".
        close(pipe_server[0]);                  //Closes unused read end of the server pipe
        close(pipe_client[1]);                  //Closes unused write end of the client pipe

        write(pipe_server[1], numbers, 9);      //Client writes the user input to the server
        read(pipe_client[0], clientBuf, 5);     //Client reads what the server wrote back


        close(pipe_server[1]);                  //Closes write end of server pipe
        close(pipe_client[0]);                  //Closes read end of client pipe

        return clientBuf;                       //Returns the value that the Server wrote to the Client
        _exit(EXIT_SUCCESS);
    } else {                                    //Since cpid is not -1, or 0, this process is the parent or "Server".
        close(pipe_server[1]);                  //Closes unused write end of server pipe
        close(pipe_client[0]);                  //Closes unused read end of client pipe

        read(pipe_server[0], serverBuf, 9);     //Server reads what the client wrote to the pipe
        if(isMagicSquare(serverBuf) == 1) {     //Checks if the 9 digit input represents a magic square
            write(pipe_client[1], "True", 5);   //The server writes "True" to the client
        } else {
            write(pipe_client[1], "False", 5);  //The server writes "False" to the client
        }

        close(pipe_server[0]);                  //Closes read end of the server pipe
        close(pipe_client[1]);                  //Closes write end of the client pipe
        wait(NULL);                             //Waits for the child process to finish running
        exit(EXIT_SUCCESS);
    }

    return "Error checking magic square";
}

/********************************************************************************************************************************************
 * int main()
 * Author: Ian Falk
 * Modified on: 13 September 2021
 *  by: Ian Falk
 * Description: Prints system information, and calls the serverClient procedure. 
 * 
 * Parameters: 
 *      main   O/P   int          Status code (not currently used).
 ********************************************************************************************************************************************/
int main() {
    int MAXLEN_HOST = 256;          //Variable used to set the max length that the host name could be.
    int MAXLEN_DOMAIN = 256;        //Variable used to set the max length that the domain name could be.

    char hostname[MAXLEN_HOST];                             //Creates a char array for the hostname to be stored in
    if(gethostname(hostname, MAXLEN_HOST) == 0) {
        cout << "The Hostname is: " << hostname << "\n";    //If the hostname is returned, print it to the terminal
    } else {
        perror("Failed getting Host name");                 //If there was an error returning the hostname, print the error to the console.
    }

    char domainname[MAXLEN_DOMAIN];                         //Creates a char array for the domain name to be stored in
    if(getdomainname(domainname, MAXLEN_DOMAIN) == 0) {
        cout << "The Domain name is: " << domainname << "\n";   //If the domain name is returned, print it to the terminal
    } else {
        perror("Failed getting Domain name");                   //If there was an error returning the domainname, print the error to the console.
    }

    struct utsname info;

    //Using uname, print the Sysname, nodename, release, version, machine, and domainname to the terminal.
    //If there is an error fetching this info, print the error to the terminal. 
    if(uname(&info) == 0) {
        cout << "Sysname: " << info.sysname << "\n";
        cout << "Nodename: " << info.nodename << "\n";
        cout << "Release: " << info.release << "\n";
        cout << "Version: " << info.version << "\n";
        cout << "Machine: " << info.machine << "\n";
        cout << "Domainname: " << info.domainname << "\n";
        cout << "------------------------------------------------------------------\n";
    } else {
        perror("Error fetching info");
    }

    cout << serverClient() << "\n\n";   //Calls the serverClient procedure and prints the result returned by the procedure. (True or False)

    return 0;

}

