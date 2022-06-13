/**
 * DSA Final Assessment Question 3 - Q3Heap.java
 *
 * Name : Samuel Wyatt
 * ID   : 20555535
 *
 **/
 
public class Q3Heap
{
	public class HeapEntry
	{
		private double priority;
		private Object value;

		public HeapEntry(double priority, Object value)
		{
			this.priority = priority;
			this.value = value;
		}

		public double getPriority()
		{
			return priority;
		}

		public Object getValue()
		{
			return value;
		}

		public String toString()
		{
			return "Value: " + value + " | Priority: " + priority;
		}
	}
	private HeapEntry[] heap;
	private int count;
	private int MAXSIZE = 10;
	
	
	public Q3Heap()
	{
		heap = new HeapEntry[MAXSIZE];
		count = 0;	
	}


	public void add(double priority, Object value) throws PracExamException
	{
		if (priority >= 0)
		{
			HeapEntry entry = new HeapEntry(priority, value);
			heap[count] = entry;
			count++;
			trickleUp(count-1);
		} else 
		{
			throw new PracExamException("Priority must be greater than zero");
		}
	}

	public Object remove() throws PracExamException 
	{
		Object retValue;
		if (count == 0) 
		{
			throw new PracExamException("Heap is already empty");
		} else 
		{
			HeapEntry entry = heap[0];
			retValue = entry.getValue();
			heap[0] = heap[count-1];
			heap[count-1] = null;
			count--;
			trickleDown(0);
		}
		return retValue;
	}

	private void trickleDown(int index)
	{
	   int leftIdx = index * 2 + 1;
	   int rightIdx = leftIdx + 1;
	   int largeIdx;
	   HeapEntry temp;

	   if (leftIdx < count)
		{
		largeIdx = leftIdx;			
		if (rightIdx < count)
		{
		   if (heap[leftIdx].getPriority() < heap[rightIdx].getPriority())
			{
				largeIdx = rightIdx;
			}
		}
		if (heap[largeIdx].getPriority() > heap[index].getPriority())
		{
              	temp = heap[largeIdx];
              	heap[largeIdx] = heap[index];
              	heap[index] = temp;
			trickleDown(largeIdx);
		}	
	   }
	}
	
	private void trickleUp(int index)
	{
		int parentIndex;
		HeapEntry temp;

		parentIndex = (index-1)/2;

		if (index > 0 )
		{
			if (heap[index].getPriority() > heap[parentIndex].getPriority())
			{
				temp = heap[parentIndex];
				heap[parentIndex] = heap[index];
				heap[index] = temp;
				trickleUp(parentIndex);
			}
		}
		
	}

	public boolean isEmpty()
	{
		return count == 0;
	}
}