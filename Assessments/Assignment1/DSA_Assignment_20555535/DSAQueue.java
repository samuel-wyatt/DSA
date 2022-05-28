/*************************************************************
 * Author: Samuel Wyatt (20555535)                           *
 * Date: 01/04/2021                                          *
 * File Name: DSAStack                                       *
 * Purpose: To create a class to imitate the stack.          *
 * IMPORTANT: This code has been re-used from Practical 4,   *
 *            which was uploaded to Blackboard on 09/04/2022 *
 *************************************************************/
import java.io.Serializable;
import java.util.*;
public class DSAQueue implements Iterable, Serializable {

    private DSALinkedList queue = new DSALinkedList();

    public void enqueue(Object value) {
            queue.insertLast(value);
    }

    public Object dequeue() {
        Object value = queue.peekFirst();
        if (value == null) {
            throw new IndexOutOfBoundsException("Queue is empty.");
        } else {
            queue.removeFirst();
        }   
        return value;
    }

    public boolean isEmpty() {
        return (queue.size() == 0);
    }

    public int size() {
        return queue.size();
    }

    public Object peek() {
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("Queue is empty.");
        } else {
            return queue.peekFirst();
        }
    }

    public void display() {
        System.out.println(queue.toString());
    }

    public Iterator iterator() {
        return queue.iterator();
    }

    public String toString() {
        String toString = "";
        Iterator iter = queue.iterator();

        while (iter.hasNext()) {
            toString += iter.next() + " ";
        }
        return toString.trim();
    }
}