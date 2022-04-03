public class DSALinkedList {
   
    private class DSAListNode {
        
        private Object value;
        private DSAListNode next;
        private DSAListNode prev;

        public DSAListNode(Object inValue) {
            value = inValue;
            next = null;
            prev = null;
        }
        
        Object getValue() {
            return this.value;
        }

        void setValue(Object newValue) {
            value = newValue;
        }

        DSAListNode getNext() {
            return this.next;
        }

        void setNext(DSAListNode newNext) {
            next = newNext;
        }

        DSAListNode getPrev() {
            return this.prev;
        }

        void setPrev(DSAListNode newPrev) {
            prev = newPrev;
        }
    }

    private static int counter;
    private DSAListNode head;
    private DSAListNode tail;

    public DSALinkedList() {
        head = null;
    }

    void insertFirst(Object newValue) {

        DSAListNode newNd = new DSAListNode(newValue);
        DSAListNode currNd = head;
        if (isEmpty()) {
            head = newNd;
            tail = newNd;
        } else {
            currNd.setPrev(newNd);
            newNd.setNext(currNd);
            head.setNext(newNd);
        }
        incrementCounter();
    }

    void insertLast(Object newValue) {

        DSAListNode newNd = new DSAListNode(newValue);
        DSAListNode currNd = tail;

        if (isEmpty()) {
            head = newNd;
            tail = newNd;
        } else {
            currNd.setNext(newNd);
            newNd.setPrev(currNd);
            tail.setNext(newNd);
        }
        incrementCounter();
    }

    boolean isFull() {
        return false;
    }

    boolean isEmpty() {
        boolean empty = false;
        if (size() == 0) {
            empty = true;
        }
        return empty;
    }

    private static void incrementCounter() {
        counter++;
    }

    private void decrementCounter() {
        counter--;
    }

    private static int getCounter() {
        return counter;
    }

    public int size() {
        return getCounter();
    }

    Object peekFirst() {
        Object nodeValue;
        if (isEmpty()) {
           return null;
       } else {
        nodeValue = head.getValue();
       }
       return nodeValue;
    }

    Object peekLast() {
        Object nodeValue;
        if (isEmpty()) {
            return null;
        } else {
            nodeValue = tail.getValue();
        }
        return nodeValue;
    }

    Object removeFirst() {
        Object nodeValue;
        if (isEmpty()) {
            return null;
        } else {
            nodeValue = head.getValue();
            head.setNext(head.getNext());
            head.setPrev(null);
        }
        decrementCounter();
        return nodeValue;
    }

    Object removeLast() {
        Object nodeValue;
        if (isEmpty()) {
            return null;
        } else {
            nodeValue = tail.getValue();
            tail.setPrev(tail.getPrev());
            tail.setPrev(null);
        }

        decrementCounter();
        return nodeValue;
    } 

    public String toString() {
        String output = "";

        if (isEmpty()) {
            DSAListNode currNd = head.getNext();
            while (currNd != null) {
                output += "[" + currNd.getValue().toString() + "]";
                currNd = currNd.getNext();
            }
        }
        return output;
    }
}