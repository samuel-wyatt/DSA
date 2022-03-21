/*************************************************************************
 * Author: Samuel Wyatt (20555535)                                       *
 * Date: 18/03/2022                                                      *
 * Title: activityTwo                                                    *
 * Purpose: A recursive algorithm to find the greatest common denominator. *
 *************************************************************************/
import java.util.*;
public class activityTwo
{
    public static void main(String args[])
    {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Please enter integer 1: ");
            int n1 = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Please enter integer 2: ");
            int n2 = Integer.parseInt(sc.nextLine().trim());
            sc.close();
            int gcd = greatestComDenomWrapper(n1, n2);
            System.out.println("The greatest common denominator of " + n1 + " and " + n2 + " is: " + gcd);
        } catch (Exception e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
    }

    /*
    SUBMODULE: greatestComDenomWrapper
    IMPORT: n1 (integer), n2 (integer)
    EXPORT: greatestComDenom(n1, n2)
    PURPOSE: To act as a input validation wrapper.
    */
    public static int greatestComDenomWrapper(int n1, int n2)
    {
        if ((n1 < 0) || (n2 < 0))
        {
            throw new IllegalArgumentException("Import must be positive");
        } else {
            return greatestComDenom(n1, n2);
        }
    }
    /*
    SUBMODULE: greatestComDenom
    IMPORT: n1 (integer), n2 (integer)
    EXPORT: gco (integer)
    PURPOSE: To find the greatest denominator of the imported integers using recursion.
    */
    private static int greatestComDenom(int n1, int n2)
    {
        //Code sourced from https://www.programiz.com/java-programming/examples/gcd-hcf-recursion
        if (n2 != 0)
        {
            return greatestComDenom(n2, n1 % n2);
        } else {
            return n1;
        }
    }
}
