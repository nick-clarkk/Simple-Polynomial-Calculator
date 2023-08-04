package edu.miracostacollege.cs112.finalproject.model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Objects;

public class Function implements Serializable, Comparable<Function> {

    protected double mExponent, mScalar;
    protected String mBase;

    public static final DecimalFormat thousandthsPlace = new DecimalFormat("0.000");

    public Function(String base, double exponent, double scalar) {
        mBase = base;
        mExponent = exponent;
        mScalar = scalar;
    }

    public String getBase() {
        return mBase;
    }

    public void setBase(String base) {
        mBase = base;
    }

    public double getExponent() {
        return mExponent;
    }

    public void setExponent(double exponent) {
        mExponent = exponent;
    }

    public double getScalar() {
        return mScalar;
    }

    public void setScalar(double scalar) {
        mScalar = scalar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Function function = (Function) o;
        return Double.compare(function.mExponent, mExponent) == 0 && Double.compare(function.mScalar, mScalar) == 0 && Objects.equals(mBase, function.mBase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mBase, mExponent, mScalar);
    }

    @Override
    public String toString() {
        return "Function [ " +
                "Base = " + mBase +
                ", Exponent = " + mExponent +
                ", Scalar = " + mScalar +
                ']';
    }

    @Override
    public int compareTo(Function other) {
        int exponentComp = Double.compare(this.mExponent, other.mExponent);
        if (exponentComp != 0) return exponentComp;

        return Double.compare(this.mScalar, other.mScalar);
    }
}

//DONE
