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
        int size = A.length;
        do 
        {
            swap = false;
            for (int i = 0; i < size - 1; i++) 
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
    }// selectionSort()

    // insertion sort
    public static void insertionSort(int[] A)
    {
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
