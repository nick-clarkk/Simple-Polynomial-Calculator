package edu.miracostacollege.cs112.finalproject.model;

import java.io.Serializable;

public class Integral extends Function implements Serializable {

    double mLowerBound, mUpperBound;

    public Integral(Function f, double lower, double upper) {
        super(f.mBase, f.mExponent, f.mScalar);
        mLowerBound = lower;
        mUpperBound = upper;
    }

}
