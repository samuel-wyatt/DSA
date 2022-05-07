import java.io.*;
import java.util.*;

public class interactiveMenu implements Serializable {
    public static void main (String args[]) {
        int userIn;
        String fileName;
        Scanner sc = new Scanner(System.in);
        serialization serial = new serialization();
        DSABinarySearchTree tree = new DSABinarySearchTree();
        do {
            menu();
            userIn = Integer.parseInt(sc.nextLine().trim());
            switch (userIn) {
                case 1:
                    tree = readCSV();
                break;
                case 2:
                    System.out.print("Name: ");
                    fileName = sc.nextLine().trim();

                    tree = serial.load(fileName);
                break;
                case 3: 
                    tree = modifyTree(tree);
                break;
                case 4: 
                    displayTree(tree);
                break;
                case 5:
                    writeCSV(tree);
                    userIn = 0;
                break;
                case 6:
                    System.out.print("Name: ");
                    fileName = sc.nextLine().trim();

                    serial.save(tree, fileName);
                    userIn = 0;
                break;
                case 0:
                break;
                default:
                    System.out.println("Invalid Input.");
                break;
            }
        } while (userIn != 0);
        sc.close();
    }

    public static void menu() {
        System.out.println("\n-----Interactive Menu-----");
        System.out.println("1. Read a .csv file");
        System.out.println("2. Read a serialised file");
        System.out.println("3. Modify tree");
        System.out.println("4. Display the tree");
        System.out.println("5. Write to a csv file");
        System.out.println("6. Write a serialised file");
        System.out.println("0. Exit\n");
    }

    public static DSABinarySearchTree readCSV() {
        String fileName;
        String line, delimiter = ",";
        String[] tree = {};
        
        Scanner sc = new Scanner(System.in);
        System.out.print("File Name : ");
        fileName = sc.nextLine().trim();

        try {

            FileInputStream fileStream = null;
            InputStreamReader isr;
            BufferedReader bufRdr;

            fileStream = new FileInputStream(fileName + ".csv");
            isr = new InputStreamReader(fileStream);
            bufRdr = new BufferedReader(isr);
            
            
            while ((line = bufRdr.readLine()) != null) {
                tree = line.split(delimiter);
            }
            fileStream.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        sc.close();
        
        DSABinarySearchTree BST = new DSABinarySearchTree();
        for (int i = 0; i < tree.length; i++) {
            int key = Integer.parseInt(tree[i]);
            BST.insert(key, "");
        }
        return BST;
    }

    public static void writeCSV(DSABinarySearchTree BST) {
        String fileName;
        Scanner sc = new Scanner(System.in);
        System.out.print("File Name: ");
        fileName = sc.nextLine().trim();

        try {
            DSALinkedList ll = new DSALinkedList();
            BufferedWriter br;
            FileWriter fw;
            
            fw = new FileWriter(fileName + ".csv");
            br = new BufferedWriter(fw);
            
            System.out.println("Save BST as: ");
            System.out.println("1. in-order\n2. pre-order\n3.post-order");
            switch (Integer.parseInt(sc.nextLine().trim())) {
                case 1:
                    ll = BST.inorder();
                break;
                case 2:
                    ll = BST.preorder();
                break;
                case 3:
                    ll = BST.postorder();
                break;
            }

            Iterator iter = ll.iterator();
            while (iter.hasNext()) {
                br.write((Integer)iter.next());
                br.write(",");
            }
            br.close();
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        sc.close();
    }

    public static void displayTree(DSABinarySearchTree tree) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. in-order\n2. pre-order\n3. post-order");
        int userIn = Integer.parseInt(sc.nextLine().trim());
        switch (userIn) {
            case 1:
                System.out.println();
                tree.display("inorder");
                System.out.println();
            break;
            case 2:
                System.out.println();
                tree.display("preorder");
                System.out.println();
            break;
            case 3:
                System.out.println();
                tree.display("postorder");
                System.out.println();
            break;
        }
    }

    public static DSABinarySearchTree modifyTree(DSABinarySearchTree tree) {
        Scanner sc = new Scanner(System.in);
        int userIn;
        do {
            System.out.println("1. Insert\n2. Find\n3. Delete\n4. Height, Min, and Max\n5. Balance Percentage\n0. Go Back");
            userIn = Integer.parseInt(sc.nextLine().trim());
            switch (userIn) {
                case 1:
                    System.out.print("\nKey: ");
                    int key = Integer.parseInt(sc.nextLine().trim());
                    System.out.print("Value: ");
                    String value = sc.nextLine().trim();
                    tree.insert(key, value);
                break;
                case 2:
                    System.out.print("\nKey: ");
                    key = Integer.parseInt(sc.nextLine().trim());
                    System.out.println("Value: " + tree.find(key));
                break;
                case 3:
                    System.out.print("\nKey: ");
                    key = Integer.parseInt(sc.nextLine().trim());
                    tree.delete(key);
                break;
                case 4:
                    System.out.println("\nMin: " + tree.min());
                    System.out.println("Max: " + tree.max());
                    System.out.println("Height: " + tree.height() + "\n"); 
                break;
                case 5:
                    //System.out.println(tree.balance());
                case 0:
                break;
                default:
                System.out.println("Invalid");
                break;
            }
        } while (userIn != 0);
        return tree;
    }
}
