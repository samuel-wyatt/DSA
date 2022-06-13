/**
 * DSA Final Assessment Question 2 - Q2BinarySearchTree.java
 *
 * Name : Samuel Wyatt
 * ID   : 20555535
 *
 **/

public class Q2BSTree {   
	// Inner class Q2TreeNode
	public class Q2TreeNode {
		private int value;
		private Q2TreeNode left;
		private Q2TreeNode right;
		private int height;
		
		public Q2TreeNode(int inVal)
		{
			value = inVal;
			left = null;
			right = null;
			height = -1;
		}

		//Accessors
		public int getHeight() {
			return height;
		}
		public Q2TreeNode getLeft() {
			return left;
		}
		public Q2TreeNode getRight() {
			return right;
		}
		public int getValue() {
			return value;
		}

		//Mutators
		public void setHeight(int height) {
			this.height = height;
		}
		public void setLeft(Q2TreeNode left) {
			this.left = left;
		}
		public void setRight(Q2TreeNode right) {
			this.right = right;
		}
		public void setValue(int value) {
			this.value = value;
		}
	}
	// End Inner class
	// Class Q2BSTree
	private Q2TreeNode root;
	
	public Q2BSTree()
	{
		root = null;
	}
	
	public void insert(int val)
	{
		if (isEmpty())
		{
			root = new Q2TreeNode(val);
			root.setHeight(0);
		}
		else
		{
			root = insertRec(val, root);
			genTreeHeights(root);
		}
	}

	public Boolean isEmpty()
	{
		return root == null;
	}

	private Q2TreeNode insertRec(int inVal, Q2TreeNode cur)
	{
		if (cur == null)
		{
			cur = new Q2TreeNode(inVal);
		}
		else
		{
			if (inVal < cur.getValue())
			{
				cur.setLeft(insertRec(inVal, cur.getLeft()));
			}
			else	
			{
				cur.setRight(insertRec(inVal, cur.getRight()));
			}
		}
		return cur;
	}
	
	private void genTreeHeights(Q2TreeNode currNd) 
	{
		genTreeHeightsRec(root, -1);
	}

	private void genTreeHeightsRec(Q2TreeNode currNd, int height)
	{
		height++;
		if (currNd != null)
		{
			currNd.setHeight(height);
			genTreeHeightsRec(currNd.getLeft(), height);
			genTreeHeightsRec(currNd.getRight(), height);
		}
		height--;
	}

	public Q2TreeNode getNode(int inVal) throws PracExamException
	{
		return getNodeRec(inVal, root);
	}

	//This method was written to help with testing, so I could inspect a node to see if height was calculated correctly.
	//In doing this, I had to make Q2TreeNode a public inner class, set its variables to private, and create accessors and mutators.
	private Q2TreeNode getNodeRec(int inVal, Q2TreeNode currNd) throws PracExamException
	{
		Q2TreeNode returnNode = null;
		if (currNd == null)
		{
			throw new PracExamException("Key could not be found");
		} else if (currNd.getValue() == inVal )
		{
			returnNode = currNd;
		} else if (inVal < currNd.getValue())
		{
			returnNode = getNodeRec(inVal, currNd.getLeft());
		} else 
		{
			returnNode = getNodeRec(inVal, currNd.getRight());
		}
		return returnNode;
	}
}