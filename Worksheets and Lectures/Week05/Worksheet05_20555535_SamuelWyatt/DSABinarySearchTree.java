import java.util.*;
import java.io.*;

public class DSABinarySearchTree implements Serializable {

    private class DSATreeNode implements Serializable {
        //Class variables
        private int key;
        private Object value;
        private DSATreeNode leftChild;
        private DSATreeNode rightChild;

        //Constructor for new DSATreeNode
        public DSATreeNode(int inKey, Object inVal) {
            key = inKey;
            value = inVal;
            leftChild = null;
            rightChild = null;
        }

        //Getters and setters for class variables
        public int getKey() { return this.key; }
        public Object getValue() { return this.value; }
        public DSATreeNode getLeft() { return this.leftChild; }
        public DSATreeNode getRight() { return this.rightChild; }
        public void setLeft(DSATreeNode newLeft) { this.leftChild = newLeft; }
        public void setRight(DSATreeNode newRight) { this.rightChild = newRight; }
    }

    //Class variable
    private DSATreeNode root;

    //Constructor
    public DSABinarySearchTree() { root = null; }

    //find() method
    public Object find(int key) { return findRec(key, root); }

    private Object findRec(int key, DSATreeNode currNd) {
        Object value = null;
        //If tree is empty throw exception
        if (currNd == null) {
            throw new NoSuchElementException("Key " + key + " not found");

        //If the key of the current node equals Key, return the value of the node.
        //This is what ends the recursive algorithm.
        } else if (key == currNd.getKey()) {
            value = currNd.getValue();

        //Checks if the key is less than the current node, and goes left if true.
        } else if (key < currNd.getKey()) {
            value = findRec(key, currNd.getLeft());

        //At this point the key must go to the right.
        } else {
            value = findRec(key, currNd.getRight());
        }
        return value;
    }
    
    //insert() method
    public void insert(int key, Object data) { root = insertRec(key, data, root); }

    private DSATreeNode insertRec(int key, Object data, DSATreeNode currNd) {

        DSATreeNode updateNode = currNd;
        //Once the recursive algo has reached the bottom of the tree, it inserts the new node.
        if (currNd == null) {
            DSATreeNode newNode = new DSATreeNode(key, data);
            updateNode = newNode;
        //If key already exists in tree, something has gone wrong. Throws an error.
        } else if (key == currNd.getKey()) {
            throw new IllegalArgumentException("Key already exists. Aborting");
        //If the key is 'less than' the key of the current node then the recursive algo is called to go left.
        } else if (key < currNd.getKey()) {
            currNd.setLeft(insertRec(key, data, currNd.getLeft()));
        //The key has to be 'greater than' the key of the current node, so the recursive algo goes right.
        } else {
            currNd.setRight(insertRec(key, data, currNd.getRight()));
        } 
        return updateNode;
    }

    //delete() method/s
    public DSATreeNode delete(int key) { return deleteRec(key, root); }

    private DSATreeNode deleteRec(int key, DSATreeNode currNd) {
        DSATreeNode updateNode = currNd;
        if (currNd == null) {
            throw new  NoSuchElementException("Key does not exist.");
        } else if (key == currNd.getKey()) {
            updateNode = deleteNode(key, currNd);
        } else if (key < currNd.getKey()) {
            currNd.setLeft(deleteRec(key, currNd.getLeft()));
        } else {
            currNd.setRight(deleteRec(key, currNd.getRight()));
        }
        return updateNode;
    }

    private DSATreeNode deleteNode(int key, DSATreeNode delNode) {
        DSATreeNode updateNode = null;
        if (delNode.getLeft() == null && delNode.getRight() == null) {
            updateNode = null;
        } else if (delNode.getLeft() != null && delNode.getRight() == null) {
            updateNode = delNode.getLeft();
        } else if (delNode.getLeft() == null && delNode.getRight() != null) {
            updateNode = delNode.getRight();
        } else {
            updateNode = promoteSuccessor(delNode.getRight());
            if (updateNode != delNode.getRight()) {
                updateNode.setRight(delNode.getRight());
            }
            updateNode.setLeft(updateNode.getLeft());
        }
        return updateNode;
    }

    private DSATreeNode promoteSuccessor(DSATreeNode currNd) {
        DSATreeNode successor = currNd;
        if (currNd.getLeft() == null) {
            successor = currNd;
        } else {
            if (currNd.getLeft() == null) {
                successor = promoteSuccessor(currNd.getLeft());
                if (successor == currNd.getLeft()) {
                    currNd.setLeft(successor.getRight());
                }
            }
        }
        return successor;
    }

    //display() method
    public void display(String type) {
        DSALinkedList ll = new DSALinkedList();
        
        if (type.equals("inorder")) {
            ll = inorder();
            System.out.println(ll.toString());

        } else if (type.equals("preorder")) {
            ll = preorder();
            System.out.println(ll.toString());
        } else if (type.equals("postorder")) {
            ll = postorder();
            System.out.println(ll.toString());

        } else {
            throw new IllegalArgumentException("Incorrect type provided");
        }
    }
    
    
    //height() method
    public int height() { return heightRec(root); }

    private int heightRec(DSATreeNode currNd) {
        int htSoFar, leftHt, rightHt;
        //Checks for base case, no more nodes on this branch
        if (currNd == null) {
            htSoFar = -1;
        } else {
            //Calculates the left and right heights from currNd.
            leftHt = heightRec(currNd.getLeft());
            rightHt = heightRec(currNd.getRight());

            //Checks which branch (left/right) was longer.
            if (leftHt > rightHt) {
                htSoFar = leftHt + 1;
            } else {
                htSoFar = rightHt + 1;
            }
        }
        return htSoFar; 
    }

    //min() method
    public int min() {
        int minKey;
        DSATreeNode currNd = root;
        if (currNd == null) {
            throw new NoSuchElementException("Tree cannot be empty");
        } else  {
            while (currNd.getLeft() != null) {
            currNd = currNd.getLeft();
            }
            minKey = currNd.getKey();
        }
        return minKey;
    }

    //max() method
    public int max() {
        int maxKey;
        DSATreeNode currNd = root;
        if (currNd == null) {
            throw new NoSuchElementException("Tree cannot be empty");
        } else {
            while (currNd.getRight() != null) {
                currNd = currNd.getRight();
            }
            maxKey = currNd.getKey();
        }
        return maxKey;
    }

    //balance() method
   public double balance() { return balanceRec(root); }

   private double balanceRec(DSATreeNode currNd) {
        double balance = 100, leftHeight, rightHeight, totalHeight;
        leftHeight = heightRec(currNd.getLeft());
        rightHeight = heightRec(currNd.getRight());
        totalHeight = height();
        
        if (leftHeight == rightHeight) {
            balance = 100;
        } else if ((leftHeight < rightHeight)) {
            if (rightHeight - leftHeight > 1) {
                balance = (totalHeight / (rightHeight - leftHeight) * 100) - 100;
            } else {
                balance = 100;
            }
        } else {
            if ((leftHeight - rightHeight) > 1) {
                balance = (totalHeight / (leftHeight - rightHeight) * 100) - 100; 
            } else {
                balance = 100;
            }
        }

        return balance;
   }

    //inorder() method
    public DSALinkedList inorder() { 
        DSALinkedList l = new DSALinkedList();
        return inorderRec(l ,root); }

    private DSALinkedList inorderRec(DSALinkedList l, DSATreeNode currNd) {
        if (currNd != null) {
            inorderRec(l, currNd.getLeft());
            l.insertLast(currNd.getKey());
            inorderRec(l, currNd.getRight());
        }
        return l;
    }

    //preorder() method
    public DSALinkedList preorder() { 
        DSALinkedList l = new DSALinkedList();
        return preorderRec(l, root); }

    private DSALinkedList preorderRec(DSALinkedList l, DSATreeNode currNd) {
        if (currNd != null) {
            l.insertLast(currNd.getKey());
            preorderRec(l, currNd.getLeft());
            preorderRec(l, currNd.getRight());
        }
        return l;
    }

    //postorder() method
    public DSALinkedList postorder() { 
        DSALinkedList l = new DSALinkedList();
        return postorderRec(l, root); }

    private DSALinkedList postorderRec(DSALinkedList l, DSATreeNode currNd) {
        if (currNd != null) {
            postorderRec(l, currNd.getLeft());
            postorderRec(l, currNd.getRight());
            l.insertLast(currNd.getKey());
        }
        return l;
    }
}