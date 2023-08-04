package edu.miracostacollege.cs112.finalproject.model;

import java.io.Serializable;

public class Derivative extends Function implements Serializable {

    double mPoint;

    public Derivative(Function f, double point) {
        super(f.mBase, f.mExponent, f.mScalar);
        mPoint = point;
    }


}
