/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package outlab2;

import java.io.*;

/**
 *
 * @author Michael Valentino-Manno
 */
public class DoubleLinkedList 
{
    private int cand;           //used to keep track of number of candidates left
    private Node Head = new Node(1);  //creates first node in circle
    private Node Tail = Head;
    private Node on = Head;        
    PrintWriter writer;         //printwriter, used to write output to text file

    public DoubleLinkedList(int n, PrintWriter inWriter) throws FileNotFoundException, UnsupportedEncodingException 
    {
        writer = inWriter;             //makes writer so can write to outpur
        for (int i = 2; i <= n; i++)   //for that constructs circular list
        {
            add(i);                    //call the add method, make a new node, with value i
        }
        cand = n;         //sets candidates equal to size of circle
    }
    
    public void writeClose()          //was running into output issues, so made a method to stop output/writer
    {
        writer.println(" ");
        writer.println("End of program 4");
        writer.close();
    }

    
    public void candidateSelection(int n, int k, int m)   //method that gives political selectors the amount of people (nodes) to go
    {                                            //until stopping and removing the person (node) they stop on
        Node forward;         //node that represents the selector going clockwise    
        Node backward;        //node that represents the selector going counter-clockwise
        forward = Head;       //clockwise selector starts at the first node
        backward = Tail;      //counter-clockwise selector starts at the last node
        writer.println(" ");
        writer.println("N = " + n + ", " + "k = " + k + ", " + "m = " + m);
        writer.println(" ");
        writer.println("Output");
        writer.println("------");
        if (m > n)          //if so that the counter-clockwise selector doesn't waste time circling
        {                  
            m = m % n;
        }
        if (k > n)          //if so that the clockwise selector doesn't waste time circling
        {               
            k = k % n;
        }
        while (cand > 0)    //as long as the candidates in the circle are > 0
        {
            for (int x = n - 1; x > (n - m); x--) //iterates until backward is on the person m away
            {
                backward = backward.getPrev();
            }
            for (int x = 1; x < k; x++)    //iterates until forward is on the the person k away
            {
                forward = forward.getNext();
            }
            if (forward.getData() == backward.getData())   //if the selectors end
            {                                              //on the same person
                writer.println(forward.getData());         //only print once, for
                delete(forward.getData());                 //they are worthy
                backward = backward.getPrev();             //but still remove them
                forward = forward.getNext();               //from the circle and set
            }                                              //the selectors on the next candidates
            else 
            {
                writer.println(forward.getData() + " " + backward.getData());
                delete(backward.getData());           //if the selectors aren't on the
                delete(forward.getData());            //same candidate, remove both
                forward = forward.getNext();          //for they are not worthy,
                backward = backward.getPrev();        //then move the selectors to the
                if (cand == 2)                        //next candidates
                {
                    backward = backward.getPrev();    //resloves an error that I was suffering
                }                                     //with the last 2 lines of output
            }
        }
    }

    private void delete(int data)      //method that deletes the node with the passed value
    {
        on = Head;            
        if (Head.getNext() != null)    
        {
            while (data != on.getData()) //finds the node to delete
            {
                on = on.getNext();
            }
            if (on.getData() == Tail.getData())  //if the last node is to be deleted
            {
                Tail = on.getPrev();           //the tail is the previous node
                on.getPrev().setNext(Head);    //sets new tails next to head
            }
            if (on.getData() == Head.getData()) //if the first node is to be deleted
            {     
                Head = on.getNext();           //the head is the next node
                on.getNext().setPrev(Head);    //sets next nodes previous to head
            }
            on.getPrev().setNext(on.getNext());  //remove everything pointing to the 
            on.getNext().setPrev(on.getPrev());  //node to delete it
        }
        cand = cand - 1;   //when a node is deleted, theres one less candidate
    }

    private void add(int num)   //adds a new node to the end of the circular list
    {
        Node temporaryNode = new Node(num);      //temporary node
        Tail.setNext(temporaryNode);        //temporary is always new tail
        temporaryNode.setNext(Head);        //sets temps next to head and previous to tail
        temporaryNode.setPrev(Tail);        //which adds it as the last position in the list
        Tail = temporaryNode;               //the new tail is equal to the temporary node
        Head.setPrev(Tail);             //makes the list circular
    }

}
