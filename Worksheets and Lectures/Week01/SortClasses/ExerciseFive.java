import java.io.*;
public class ExerciseFive 
{
    public static void main(String args[])
    {
        String fileName = "RandomNames7000.csv";
        Student[] studentArray = new Student[7000];
        studentArray = readCSV(fileName);
        sorts(studentArray);
    }

    public static Student[] readCSV(String pFileName)
    {
        Student[] studentArray = new Student[7000];
        FileInputStream fileStream = null;
        InputStreamReader isr;
        BufferedReader bufRdr;
        String line;
        try
        {
            fileStream = new FileInputStream(pFileName);
            isr = new InputStreamReader(fileStream);
            bufRdr = new BufferedReader(isr);
            line = bufRdr.readLine();
            
            for (int i = 0; i < 7000; i++)
            {
                studentArray[i] = parseLine(line);
                line = bufRdr.readLine();
            }
            fileStream.close();
        } catch (IOException errorDetails)
        {
            if (fileStream != null)
            {
                try
                {
                    fileStream.close();
                }
                catch (IOException ex2)
                { }
            }
            System.out.println("Error in processing file: " + errorDetails.getMessage());
        }
        return studentArray;
    }

    public static Student parseLine(String csvLine)
    {
        String[] splitLine;
        splitLine = csvLine.split(",");
        
        int studentID = Integer.parseInt(splitLine[0]);
        String name = splitLine[1];
        Student newStudent = new Student(name, studentID);
        return newStudent;        
    }

    public static void sorts(Student[] studentArray)
    {
        int[] bubbleSortArray = new int[7000];
        int[] insertionSortArray = new int[7000];
        int[] selectionSortArray = new int[7000];

        for (int i = 0; i < 7000; i++)
        {
            bubbleSortArray[i] = studentArray[i].getStudentID();
            insertionSortArray[i] = studentArray[i].getStudentID();
            selectionSortArray[i] = studentArray[i].getStudentID();
        }
        Sorts.bubbleSort(bubbleSortArray);
        Sorts.insertionSort(insertionSortArray);
        Sorts.selectionSort(selectionSortArray);

        printSort(bubbleSortArray, insertionSortArray, selectionSortArray);
    }

    public static void printSort(int[] bubbleSortArray, int[] insertionSortArray, int[] selectionSortArray)
    {
        try 
        {
            FileWriter fw = new FileWriter("sortedArray.csv");
            PrintWriter out = new PrintWriter(fw);
            out.print("Bubble");
            out.print(",");
            out.print("Insertion");
            out.print(",");
            out.print("Selection");
            out.print("\n");

            for (int i = 0; i < 7000; i++)
            {
                out.print(bubbleSortArray[i]);
                out.print(",");
                out.print(insertionSortArray[i]);
                out.print(",");
                out.print(selectionSortArray[i]);
                out.print(",");
                out.print("\n");
            }
            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
