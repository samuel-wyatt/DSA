import java.util.Iterator;
import java.util.NoSuchElementException;

public class DSAGraph {
    private class DSAGraphVertex {
        //Class variables
        private String label;
        private Object value;
        private DSALinkedList vertexList;

        //Constructor
        private DSAGraphVertex(String inLabel, Object inValue) {
            this.label = inLabel;
            this.value = inValue;
            vertexList = new DSALinkedList();
        }
        //Accessors
        private String getLabel() {
            return this.label;
        }
        private Object getValue() {
            return this.value;
        }
        private DSALinkedList getAdjacent() {
            return this.vertexList;
        }

        //Mutator
        private void addEdge(DSAGraphVertex inVertex) {
            vertexList.insertLast(inVertex);
        }

        //Searching 
        private void setVisited() {

        }
        private void clearVisited() {

        }
        private void getVisited() {

        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Iterator iter = vertexList.iterator();

            sb.append(label + " : ");
            while(iter.hasNext()) {
                sb.append("[" + ((DSAGraphVertex)iter.next()).getLabel() + "] ");
            }
            return sb.toString();
        }
    }

    //Class variables
    private DSALinkedList vertices;

    //Constructor
    public DSAGraph() {
        vertices = new DSALinkedList();
    }

    //addVertex
    public void addVertex(String inLabel, Object inValue) {
        DSAGraphVertex vertex = new DSAGraphVertex(inLabel, inValue);
        vertices.insertLast(vertex);
    }

    //addEdge
    public void addEdge(String inLabel1, String inLabel2) {
        boolean exit = false;
        boolean add = false;

        //Initialise iterator
        Iterator iter = vertices.iterator();

        //Initialise two new DSAGraphVertex, to be inserted into the vertexList of the corrosponding vertex
        DSAGraphVertex vertex1 = new DSAGraphVertex(inLabel1, null);
        DSAGraphVertex vertex2 = new DSAGraphVertex(inLabel2, null);

        //Find each vertex, and insert the 'opposite' label
        if (vertices.size() < 2) {
            throw new NoSuchElementException("There are less than 2 vertices");
        } else {
            while (iter.hasNext() || exit != true) {
                DSAGraphVertex v = (DSAGraphVertex)iter.next();
                if (v.getLabel().equals(inLabel1)) {
                    v.addEdge(vertex2);
                    exit = true;
                } else if (v.getLabel().equals(inLabel2)) {
                    v.addEdge(vertex1);
                    exit = true;
                }
            }
        }
    }

    //hasVertex
    public boolean hasVertex(String inLabel) {
        boolean hasVertex = false;
        Iterator iter = vertices.iterator();

        while (iter.hasNext()) {
            if (iter.next().equals(inLabel)) {
                hasVertex = true;
            }
        }
        return hasVertex;
    }

    //getVertexCount
    public int getVertexCount() {
        return vertices.size();
    }

    //getEdgeCount
    public int getEdgeCount() {
        int count = 0;
        Iterator iter = vertices.iterator();
        while (iter.hasNext()) {
            count += ((DSAGraphVertex)iter.next()).getAdjacent().size();
        }
        return count/2;
    }

    //getVertex
    public DSAGraphVertex getVertex(String inLabel) {
        //Initalise variables
        DSAGraphVertex outVertex = null;
        boolean exit = false;
        Iterator iter = vertices.iterator();
        
        //While loop, to iterate through the linked list until inLabel is found, or list is empty.
        while (!iter.next().equals(inLabel) || exit == false) {
            if (iter.next().equals(inLabel)) {
                outVertex = (DSAGraphVertex)iter.next();
                exit = true;
            }
        }
        return outVertex;
    }

    //getAdjacent
    public DSALinkedList getAdjacent(String inLabel) {
        //Initalise variables
        DSALinkedList ll = new DSALinkedList();
        Iterator iter = vertices.iterator();
        boolean exit = false;

        //
        while (iter.hasNext() || exit != true) {
            DSAGraphVertex v = (DSAGraphVertex)iter.next();
            if (v.getLabel().equals(inLabel)) {
                ll = v.getAdjacent();
                exit = true;
            } else {
                throw new NoSuchElementException("No vertex found");
            }
        }
        return ll;
    }   

    //isAdjacent
    public boolean isAdjacent(String inLabel1, String inLabel2) {
        //Initialise variables
        boolean isAdjacent = false, exit = false, exit2 = false;
        Iterator iter = vertices.iterator();
        DSALinkedList v = new DSALinkedList();

        //Loop until end of linked list is reached or exit is true
        while (iter.hasNext() || exit != true) {
            //Create a temp vertex containing the vertex at that point in the iterator
            DSAGraphVertex vertex = (DSAGraphVertex)iter.next();

            //Checks if the label of the vertex is equal to inLabel1
            if (vertex.getLabel().equals(inLabel1)) {
                //If true, extracts the vertex list
                v = vertex.getAdjacent();
                //Creates a new iterator for the linked list containing adjacent vertices
                Iterator iter2 = v.iterator();

                //Loop until end of linked list is reached or exit2 is true
                while (iter2.hasNext() || exit2 != true) {
                    //Creates a temp vertex containing the vertex at the current point of the iterator
                    DSAGraphVertex vertex2 = (DSAGraphVertex)iter2.next();

                    //Checks if the label of the vertex is the same as inLabel2
                    if (vertex2.getLabel().equals(inLabel2)) {
                        //If true, we have found the edge.
                        isAdjacent = true;
                        //Exit 2nd loop
                        exit2 = true;
                    }
                    isAdjacent = false;
                }
                //Exit first loop
                exit = true;
            }
        }
        return isAdjacent;
    }

    //displayAsList
    public void displayAsList() {
        Iterator iter = vertices.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next().toString());
        }
    }

    //displayAsMatrix
    //public void displayAsMatrix() {}
}