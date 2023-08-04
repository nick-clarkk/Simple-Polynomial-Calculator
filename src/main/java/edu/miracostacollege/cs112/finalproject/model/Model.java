package edu.miracostacollege.cs112.finalproject.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

public class Model {
    public static final String BINARY_FILE = "DerivativesAndIntegrals.dat";
    public static boolean binaryFileHasData()
    {
        File binaryFile = new File(BINARY_FILE);
        return (binaryFile.exists() && binaryFile.length() > 5L);
    }

    public static ObservableList<Function> populateListFromBinaryFile()
    {
        ObservableList<Function> allFunctions = FXCollections.observableArrayList();
        if (binaryFileHasData()) {
        File binaryFile = new File(BINARY_FILE);

            try {
                ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(binaryFile));
                Function[] tempArray = (Function[]) fileReader.readObject();
                allFunctions.addAll(tempArray);
                fileReader.close();
            } catch (Exception e) {
                System.err.println("Error opening file: " + BINARY_FILE + " for reading.\nCaused by: " + e.getMessage());
            }

        }
        return allFunctions;
    }

    public static boolean writeDataToBinaryFile(ObservableList<Function> allFunctionsList)
    {
        if (allFunctionsList.size() == 0)
            return false;

        File binaryFile = new File(BINARY_FILE);
        try {
            ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(binaryFile));
            Function[] tempArray = new Function[allFunctionsList.size()];
            allFunctionsList.toArray(tempArray);
            fileWriter.writeObject(tempArray);
            fileWriter.close();
            return true;
        }
        catch (Exception e)
        {
            System.err.println("Error writing binary file: " + BINARY_FILE + "\n" + e.getMessage());
            return false;
        }
    }
}

//DONE