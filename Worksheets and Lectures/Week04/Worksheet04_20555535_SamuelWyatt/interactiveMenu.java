import java.io.*;
import java.util.*;
public class interactiveMenu implements Serializable {
    public static void main(String args[]) {
        menuFunc();
    }

    public static void menuFunc() {
        Scanner sc = new Scanner(System.in);
        int userInt = 0;
        boolean exit = false;
        DSALinkedList list = new DSALinkedList();
        serialization serial = new serialization();
        do {
            mainMenu();
            try {
                userInt = Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Invalid input : " + e.getMessage());
            }
    
            switch(userInt) {
                case 1:
                    System.out.println("\n1. InsertFirst\n2. InsertLast");
                    try {
                        userInt = Integer.parseInt(sc.nextLine().trim());
                    } catch (Exception e) {
                        System.out.println("Invalid input : " + e.getMessage());
                    }
                    if (userInt == 1) {
                        System.out.print("\nPlease enter data to add to the start of the LinkedList : ");
                        Object value = sc.next();
                        sc.nextLine();
                        list.insertFirst(value);
                    } else if (userInt == 2) {
                        System.out.print("\nPlease enter data to add to the end of the LinkedList : ");
                        Object value = sc.next();
                        sc.nextLine();
                        list.insertLast(value);      
                    } else {
                        System.out.println("Invalid Choice");
                    }
                break;
                case 2:
                    System.out.println("\n1. RemoveFirst\n2. RemoveLast");
                    try {
                        userInt = Integer.parseInt(sc.nextLine().trim());
                    } catch (Exception e) {
                        System.out.println("Invalid input : " + e.getMessage());
                    }
                    if (userInt == 1) {
                        list.removeFirst();
                        System.out.println("First element removed.");
                    } else if (userInt == 2) {
                        list.removeLast();
                        System.out.println("Last element removed.");
                    } else {
                        System.out.println("Invalid Choice");
                    }
                break;
                case 3:
                    System.out.println(list.toString());
                break;
                case 4:
                    System.out.println("Please enter the name of the file you would like to write to.");
                    String fileName = sc.nextLine();
                    
                    serial.save(list, fileName);
                    exit = true;
                break;
                case 5:
                    System.out.println("Please enter the name of the file you would like to load.");
                    fileName = sc.nextLine();
                    list = serial.load(fileName);
                break;
                case 0:
                    exit = true;
                break;
            }
        } while (!exit);
        sc.close();
    }

    public static void mainMenu() {
        System.out.println("\n(1) InsertFirst / InsertLast");
        System.out.println("(2) RemoveFirst / RemoveLast");
        System.out.println("(3) Display");
        System.out.println("(4) Write serialised file");
        System.out.println("(5) Read serialised file");
        System.out.println("(0) Exit");
    }
}
