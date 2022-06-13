/**
 * DSA Final Assessment Question 1 - Q2TreeTest.java
 *
 * Name : Samuel Wyatt
 * ID   : 20555535
 *
 **/
public class TreeTest
{
	public static void main(String args[])
	{
		System.out.println("\n##### Question 2: Testing Trees #####\n");
        Q2BSTree t = new Q2BSTree();
		
		//Testing node insertion
		System.out.print("Testing insert(): ");
		try
		{
			t.insert(100);
			t.insert(15);
			t.insert(20);
			t.insert(25);
			t.insert(50);
			t.insert(55);
			t.insert(60);
			t.insert(65);
			t.insert(30);
			t.insert(35);
			t.insert(40);
			t.insert(45);
			t.insert(70);
			System.out.println("passed");
		} catch (Exception e)
		{
			System.out.println("failed");
		}

		//Testing finding a node
		System.out.print("Testing getNode(): ");
		try
		{
			Q2BSTree.Q2TreeNode node = t.getNode(70);
			if (node != null)
			{
				System.out.println("passed");
			} else 
			{
				System.out.println("failed");
			}
		} catch (Exception e)
		{
			System.out.println("failed");
		}

		//Testing if height is correct
		System.out.print("Testing height: ");
		try
		{
			Q2BSTree.Q2TreeNode node = t.getNode(70);
			if (node.getHeight() == 8)
			{
				System.out.println("passed");
			} else 
			{
				System.out.println("failed");
			}
		} catch (Exception e)
		{
			System.out.println("failed");
		}


		System.out.println("\n##### Tests Complete #####\n");

	}
	
}
