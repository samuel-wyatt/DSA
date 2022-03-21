/*************************************************************************
 * Author: Samuel Wyatt (20555535)                                       *
 * Date: 18/03/2022                                                      *
 * Title: activityThree                                                  *
 * Purpose: A recursive algorithm to convert from base 10 to 2-16        *
 *************************************************************************/
import java.util.*;
public class activityThree 
{
    public static void main(String[] args) 
    {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Please enter the base: ");
            int base = Integer.parseInt(sc.nextLine().trim());
            System.out.print("Please enter the number (base 10): ");
            int number = Integer.parseInt(sc.nextLine().trim());
            String newNumber = convertWrapper(number, base);
            System.out.println(number + " in base " + base + " is " + newNumber);
            sc.close();
        } catch (Exception e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
    }

/*
SUBMODULE: convertWrapper
IMPORT: number (integer), base (integer)
EXPORT: convert(number, base)
PURPOSE: To act as a input validation wrapper.
*/
    public static String convertWrapper(int number, int base)
    {
        if ((base > 16) || (base < 2))
        {
            throw new IllegalArgumentException("Base must be between 2 and 16");
        } else if (number < 0) {
            throw new IllegalArgumentException("Number must be greater than or equal to 0");
        } else {
            return convert(number, base);
        }
    }
/*
SUBMODULE: convert
IMPORT: number (integer), base (integer)
EXPORT: (String)
ASSERTION: To convert a number from base 10 to 2-16.
*/
    private static String convert(int number, int base)
    {
        int quotient = number / base;
        int remainder = number % base;
        StringBuilder builder = new StringBuilder();

        if (quotient == 0)
        {
            return Integer.toString(remainder);      
        } else if (base == 16) {
            //Code sourced from https://makeinjava.com/convert-decimal-number-hexadecimal-java-iterative-recursive/
            String hexNumber = convert(quotient, base);
            String hexCode = "0123456789ABCDEF";
            int hexDigit = number % 16;
            builder.append(hexNumber + hexCode.charAt(hexDigit));
            return builder.toString();

        } else if (base ==15) {
            String hexNumber = convert(quotient, base);
            String hexCode = "0123456789ABCDE";
            int hexDigit = number % 15;
            builder.append(hexNumber + hexCode.charAt(hexDigit));
            return builder.toString();   
        } else if (base == 14) {
            String hexNumber = convert(quotient, base);
            String hexCode = "0123456789ABCD";
            int hexDigit = number % 14;
            builder.append(hexNumber + hexCode.charAt(hexDigit));
            return builder.toString();
        } else if (base == 13) {
            String hexNumber = convert(quotient, base);
            String hexCode = "0123456789ABC";
            int hexDigit = number % 13;
            builder.append(hexNumber + hexCode.charAt(hexDigit));
            return builder.toString();
        } else if (base == 12) {
            String hexNumber = convert(quotient, base);
            String hexCode = "0123456789AB";
            int hexDigit = number % 12;
            builder.append(hexNumber + hexCode.charAt(hexDigit));
            return builder.toString();
        } else if (base == 11) {
            String hexNumber = convert(quotient, base);
            String hexCode = "0123456789A";
            int hexDigit = number % 11;
            builder.append(hexNumber + hexCode.charAt(hexDigit));
            return builder.toString();
        } else {
            return convert(quotient, base) + Integer.toString(remainder);
        }            
    }
}   