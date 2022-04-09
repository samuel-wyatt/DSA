import java.util.NoSuchElementException;

public class DSABinarySearchTree {
    private class DSATreeNode {
        //Class variables
        private String key;
        private Object value;
        private DSATreeNode leftChild;
        private DSATreeNode rightChild;

        //Constructor for new DSATreeNode
        public DSATreeNode(String inKey, Object inVal) {
            if (inKey == null) {
                throw new IllegalArgumentException("Key cannot be null");
            }
            key = inKey;
            value = inVal;
            leftChild = null;
            rightChild = null;
        }

        //Getters and setters for class variables
        public String getKey() { return key; }
        public Object getValue() { return value; }
        public DSATreeNode getLeft() { return leftChild; }
        public DSATreeNode getRight() { return rightChild; }
        public void setLeft(DSATreeNode newLeft) { leftChild = newLeft; }
        public void setRight(DSATreeNode newRight) { rightChild = newRight; }
    }

    //Class variable
    private DSATreeNode root;

    //Constructor
    public DSABinarySearchTree() { root = null; }

    //find() method
    public Object find(String key) { return findRec(key, root); }

    private Object findRec(String key, DSATreeNode currNd) {
        Object value = null;
        //If tree is empty throw exception
        if (currNd == null) {
            throw new NoSuchElementException("Key " + key + " not found");
        //If the key of the current node equals Key, return the value of the node.
        //This is what ends the recursive algorithm.
        } else if (key.equals(currNd.getKey())) {
            value = currNd.getValue();
        //Checks if the key is less than the current node, and goes left if true.
        } else if (key.compareTo(currNd.getKey()) < 0) {
            value = findRec(key, currNd.getLeft());
        //At this point the key must go to the right.
        } else {
            value = findRec(key, currNd.getRight());
        }
        return value;
    }
    
    //insert() method
    public DSATreeNode insert(String key, Object data) { return insertRec(key, data, root); }

    private DSATreeNode insertRec(String key, Object data, DSATreeNode currNd) {

        DSATreeNode updateNode = currNd;

        //Once the recursive algo has reached the bottom of the tree, it inserts the new node.
        if (currNd == null) {
            DSATreeNode newNode = new DSATreeNode(key, data);
            updateNode = newNode;
        //If key already exists in tree, something has gone wrong. Throws an error.
        } else if (key.equals(currNd.getKey())) {
            throw new IllegalArgumentException("Key already exists. Aborting");
        //If the key is 'less than' the key of the current node then the recursive algo is called to go left.
        } else if (key.compareTo(currNd.getKey()) < 0) {
            currNd.setLeft(insertRec(key, data, currNd.getLeft()));
        //The key has to be 'greater than' the key of the current node, so the recursive algo goes right.
        } else {
            currNd.setRight(insertRec(key, data, currNd.getRight()));
        }
        return updateNode;
    }
    //delete() method
  //  public void delete(String key) {}

    //display() method
  //  public void display(String type) {

    
    
    //height() method
    public int height() { return heightRec(root); }

    private int heightRec(DSATreeNode currNd) {
        int htSoFar, iLeftHt, iRightHt;
        //Checks for base case, no more nodes on this branch
        if (currNd == null) {
            htSoFar = -1;
        } else {
            //Calculates the left and right heights from currNd.
            iLeftHt = heightRec(currNd.getLeft());
            iRightHt = heightRec(currNd.getRight());

            //Checks which branch (left/right) was longer.
            if (iLeftHt > iRightHt) {
                htSoFar = iLeftHt + 1;
            } else {
                htSoFar = iRightHt + 1;
            }
        }
        return htSoFar; 
    }

    public String min() {
        String minKey;
        DSATreeNode currNd = root;
        while (currNd.getLeft() != null) {
            currNd = currNd.getLeft();
        }
        minKey = currNd.getKey();
        return minKey;
    }

    public String max() {
        String maxKey;
        DSATreeNode currNd = root;
        while (currNd.getRight() != null) {
            currNd = currNd.getRight();
        }
        maxKey = currNd.getKey();
        return maxKey;
    }

   //public double balance() { return balanceRec(root); }

   // public double balanceRec(DSATreeNode currNd) {
        
    

    public DSALinkedList inorder() { return inorderRec(root); }

    private DSALinkedList inorderRec(DSATreeNode currNd) {
        DSALinkedList ll = new DSALinkedList();
        if (currNd == null) {
            throw new NoSuchElementException("Binary Search Tree is empty.");
        } else {
            while (currNd.getLeft() != null) {
                inorderRec(currNd.getLeft());
            }
            ll.insertLast(currNd.getLeft().getValue());

            ll.insertLast(currNd.getValue());

            while (currNd.getRight() != null) {
                inorderRec(currNd.getRight());
            }
            ll.insertLast(currNd.getRight().getValue());
        }
        return ll;
    }
}