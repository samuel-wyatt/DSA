import java.util.*;
public class DSAStackHarness {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        DSAStack stack = new DSAStack();
        int userIn;
        System.out.println("-----DSAStack Test Harness-----\n\t1. Default stack size.\n\t2. Custom stack size.");
        userIn = sc.nextInt();
        switch(userIn) {
            case 1:
            stack = new DSAStack();
            break;

            case 2:
            System.out.print("Please enter the stack size: ");
            int stackSize = sc.nextInt();
            stack = new DSAStack(stackSize);
            break;
        }
        boolean exit = false;
        do {
            System.out.println("--Methods--\n\t1. Push (Add new value)\n\t2. Pop (Remove top value)\n\t3. Top (Look at top value)\n\t4. isEmpty\n\t5. isFull\n\t6. getCount\n\t0. Exit");
            userIn = sc.nextInt();
            switch(userIn) {
                case 1:
                System.out.println("1. Int\n2. Double\n3. Char\n4. String");
                int choice = sc.nextInt();
                if (choice == 1){
                    int value = sc.nextInt();
                    stack.push(value);
                } else if (choice == 2) { 
                    double value = sc.nextDouble();
                    stack.push(value);
                } else if (choice == 3) { 
                    sc.nextLine();
                    char value = sc.nextLine().charAt(0);
                    stack.push(value);
                } else if (choice == 4) {
                    sc.nextLine();
                    String value = sc.nextLine();
                    stack.push(value);
                }
                
                break;
                case 2:
                stack.pop();
                break;
                case 3:
                System.out.println(stack.top());
                break;
                case 4:
                System.out.println(stack.isEmpty());
                break;
                case 5:
                System.out.println(stack.isFull());
                break;
                case 6:
                System.out.println(stack.getCount());
                break;
                case 0:
                exit = true;
                break;
            } 
        } while (!exit);
        sc.close();
    }
}