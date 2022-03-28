/****************************************************
 * Author: Samuel Wyatt (20555535)                  *
 * Date: 27/03/2021                                 *
 * File Name: DSAStack                              *
 * Purpose: To create a class to imitate the stack. *
 ****************************************************/

public class DSAStack {

    private final int DEFAULT_CAPACITY = 100;
    private int count;
    private Object[] stack;

    public DSAStack() {
        stack = new Object[DEFAULT_CAPACITY];
        count = 0;
    }

    public DSAStack(int maxCapacity) {
        stack = new Object[maxCapacity];
        count = 0;
    }

    public int getCount() {
        return this.count;
    }

    public boolean isEmpty() {
        boolean empty = false;
        /*for (int i = 0; i < stack.length; i++) {
            empty = false;
            if (stack[i] == null) {
                empty = true;
            }
        }    
        return empty;    */
        
        
        if (count == 0) {
            empty = true;
        }
        return empty;
    }

    public boolean isFull() {
        boolean full = false;
        if (count == stack.length) {
            full = true;
        }
        return full;
    }

    public void display() {
        System.out.println("________Stack Content________");
        for (int i = 0; i < count; i ++) {
            System.out.print("\t" + stack[i]);
        }
        System.out.println("\n_____________________________");
    }

    public void push(Object value) {
        if (isFull()) {
            throw new ArrayIndexOutOfBoundsException("Stack is full.");
        } else {
            stack[count] = value;
            count += 1;
        }
    }

    public Object pop() {
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("Stack is empty.");
        }
        Object topVal = top();
        count -= 1;
        return topVal;
    }

    public Object top() {
        Object topVal;
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("Stack is empty.");
        } else {
            topVal = stack[count - 1];
        }
        return topVal;
    }
}    