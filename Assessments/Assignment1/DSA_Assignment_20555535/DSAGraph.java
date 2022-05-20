import java.util.*;

public class DSAGraph {
    private class DSAGraphVertex {
        //Class variables
        private String label;
        private Object value;
        private boolean visited;

        //Constructor
        private DSAGraphVertex(String inLabel, Object inValue) {
            this.label = inLabel;
            this.value = inValue;
            visited = false;
        }
        //Accessors
        private String getLabel() {
            return this.label;
        }
        private Object getValue() {
            return this.value;
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
        //toString method
        public String toString() {
            String vertex = label + ": Value = " + value;
            return vertex;
        }
    }

    private class DSAGraphEdge {
        //Class Variables
        private DSAGraphVertex src;
        private DSAGraphVertex dest;
        private double distance;
        private DSAQueue barrier;
        private DSAQueue security;

        //Constructor
        private DSAGraphEdge(DSAGraphVertex inSrc, DSAGraphVertex inDest, DSAQueue inBarrier, DSAQueue inSecurity, double inDistance) {
            this.src = inSrc;
            this.dest = inDest;
            this.distance = inDistance;

            this.barrier = inBarrier;
            this.security = inSecurity;
        }
        //Accessors
        private DSAGraphVertex getSource() {
            return this.src;
        }
        private DSAGraphVertex getDest() {
            return this.dest;
        }
        private double getDistance() {
            return this.distance;
        }
        private DSAQueue getBarrier() {
            return this.barrier;
        }
        private DSAQueue getSecurity() {
            return this.security;
        }
        //toString method
        public String toString() {
            String edge = src.getLabel() + ">" + dest.getLabel() + "|D:" + distance + "|S:" + security.toString() + "B:" + barrier.toString();
            return edge;
        }
    }

    //Class variables
    private DSALinkedList vertices;
    private DSALinkedList edges;

    //Constructor
    public DSAGraph() {
        vertices = new DSALinkedList();
        edges = new DSALinkedList();
    }

    //addVertex
    public void addVertex(String inLabel, Object inValue) {
        DSAGraphVertex vertex = new DSAGraphVertex(inLabel, inValue);
        vertices.insertLast(vertex);
    }

    //addEdge
    public void addEdge(String src, String dest, String barrier, String distance) {
        
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
        return edges.size();
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