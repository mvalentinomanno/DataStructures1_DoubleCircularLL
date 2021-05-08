/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package outlab2;

import java.io.*;  
import java.util.*;

/**
 *
 * @author Michael Valentino-Manno
 */
public class Politics 
{
    String file;
    Scanner s = new Scanner(System.in);    //new scanner

    public void runProgram() throws FileNotFoundException, UnsupportedEncodingException 
    {
        PrintWriter writeOutput = new PrintWriter("LinkedListProgram.txt", "UTF-8");  //creates new PrintWriter, used to write output to text file
        System.out.println("Enter a file name");
        file = s.nextLine();               //scanner reads in a string and stores it
        try //try - catch for filenotfound
        {
            FileReader name = new FileReader(file);  //new FileReader to read in the file name
            Scanner fileRead = new Scanner(name);    //used to read in text file for input

            int n = 1;     //ints used for text file input
            int k = 1;
            int m = 1;
            writeOutput.println("Program 4");
            writeOutput.println("---------");
            while (n != 0 && k != 0 && m != 0)  //n, k, m being equal to 0 denotes the end
            {
                n = fileRead.nextInt();     //reads in each number from the text file
                k = fileRead.nextInt();
                m = fileRead.nextInt();

                DoubleLinkedList circle = new DoubleLinkedList(n, writeOutput);  //create new instance of the DoubleLinkedList class
                                                                                 //passing it the number in the circle, and printWriter, used for output
                if (n == 0 && k == 0 && m == 0)      //if n, k, m are equal to 0, close the writer, denotes end of program
                {
                     circle.writeClose();
                }
                else                                 //if they are not equal to 0, go through with the selection
                {
                     circle.candidateSelection(n, k, m);
                }
            }
            fileRead.close();            //along with closing the file writer, close the reader as well, because its the end of execution
        } 
        catch (FileNotFoundException e)    //if given file doesn't exist, print error message
        {
            System.out.println("Given file was not found");
        }
        System.out.println("Finished, check output file!");   //print when program is done executing, so the user knows its done
    }
}
