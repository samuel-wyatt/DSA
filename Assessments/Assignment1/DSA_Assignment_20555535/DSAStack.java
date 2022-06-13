/*************************************************************
 * Author: Samuel Wyatt (20555535)                           *   
 * Date: 01/04/2021                                          *
 * File Name: DSAStack                                       *
 * Purpose: To create a class to imitate the stack.          *
 * IMPORTANT: This code has been re-used from Practical 4,   *
 *            which was uploaded to Blackboard on 09/04/2022 * 
 *************************************************************/
import java.util.*;
public class DSAStack implements Iterable {

    private DSALinkedList stack = new DSALinkedList();

    public boolean isEmpty() {
        return (stack.size() == 0);
    }

    public int size() {
        return stack.size();
    }

    public void display() {
        System.out.println(stack.toString());
    }

    public void push(Object value) {
        stack.insertFirst(value);
    }

    public Object pop() {
        Object topVal;
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("Stack is empty.");
        } else {
            topVal = top();
            stack.removeFirst();
        }
        return topVal;
    }

    public Object top() {
        Object topVal;
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("Stack is empty.");
        } else { 
            topVal = stack.peekFirst();
        }
        return topVal;
    }   

    public Iterator iterator() {
        return stack.iterator();
    }
}    