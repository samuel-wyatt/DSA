import java.io.Serializable;

import java.io.*;
public class serialization implements Serializable {   
    public static void save(DSAGraph objToSave, String fileName) {
        FileOutputStream fileStrm;
        ObjectOutputStream objStrm;
        try {
            fileStrm = new FileOutputStream(fileName);
            objStrm = new ObjectOutputStream(fileStrm);
            objStrm.writeObject(objToSave);
            objStrm.close();

        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to save object to file : " + e.getMessage());
        }
    }

    public static DSAGraph load(String fileName) throws IllegalArgumentException {
        DSAGraph inObj = null;
        FileInputStream fileStrm;
        ObjectInputStream objStrm;
        try {
            fileStrm = new FileInputStream(fileName);
            objStrm = new ObjectInputStream(fileStrm);
            inObj = (DSAGraph)objStrm.readObject();
            objStrm.close();
            
        } catch (ClassNotFoundException e) {
            System.out.println("Class DSAGraph not found" + e.getMessage());
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to load object from file.");
        }
        return inObj;
    }
}
