import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class HeapIO {
    public static void loadFile(Q3Heap heap, String fileName)
    {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
        {
            String line = "";
            while ((line = br.readLine()) != null)
            {
                String splitLine[] = line.split(";");
                String movieLine = splitLine[0];
                String ratingLine = splitLine[1];
                String quoteLine = splitLine[2];
                String movie = "";
                double rating = 0;

                movie = movieLine.replace("Movie:", "");
                movie = movie.trim();
                try
                {
                    rating = Double.parseDouble(ratingLine.replace(" Rating: ", ""));
                } catch (NumberFormatException e)
                {
                    System.out.println("Invalid rating");
                }
                String[] quotes = quoteLine.split("\\|");
                quotes[0] = quotes[0].replace(" Quotes: ", "");
                quotes[0] = quotes[0].replace("Quotes: ", "");
                for (int i = 0; i < quotes.length; i++)
                {
                    quotes[i] = quotes[i].trim();
                }
                addEntry(heap, movie, quotes, rating);
            }
        } catch (IOException e)
        {
            System.out.println("File could not be read");
        }
    }  

    private static void addEntry(Q3Heap heap, String movie, String[] quotes, double rating)
    {
        String value = movie + ", Quotes: " + Arrays.toString(quotes);
        try
        {
            heap.add(rating, value);
        } catch (PracExamException e) {}
        System.out.println(rating + " = " + value);
    }
}
