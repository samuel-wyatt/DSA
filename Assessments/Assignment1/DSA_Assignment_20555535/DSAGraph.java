/**********************************************************
 * Author: Samuel Wyatt (20555535)                        *
 * Date: 25/04/2022                                       *
 * File Name: DSAGraph                                    *
 * Purpose: To create a class to imitate a graph.         *
 * IMPORTANT: This code has been re-used from Practical 5 *
 **********************************************************/
import java.io.Serializable;
import java.util.*;

public class DSAGraph implements Serializable {
    public class DSAGraphVertex implements Serializable {
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
        public String getLabel() {
            return this.label;
        }
        public Object getValue() {
            return this.value;
        }

        //Mutators
        public void setLabel(String inLabel) {
            this.label = inLabel;
        }
        public void setValue(Object inValue) {
            this.value = inValue;
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
            return label;
        }
    }

    public class DSAGraphEdge implements Serializable {
        //Class Variables
        private DSAGraphVertex src;
        private DSAGraphVertex dest;
        private double weight;
        private DSAQueue barrier;
        private DSAQueue security;
        private String weightUnit;

        //Constructor
        public DSAGraphEdge(DSAGraphVertex inSrc, DSAGraphVertex inDest, DSAQueue inBarrier, DSAQueue inSecurity, double inWeight) {
            this.src = inSrc;
            this.dest = inDest;
            this.weight = inWeight;
            this.barrier = inBarrier;
            this.security = inSecurity;
        }
        //Accessors
        public DSAGraphVertex getSource()  {
            return this.src;
        }
        public DSAGraphVertex getDest() {
            return this.dest;
        }
        public double getWeight() {
            return this.weight;
        }
        public DSAQueue getBarrier() {
            return this.barrier;
        }
        public DSAQueue getSecurity() {
            return this.security;
        }
        public String getWeightUnit() {
            return this.weightUnit;
        }

        //Mutators
        public void setSource(DSAGraphVertex inSrc)  {
            this.src = inSrc;
        }
        public void setDest(DSAGraphVertex inDest) {
            this.dest = inDest;
        }
        public void setWeight(double inWeight) {
            this.weight = inWeight;
        }
        public void setBarrier(DSAQueue inBarrier) {
            this.barrier = inBarrier;
        }
        public void setSecurity(DSAQueue inSecurity) {
            this.security = inSecurity;
        }
        public void setWeightUnit(String inWeightUnit) {
            this.weightUnit = inWeightUnit;
        }

        //toString method
        public String toString() {
            String edge = src.getLabel() + ">" + dest.getLabel() + "|D:" + weight + "|S:" + security.toString() + "|B:" + barrier.toString();
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
    public void addEdge(String src, String dest, DSAQueue barrier, double weight, DSAQueue security) {
        DSAGraphVertex source = null;
        DSAGraphVertex destination = null;

        source = getVertex(src);
        destination = getVertex(dest);

        //Check if both vertices were found, if not throw exception
        if (source == null) {
            throw new NoSuchElementException("Source vertex not found");
        } else if (destination == null) {
            throw new NoSuchElementException("Destination vertex not found");
        } else {
            DSAGraphEdge edge = new DSAGraphEdge(source, destination, barrier, security, weight);
            edges.insertLast(edge);
        }
    }

    //deleteVertex
    public void deleteVertex(String inLabel) {
        Iterator iter = vertices.iterator();
        boolean exit = false;
        while (iter.hasNext() && !exit) {
            DSAGraphVertex v = (DSAGraphVertex)iter.next();
            if (v.getLabel().equals(inLabel)) {
                iter.remove();
                exit = true;
            }
        }
    }

    //deleteEdge
    public void deleteEdge(String inLabel) {
        Iterator iter = edges.iterator();
        while (iter.hasNext()) {
            DSAGraphEdge e = (DSAGraphEdge)iter.next();
            //If the source or destination's label equals the inLabel, delete the edge
            if (e.getSource().getLabel().equals(inLabel) || e.getDest().getLabel().equals(inLabel)) {
                iter.remove();
            }
        }
    }

    //deleteEdge
    public void deleteEdge(String src, String dest) {
        Iterator iter = edges.iterator();
        while (iter.hasNext()) {
            DSAGraphEdge e = (DSAGraphEdge)iter.next();
            //If the source's label equals src and the destination's label equals the dest, delete the edge
            if (e.getSource().getLabel().equals(src) && e.getDest().getLabel().equals(dest)) {
                iter.remove();
            }
        }
    }

    //hasVertex
    public boolean hasVertex(String inLabel) {
        boolean hasVertex = false;
        Iterator iter = vertices.iterator();

        while (iter.hasNext()) {
            DSAGraphVertex v = (DSAGraphVertex)iter.next();
            if (v.getLabel().equals(inLabel)) {
                hasVertex = true;
            }
        }
        return hasVertex;
    }

    //hasEdge
    public boolean hasEdge(String src, String dest) {
        boolean hasEdge = false, exit = false;
        DSAGraphVertex source = null, destination = null;
        Iterator iter = edges.iterator();

        try {
            source = getVertex(src);
            destination = getVertex(dest);
        } catch (NullPointerException e) {}
        //Check if both vertices were found, if not throw exception

        if (source != null && destination != null) {
            while (iter.hasNext() || exit != true) {
                DSAGraphEdge e = (DSAGraphEdge)iter.next();
                if (e.getSource() == source && e.getDest() == destination) {
                    hasEdge = true;
                    exit = true;
                }
            }
        }
        return hasEdge;
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
        Iterator iter = vertices.iterator();
        
        //While loop, to iterate through the linked list until list is empty.
        while (iter.hasNext()) {
            DSAGraphVertex v = (DSAGraphVertex)iter.next();
            if (v.getLabel().equals(inLabel)) {
                outVertex = v;
            }
        }
        return outVertex;
    }

    //getEdge
    public DSAGraphEdge getEdge(String src, String dest) {
        DSAGraphEdge edge = null;
        Iterator iter = edges.iterator();

        while (iter.hasNext()) {
            DSAGraphEdge e = (DSAGraphEdge)iter.next();
            if (e.getSource().getLabel().equals(src) && e.getDest().getLabel().equals(dest)) {
                edge = e;
            }
        }
        return edge;
     }

    //getAdjacent
    public DSALinkedList getAdjacent(String src) {
        DSALinkedList adjacent = new DSALinkedList();
        Iterator iter = edges.iterator();

        while (iter.hasNext()) {
            DSAGraphEdge edge = (DSAGraphEdge)iter.next();
            DSAGraphVertex source = edge.getSource();
            DSAGraphVertex dest = edge.getDest();
            if (source.getLabel().equals(src)) {
                adjacent.insertLast(dest);
            }
        }
        return adjacent;
    }

    public DSALinkedList getAdjacentEdges(String src) {
        DSALinkedList adjacent = new DSALinkedList();
        Iterator iter = edges.iterator();

        while (iter.hasNext()) {
            DSAGraphEdge edge = (DSAGraphEdge)iter.next();
            DSAGraphVertex source = edge.getSource();
            if (source.getLabel().equals(src)) {
                adjacent.insertLast(edge);
            }
        }
        return adjacent;
    }

    //getEdgeList
    public DSALinkedList getEdgeList() {
        return edges;
    }

    //displayAsList
    public void displayAsList() {
        Iterator iter = vertices.iterator();
        //Iterate through the entire vertices list
        while (iter.hasNext()) {
            DSAGraphVertex v = (DSAGraphVertex)iter.next();
            //Print the vertex using its toString method
            System.out.print(v.toString());
            //Create a second iterator for the edge list
            Iterator iter2 = edges.iterator();
            //Iterate through the entire edge list
            while (iter2.hasNext()) {
                DSAGraphEdge e = (DSAGraphEdge)iter2.next();
                //Check if the label of the source of edge e is equal to the label of the current vertex
                if (e.getSource().getLabel().equals(v.getLabel())) {
                    System.out.print(" -> " + e.getDest().getLabel());
                }
            }
            System.out.println();
        }
    }

    //displayAsMatrix
    public void displayAsMatrix() {
        Iterator iter = edges.iterator();
        int[][] matrix = new int[vertices.size()][vertices.size()];
        while (iter.hasNext()) {
            DSAGraphEdge e = (DSAGraphEdge)iter.next();

        }
    } 

    public DSALinkedList GetPaths(String startVertex, String targetVertex) {
        DSALinkedList paths = new DSALinkedList();
        paths = GetPathsRec(getVertex(startVertex), targetVertex, new DSAStack(), new DSAStack(), paths);
        return paths;
    }

    private DSALinkedList GetPathsRec(DSAGraphVertex cur, String target, DSAStack path, DSAStack edges, DSALinkedList finalPath) {
        path.push(cur);
        if (cur.getLabel().equals(target)) {
            DSAStack edgesCopy = new DSAStack();
            Iterator iter = edges.iterator();
            while (iter.hasNext()) {
                edgesCopy.push(iter.next());
            }
            finalPath.insertLast(edgesCopy);
        } else {
            DSALinkedList adj = getAdjacentEdges(cur.getLabel());
            Iterator iter = adj.iterator();
            while (iter.hasNext()) {
                DSAGraphEdge curEdge = (DSAGraphEdge)iter.next();
                DSAGraphVertex dest = curEdge.getDest();
                if (vertexNotInPath(dest, path)) {
                    edges.push(curEdge);
                    GetPathsRec(dest, target, path, edges, finalPath);
                    edges.pop();
                }
            }
        }
        path.pop();
        return finalPath;
    }

    private Boolean vertexNotInPath(DSAGraphVertex vertex, DSAStack path) {
        Iterator iter = path.iterator();
        while (iter.hasNext()) {
            String pathNodeName = ((DSAGraphVertex)iter.next()).getLabel();
            if (vertex.getLabel().equals(pathNodeName)) {
                return false;
            }
        }
        return true;
    }
}