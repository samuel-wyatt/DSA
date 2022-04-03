import java.util.*;
public class callStackMain {
    public static void main(String args[]) {
        DSAStack s = new DSAStack();
        Scanner sc = new Scanner(System.in);
        System.out.println("1. calcNFactorial (I)\n2. calcNFactorial (R)\n3. fibIterative\n4. fibRecursive\n5. greatestComDenom");
        int usrIn = Integer.parseInt(sc.nextLine().trim());
        switch (usrIn) {
            case 1:  calcNFactorial(10, s); break;
            case 2:  calcNFactorialRecursive(10, s); break;
            case 3:  fibIterative(15, s); break;
            case 4:  fibRecursive(15, s); break;
            case 5:  greatestComDenom(160, 76, s); break;
        }     
    }

    private static long calcNFactorial(int n, DSAStack s)
    {
        s.push("calcNFactorial (n = " + n + ")");
        s.display();
        long nFactorial = 1;
        for (int i = n; i >= 2; i--)
        {
            nFactorial *= i;
        }
        s.pop();
        return nFactorial;
    }

    private static long calcNFactorialRecursive(int n, DSAStack s)
    {
        s.push("calcNFactorialRecursive (n = " + n + ")");
        s.display();
        long a;
        if (n == 0)
        {
            a = 1;
        } else {
            a = n * calcNFactorialRecursive(n - 1, s);
        }
        s.pop();
        return a;
    }

    private static int fibIterative(int n, DSAStack s)
    {
        int fibVal = 0;
        int currVal = 1;
        int lastVal = 0;
        s.push("fibIterative (n = " + n + ")");
        s.display();

        if (n == 0)
        {
            fibVal = 0;
        } else if (n == 1) {
            fibVal = 1;
        } else {
            for (int i = 1; i < n; i++)
            {
                fibVal = currVal + lastVal;
                lastVal = currVal;
                currVal = fibVal;
            }
        }
        s.pop();
        return fibVal;
    }
    
    public static int fibRecursive(int n, DSAStack s)
    {
        s.push("fibRecursive (n = " + n + ")");
        s.display();
        int fibVal = 0;
        if (n == 0)
        {
            fibVal = 0;
        } else if (n == 1) {
            fibVal = 1;
        } else {
            fibVal = fibRecursive(n - 1, s) + fibRecursive(n - 2, s);
        }
        s.pop();
        return fibVal;
    }

    private static int greatestComDenom(int n1, int n2, DSAStack s)
    {
        int a;
        //Code sourced from https://www.programiz.com/java-programming/examples/gcd-hcf-recursion
        s.push("greatestComDenom (n1 = " + n1 + ", n2 = " + n2 + ")");
        s.display();
        if (n2 != 0)
        {
            
            a = greatestComDenom(n2, n1 % n2, s);
        } else {
            a = n1;
        }
        s.pop();
        return a;
    }
}