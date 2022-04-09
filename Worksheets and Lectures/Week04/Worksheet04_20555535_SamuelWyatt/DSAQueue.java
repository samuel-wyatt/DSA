/****************************************************
 * Author: Samuel Wyatt (20555535)                  *
 * Date: 01/04/2021                                 *
 * File Name: DSAStack                              *
 * Purpose: To create a class to imitate the stack. *
 ****************************************************/
//Code used from Worksheet 03 DSAQueue.
import java.util.*;
public class DSAQueue implements Iterable {

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
}