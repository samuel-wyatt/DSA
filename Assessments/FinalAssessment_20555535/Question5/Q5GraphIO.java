/**
 * DSA Final Assessment Question 5 - Q5GraphIO.java                             4
 *
 * Name : Samuel Wyatt
 * ID   : 20555535
 *
 **/
import java.io.*;
public class Q5GraphIO 
{
    public static Q5Graph loadFile(String fileName)
    {
        Q5Graph newGraph = new Q5Graph();
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
        {
            while ((line = br.readLine()) != null)
            {
                if (line.charAt(1) == '-')
                {
                    String[] lineSplit = line.split("-");
                    String label = lineSplit[0];
                    String colour = lineSplit[1];
                    newGraph.addVertex(label, colour);
                } else 
                {
                    String locn1 = "", locn2 = "";
                    int direction = 2, weight = 0;
                    String[] lineSplit = line.split("-");
                    String connection = lineSplit[0];
                    try
                    {
                        weight = Integer.parseInt(lineSplit[1]);
                    } catch (NumberFormatException e)
                    {
                        System.out.println("Weight was not an integer");
                    }
                    //The following code has been used from the DSA Assignment. This code is my own work.
                    //Submitted to blackboard on 29/05/2022
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

                    switch (direction) {
                        case 1:
                            newGraph.addEdge(locn1, locn2, weight);
                            break;
                        case -1:
                            newGraph.addEdge(locn2, locn1, weight);
                            break;
                        case 0:
                            newGraph.addEdge(locn1, locn2, weight);
                            newGraph.addEdge(locn2, locn1, weight);
                            break;
                        default:
                            break;
                    }
                    //End of re-used code. This code was re-used due to its identical purpose for adding vertex's
                    //to directed graphs from a file. It would be unnecessary to re-write this code.
                }
            }
            br.close();
        } catch (IOException e)
        {
            System.out.println("Unable to read file \"" + fileName + "\"");
            newGraph = null;
        }
        return newGraph;
    }
}
