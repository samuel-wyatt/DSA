import java.util.EmptyStackException;
import java.util.Stack;

/**
 * DSA Final Assessment Question 4 - Q4Stack_Test.java                             4
 *
 * Name : Samuel Wyatt
 * ID   : 20555535
 *
 **/
public class Q4Stack_Test
{
    public static void main(String[] args) 
    {
        //Creating a new stack
        Stack<Integer> s = new Stack<>();

        //Testing push method
        testPush(s);
        //Testing pop method
        testPop(s);
        //Testing peek method
        testPeek(s);
        //Testing empty method
        testEmpty(s);
        //Testing search method
        testSearch(s);
    }

    public static void testPush(Stack<Integer> s) 
    {   
        System.out.print("Testing push(): ");
        //Push method will not allow for invalid data entry, so only test integers
        try
        {
            s.push(10);
            s.push(-10);
            s.push(0);
            System.out.println("passed");
        } catch (Exception e) 
        {
            System.out.println("failed");
        }   
    }

    public static void testPop(Stack<Integer> s)
    {
        System.out.print("Testing pop(): ");
        //Pop off each value, and compare with the expected value
        int val1 = s.pop();
        int val2 = s.pop();
        int val3 = s.pop();

        if (val1 != 0)
        {
            System.out.println("failed");
        } else if (val2 != -10)
        {
            System.out.println("failed");
        } else if (val3 != 10)
        {
            System.out.println("failed");
        }

        try 
        {
            s.pop();
            System.out.println("failed");
        } catch (EmptyStackException e)
        {
            System.out.println("passed");
        }
    }

    public static void testPeek(Stack<Integer> s)
    {
        System.out.print("Testing peek(): ");
        //Add a value to the stack
        s.push(10);
        if (s.peek() != 10)
        {
            System.out.println("failed");
        } else {
            s.push(20);
            if (s.peek() != 20)
            {
                System.out.println("failed");
            } else 
            {
                System.out.println("passed");
            }
        }
    }

    public static void testEmpty(Stack<Integer> s)
    {
        System.out.print("Testing empty(): ");
        s.pop();
        //There is now 1 value on the stack
        if (s.empty())
        {
            System.out.println("failed");
        } else 
        {
            s.pop();
            if (!s.empty())
            {
                System.out.println("failed");
            } else 
            {
                System.out.println("passed");
            }
        }
    }

    public static void testSearch(Stack<Integer> s)
    {
        System.out.print("Testing search(): ");
        s.push(100);
        s.push(200);
        s.push(300);

        int index0 = s.search(100);
        int index1 = s.search(200);
        int index2 = s.search(300);
        int indexInvalid = s.search(400);

        if (index0 != 3)
        {
            System.out.println("failed");
        } else if (index1 != 2)
        {
            System.out.println("failed");
        } else if (index2 != 1)
        {
            System.out.println("failed");
        } else if (indexInvalid != -1)
        {
            System.out.println("failed");
        } else 
        {
            System.out.println("passed");
        }
    }
}