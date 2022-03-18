/** 
** Software Technology 152
** Class to hold various static sort methods.
*/
class Sorts
{
    // bubble sort
    public static void bubbleSort(int[] A)
    {
        boolean swap;
        int s = A.length;
        do 
        {
            swap = false;
            for (int i = 0; i < s - 1; i++) 
            { 
                if (A[i] > A[i + 1]) 
                {
                    int temp = A[i + 1];
                    A[i + 1] = A[i];
                    A[i] = temp;
                    swap = true;
                }
            }
        } while (swap);
    }//bubbleSort()

    // selection sort
    public static void selectionSort(int[] A)
    {
        int s = A.length;
        for (int i = 0; i < s - 1; i++)  
        {
            int minValue = A[i];
            int minIndex = i;
            for (int j = i + 1; j < s; j++)
            {
                if (A[j] < minValue)
                {
                    minValue = A[j];
                    minIndex = j;
                }
            }
            if (minValue < A[i])
            {
                int temp = A[i];
                A[i] = A[minIndex];
                A[minIndex] = temp;            
            }
        }
    }// selectionSort()

    // insertion sort
    public static void insertionSort(int[] A)
    {
        int s = A.length;
        for (int i = 1; i < s; i++)
        {
            int hold = A[i];
            int j = i - 1;
            while ((j > -1) && (A[j] > hold))
            {
                int temp = A[j];
                A[j] = A[j + 1];
                A[j + 1] = temp;
                j--;
            }
        }
    }// insertionSort()

    // mergeSort - front-end for kick-starting the recursive algorithm
    public static void mergeSort(int[] A)
    {
    }//mergeSort()
    private static void mergeSortRecurse(int[] A, int leftIdx, int rightIdx)
    {
    }//mergeSortRecurse()
    private static void merge(int[] A, int leftIdx, int midIdx, int rightIdx)
    {
    }//merge()


    // quickSort - front-end for kick-starting the recursive algorithm
    public static void quickSort(int[] A)
    {
    }//quickSort()
    private static void quickSortRecurse(int[] A, int leftIdx, int rightIdx)
    {
    }//quickSortRecurse()
    private static int doPartitioning(int[] A, int leftIdx, int rightIdx, int pivotIdx)
    {
		return 0;	// TEMP - Replace this when you implement QuickSort
    }//doPartitioning


}//end Sorts calss
