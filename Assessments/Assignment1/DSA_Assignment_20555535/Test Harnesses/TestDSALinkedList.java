public class TestDSALinkedList {
    public static void main(String args[]) {
        DSALinkedList l = new DSALinkedList();
        //Testing isEmpty()
        System.out.print("Testing isEmpty() : ");
        if (l.isEmpty() == true) {
            System.out.println("passed");
        } else {
            System.out.println("failed");
        }
        

        //Testing insertFirst()
        try {
            System.out.print("Testing insertFirst() : ");
            l.insertFirst("abc");
            l.insertFirst("def");
            l.insertFirst("ghi");
            l.insertFirst("jkl");
            System.out.println("passed");
        } catch (Exception e) {
            System.out.println("failed");
        }

        //Testing peekFirst()
        try {
            System.out.print("Testing peekFirst() : ");
            if (l.peekFirst() != "jkl") {
                System.out.println("failed");
            } else {
                System.out.println("passed");
            }
        } catch (Exception e) {}

        //Testing insertLast()
        try {
            System.out.print("Testing insertLast() : ");
            l.insertLast("123");
            System.out.println("passed");
        } catch (Exception e) {
            System.out.println("failed");
        }

        //Testing peekLast()
        try {
            System.out.print("Testing peekLast() : ");
            if (l.peekLast() != "123") {
                System.out.println("failed");
            } else {
                System.out.println("passed");
            }
        } catch (Exception e) {}

        //Testing removeFirst()
        try {
            System.out.print("Testing removeFirst() : ");
            l.removeFirst();
            if (l.peekFirst() != "ghi") {
                System.out.println("failed");
            } else {
                System.out.println("passed");
            }
        } catch (Exception e) {}

        //Testing removeLast() + printing current LL.
        System.out.println("CURRENT LINKED LIST: " + l.toString());
        try {
            System.out.print("Testing removeLast() : ");
            l.removeLast();
            if (l.peekLast() != "abc") {
                System.out.println("failed");
            } else {
                System.out.println("passed");
            }
        } catch (Exception e) {}

        //Testing removeFirst
        DSALinkedList ll = new DSALinkedList();
        System.out.println("_____________________________________________________");
        System.out.println("Testing removeFirst()");
        System.out.println("_____________________________________________________");
        ll.insertFirst(5);
        ll.insertFirst(10);
        ll.insertFirst(15);
        ll.insertFirst(20);
        ll.insertFirst(25);
        ll.insertFirst(30);
        ll.insertFirst(35);
        System.out.println(ll.toString() + " + Count = " + ll.size());
        ll.removeFirst();
        System.out.println(ll.toString() + " + Count = " + ll.size());
        ll.removeFirst();
        System.out.println(ll.toString() + " + Count = " + ll.size());
        ll.removeFirst();
        System.out.println(ll.toString() + " + Count = " + ll.size());
        ll.removeFirst();
        System.out.println(ll.toString() + " + Count = " + ll.size());
        ll.removeFirst();
        System.out.println(ll.toString()  + " + Count = " + ll.size());
        ll.removeFirst();
        System.out.println(ll.toString() + " + Count = " + ll.size());
        ll.removeFirst();
        System.out.println(ll.toString() + " + Count = " + ll.size());

        //Testing removeLast()
        System.out.println("_____________________________________________________");
        System.out.println("Testing removeLast()");
        System.out.println("_____________________________________________________");
        ll.insertFirst(5);
        ll.insertFirst(10);
        ll.insertFirst(15);
        ll.insertFirst(20);
        ll.insertFirst(25);
        ll.insertFirst(30);
        ll.insertFirst(35);
        System.out.println(ll.toString() + " + Count = " + ll.size());
        ll.removeLast();
        System.out.println(ll.toString() + " + Count = " + ll.size());
        ll.removeLast();
        System.out.println(ll.toString() + " + Count = " + ll.size());
        ll.removeLast();
        System.out.println(ll.toString() + " + Count = " + ll.size());
        ll.removeLast();
        System.out.println(ll.toString() + " + Count = " + ll.size());
        ll.removeLast();
        System.out.println(ll.toString() + " + Count = " + ll.size());
        ll.removeLast();
        System.out.println(ll.toString() + " + Count = " + ll.size());
        ll.removeLast();
        System.out.println(ll.toString() + " + Count = " + ll.size());

        //Testing find()
        ll.insertFirst(15);
        System.out.println("_____________________________________________________");
        System.out.println("Testing find()");
        System.out.println("_____________________________________________________");
        System.out.println("100 =  "  + ll.find(100));
        System.out.println("15 = " + ll.find(15));
    }
}