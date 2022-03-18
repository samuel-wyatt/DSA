/****************************************
 * Author: Samuel Wyatt (20555535)      *
 * Date: 18/03/2022                     *
 * Title: actvityOne                    *
 * Purpose: DSA Worksheet 2 activityOne *
 ****************************************/
import java.util.*;
public class activityOne
{
    public static void main(String args[])
    {
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter a number (Factorial): ");
        int n = sc.nextInt();
        System.out.print("Please enter a number (Fibonacci): ");
        int nn = sc.nextInt();
        
        /*Largest possible value of n is 20, because after that the number is outside the long
        data type's range, and will cause overflow. */
        long factorialIterative = calcNFactorial(n);
        System.out.println("Factorial Iterative: " + factorialIterative);
        long factorialRecursive = calcNFactorialRecursive(n);
        System.out.println("Factorial Recursive: " + factorialRecursive);
        
        /*Largest possible value of nn is 46, because after that, the fibonacci sequence will
        reach the overflow value for int, and will not output correct values. */
        int fibonacciIterative = fibIterative(nn);
        System.out.println("Fibonacci Iterative: " + fibonacciIterative);
        int fibonacciRecursive = fibRecursive(nn);
        System.out.println("Fibonacci Recursive: " + fibonacciRecursive);
        sc.close();
    }
/*
SUBMODULE: calcNFactorial
IMPORT: n (integer)
EXPORT: nFactorial (long)
PURPOSE: To calculate the factorial of n, using a iterative algorithmn.
*/
    public static long calcNFactorial(int n)
    {
        long nFactorial = 1;
        for (int i = n; i >= 2; i--)
        {
            nFactorial *= i;
        }
        return nFactorial;
    }
/*
SUBMODULE: calcNFactorialRecursive
IMPORT: n (integer)
EXPORT: n (long)
PURPOSE: To calculate the factorial of n, using a recursive algorithmn.
*/
    public static long calcNFactorialRecursive(int n)
    {
        if (n == 0)
        {
            return 1;
        } else {
            return n * calcNFactorialRecursive(n - 1);
        }
    }
/*
SUBMODULE: fibIterative
IMPORT: nn (integer)
EXPORT: fibVal (integer)
PURPOSE:
*/
    public static int fibIterative(int nn)
    {
        int fibVal = 0;
        int currVal = 1;
        int lastVal = 0;

        if (nn == 0)
        {
            fibVal = 0;
        } else if (nn == 1) {
            fibVal = 1;
        } else {
            for (int i = 1; i < nn; i++)
            {
                fibVal = currVal + lastVal;
                lastVal = currVal;
                currVal = fibVal;
            }
        }
        return fibVal;
    }
/*
SUBMODULE: fibRecursive
IMPORT: nn (integer)
EXPORT: fibVal (integer)
PURPOSE: 
*/
    public static int fibRecursive(int n)
    {
        int fibVal = 0;
        if (n == 0)
        {
            fibVal = 0;
        } else if (n == 1) {
            fibVal = 1;
        } else {
            fibVal = fibRecursive(n - 1) + fibRecursive(n - 2);
        }
        return fibVal;
    }
}