import java.io.*;
/**
 * DSA Final Assessment Question 5 - GraphTest.java
 *
 * Name : Samuel Wyatt
 * ID   : 20555535
 *
 **/
public class GraphTest
{
  //Code should be run with the -ea flag before the file name to enable assertions
	public static void main(String args[])
	{
			System.out.println("\n##### Question 5: Testing Graphs #####\n");

			//Loading the graph from the text file with a valid nam
			Q5Graph g = Q5GraphIO.loadFile("GraphData.txt");

			//Testing that every vertex was received from the file
			testVertices(g);

			//Testing that displayColourMatrix has output correctly
			testDisplayColourMatrix(g);

			//Testing that displayColourList has output correctly
			testDisplayColourList(g);

			
			System.out.println("\n##### Tests Complete #####\n");
	}

	private static void testDisplayColourList(Q5Graph g) 
	{
		System.out.print("Testing displayColourList(): ");
		//Capture the output 
		PrintStream oldOutput = System.out;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PrintStream print = new PrintStream(out);
		System.setOut(print);

		g.displayColourList("red");
		String redOutput = out.toString().trim();
		redOutput = redOutput.replace(" ", "");
		redOutput = redOutput.replace("\t", "");
		redOutput = redOutput.replace("\n", "");
		String expectedRedOutput = "Adjacencylistforgraph[red]is:B|C(4),E(3),C|E(5),E|B(3),C(5),";

		out.reset();

		g.displayColourList("black");
		String blackOutput = out.toString().trim();
		blackOutput = blackOutput.replace(" ", "");
		blackOutput = blackOutput.replace("\t", "");
		blackOutput = blackOutput.replace("\n", "");
		String expectedBlackOutput = "Adjacencylistforgraph[black]is:A|D(3),D|A(3),F(8),F|D(8),";

		//Restore the output
		System.out.flush();
		System.setOut(oldOutput);

		try 
		{
			assert redOutput.equals(expectedRedOutput) : "failed red output";
			assert blackOutput.equals(expectedBlackOutput) : "failed black output";
			System.out.println("passed");
		} catch (AssertionError e)
		{
			System.out.println(e.getMessage());
		}
	}

	private static void testDisplayColourMatrix(Q5Graph g) 
	{
		System.out.print("Testing displayColourMatrix(): ");
		//Capture the output 
		PrintStream oldOutput = System.out;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PrintStream print = new PrintStream(out);
		System.setOut(print);

		g.displayColourMatrix("red");
		String redOutput = out.toString().trim();
		redOutput = redOutput.replace(" ", "");
		redOutput = redOutput.replace("\t", "");
		redOutput = redOutput.replace("\n", "");
		String expectedRedOutput = "Weightmatrixforgraph[red]is:ABCDEF---------------------A|048350B|004535C|800053D|350048E|535404F|053840";

		out.reset();

		g.displayColourMatrix("black");
		String blackOutput = out.toString().trim();
		blackOutput = blackOutput.replace(" ", "");
		blackOutput = blackOutput.replace("\t", "");
		blackOutput = blackOutput.replace("\n", "");
		String expectedBlackOutput = "Weightmatrixforgraph[black]is:ABCDEF---------------------A|048350B|004535C|800053D|350048E|535404F|053840";

		//Restore the output
		System.out.flush();
		System.setOut(oldOutput);

		try 
		{
			assert redOutput.equals(expectedRedOutput) : "failed red output";
			assert blackOutput.equals(expectedBlackOutput) : "failed black output";
			System.out.println("passed");
		} catch (AssertionError e)
		{
			System.out.println(e.getMessage());
		}
	}

	public static void testVertices(Q5Graph g)
	{
		System.out.print("File IO has recieved all vertices: ");
		try {
			assert g.hasVertex("A") == true: "failed, A does not exist";
			assert g.hasVertex("B") == true: "failed, B does not exist";
			assert g.hasVertex("C") == true: "failed, C does not exist";
			assert g.hasVertex("D") == true: "failed, D does not exist";
			assert g.hasVertex("E") == true: "failed, E does not exist";
			assert g.hasVertex("F") == true: "failed, F does not exist";
			System.out.println("passed");
		} catch (AssertionError e)
		{
			System.out.println(e.getMessage());
		}	
	}
}
