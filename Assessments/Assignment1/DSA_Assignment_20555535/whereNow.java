import java.util.*;
import java.io.*;
import java.nio.file.attribute.UserPrincipalNotFoundException;
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
        System.out.println("------Interactive Mode------");
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
        DSAGraph mapGraph = null;;
        int userInput;
        //Do 
        do {
            menu();
            System.out.print("\n");
            userInput = Integer.parseInt(sc.nextLine().trim());
            switch (userInput) {
                case 1:
                    System.out.print("\n\nPlease enter the file name: ");
                    String fileName = sc.nextLine().trim();
                    mapGraph = loadInputFile(fileName);
                    if (mapGraph == null) {
                        System.out.println("ERROR: The map is null, no input has been recieved");
                    }
                    break;
                case 2:
                    
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    
                    break;
                case 6:
                    break;
                case 7:
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
                    }
                    break;
                default:
                    System.out.println("Invalid Input!");
                    break;
            }
        } while (userInput != 0);
    }

    public static void silentMode(String[] args) {
        //Extract the provided information from the command line arguments
        String inFile = args[1];
        String journey = args[2];
        String saveFile = args[3];




    }

    public static DSAGraph loadInputFile(String fileName) {
        //Create the graph which the information will be placed in
        DSAGraph graph = new DSAGraph();
        //String to hold each line being read
        String line = "";
        //Variables to hold all of the information from each line
        String connection, locn1, locn2, distance, sec1, sec2, barrier;
        //String array to hold each group after being split
        String[] splitLine = new String[4];
        //Int which describes which direction the edge is
        int direction; //1 = from/to, -1 to/from, 0 = both
        
        //Open the file
        try (FileReader fr = new FileReader(fileName)) {
            BufferedReader br = new BufferedReader(fr);
            //Loop until EOF is reached
            while (line != null) {
                //Read a line from the file
                line = br.readLine();
                //Check if the line is a commented line, proceed if not
                if (line.charAt(0) != '#') {
                    splitLine = line.split("|");
                    //Extract the connection from the array, which contains both locations, and the to/from
                    connection = splitLine[0];
                    //Check which way the connection is going, and split by that token
                    if (connection.contains(">")) {
                        String[] tmp = connection.split(">");
                        locn1 = tmp[0];
                        locn2 = tmp[1];
                        direction = 1;
                    } else if (connection.contains("<")) {
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

                    //Extract the distance from the array
                    distance = splitLine[1];
                    distance.replace("D:", "");

                    //Extract the barrier from the array
                    barrier = splitLine[3];
                    barrier.replace("B:", "");

                    //Extract the security from the array
                    String temp = splitLine[2];
                    temp.replace("S:", "");
                    String[] tmpArr = temp.split(",");
                    sec1 = tmpArr[0];
                    sec2 = tmpArr[1];

                    //Add the node to the graph
                    
                }
            }
            //Close the file
            fr.close();
        } catch (IOException e) {
            System.out.println("ERROR: Unable to read file \"" + fileName + "\" due to: " + e.getMessage());
        }
        return graph;
    }

    public static void nodeOperations(DSAGraph graph) {
        System.out.println("\n\n(1) Find\n(2) Insert\n(3) Delete\n(4) Update\n(0) Go Back");
        Scanner sc = new Scanner(System.in);
        int userInput;
        do {
            userInput = Integer.parseInt(sc.nextLine().trim());

            switch (userInput) {
                //Find operations
                case 1:
                    System.out.print("Please enter the label: ");
                    String label = sc.nextLine().trim();
                    boolean hasVertex = graph.hasVertex(label);
                    if (hasVertex) {
                        System.out.println("The node was found\nWould you like to make any additional operations to this node?\n[y/n]: ");
                        char yesNo = sc.nextLine().charAt(0);
                        if (yesNo == 'y') { 

                        } else 

                    } else {
                        System.out.println("The node was not found");
                    }
                    break;
                //Insert operations
                case 2:
                    break;
                //Delete operations
                case 3:
                    break;
                //Update operations
                case 4:
                    break;
                default:
                    System.out.println("Invalid Input!");
            }
        } while (userInput != 0);
    }

    public static void edgeOperations(DSAGraph graph) {

    }
}