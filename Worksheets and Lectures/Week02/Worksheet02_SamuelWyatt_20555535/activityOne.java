/****************************************************************************
 * Author: Samuel Wyatt (20555535)                                          *
 * Date: 18/03/2022                                                         *
 * Title: actvityOne                                                        *
 * Purpose: Recursive and Iterative algorithms for factorial and fibonacci. *
 ****************************************************************************/
import java.util.*;
public class activityOne
{
    public static void main(String args[])
    {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Please enter a number (Factorial): ");
            int n = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Please enter a number (Fibonacci): ");
            int nn = Integer.parseInt(sc.nextLine().trim());

            /*Largest possible value of n is 20, because after that the number is outside the long
            data type's range, and will cause overflow. */
            long factorialIterative = calcNFactorialWrapper(n);
            System.out.println("Factorial Iterative: " + factorialIterative);
            long factorialRecursive = calcNFactorialRecursiveWrapper(n);
            System.out.println("Factorial Recursive: " + factorialRecursive);
            
            /*Largest possible value of nn is 46, because after that, the fibonacci sequence will
            reach the overflow value for int, and will not output correct values. */
            int fibonacciIterative = fibIterativeWrapper(nn);
            System.out.println("Fibonacci Iterative: " + fibonacciIterative);
            int fibonacciRecursive = fibRecursiveWrapper(nn);
            System.out.println("Fibonacci Recursive: " + fibonacciRecursive);
            sc.close();
        } catch (Exception e) {
            System.out.println("Invalid input: " + e.getMessage());
        }

    }

/*
SUBMODULE: calcNFactorialWrapper
IMPORT: n (integer)
EXPORT: calcNFactorial(n)
PURPOSE: To act as a input validation wrapper.
*/
    public static long calcNFactorialWrapper(int n)
    {
        if (n < 0)
        {
            throw new IllegalArgumentException("Import must be positive");
        } else {
            return calcNFactorial(n);
        }
    }

/*
SUBMODULE: calcNFactorial
IMPORT: n (integer)
EXPORT: nFactorial (long)
PURPOSE: To calculate the factorial of n, using a iterative algorithm.
*/
    private static long calcNFactorial(int n)
    {
        long nFactorial = 1;
        for (int i = n; i >= 2; i--)
        {
            nFactorial *= i;
        }
        return nFactorial;
    }

/*
SUBMODULE: calcNFactorialRecursiveWrapper
IMPORT: n (integer)
EXPORT: calcNFactorialRecursive(n)
PURPOSE: To act as a input validation wrapper.
*/
    public static long calcNFactorialRecursiveWrapper(int n)
    {
        if (n < 0)
        {
            throw new IllegalArgumentException("Import must be positive");
        } else if (n == 0) {
            return 1;
        } else {
            return calcNFactorialRecursive(n);
        }
    }

/*
SUBMODULE: calcNFactorialRecursive
IMPORT: n (integer)
EXPORT: n (long)
PURPOSE: To calculate the factorial of n, using a recursive algorithm.
*/
    private static long calcNFactorialRecursive(int n)
    {
        if (n == 0)
        {
            return 1;
        } else {
            return n * calcNFactorialRecursive(n - 1);
        }
    }

/*
SUBMODULE: fibIterativeWrapper
IMPORT: nn (integer)
EXPORT: fibIterative(nn)
PURPOSE: To act as a input validation wrapper
*/
    public static int fibIterativeWrapper(int nn)
    {
        if (nn < 0)
        {
            throw new IllegalArgumentException("Import must be positive");
        } else {
            return fibIterative(nn);
        } 
    }
/*
SUBMODULE: fibIterative
IMPORT: nn (integer)
EXPORT: fibVal (integer)
PURPOSE: To calculate the fibonacci value at the point nn in the sequence, using a recursive algorithm.
*/
    private static int fibIterative(int nn)
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
SUBMODULE: fibRecursiveWrapper
IMPORT: nn (integer)
EXPORT: fibRecursive(nn)
PURPOSE: To act as a input validation wrapper
*/
public static int fibRecursiveWrapper(int nn)
{
    if (nn < 0)
    {
        throw new IllegalArgumentException("Import must be positive");
    } else {
        return fibRecursive(nn);
    }
}

/*
SUBMODULE: fibRecursive
IMPORT: nn (integer)
EXPORT: fibVal (integer)
PURPOSE: To calculate the fibonacci value at the point nn in the sequence, using a recursive algorithm.
*/
    public static int fibRecursive(int nn)
    {
        int fibVal = 0;
        if (nn == 0)
        {
            fibVal = 0;
        } else if (nn == 1) {
            fibVal = 1;
        } else {
            fibVal = fibRecursive(nn - 1) + fibRecursive(nn - 2);
        }
        return fibVal;
    }
}