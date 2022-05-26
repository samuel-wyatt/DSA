import java.util.*;
import java.io.*;
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
        int userInput;
        //Do 
        do {
            menu();
            System.out.print("\n");
            userInput = Integer.parseInt(sc.nextLine().trim());
            String fileName;
            switch (userInput) {
                case 1:
                    //Check how the user wishes to load the map
                    System.out.println("(1) Load from text file\n(2) Load from serialized file\n");
                    int userInput2 = Integer.parseInt(sc.nextLine().trim());

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
                    }else {
                        System.out.println("Invalid Input!");
                    }

                    //Check if the map has successfully been loaded
                    if (mapGraph == null) {
                        System.out.println("ERROR: The map is null, no input has been recieved");
                    } else {
                        System.out.println("Loaded...");
                    }
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    parameterTweak(mapGraph);
                    break;
                case 5:
                    break;
                case 6:
                    mapGraph.displayAsList();
                    break;
                case 7:
                    System.out.print("\n\nPlease enter the file name: ");
                    fileName = sc.nextLine().trim();
                    mapJourney = loadJourneyFile(fileName);
                    if (mapJourney == null) {
                        System.out.println("ERROR: The journey is null, please try again");
                    } else {
                        System.out.println("Loaded...");
                    }
                    break;
                case 8:
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
        DSAGraph graph = loadInputFile(inFile);
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
                    for (int i = 0; i < tmpArr2.length; i++) {
                        security.enqueue(tmpArr2[i]);
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
        }
        return graph;
    }

    public static void nodeOperations(DSAGraph graph) {
        
    }

    public static void edgeOperations(DSAGraph graph) {

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

            String[] tmpArr = security.split(",");
            DSAQueue barr = new DSAQueue();
            for (int i = 0; i < tmpArr.length; i++) {
                barr.enqueue(tmpArr[i]);
            }

            String[] tmpArr2 = barriers.split(",");
            DSAQueue sec = new DSAQueue();
            for (int i = 0; i < tmpArr2.length; i++) {
                sec.enqueue(tmpArr2[i]);
            }
            mapJourney = new Journey(from, to, time, barr, sec);
        } catch (IOException e) {
            System.out.println("ERROR: Unable to read file \"" + fileName + "\" due to: " + e.getMessage());
        }
        return mapJourney;
    }

    public static void enterJourney(String from, String to, String time, DSAQueue barrier, DSAQueue security) {
        System.out.println(from + " + " + to + " + " + time + " + " + barrier.toString() + " + " + security.toString());
    }

    public static void parameterTweak(DSAGraph mapGraph) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the relevant information to adjust the weight mapping of the edges");
        System.out.print("\tMultiplier: ");
        double multi = Double.parseDouble(sc.nextLine().trim());
        System.out.print("\tParameter Unit: ");
        String unit = sc.nextLine().trim();

        DSALinkedList edges = mapGraph.getEdgeList();
        Iterator iter = edges.iterator();
        while(iter.hasNext()) {
            DSAGraph.DSAGraphEdge e = (DSAGraph.DSAGraphEdge)iter.next();
            double oldWeight = e.getWeight();
            double newWeight = oldWeight * multi;
            e.setWeight(newWeight);
            e.setWeightParam(unit);
        }
    }
}