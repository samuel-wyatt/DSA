/**
 * DSA Final Assessment Question 5 - Q5Graph.java                             4
 *
 * Name : Samuel Wyatt
 * ID   : 20555535
 *
 **/
public class Q5Graph 
{
    //Created a private inner class for the nodes, so that colour could be stored with the label
    private class Q5GraphNode
    {
        private String label;
        private String colour;

        Q5GraphNode(String inLabel, String inColour)
        {
            label = inLabel;
            colour = inColour;
        }

        //Accessors
        public String getLabel() {
            return label;
        }
        public String getColour() {
            return colour;
        }

        public String toString()
        {
            return label + " (" + colour + ")";
        }
    }
    private int maxsize;
    private int wmatrix[][];
    private Object labels[];
    private int vertexCount;

    public Q5Graph() 
    {
        maxsize = 20;
        wmatrix = new int[maxsize][maxsize];
        labels = new Object[maxsize];
        for(int i=0; i < maxsize; i++)
        {
            for (int j=0; j< maxsize; j++)
            { 
                wmatrix[i][j] = 0;
            }
        }
        vertexCount = 0;
    }

    public void addVertex(String label, String colour)
    {
        if (vertexCount == maxsize)
	    {
		    // do nothing, but should throw exception
	    }
	    else if (!(hasVertex(label))) 
        {
            Q5GraphNode newNode = new Q5GraphNode(label, colour);
            labels[vertexCount] = (Object)newNode;
            vertexCount++;
        }
    }

    public void addEdge(String label1, String label2, int weight)
    {
        int v1, v2;

        v1 = getIndex(label1); 
        v2 = getIndex(label2);   

        wmatrix[v1][v2] = weight;
    }

    public boolean hasVertex(String label) 
    {
        boolean has = false;
        for (int i=0; i < vertexCount; i++) 
        {
           if (((Q5GraphNode)labels[i]).getLabel().equals(label))
                has = true;
        }
        return has;
    }

    public int getIndex(String label) 
    {
        int theVertex = -1;
        for (int i=0; i < vertexCount; i++) 
            {
            if (((Q5GraphNode)labels[i]).getLabel().equals(label))
                theVertex = i;
            }
	    return theVertex;    
	}

    public void displayAsList() 
    {
		System.out.println("Adjacency List display (stub)");
    }

	public void displayColourMatrix(String colour)
    {
        System.out.println("\nWeight matrix for graph[" + colour + "] is: \n");
        System.out.print("    ");
        String divider = "";
        for (int i = 0; i < vertexCount; i++)
        {
            Q5GraphNode tempNode = (Q5GraphNode)labels[i];
            System.out.print(tempNode.getLabel() + "  ");
            divider += "---";
        }
        System.out.println("\n---" + divider);
        for (int i = 0; i < vertexCount; i++)
        {
            Q5GraphNode tempNode = (Q5GraphNode)labels[i];
            System.out.print(tempNode.getLabel() + " | ");
            for (int j = 0; j < vertexCount; j++)
            {
                System.out.print(wmatrix[i][j] + "  ");
            }
            System.out.println();
        }
    }

    public void displayColourList(String colour)
    {
        System.out.println("\nAdjacency list for graph[" + colour + "] is: ");
        for (int i = 0; i < vertexCount; i++)
        {
            Q5GraphNode tempNode = (Q5GraphNode)labels[i];
            if (tempNode.getColour().equals(colour))
            {
                //Print the source node
                System.out.print("\n\t" + tempNode.getLabel() + " | ");
                //For each connection, we will print it if it's colour is the same
                for (int j = 0; j < vertexCount; j++)
                {
                    Q5GraphNode tempNode2 = (Q5GraphNode)labels[j];
                    //Checks if the current node in the adjacency matrix is the correct colour AND not the same node as the source node
                    if (tempNode2.getColour().equals(colour) && !tempNode2.getLabel().equals(tempNode.getLabel()) && wmatrix[i][j] != 0)
                    {
                        System.out.print(tempNode2.getLabel() + "(" + wmatrix[i][j] + "), ");
                    }
                }
            }
        }
    }
}  
