import java.io.*;
import java.util.*;
public class DSAGraphIO {
    public static void main(String[] args) {
        String fileName;
        int i = 0, count = 0;

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the file name: ");
        fileName = sc.nextLine().trim();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                count++;
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        String[] content = new String[count];

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                content[i] = line;
                i++;
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        createGraph(content);
    }

    public static void createGraph(String[] content) {
        
    }
}
