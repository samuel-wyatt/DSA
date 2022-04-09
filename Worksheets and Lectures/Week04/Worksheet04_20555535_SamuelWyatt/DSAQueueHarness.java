//Code used from Worksheet 03 DSAQueueHarness
import java.util.*;
public class DSAQueueHarness {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        DSAQueue queue = new DSAQueue();
        int userIn;
        System.out.println("-----DSAQueue Test Harness-----");
        boolean exit = false;
        do {
            System.out.println("--Methods--\n\t1. Enqueue (Add value)\n\t2. Dequeue (Remove value)\n\t3. Peek (look at front value)\n\t4. isEmpty\n\t5. Display\n\t0. Exit");
            userIn = sc.nextInt();
            switch(userIn) {
                case 1:
                System.out.println("1. Int\n2. Double\n3. Char\n4. String");
                int choice = sc.nextInt();
                if (choice == 1){
                    int value = sc.nextInt();
                    queue.enqueue(value);
                } else if (choice == 2) { 
                    double value = sc.nextDouble();
                    queue.enqueue(value);
                } else if (choice == 3) { 
                    sc.nextLine();
                    char value = sc.nextLine().charAt(0);
                    queue.enqueue(value);
                } else if (choice == 4) {
                    sc.nextLine();
                    String value = sc.nextLine();
                    queue.enqueue(value);
                }
                
                break;
                case 2:
                queue.dequeue();
                break;
                case 3:
                System.out.println(queue.peek());
                break;
                case 4:
                System.out.println(queue.isEmpty());
                break;
                case 5:
                queue.display();
                break;
                case 0:
                exit = true;
                break;
            } 
        } while (!exit);
        sc.close();
    }
}