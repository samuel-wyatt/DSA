public class TestDSAGraph {
   public static void main(String args[]) {
       testFunc();
   } 

   public static void testFunc() {
       DSAGraph graph = new DSAGraph();
       graph.addVertex("1", "one");
       graph.addVertex("2", "two");
       graph.addVertex("3", "three");
       graph.addVertex("4", "four");
       graph.addVertex("5", "five");

       graph.addEdge("1", "2");
       graph.addEdge("1", "3");
       graph.addEdge("1", "5");
       graph.addEdge("2", "4");
       graph.addEdge("2", "5");
       graph.addEdge("3", "4");
       graph.addEdge("3", "5");
       graph.addEdge("4", "5");

       graph.displayAsList();

       System.out.println("E: " + graph.getEdgeCount());
       System.out.println("V: " + graph.getVertexCount());

       System.out.println(graph.isAdjacent("1", "2"));
       System.out.println(graph.isAdjacent("4", "5"));
       
   }
}