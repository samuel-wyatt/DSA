import java.util.*;

public class DSAGraph {
    private class DSAGraphVertex {
        //Class variables
        private String label;
        private Object value;
        private DSALinkedList edgeList;
        private boolean visited;

        //Constructor
        private DSAGraphVertex(String inLabel, Object inValue) {
            this.label = inLabel;
            this.value = inValue;
            edgeList = new DSALinkedList();
            visited = false;
        }
        //Accessors
        private String getLabel() {
            return this.label;
        }
        private Object getValue() {
            return this.value;
        }
        private DSALinkedList getAdjacent() {
            return this.edgeList;
        }

        //Mutator
        private void addEdge(DSAGraphEdge inEdge) {
            edgeList.insertLast(inEdge);
        }

        //Searching 
        private void setVisited() {
            this.visited = true;
        }
        private void clearVisited() {
            this.visited = false;
        }
        private boolean getVisited() {
            return this.visited;
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Iterator iter = edgeList.iterator();

            sb.append(label + " : ");
            while(iter.hasNext()) {
                sb.append("[" + ((DSAGraphVertex)iter.next()).getLabel() + "] ");
            }
            return sb.toString();
        }
    }

    private class DSAGraphEdge {
        //Class Variables
        private String source;
        private String destination;
        private DSALinkedList barrier;
        private double distance;

        //Constructor
        private DSAGraphEdge(String inSource, String inDestination, String[] inBarrier, double inDistance) {
            this.source = inSource;
            this.destination = inDestination;
            this.distance = inDistance;

            this.barrier = new DSALinkedList();
            for (int i = 0; i < inBarrier.length; i++) {
                this.barrier.insertLast(inBarrier[i]);    
            }
        }
        //Accessors
        private String getSource() {
            return this.source;
        }
        private String getDest() {
            return this.destination;
        }
        private String getBarrier() {
            return this.barrier;
        }
        private double getDistance() {
            return this.distance;
        }
        //Mutators
        private void setBarrier(String inBarrier) {
            this.barrier = inBarrier;
        }
        private void setDistance(double inDistance) {
            this.distance = inDistance;
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
    public void addEdge(String src, String dest, String barrier, String distance) {
        boolean exit = false, exit2 = false;

        //Initialise iterators
        Iterator iter = vertices.iterator();
        Iterator iter2 = vertices.iterator();

        //Check if there 2 or more vertices.
        if (vertices.size() < 2) {
            throw new NoSuchElementException("There are less than 2 vertices");
        } else {
            //While loop to iterate over vertices list
            while (iter.hasNext() || exit != true) {
                //Creates a temp vertex of the current node
                DSAGraphVertex v = (DSAGraphVertex)iter.next();
                //Checks if the temp vertex is equal to the destination label
                if (v.getLabel().equals(dest)) {
                    //If true, create a new vertex with the label and value from the vertex.
                    DSAGraphEdge newEdge = new DSAGraphEdge(v.getLabel(), v.getValue());
                    exit = true;

                    //2nd while loop to iterate over vertices list again.
                    while (iter2.hasNext() || exit2 != true) {
                        //Creates a temp vertex of current node.
                        DSAGraphVertex vv = (DSAGraphVertex)iter2.next();
                        //Checks if the label of the current node equals the source.
                        if (vv.getLabel().equals(src)) {
                            //Adds the new edge 
                            vv.addEdge(newEdge);
                            exit2 = true;
                            //If the iterator reaches the end of the list, the source has not been found.
                        } else if (!iter2.hasNext() && exit == false) {
                            throw new NoSuchElementException("Source does not exist");
                        }
                    }
                    //If the iterator reaches the end of the list, the dest has not been found.
                } else if (!iter.hasNext() && exit == false) {
                    throw new NoSuchElementException("Destination vertex does not exist");
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
        while (iter.hasNext() || exit != true) {
            DSAGraphVertex v = (DSAGraphVertex)iter.next();
            if (v.getLabel().equals(inLabel)) {
                outVertex = v;
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
            } else if (!iter.hasNext() && exit == false) {
                throw new NoSuchElementException("No vertex found");
            }
        }
        return ll;
    }   

    //isAdjacent
    public boolean isAdjacent(String src, String dest) {
        //Initialise variables
        boolean exit = false, exit2 = false, adjacent = false;

        //Two iterators for two loops
        Iterator iter = vertices.iterator();

        //First loop to find source vertex
        while (iter.hasNext() || exit != true) {
            DSAGraphVertex v = (DSAGraphVertex)iter.next();

            //Check if the source is the current node.
            if (v.getLabel().equals(src)) {
                //Extract the adjacency list.
                DSALinkedList ll = v.getAdjacent();
                //2nd iterator for the adjacency list
                Iterator iter2 = ll.iterator();

                exit = true;

                //2nd loop to find dest vertex.
                while (iter2.hasNext() || exit2 != true) {
                    DSAGraphVertex vv = (DSAGraphVertex)iter2.next();

                    if (vv.getLabel().equals(dest)) {
                        adjacent = true;
                        exit2 = true;
                    } else if (!iter2.hasNext() && exit2 == false) {
                        return false;
                    }
                }
            } else if (!iter.hasNext() && exit == false) {
                return false;
            }
        }
        return adjacent;
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

    public DSAQueue depthFirstSearch(String startingVertex) {
        DSAQueue T = new DSAQueue();
        DSAStack S = new DSAStack();
        Iterator verticesList = vertices.iterator();
        while (verticesList.hasNext()) {
            ((DSAGraphVertex)verticesList.next()).clearVisited();
        }
        DSAGraphVertex v = getVertex(startingVertex);
        if (v == null) {
            Iterator iter = vertices.iterator();
            v = (DSAGraphVertex)iter.next();
        }
        v.setVisited();
        S.push(v);
        while (!S.isEmpty()) {
            DSALinkedList adjacencyList = v.getAdjacent();
            Iterator adjacent = adjacencyList.iterator();
            while (adjacent.hasNext()) {
                DSAGraphVertex w = (DSAGraphVertex)adjacent.next();
                T.enqueue(v);
                T.enqueue(w);
                w.setVisited();
                S.push(w);
                v = w;
            }
            v = (DSAGraphVertex)S.pop();
        }
        return T;
    }

    public DSAQueue breadthFirstSearch(String startingVertex) {
        DSAQueue T = new DSAQueue();
        DSAQueue Q = new DSAQueue();
        Iterator verticesList = vertices.iterator();
        while (verticesList.hasNext()) {
            ((DSAGraphVertex)verticesList.next()).clearVisited();
        }
        DSAGraphVertex v = getVertex(startingVertex);
        if (v == null) {
            Iterator iter = vertices.iterator();
            v = (DSAGraphVertex)iter.next();
        }
        v.setVisited();
        Q.enqueue(startingVertex);
        while (!Q.isEmpty()) {
            v = (DSAGraphVertex)Q.dequeue();
            DSALinkedList adjacencyList = v.getAdjacent();
            Iterator adjacent = adjacencyList.iterator();
            while (adjacent.hasNext()) {
                DSAGraphVertex w = (DSAGraphVertex)adjacent.next();
                if (w.getVisited() != true) {
                    T.enqueue(v);
                    T.enqueue(w);
                    w.setVisited();
                    Q.enqueue(w);
                }
            }
        }
        return T;
    }
}