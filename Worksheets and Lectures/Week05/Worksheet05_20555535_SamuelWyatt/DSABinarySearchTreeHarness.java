import java.util.NoSuchElementException;

public class DSABinarySearchTreeHarness {
    public static void main(String args[]) {
        DSABinarySearchTree tree = new DSABinarySearchTree();
        //Testing insert
        try {
            Object t1 = 50;
            Object t2 = 30;
            Object t3 = 20;
            Object t4 = 40;
            Object t5 = 70;
            Object t6 = 60;
            Object t7 = 80;

            tree.insert("50", t1);
            tree.insert("30", t2);
            tree.insert("20", t3);        
            tree.insert("40", t4);
            tree.insert("70", t5);
            tree.insert("60", t6);
            tree.insert("80", t7);
            System.out.println("Success : Elements successfully inserted");
        } catch (IllegalArgumentException e) {
            System.out.println("Error : " + e.getMessage());
        }
        
        //Testing find and height
        try {
            System.out.println(tree.find("50"));
            System.out.println(tree.height());
            System.out.println("Success : Element was found and height was found.");
        } catch (NoSuchElementException e) {
            System.out.println("Error : " + e.getMessage());
        }

        //Testing inorder
        try {
            DSALinkedList ll = new DSALinkedList();
            ll = tree.inorder();
            System.out.println(ll.toString());
        } catch (NoSuchElementException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }
}
