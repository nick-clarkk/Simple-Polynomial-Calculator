package edu.miracostacollege.cs112.finalproject.controller;

import edu.miracostacollege.cs112.finalproject.model.Derivative;
import edu.miracostacollege.cs112.finalproject.model.Integral;
import edu.miracostacollege.cs112.finalproject.model.Model;
import edu.miracostacollege.cs112.finalproject.model.Function;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Controller {

    private static Controller theInstance;

    private ObservableList<Function> mAllFunctionsList;

    private ObservableList<Function> mFilteredFunctionList;

    private Controller() {}

    public static Controller getInstance() {
        if (theInstance == null) {
            theInstance = new Controller();

            theInstance.mAllFunctionsList = Model.populateListFromBinaryFile();

            FXCollections.sort(theInstance.mAllFunctionsList);
        }
        return theInstance;
    }

    public ObservableList<Function> getAllFunctions() {
        return mAllFunctionsList;}

    public void addFunction(Function function) {
        mAllFunctionsList.add(function);
        FXCollections.sort(mAllFunctionsList);

    }

    public void addDerivative(Derivative derivative) {
        mAllFunctionsList.add(derivative);
        FXCollections.sort(mAllFunctionsList);

    }

    public void addIntegral(Integral integral) {
        mAllFunctionsList.add(integral);
        FXCollections.sort(mAllFunctionsList);
    }

    public void saveData() {
        Model.writeDataToBinaryFile(mAllFunctionsList);
    }

}

//DONE