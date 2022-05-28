import java.util.*;
public class operations {
    public static void nodeFind(DSAGraph mapGraph) {
        Scanner sc = new Scanner(System.in);
        DSAGraph.DSAGraphVertex v = null;
        System.out.print("\n--Find--\nLabel: ");
        String label = sc.nextLine().trim();
        v = mapGraph.getVertex(label);
        if (v == null) {
            System.out.println("ERROR: This node could not be found");
        } else {
            System.out.println("\nNode Found (Label: Value): " + v.toString() + ": " + v.getValue());
        }
    }

    public static void nodeInsert(DSAGraph mapGraph) {
        Scanner sc = new Scanner(System.in);
        System.out.print("\n--Insert--\nLabel: ");
        String label = sc.nextLine().trim();
        System.out.print("Value: ");
        Object value = sc.next().trim();
        mapGraph.addVertex(label, value);
    }

    public static void nodeDelete(DSAGraph mapGraph) {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        System.out.print("--Delete--\nLabel: ");
        String label = sc.nextLine().trim();
        mapGraph.deleteVertex(label);

        do {
            System.out.println("Would you like to delete all edges containing this node? [y/n]");
            String choice = sc.nextLine().trim();
            if (choice.charAt(0) == 'y' || choice.charAt(0) == 'Y') {
                mapGraph.deleteEdge(label);
                exit = true;
            } else if (choice.charAt(0) == 'n' || choice.charAt(0) == 'N') {
                exit = true;
            } else {
                System.out.println("Invalid Input!");
            }
        } while (exit != true);
    }

    public static void nodeUpdate(DSAGraph mapGraph) {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        System.out.print("\n--Update--\nLabel: ");

        do {
            String label = sc.nextLine().trim();
            DSAGraph.DSAGraphVertex v = mapGraph.getVertex(label);
            if (v == null) {
                System.out.println("Node could not be found, please try again\nLabel: ");
            } else {
                exit = true;
                int choice;
                do {
                    System.out.println("(1) Update label\n(2) Update Value\n(0) Exit");
                    choice = Integer.parseInt(sc.nextLine().trim());

                    switch (choice) {
                        case 1:
                            System.out.print("New Label: ");
                            label = sc.nextLine().trim();
                            v.setLabel(label);
                            break;
                        case 2: 
                            System.out.print("New Value: ");
                            Object value = sc.next().trim();
                            v.setValue(value);
                            break;
                        case 0:
                            break;
                        default: 
                            System.out.println("Invalid Input!");
                            break;
                    }
                } while (choice != 0);
            }
        } while (exit != true);
    } 

    public static void edgeFind(DSAGraph mapGraph) {
        Scanner sc = new Scanner(System.in);
        DSAGraph.DSAGraphEdge e = null;
        System.out.print("\n--Find--\nSource: ");
        String source = sc.nextLine().trim();
        System.out.print("Destination: ");
        String dest = sc.nextLine().trim();
        e = mapGraph.getEdge(source, dest);
        if (e == null) {
            System.out.println("ERROR: This edge could not be found");
        } else {
            System.out.println("\nEdge Found : " + e.toString());
        }
    }

    public static void edgeInsert(DSAGraph mapGraph) {
        Scanner sc = new Scanner(System.in);
        System.out.print("\n--Insert--\nSource: ");
        String source = sc.nextLine().trim();

        System.out.print("Destination: ");
        String dest = sc.nextLine().trim();
        
        double weight = 0;
        boolean exit = true;
        do {
            System.out.print("Weight: ");
            try {
                weight = Double.parseDouble(sc.nextLine().trim());
                exit = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input! Aborting...");
                exit = false;
            }
        } while (exit != true);

        DSAQueue security = new DSAQueue();
        String userIn = "";
        do {
            System.out.print("Security (Press enter after each entry, press x to exit): ");
            userIn = sc.nextLine().trim();
            if (!userIn.equals("x") && !userIn.equals("X")) {
                security.enqueue(userIn);
            }
        } while (!userIn.equals("x") && !userIn.equals("X"));

        System.out.println();
        DSAQueue barrier = new DSAQueue();
        String userIn2 = "";
        barrier.enqueue(userIn);
        do {
            System.out.print("Barriers (Press enter after each entry, press x to exit): ");
            userIn2 = sc.nextLine().trim();
            if (!userIn2.equals("x") && !userIn2.equals("X")) {
                barrier.enqueue(userIn);
            }
        } while (!userIn2.equals("x") && !userIn2.equals("X"));
        
        mapGraph.addEdge(source, dest, security, weight, barrier);
    }

    public static void edgeDelete(DSAGraph mapGraph) {
        Scanner sc = new Scanner(System.in);
        System.out.print("--Delete--\nWarning: This will delete all edges with the same source and destination\n\nSource: ");
        String source = sc.nextLine().trim();

        System.out.print("Destination: ");
        String dest = sc.nextLine().trim();
    
        mapGraph.deleteEdge(source, dest); 
    }

    public static void edgeUpdate(DSAGraph mapGraph) {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        System.out.println("\n--Update--");

        do {
            System.out.print("Source: ");
            String source = sc.nextLine().trim();

            System.out.print("Destination: ");
            String dest = sc.nextLine().trim();
            
            DSAGraph.DSAGraphEdge e = mapGraph.getEdge(source, dest);
            if (e == null) {
                System.out.println("Edge could not be found, please try again");
            } else {
                exit = true;
                int choice;
                do {
                    System.out.println("\n(1) Update source\n(2) Update destination\n(3) Update weight\n(4) Add/Remove Security\n(5) Add/Remove Barriers\n(0) Exit");
                    choice = Integer.parseInt(sc.nextLine().trim());

                    switch (choice) {
                        case 1:
                            System.out.print("New Source: ");
                            String src = sc.nextLine().trim();
                            DSAGraph.DSAGraphVertex v = mapGraph.getVertex(src);
                            try {
                                e.setSource(v);
                            } catch (Exception e1) {
                                System.out.println("ERROR: " + e1.getMessage());
                            }
                            break;
                        case 2: 
                            System.out.print("New Destination: ");
                            String destination = sc.nextLine().trim();
                            v = mapGraph.getVertex(destination);
                            try {
                                e.setDest(v);
                            } catch (Exception e1) {
                                System.out.println("ERROR: " + e1.getMessage());
                            }
                            break;
                        case 3:
                            double weight = 0;
                            System.out.print("New Weight: ");
                            try {
                                weight = Double.parseDouble(sc.nextLine().trim());
                            } catch (NumberFormatException e1) {
                                System.out.println("Invalid Input!");
                            }
                            e.setWeight(weight);
                            break;
                        case 4:
                            System.out.println("(1) Add\n(2) Remove");
                            choice = Integer.parseInt(sc.nextLine().trim());
                            if (choice == 1) {
                                System.out.print("Security to add: ");
                                String newSecurity = sc.nextLine().trim();
                                DSAQueue security = e.getSecurity();
                                security.enqueue(newSecurity);
                                e.setSecurity(security);
                            } else if (choice == 2) {
                                System.out.print("Security to remove: ");
                                String newSecurity = sc.nextLine().trim();
                                DSAQueue oldSecQueue = new DSAQueue();
                                DSAQueue newSecQueue = new DSAQueue();
                                oldSecQueue = e.getSecurity();
                                while (!oldSecQueue.isEmpty()) {
                                    String oldSecurity = (String)oldSecQueue.dequeue();
                                    if (oldSecurity.equals(newSecurity)) {
                                        System.out.println("Security successfully removed");
                                    } else {
                                        newSecQueue.enqueue(oldSecurity);
                                    }
                                }
                                e.setSecurity(newSecQueue);
                            } else {
                                System.out.println("Invalid Input!");
                            }
                            break;
                        case 5:
                            System.out.println("(1) Add\n(2) Remove");
                            choice = Integer.parseInt(sc.nextLine().trim());
                            if (choice == 1) {
                                System.out.print("Barrier to add: ");
                                String newBarrier = sc.nextLine().trim();
                                DSAQueue barrier = e.getBarrier();
                                barrier.enqueue(newBarrier);
                                e.setSecurity(barrier);
                            } else if (choice == 2) {
                                System.out.print("Barrier to remove: ");
                                String newBarrier = sc.nextLine().trim();
                                DSAQueue oldBarQueue = new DSAQueue();
                                DSAQueue newBarQueue = new DSAQueue();
                                oldBarQueue = e.getBarrier();
                                while (!oldBarQueue.isEmpty()) {
                                    String oldBarrier = (String)oldBarQueue.dequeue();
                                    if (oldBarrier.equals(newBarrier)) {
                                        System.out.println("Barrier successfully removed");
                                    } else {
                                        newBarQueue.enqueue(oldBarrier);
                                    }
                                }
                                e.setBarrier(newBarQueue);
                            } else {
                                System.out.println("Invalid Input!");
                            }
                            break;
                        case 0:
                            break;
                        default: 
                            System.out.println("Invalid Input!");
                            break;
                    }
                } while (choice != 0);
            }
        } while (exit != true);
    }
}