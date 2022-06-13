/**
 * DSA Final Assessment Question 3 - Q3HeapTest.java
 *
 * Name : Samuel Wyatt	
 * ID   : 20555535
 *
 **/
 
public class HeapTest
{
	public static void main(String args[])
	{
		System.out.println("\n##### Question 3: Testing Heaps #####\n");
		
		Q3Heap  testHeap = new Q3Heap();
		
		//Testing adding items
        System.out.println("Adding items...");
		for (int i=0; i<10; i++)
		{
			try {
				testHeap.add(i, "value-"+ Integer.toString(i));
			} catch (PracExamException e)
			{
				System.out.println(e.getMessage());
			}
			System.out.println("Added " + "value-"+Integer.toString(i)+" Priority: " + Integer.toString(i));
		}
		
		//Testing removing items
        System.out.println();
        System.out.println("Removing items...");
		String temp;
		
		for (int i=0; i<10; i++)
		{
			try 
			{
				temp = (String)testHeap.remove();
				System.out.println(temp);
			} catch (PracExamException e)
			{
				System.out.println(e.getMessage());
			}
		}

		//Testing exceptions
		//Exception 1 is heap is already empty, and attempt to remove 
		System.out.print("\nTesting remove on empty: ");
		try 
		{
			testHeap.remove();
			System.out.println("failed");
		} catch (PracExamException e)
		{
			System.out.println("passed");
		}
		//Exception 2 is value is added with a negative priority
		System.out.print("Testing negative priority: ");
		try{
			testHeap.add(-1, "Negative");
			System.out.println("failed");
		} catch (PracExamException e)
		{
			System.out.println("passed");
		}

		//Testing the file entry
		System.out.println("Testing file entry: \nAdding titles...");
		HeapIO.loadFile(testHeap, "movies.txt");
		//Now, removing the entries, printing as we go
		System.out.println("\nRemoving titles...");
		while (!testHeap.isEmpty())
		{
			String output = "";
			try
			{
				output = testHeap.remove().toString();
			} catch (PracExamException e) {}
			System.out.println(output);
		}
		System.out.println("\n##### Tests Complete #####\n");
	}
}