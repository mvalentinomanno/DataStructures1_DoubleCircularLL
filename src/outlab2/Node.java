/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package outlab2;

/**
 *
 * @author Michael Valentino-Manno
 */
public class Node 
{

    private Node next;   //nodes denoting the next and previous in the list
    private Node prev;
    private int politician = 0;  //politicians arbitrary number

    public Node(int person) 
    {
        politician = person;    //assign a value to a node
    }

    public void setNext(Node n) 
    {
        next = n;              //set next equal to passed node
    }

    public void setPrev(Node n) 
    {
        prev = n;              //set previous equal to passed node
    }

    public Node getNext() 
    {
        return next;           //method that returns the next node
    }

    public Node getPrev() 
    {
        return prev;           //method that returns the previous node
    }

    public int getData() 
    {
        return politician;     //method to return the value assigned to a certain node
    }
}
