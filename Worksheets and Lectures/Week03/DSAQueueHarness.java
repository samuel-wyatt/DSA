import java.util.*;
public class DSAQueueHarness {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Shuffling Queue\n2. Circular Queue");
        int userIn = sc.nextInt();
        if (userIn == 1) {
            shufflingQueue();
        } else if (userIn == 2) {
            circularQueue();
        }
        sc.close();
    }

    public static void shufflingQueue() {
        Scanner sc = new Scanner(System.in);
        ShufflingQueue queue = new ShufflingQueue();
        int userIn;
        System.out.println("-----DSAQueue Test Harness-----\n\t1. Default queue size.\n\t2. Custom queue size.");
        userIn = sc.nextInt();
        switch(userIn) {
            case 1:
        queue = new ShufflingQueue();
            break;

            case 2:
            System.out.print("Please enter the queue size: ");
            int queueSize = sc.nextInt();
            queue = new ShufflingQueue(queueSize);
            break;
        }
        boolean exit = false;
        do {
            System.out.println("--Methods--\n\t1. Enqueue (Add value)\n\t2. Dequeue (Remove value)\n\t3. Peek (look at front value)\n\t4. isEmpty\n\t5. isFull\n\t6. getCount\n\t7. Display\n\t0. Exit");
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
                System.out.println(queue.isFull());
                break;
                case 6:
                System.out.println(queue.getCount());
                break;
                case 7:
                queue.display();
                case 0:
                exit = true;
                break;
            } 
        } while (!exit);
        sc.close();
    }

    public static void circularQueue() {
        Scanner sc = new Scanner(System.in);
        CircularQueue queue = new CircularQueue();
        int userIn;
        System.out.println("-----DSAQueue Test Harness-----\n\t1. Default queue size.\n\t2. Custom queue size.");
        userIn = sc.nextInt();
        switch(userIn) {
            case 1:
        queue = new CircularQueue();
            break;

            case 2:
            System.out.print("Please enter the queue size: ");
            int queueSize = sc.nextInt();
            queue = new CircularQueue(queueSize);
            break;
        }
        boolean exit = false;
        do {
            System.out.println("--Methods--\n\t1. Enqueue (Add value)\n\t2. Dequeue (Remove value)\n\t3. Peek (look at front value)\n\t4. isEmpty\n\t5. isFull\n\t6. getCount\n\t7. Display\n\t0. Exit");
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
                System.out.println(queue.isFull());
                break;
                case 6:
                System.out.println(queue.getCount());
                break;
                case 7:
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
