
import java.util.*;
import java.io.*;
import java.lang.*;
public class whereNow {
    public static void main(String[] args) {
        //Check the amount of command line arguments provided. If less than 1 print usage
        if (args.length < 1) {
            System.out.println("Interactive Mode Usage: java whereNow -i");
            System.out.println("Silent Mode Usage:      java whereNow -s <inFile> <journey> <saveFile>");
        } else {
            if (args[0].equals("-s") && args.length < 4) {
                System.out.println("ERROR: Correct amount of parameters were not provided");
            } else if (args[0].equals("-s") && args.length > 3) {
                silentMode(args);
            } else if (args[0].equals("-i")) {
                interactiveMode();
            } else {
                System.out.println("ERROR: Invalid flag was provided");
            }
        }
    }

    public static void menu() {
        System.out.println("\n------Interactive Mode------");
        System.out.println("(1) Load input file");
        System.out.println("(2) Node operations");
        System.out.println("(3) Edge operations");
        System.out.println("(4) Parameter tweaks");
        System.out.println("(5) Display graph");
        System.out.println("(6) Display world");
        System.out.println("(7) Enter journey details");
        System.out.println("(8) Generate routes");
        System.out.println("(9) Display routes");
        System.out.println("(10) Save network");
        System.out.println("(0) Exit");
    }

    public static void interactiveMode() {
        //Initialise the scanner to get user input
        Scanner sc = new Scanner(System.in);
        //Declare the graph, which will contain the entire map
        DSAGraph mapGraph = null;
        Journey mapJourney = null;
        int userInput = 11;
        //Do 
        do {
            userInput = -1;
            menu();
            System.out.print("\n");
            try {
                userInput = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {}
            String fileName;
            switch (userInput) {
                case 1:
                    //Check how the user wishes to load the map
                    System.out.println("(1) Load from text file\n(2) Load from serialized file\n");
                    int userInput2 = 0;
                    try {
                        userInput2 = Integer.parseInt(sc.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Input!");
                    } 
                    //If loading from text file, call loadInputFile method
                    if (userInput2 == 1) {
                        System.out.print("\nPlease enter the file name: ");
                        fileName = sc.nextLine().trim();
                        mapGraph = loadInputFile(fileName);

                    //If loading from text file, call load method from serialization
                    } else if (userInput2 == 2) {
                        System.out.print("\nPlease enter the file name: ");
                        fileName = sc.nextLine().trim();
                        try {
                            mapGraph = serialization.load(fileName);
                        } catch (IllegalArgumentException e) {
                            System.out.println("ERROR: " + e.getMessage());
                        }
                    }
                    if (mapGraph == null) {
                        System.out.println("ERROR: The map is null, no input has been recieved");
                    } else {
                        System.out.println("Loaded...");
                    }
                    break;
                case 2:
                    try {    
                        nodeOperations(mapGraph);
                    } catch (Exception e) {
                        System.out.println("ERROR: " + e);
                    }
                    break;
                case 3:
                    try {
                        edgeOperations(mapGraph);
                    } catch (Exception e) {
                        System.out.println("ERROR: " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        parameterTweak(mapGraph);
                    } catch (Exception e) {
                        System.out.println("ERROR: " + e.getMessage());
                    }
                    break;
                case 5:
                    mapGraph.displayAsList();
                    break;
                case 6:
                    displayWorld(mapGraph, mapJourney);
                    break;
                case 7:
                    System.out.println("(1) Load from text file\n(2) Manual input\n");
                    userInput2 = 0;
                    try {
                        userInput2 = Integer.parseInt(sc.nextLine().trim());
                        if (userInput2 == 1) {
                            System.out.print("\n\nPlease enter the file name: ");
                            fileName = sc.nextLine().trim();
                            mapJourney = loadJourneyFile(fileName);
                        } else if (userInput2 == 2) {
                            mapJourney = enterJourney();
                        }
                        if (mapJourney == null) {
                            System.out.println("ERROR: The journey is null, please try again");
                        } else {
                            System.out.println("Loaded...");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Input!");
                    }
                    break;
                case 8:
                    DSAGraph traversableGraph = createTraversableGraph(mapGraph, mapJourney);
                    findRoutes(traversableGraph, mapJourney);
                    break;
                case 9:
                    break;
                case 10:
                    if (mapGraph == null) {
                        System.out.println("ERROR: The map is null. Unable to save");
                    } else {
                        System.out.print("\n\nPlease enter the file name: ");
                        fileName = sc.nextLine().trim();
                        serialization.save(mapGraph, fileName);
                        System.out.println("Saved...");
                    }
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid Input!");
                    break;
            }
        } while (userInput != 0);
        sc.close();
    }

    public static void silentMode(String[] args) {
        //Extract the provided information from the command line arguments
        String inFile = args[1];
        String journey = args[2];
        String saveFile = args[3];

        //Load the map provided in "inFile" and assign it to a DSAGraph
        DSAGraph mapGraph = loadInputFile(inFile);
        //Load the journey from the provided file
        Journey mapJourney = loadJourneyFile(journey);
        //Calculate the routes 
        DSAGraph traversableGraph = createTraversableGraph(mapGraph, mapJourney);
        findRoutes(traversableGraph, mapJourney);

    }

    public static DSAGraph loadInputFile(String fileName) {
        //Create the graph which the information will be placed in
        DSAGraph graph = new DSAGraph();
        //String to hold each line being read
        String line = "";
        //Variables to hold all of the information from each line
        String connection, locn1 = "", locn2 = "", weight;
        //String array to hold each group after being split
        String[] splitLine = new String[4];
        //Int which describes which direction the edge is
        int direction = 2; //1 = from/to, -1 to/from, 0 = both
        
        //Open the file
        try (FileReader fr = new FileReader(fileName)) {
            BufferedReader br = new BufferedReader(fr);
            line = br.readLine();
            //Loop until EOF is reached
            while (line != null) {
                //Check if the line is a commented line, proceed if not
                if (line.charAt(0) != '#') {
                    splitLine = line.split("\\|");
                    //Extract the connection from the array, which contains both locations, and the to/from
                    connection = splitLine[0];
                    //Check which way the connection is going, and split by that token
                    if (connection.contains(">") && !connection.contains("<")) {
                        String[] tmp = connection.split(">");
                        locn1 = tmp[0];
                        locn2 = tmp[1];
                        direction = 1;
                    } else if (connection.contains("<") && !connection.contains(">")) {
                        String[] tmp = connection.split("<");
                        locn1 = tmp[0];
                        locn2 = tmp[1];
                        direction = -1; 
                    } else if (connection.contains("<>")) {
                        String[] tmp = connection.split("<>");
                        locn1 = tmp[0];
                        locn2 = tmp[1];
                        direction = 0;
                    }

                    //Extract the weight from the array
                    weight = splitLine[1];
                    weight = weight.replace("D:", "");
                    Double dist = Double.parseDouble(weight);

                    //Extract the barrier from the array
                    String temp1 = splitLine[3];
                    temp1 = temp1.replace("B:", "");
                    String[] tmpArr = temp1.split(",");
                    DSAQueue barrier = new DSAQueue();
                    for (int i = 0; i < tmpArr.length; i++) {
                        barrier.enqueue(tmpArr[i]);
                    }


                    //Extract the security from the array
                    String temp2 = splitLine[2];
                    temp2 = temp2.replace("S:", "");
                    String[] tmpArr2 = temp2.split(",");
                    DSAQueue security = new DSAQueue();
                    if (tmpArr2[0].isEmpty()) {
                        security.enqueue(0);
                    } else {
                        for (int i = 0; i < tmpArr2.length; i++) {
                            security.enqueue(tmpArr2[i]);
                        }
                    }

                    //Add the node to the graph
                    if (!graph.hasVertex(locn1)) {
                        graph.addVertex(locn1, null);
                    }
                    if (!graph.hasVertex(locn2)) {
                        graph.addVertex(locn2, null);
                    }
                    //Adds the edge depending on the direction
                    switch (direction) {
                        case 1:
                            graph.addEdge(locn1, locn2, barrier, dist, security);
                            break;
                        case -1:
                            graph.addEdge(locn2, locn1, barrier, dist, security);
                            break;
                        case 0:
                            graph.addEdge(locn1, locn2, barrier, dist, security);
                            graph.addEdge(locn2, locn1, barrier, dist, security);
                            break;
                        default:
                            break;
                    }
                }
                //Read the next line
                line = br.readLine();
            }
            //Close the file
            fr.close();
        } catch (IOException e) {
            System.out.println("ERROR: Unable to read file \"" + fileName + "\" due to: " + e.getMessage());
            graph = null;
        }
        return graph;
    }

    public static void nodeOperations(DSAGraph mapGraph) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n----Node Operations----");
        int userInput;
        do {
            System.out.println("(1) Find a node\n(2) Insert a node\n(3) Delete a node\n(4) Update a node\n(0) Exit");
            userInput = Integer.parseInt(sc.nextLine().trim());
            switch (userInput) {
                case 1:
                    operations.nodeFind(mapGraph);
                    break;
                case 2:
                    operations.nodeInsert(mapGraph);
                    break;
                case 3:
                    operations.nodeDelete(mapGraph);
                    break;
                case 4:
                    operations.nodeUpdate(mapGraph);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid Input!");
                    break;
            }
        } while (userInput != 0);
    }

    public static void edgeOperations(DSAGraph mapGraph) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n----Edge Operations----");
        int userInput;
        do {
            System.out.println("(1) Find an edge\n(2) Insert an edge\n(3) Delete an edge\n(4) Update an edge\n(0) Exit");
            userInput = Integer.parseInt(sc.nextLine().trim());
            switch (userInput) {
                case 1:
                    operations.edgeFind(mapGraph);
                    break;
                case 2:
                    operations.edgeInsert(mapGraph);
                    break;
                case 3:
                    operations.edgeDelete(mapGraph);
                    break;
                case 4:
                    operations.edgeUpdate(mapGraph);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid Input!");
                    break;
            }
        } while (userInput != 0);
    }

    public static Journey loadJourneyFile(String fileName) {
        String line = "";
        Journey mapJourney = null;
        try (FileReader fr = new FileReader(fileName)) {
            BufferedReader br = new BufferedReader(fr);
            //Skip the comments in the file -
            line = br.readLine();
            while (line.charAt(0) == '#') {
                line = br.readLine();
            }
            //Extract all of the data from the journey file
            String from = line;
            String to = br.readLine();
            String time = br.readLine();
            String barriers = br.readLine();
            String security = br.readLine();

            //Removing the comment from the line
            from = from.split("#")[0];
            to = to.split("#")[0];
            time = time.split("#")[0];
            barriers = barriers.split("#")[0];
            security = security.split("#")[0];

            //Removing the "prefix" from the line
            from = from.replace("From ", "");
            to = to.replace("To ", "");
            time = time.replace("Time ", "");
            barriers = barriers.replace("Avoid ", "");
            security = security.replace("Security ", "");

            //Removing the whitespace from the end of the line
            from = from.trim();
            to = to.trim();
            time = time.trim();
            barriers = barriers.trim();
            security = security.trim();

            int sec = 0;
            try {
                sec = Integer.parseInt(security);
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Security was not an integer");
            }

            String[] tmpArr2 = barriers.split(",");
            DSAQueue barr = new DSAQueue();
            for (int i = 0; i < tmpArr2.length; i++) {
                barr.enqueue(tmpArr2[i]);
            }
            mapJourney = new Journey(from, to, time, barr, sec);
        } catch (IOException e) {
            System.out.println("ERROR: Unable to read file \"" + fileName + "\" due to: " + e.getMessage());
        }
        return mapJourney;
    }

    public static Journey enterJourney() {
        Scanner sc = new Scanner(System.in);
        System.out.println("----Manual Journey Entry----");

        System.out.print("Starting Position: ");
        String source = sc.nextLine().trim();

        System.out.print("Destination: ");
        String destination = sc.nextLine().trim();

        System.out.print("Time: ");
        String time = sc.nextLine().trim();

        int security = 0;
        boolean exit = false;
        do {
            try {
                System.out.print("Security: ");
                security = Integer.parseInt(sc.nextLine().trim());
                if (security < 0) {
                    System.out.println("Security must be 0 or greater");
                } else {
                    exit = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Security must be an integer");
            }
        } while (exit != true);
        
        System.out.println();
        DSAQueue barrier = new DSAQueue();
        String userIn = "";
        barrier.enqueue(userIn);
        do {
            System.out.print("Barriers (Press enter after each entry, press x to exit): ");
            userIn = sc.nextLine().trim();
            if (!userIn.equals("x") && !userIn.equals("X")) {
                barrier.enqueue(userIn);
            }
        } while (!userIn.equals("x") && !userIn.equals("X"));
        Journey mapJourney = new Journey(source, destination, time, barrier, security);
        return mapJourney;
    }

    public static void parameterTweak(DSAGraph mapGraph) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the relevant information to adjust the weight mapping of the edges");
        System.out.print("\tMultiplier: ");
        double multiplier = Double.parseDouble(sc.nextLine().trim());
        System.out.print("\tParameter Unit: ");
        String unit = sc.nextLine().trim();

        DSALinkedList edges = mapGraph.getEdgeList();
        Iterator iter = edges.iterator();
        while(iter.hasNext()) {
            DSAGraph.DSAGraphEdge e = (DSAGraph.DSAGraphEdge)iter.next();
            double oldWeight = e.getWeight();
            double newWeight = oldWeight * multiplier;
            e.setWeight(newWeight);
            e.setWeightParam(unit);
        }
    }

    public static DSAGraph createTraversableGraph(DSAGraph oldGraph, Journey mapJourney) {
        //Create a new graph, which will have all non-traversable edges removed
        DSAGraph newGraph = new DSAGraph();
        newGraph = oldGraph;

        //Extract the edge list from the graph, and create an iterator
        DSALinkedList edgeList = newGraph.getEdgeList();
        Iterator edgeIter = edgeList.iterator();

        if (oldGraph != null) {
            if (mapJourney != null) {        
                //Check if the source and destination provided are valid
                if (newGraph.hasVertex(mapJourney.getFrom())) {
                    if (newGraph.hasVertex(mapJourney.getTo())) {
                        //Iterate through the edges to find the greatest weight
                        Iterator weightIter = edgeList.iterator();
                        double maxWeight = 0;
                        while (weightIter.hasNext()) {
                            DSAGraph.DSAGraphEdge currEdge = (DSAGraph.DSAGraphEdge)weightIter.next();
                            if (currEdge.getWeight() > maxWeight) {
                                maxWeight = currEdge.getWeight();                   
                            }
                        }
                        final int WEIGHTINCREASE = (int)Math.ceil(maxWeight);
                        
                        //Loop through the edgeList, removing edges with higher security and increasing the weighting of edges with barriers
                        while (edgeIter.hasNext()) {
                            //Extract the current edge from the linked list
                            DSAGraph.DSAGraphEdge currEdge = (DSAGraph.DSAGraphEdge)edgeIter.next();
                
                            //If the edge contains a barrier that should be avoided, increase the weight by WEIGHTINCREASE
                            String[] edgeBarrier = new String[currEdge.getBarrier().size()];
                            String[] journeyBarrier = new String[mapJourney.getBarrier().size()];

                            for (int i = 0; i < edgeBarrier.length; i++) {
                                String barrier = (String)currEdge.getBarrier().dequeue();
                                edgeBarrier[i] = barrier;
                                currEdge.getBarrier().enqueue(barrier);
                            }
                            for (int i = 0; i < journeyBarrier.length; i++) {
                                String barrier = (String)mapJourney.getBarrier().dequeue();
                                journeyBarrier[i] = barrier;
                                mapJourney.getBarrier().enqueue(barrier);
                            }

                            for (int i = 0; i < edgeBarrier.length; i++) {
                                for (int j = 0; j < journeyBarrier.length; j++) {
                                    if (edgeBarrier[i].equals(journeyBarrier[j])) {
                                        double oldWeight = currEdge.getWeight();
                                        currEdge.setWeight(oldWeight + WEIGHTINCREASE);
                                    }
                                }
                            }

                            //Check the security of the traveller against the edge security level. If traveller less than edge, remove edge.
                            int[] edgeSecurity = new int[currEdge.getSecurity().size()];
                            int journeySecurity = mapJourney.getSecurity();
                            boolean removeEdge = false;

                            for (int i = 0; i < edgeSecurity.length; i++) {
                                int security = Integer.parseInt(currEdge.getSecurity().dequeue().toString());
                                edgeSecurity[i] = security;
                                currEdge.getSecurity().enqueue(security);
                            }
                            for (int i = 0; i < edgeSecurity.length; i++) {
                                if (journeySecurity >= edgeSecurity[i]) {
                                    removeEdge = false;
                                } else {
                                    removeEdge = true;
                                }
                            }
                            if (removeEdge) {
                                edgeIter.remove();
                            }
                        
                        }
                        newGraph.deleteVertex("");
                        newGraph.addVertex("", WEIGHTINCREASE);
                    } else {
                        System.out.println("Destination provided in journey does not exist! Please re-enter journey details and try again");
                    }
                } else {
                    System.out.println("Source provided in journey does not exist! Please re-enter journey details and try again");
                }
            } else {
                System.out.println("The journey has not been provided, please provide a journey");
            }
        } else {
            System.out.println("The map has not been provided, please provide a map");
        }
        return newGraph;
    }

    public static void findRoutes(DSAGraph mapGraph, Journey mapJourney) {
        mapGraph.GetPaths("314.221.lab", "204.238.lab");
    }   

    public static void displayWorld(DSAGraph mapGraph, Journey mapJourney) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---World Overview---");

        if (mapGraph == null) {
            System.out.println("Sorry! A map must be provided before using this feature");
        } else {
            System.out.print("Total Vertices: ");
            System.out.println(mapGraph.getVertexCount());

            System.out.print("Total Edges: ");
            System.out.println(mapGraph.getEdgeCount());

            System.out.print("\nProvided Journey: ");
            try {
                System.out.println(mapJourney.toString());
            } catch (Exception e) {
                System.out.println("No journey provided yet");
            }
            System.out.println("\nAdjacency List Representation: ");
            mapGraph.displayAsList();
            
            System.out.println("\n\n(1) Save\n(2) Discard");
            try {
                int userChoice = Integer.parseInt(sc.nextLine().trim());
                if (userChoice == 1) {
                    try {
                        PrintWriter pw = new PrintWriter("World.txt");
                        pw.println("---World Overview---");

                        pw.print("Total Vertices: ");
                        pw.println(mapGraph.getVertexCount());

                        pw.print("Total Edges: ");
                        pw.println(mapGraph.getEdgeCount());

                        pw.print("\nProvided Journey: ");
                        try {
                            pw.println(mapJourney.toString());
                        } catch (Exception e) {
                            pw.println("No journey provided yet");
                        }
                        pw.println("\nAdjacency List Representation: ");
                              
                        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                        System.setOut(new PrintStream(buffer));

                        mapGraph.displayAsList();

                        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));

                        String content = buffer.toString();
                        buffer.reset();
                        pw.println(content);

                        pw.close();
                    } catch (IOException e2) {
                        System.out.println("Unable to save to file. Aborting...");
                    }
                } 
            } catch (NumberFormatException e1) {
                System.out.println("Invalid Input! Discarding...");
            }
        }
    }
}