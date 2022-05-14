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

        DSAGraph graph = createGraph(content);
        graph.displayAsList();

    }

    public static DSAGraph createGraph(String[] content) {
        DSAGraph g = new DSAGraph();
        for (int i = 0; i < content.length; i++) {
            
            String temp[] = new String[2];
            temp = content[i].split(" ");
            
            String src = temp[0];
            String dest = temp[1];
            
            if (!g.hasVertex(src)) {
                g.addVertex(src, null);
            }
            if (!g.hasVertex(dest)) {
                g.addVertex(dest, null);
            }

            g.addEdge(src, dest);
        }
        return g;
    }
}
