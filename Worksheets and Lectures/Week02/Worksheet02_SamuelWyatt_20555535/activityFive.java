/****************************************************
 * Author: Samuel Wyatt (20555535)                  *
 * Date: 18/03/2022                                 *
 * Title: activityFive                              *
 * Purpose: Implement a towers of Hanoi algorithm.  *
 ****************************************************/
import java.util.*;
public class activityFive 
{
    public static void main(String args[])
    {
        try {
            final int recursion = 0;
            Scanner sc = new Scanner(System.in);
            System.out.print("Please enter the amount of disks: ");
            int n = Integer.parseInt(sc.nextLine().trim());
            towersWrapper(n, 1, 3, recursion);
        } catch (Exception e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
    }
/*
SUBMODULE: towersWrapper
IMPORT: n (integer), src (integer), dest (integer), recursion (integer)
EXPORT: None
PURPOSE: To act as a input validation wrapper.
*/
        public static void towersWrapper(int n, int src, int dest, int recursion)
        {
            if (n < 0) 
            {
                throw new IllegalArgumentException("Import must be positive");
            } else if (src != 1) {
                throw new IllegalArgumentException("src must be 1");
            } else if (dest != 3) {
                throw new IllegalArgumentException("dest must be 3");
            } else if (recursion != 0) {
                throw new IllegalArgumentException("Recursion must begin at 0");
            } else {
                towers(n, src, dest, recursion);
            }
        }

/*
SUBMODULE: towers
IMPORT: n (integer), src (integer), dest (integer), recursion (integer)
EXPORT: None
PURPOSE: To recursively solve the Towers of Hanoi problem.
*/
    public static void towers(int n, int src, int dest, int recursion)
    {
        recursion += 1;
        int tmp;
        if (n == 0)
        {
            return;
        } else if (n == 1) {
            moveDisk(src, dest, n, recursion);
        } else {
        tmp = 6 - src - dest;
        towers(n - 1, src, tmp, recursion);
        moveDisk(src, dest, n, recursion);
        towers(n - 1, tmp, dest, recursion);
        }
    }

/*
SUBMODULE: moveDisk
IMPORT: src (integer), dest (integer), n (integer)
EXPORT: None
PURPOSE: To print the information about disk movement.
*/
    public static void moveDisk(int src, int dest, int n, int recursion)
    {
        for (int i = 1; i < recursion; i++)
        {
            System.out.print("\t");
        }
        System.out.println("Recursion Level = " + recursion);
        for (int i = 1; i < recursion; i++)
        {
            System.out.print("\t");
        }
        System.out.println("Moving Disk " + n + " from Source " + src + " to Destination " + dest);
        for (int i = 1; i < recursion; i++)
        {
            System.out.print("\t");
        }
        System.out.println("n = " + n + ", src = " + src + ", dest = " + dest);
        System.out.println();
    }
}