public class TestDSAGraph {
    public static void main(String[] args) {
        testHarness();
    }

    public static void testHarness() {
        //Testing the constructor
        DSAGraph graph = new DSAGraph();

        addVertexTest(graph);

        System.out.println();

        addEdgeTest(graph);

        System.out.println();

        hasVertexTest(graph);

        System.out.println();

        hasEdgeTest(graph);

        System.out.println();

        getCountTest(graph);

        System.out.println();

        getVertexTest(graph);
        getEdgeTest(graph);
        getAdjacentTest(graph);
    }

    public static void addVertexTest(DSAGraph graph) {
        System.out.print("Testing addVertex : ");
        try {
            graph.addVertex("1", "one");
            graph.addVertex("2", "two");
            if (graph != null) {
                System.out.println("passed");
            }
        } catch (Exception e) {
            System.out.println("failed");
        }
    }

    public static void addEdgeTest(DSAGraph graph) {
        System.out.print("Testing addEdge : ");
        //Testing when provided with valid sources
        try {
            DSAQueue barrier = new DSAQueue();
            barrier.enqueue("stairs");
            barrier.enqueue("construction");

            DSAQueue security = new DSAQueue();
            security.enqueue("1");
            security.enqueue("2");

            graph.addEdge("1", "2", barrier, 3.8, security);
            System.out.println("passed");
        } catch (Exception e) {
            System.out.println("failed");
        }
        //Testing when provided with invalid sources
        System.out.print("Testing addEdge : ");
        try {
            DSAQueue barrier = new DSAQueue();
            barrier.enqueue("stairs");
            barrier.enqueue("construction");

            DSAQueue security = new DSAQueue();
            security.enqueue("1");
            security.enqueue("2");

            graph.addEdge("3", "2", barrier, 3.8, security);
            System.out.println("failed");
        } catch (Exception e) {
            System.out.println("passed");
        }
    }

    public static void hasVertexTest(DSAGraph graph) {
        System.out.print("Testing hasVertex : ");
        //Testing with an existing vertex
        try {
            boolean hasVertex = graph.hasVertex("1");
            if (hasVertex == true) {
                System.out.println("passed");
            } else {
                System.out.println("failed");
            }
        } catch (Exception e) {
            System.out.println("failed");
        }
        //Testing with a non-existing vertex
        System.out.print("Testing hasVertex : ");
        try {
            boolean hasVertex = graph.hasVertex("7");
            if (hasVertex == true) {
                System.out.println("failed");
            } else {
                System.out.println("passed");
            }
        } catch (Exception e) {
            System.out.println("failed");
        }
    }

    public static void hasEdgeTest(DSAGraph graph) {
        System.out.print("Testing hasEdge : ");
        //Testing with an existing edge
        try {
            boolean hasEdge = graph.hasEdge("1", "2");
            if (hasEdge) {
                System.out.println("passed");
            } else {
                System.out.println("failed");
            }
        } catch (Exception e) {
            System.out.println("failed");
        }
        //Testing with a non-existing edge
        System.out.print("Testing hasEdge : ");
        try {
            boolean hasEdge = graph.hasEdge("1", "5");
            if (hasEdge) {
                System.out.println("failed = 1");
            } else {
                System.out.println("passed");
            }
        } catch (Exception e) {
            System.out.println("failed " + e);
        }   
    }

    public static void getCountTest(DSAGraph graph) {
        System.out.print("Testing getVertexCount : ");
        int vertexCount = graph.getVertexCount();
        int edgeCount = graph.getEdgeCount();

        if (vertexCount == 2) {
            System.out.println("passed");
        } else {
            System.out.println("failed");
        }

        System.out.print("Testing getEdgeCount : ");
        if (edgeCount == 1) {
            System.out.println("passed");
        } else {
            System.out.println("failed");
        }
    }  

    public static void getVertexTest(DSAGraph graph) {
        System.out.print("Testing getVertex : ");
        try {
            graph.addVertex("50", "fifty");
            DSAGraph.DSAGraphVertex v = null;
            v = graph.getVertex("50");
            if (v.getLabel().equals("50")) {
                System.out.println("passed");
            } else {
                System.out.println("failed");
            }
        } catch (Exception e) {
            System.out.println("failed");
        }
    }

    public static void getEdgeTest(DSAGraph graph) {
        System.out.print("Testing getEdge : ");
        try {
            DSAQueue barrier = new DSAQueue();
            DSAQueue security = new DSAQueue();
            graph.addEdge("10", "50", barrier, 5.7, security);
            DSAGraph.DSAGraphEdge e = graph.getEdge("10", "50");
            if (e.getWeight() == 5.7) {
                System.out.println("passed");
            } else {
                System.out.println("failed");
            }
        } catch (Exception e) {
            System.out.println("failed: " + e.getMessage());
        }
    }

    public static void getAdjacentTest(DSAGraph graph) {
        System.out.print("Testing getAdjacent : ");
        try {
            DSALinkedList list = new DSALinkedList();
            list = graph.getAdjacent("10");
            System.out.println(list.toString());
        } catch (Exception e) {
            System.out.println("failed");
        }
    }
}
