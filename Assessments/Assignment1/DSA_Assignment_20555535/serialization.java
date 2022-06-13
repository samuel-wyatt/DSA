/**********************************************************
 * Author: Samuel Wyatt (20555535)                        *
 * Date: 09/04/2022                                       *
 * File Name: serialization                               *
 * Purpose: To create a class to save and load objects    *
 * IMPORTANT: This code has been re-used from Practical 4,*
 * uploaded to blackboard on 09/04/2022                   *
 **********************************************************/
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
