import java.util.*;

public class TestIterator {
    public static void main(String args[]) {
        DSALinkedList ll = new DSALinkedList();
        ll.insertFirst("first");
        ll.insertFirst("second");
        ll.insertFirst("third");
        ll.insertFirst("fourth");

        Object value;
        Iterator iter = ll.iterator();
        while (iter.hasNext()) {
            value = iter.next();
            System.out.println(value);
        }

        try {
            iter.remove();
        } catch (Exception e) {
            System.out.println("ERROR : " + e.getMessage());
        }
    } 
}