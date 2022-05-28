/*************************************************************
 * Author: Samuel Wyatt (20555535)                           *
 * Date: 01/04/2021                                          *
 * File Name: DSALinkedList                                  *
 * Purpose: To create a class to imitate a LinkedList.       *
 * IMPORTANT: This code has been re-used from Practical 4,   *
 *            which was uploaded to Blackboard on 09/04/2022 *
 *************************************************************/
import java.io.*;
import java.util.*;
public class DSALinkedList implements Iterable, Serializable {
   
    public Iterator iterator() {
        return new DSALinkedListIterator(this);
    }

    private class DSAListNode implements Serializable {
        
        private Object value;
        private DSAListNode next;
        private DSAListNode prev;

        public DSAListNode(Object inValue) {
            value = inValue;
            next = null;
            prev = null;
        }
        
        Object getValue() {
            return this.value;
        }

        DSAListNode getNext() {
            return this.next;
        }

        void setNext(DSAListNode newNext) {
            next = newNext;
        }

        DSAListNode getPrev() {
            return this.prev;
        }

        void setPrev(DSAListNode newPrev) {
            prev = newPrev;
        }
    }

    private class DSALinkedListIterator implements Iterator {
        private DSAListNode iterNext;
        public DSALinkedListIterator(DSALinkedList theList) {
            iterNext = theList.head;
        }
        
        public boolean hasNext() {
            return (iterNext != null);
        }

        public Object next() {
            Object value;
             if (iterNext == null) {
                value = null;
            } else {
                value = iterNext.getValue();
                iterNext = iterNext.getNext();
            }
            return value;
        }

        public void remove() {
            Object value = iterNext.getPrev().getValue();
            removeNode(value);
        }
    }

    private int counter;
    private DSAListNode head;
    private DSAListNode tail;

    public DSALinkedList() {
        this.head = null;
        this.tail = null;
        counter = 0;
    }

    void insertFirst(Object newValue) {
        DSAListNode newNd = new DSAListNode(newValue);
        if (isEmpty()) {
            head = newNd;
            tail = newNd;
        } else {
            newNd.setNext(head);
            newNd.setPrev(null);
            head.setPrev(newNd);
            head = newNd;
        }
        incrementCounter();
    }

    void insertLast(Object newValue) {
        DSAListNode newNd = new DSAListNode(newValue);
        if (isEmpty()) {
            head = newNd;
            tail = newNd;
        } else {
            newNd.setPrev(tail);
            newNd.setNext(null);
            tail.setNext(newNd);
            tail = newNd;
        }
        incrementCounter();
    }

    boolean isFull() {
        return false;
    }

    boolean isEmpty() {
        boolean empty = false;
        if (size() == 0) {
            empty = true;
        }
        return empty;
    }

    private void incrementCounter() {
        counter++;
    }

    private void decrementCounter() {
        counter--;
    }

    private int getCounter() {
        return counter;
    }

    public int size() {
        return getCounter();
    }

    Object peekFirst() {
        Object nodeValue;
        if (isEmpty()) {
           return null;
       } else {
        nodeValue = head.getValue();
       }
       return nodeValue;
    }

    Object peekLast() {
        Object nodeValue;
        if (isEmpty()) {
            return null;
        } else {
            nodeValue = tail.getValue();
        }
        return nodeValue;
    }

    Object removeFirst() {
        Object nodeValue = null;
        if (isEmpty()) {
            return null;
        } else if (head.getNext() == null) {
            head = null;
            tail = null;
            decrementCounter();
        } else {
            DSAListNode headNode = head.getNext();
            nodeValue = head.getValue();
            head.setNext(null);
            head = headNode;
            head.setPrev(null);
            decrementCounter();
        }
        return nodeValue;
    }
    
    Object removeLast() {
        Object nodeValue = null;
        if (isEmpty()) {
            return null;
        } else if (tail.getPrev() == null) {
            head = null;
            tail = null;
            decrementCounter();
        } else {
            DSAListNode tailNode = tail.getPrev();
            nodeValue = tail.getValue();
            tail.setPrev(null);
            tail = tailNode;
            tail.setNext(null);
            decrementCounter();
        }
        return nodeValue;
    } 

    Object removeNode(Object inValue) {
        Object nodeValue = null;
        DSAListNode currNd = head;
        boolean exit = false;
        if (isEmpty()) {
            return null;
        } else {
            while (currNd != null && !exit) {
                if (currNd.getValue().equals(inValue)) {
                    if (currNd == head) {
                        removeFirst();
                    } else if (currNd == tail) {
                        removeLast();
                    } else {
                        DSAListNode next = currNd.getNext();
                        DSAListNode prev = currNd.getPrev();
                        prev.setNext(next);
                        next.setPrev(prev);
                        currNd.setNext(null);
                        currNd.setPrev(null);
                    }
                    exit = true;
                } else {
                    currNd = currNd.getNext();
                }
            }
        }
        return nodeValue;
    }

    boolean find(Object inValue) {
        DSAListNode currNd = head;
        boolean found = false;
        boolean exit = false;
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("The LinkedList is empty");
        } else if (head.getValue() == inValue) {
            found = true;
        } else {
            while (currNd.getNext() != null && !exit) {
                if (currNd.getNext().getValue() == inValue) {
                    found = true;
                }
                exit = true;
            }
        }
        return found;
   }

    public String toString() {
        String output = "";

        if (!isEmpty()) {
            DSAListNode currNd = head;
            while (currNd != null) {
                output += "[" + currNd.getValue().toString() + "]";
                currNd = currNd.getNext();
            }
        }
        return output;
    }
}