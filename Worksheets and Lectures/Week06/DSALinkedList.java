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
        this.head = null;
        this.tail = null;
        this.counter = 0;
    }

    void insertFirst(Object newValue) {
        DSAListNode newNd = new DSAListNode(newValue);
        if (isEmpty()) {
            head = newNd;
            tail = newNd;
        } else {
            newNd.setNext(head);
            newNd.setPrev(null);
            head.setPrev(newNd);
            head = newNd;
        }
        incrementCounter();
    }

    void insertBefore(Object newValue, Object beforeValue) {
        DSAListNode newNd = new DSAListNode(newValue);
        DSAListNode currNd = head;
        boolean exit = false;
        if (isEmpty()) {
            head = null;
            tail = null;
        } else if (head.getValue() == beforeValue) {
            insertFirst(newValue);
        } else {
            while (currNd.getNext() != null && !exit) {
                if (currNd.getNext().getValue() == beforeValue) {
                    newNd.setNext(currNd.getNext());
                    currNd.setNext(newNd);
                    newNd.setPrev(currNd);
                    currNd.getNext().setPrev(currNd);
                    exit = true;
                }
            }
        }
    }

    void insertLast(Object newValue) {
        DSAListNode newNd = new DSAListNode(newValue);
        if (isEmpty()) {
            head = newNd;
            tail = newNd;
        } else {
            newNd.setPrev(tail);
            newNd.setNext(null);
            tail.setNext(newNd);
            tail = newNd;
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

    Object peek(Object inValue) {
        DSAListNode currNd = head;
        Object nodeValue = null;;
        boolean exit = false;
        if (isEmpty()) {
            nodeValue = null;
        } else if (head.getValue() == inValue) {
            nodeValue = head.getValue();
        } else {
            while (currNd.getNext() != null && !exit) {
                if (currNd.getNext().getValue() == inValue) {
                    nodeValue = currNd.getNext().getValue();
                }
                exit = true;
            }
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
        Object nodeValue = null;
        if (isEmpty()) {
            return null;
        } else if (head.getNext() == null) {
            head = null;
            tail = null;
            decrementCounter();
        } else {
            DSAListNode headNode = head.getNext();
            nodeValue = head.getValue();
            head.setNext(null);
            head = headNode;
            head.setPrev(null);
            decrementCounter();
        }
        return nodeValue;
    }

//    Object remove(Object inValue) {
        //!!!!!!!!!!!!
//    }

    Object removeLast() {
        Object nodeValue = null;
        if (isEmpty()) {
            return null;
        } else if (tail.getPrev() == null) {
            head = null;
            tail = null;
            decrementCounter();
        } else {
            DSAListNode tailNode = tail.getPrev();
            nodeValue = tail.getValue();
            tail.setPrev(null);
            tail = tailNode;
            tail.setNext(null);
            decrementCounter();
        }
        return nodeValue;
    } 

    boolean find(Object inValue) {
        DSAListNode currNd = head;
        boolean found = false;
        boolean exit = false;
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("The LinkedList is empty");
        } else if (head.getValue() == inValue) {
            found = true;
        } else {
            while (currNd.getNext() != null && !exit) {
                if (currNd.getNext().getValue() == inValue) {
                    found = true;
                }
                exit = true;
            }
        }
        return found;
   }

    public String toString() {
        String output = "";

        if (!isEmpty()) {
            DSAListNode currNd = head;
            while (currNd != null) {
                output += "[" + currNd.getValue().toString() + "]";
                currNd = currNd.getNext();
            }
        }
        return output;
    }
}