import java.util.Iterator;

public class TestDSAGraph {
   public static void main(String args[]) {
       testFunc();
   } 

   public static void testFunc() {
       DSAGraph graph = new DSAGraph();
       int count = 0;

       //addVertex(valid values)
       System.out.print("Testing addVertex(valid label, valid value) : ");
       try { 
            graph.addVertex("A", "A");
            graph.addVertex("B", "B");
            graph.addVertex("C", "C");
            graph.addVertex("D", "D");
            System.out.println("passed");
            count++;
       } catch (Exception e) {
           System.out.println("failed");
       }

        //addEdge(valid values)
        System.out.print("\nTesting addEdge(valid src, valid dest) : ");
        try {
            graph.addEdge("A", "B");
            graph.addEdge("B", "C");
            graph.addEdge("C", "D");
            graph.addEdge("D", "A");
            System.out.println("passed");
            count++;
        } catch (Exception e) {
            System.out.println("failed");
        }

       //addEdge(invalid label1 and 2)
       System.out.print("\nTesting addEdge(invalid labels) : ");
       try {
           graph.addEdge("AA", "BB");
           System.out.println("failed");
       } catch (Exception e) {
           System.out.println("passed");
           count++;
       }

       //getVertexCount()
       System.out.print("\nTesting getVertexCount() : ");
       try {
           if (graph.getVertexCount() == 4) {
               System.out.println("passed");
               count++;
           } else {
               System.out.println("failed");
           }
       } catch (Exception e) {
           System.out.println("failed");
       }

       //getEdgeCount
       System.out.print("\nTesting getEdgeCount() : ");
       try {
        if (graph.getVertexCount() == 4) {
            System.out.println("passed");
            count++;
        } else {
            System.out.println("failed");
        }
    } catch (Exception e) {
        System.out.println("failed");
    }

       //getVertex(valid label)
        System.out.print("\nTesting getVertex(valid label) : ");
        try {
            Object vertex = graph.getVertex("A");
            System.out.println("passed");
            count++;
        } catch (Exception e) {
            System.out.println("failed: " + e.getMessage());
        }

       //getVertex(invalid label)
        System.out.print("\nTesting getVertex(invalid label) : ");
        try {
            Object vertex = graph.getVertex("G");
            System.out.println("failed");
        } catch (Exception e) {
            System.out.println("passed");
            count++;
        }

       //getAdjacent(valid label)
       System.out.print("\nTesting getAdjacent(valid label) : ");
       DSALinkedList ll = new DSALinkedList();
       try {
           ll = graph.getAdjacent("A");
            System.out.println("passed");
            count++;
        } catch (Exception e) {
            System.out.println("failed :" + e.getMessage());
       }

       //getAdjacent(invalid label)
       System.out.print("\nTesting getAdjacent(invalid label) : ");
       DSALinkedList l = new DSALinkedList();
       try {
            l = graph.getAdjacent("G");
            System.out.println("failed");
        } catch (Exception e) {
            System.out.println("passed");
            count++;
       }

       //isAdjacent(valid value)
       System.out.print("\nTesting isAdjacent(valid label) : ");
       try {
        if (graph.isAdjacent("A", "B") == true) {
             System.out.println("passed");
             count++;
        } else {
            System.out.println("failed");
        }
    } catch (Exception e) {
        System.out.println("failed");
    }

       //isAdjacent(invalid label 1 and 2)
       System.out.print("\nTesting isAdjacent(invalid labels) : ");
       try {
           if (graph.isAdjacent("AA", "BB") == false) {
                System.out.println("passed");
                count++;
           } else {
               System.out.println("failed");
           }
       } catch (Exception e) {
           System.out.println("failed");
       }

       //displayAsList()
       System.out.println();
       graph.displayAsList();
       
       //displayAsMatrix()
       System.out.println();
       //graph.displayAsMatrix();
    
       System.out.println(count + "/11");

       double percentage = ((double)count / 11.0) * 100.0;
       System.out.println(percentage + "%");
   }
}