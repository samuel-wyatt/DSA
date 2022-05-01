import java.util.NoSuchElementException;

public class DSABinarySearchTreeHarness {
    public static void main(String args[]) {
        DSABinarySearchTree tree = new DSABinarySearchTree();
        DSABinarySearchTree empty = new DSABinarySearchTree();
        
        //insert() with valid values.
        try {
            System.out.print("Testing insert() with valid values : ");
            tree.insert(50, 'n');
            tree.insert(20, 'e');
            tree.insert(70, 'v');
            tree.insert(10, 'e');
            tree.insert(5, 'r');
            tree.insert(15, 'g');
            tree.insert(30, 'o');
            tree.insert(25, 'n');
            tree.insert(40, 'n');
            tree.insert(60, 'a');
            tree.insert(55, 'g');
            tree.insert(65, 'i');
            tree.insert(80, 'v');
            tree.insert(75, 'e');
            tree.insert(85, 'u');
            System.out.println("passed");
        } catch (Exception e) {
            System.out.println("failed");
        }

        //insert with pre-existing key.
        try {
            System.out.print("Testing insert() with pre-existing key : ");
            tree.insert(85, 'a');
            System.out.println("failed");
        } catch (IllegalArgumentException e) {
            System.out.println("passed");
        }
        

        //find() on an empty tree.
        try {
            System.out.print("\nTesting find() on an empty tree : ");
            empty.find(0);
            System.out.println("failed");
        } catch (NoSuchElementException e) {
            System.out.println("passed");
        }

        //find() with non-existent key.
        try {
            System.out.print("Testing find() with a non-existent key : ");
            tree.find(100);
            System.out.println("failed");
        } catch (NoSuchElementException e) {
            System.out.println("passed");
        }

        //find() with valid key.
        try {
            System.out.print("Testing find() with a valid key : ");
            Object n = tree.find(65);
            char c = n.toString().charAt(0);
            if (c == 'i') {
                System.out.println("passed");
            } else {
                System.out.println("failed with incorrect value");
            }
        } catch (NoSuchElementException e) {
            System.out.println("failed with exception (" + e.getMessage() + ")");
        }


        //height() on an empty tree.
        System.out.print("\nTesting height() on an empty tree : ");
        if (empty.height() == -1) {
            System.out.println("passed");
        } else {
            System.out.println("failed");
        }

        //height() on a full tree.
        System.out.print("Testing height() on a full tree : ");
        if (tree.height() == 3) {
            System.out.println("passed");
        } else {
            System.out.println("failed: " + tree.height());
        }

        //max() on a full tree.
        try {
            System.out.print("\nTesting max() on a full tree : ");
            int n = tree.max();
            if (n == 85) {
                System.out.println("passed"); 
            }
        } catch (NoSuchElementException e) {
            System.out.println("failed");
        }

        //max() on an empty tree.
        try {
            System.out.print("Testing max() on an empty tree : ");
            Object n = empty.max();
            System.out.println("failed");
        } catch (NoSuchElementException e) {
            System.out.println("passed");
        }

        //min() on an empty tree.
        try {
            System.out.print("\nTesting min() on an empty tree : ");
            Object n = empty.min();
            System.out.println("failed");
        } catch (NoSuchElementException e) {
            System.out.println("passed");
        }

        //min() on a full tree.
        try {
            System.out.print("Testing min() on a full tree : ");
            int n = tree.min();
            if (n == 5) {
                System.out.println("passed");
            }
        } catch (NoSuchElementException e) {
            System.out.println("failed");
        }

        //balance()
        /*try {
            System.out.print("Balance : ");
            System.out.println(tree.balance());
        } catch (Exception e) {
            System.out.println("failed");
        }
        empty.insert(2, 2);
        empty.insert(3, 3);
        empty.insert(4, 4);
        empty.insert(1, 1);
        try {
            System.out.print("Balance : ");
            System.out.println(empty.balance());
        } catch (Exception e) {
            System.out.println("failed");
        }*/

        //inorder()
        try {
            System.out.print("\nTesting inorder(): ");
            DSALinkedList ll = tree.inorder();
            if (ll.toString().equals("[5][10][15][20][25][30][40][50][55][60][65][70][75][80][85]")) {
                System.out.println("passed");
            } else {
                System.out.println("failed");
            }
        } catch (Exception e) {
            System.out.println("failed with exception (" + e.getMessage() + ")");
        }

        //preorder()
        try {
            System.out.print("Testing preorder(): ");
            DSALinkedList ll = tree.preorder();
            if (ll.toString().equals("[50][20][10][5][15][30][25][40][70][60][55][65][80][75][85]")) {
                System.out.println("passed");
            } else {
                System.out.println("failed");
            }
        } catch (Exception e) {
            System.out.println("failed with exception (" + e.getMessage() + ")");
        }

        //postorder()
        try {
            System.out.print("Testing postorder(): ");
            DSALinkedList ll = tree.postorder();
            if (ll.toString().equals("[5][15][10][25][40][30][20][55][65][60][75][85][80][70][50]")) {
                System.out.println("passed");
            } else {
                System.out.println("failed");
            }
        } catch (Exception e) {
            System.out.println("failed with exception (" + e.getMessage() + ")");
        }
    }
}
