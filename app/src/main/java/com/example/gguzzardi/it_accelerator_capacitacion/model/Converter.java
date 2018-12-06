package com.example.gguzzardi.it_accelerator_capacitacion.model;

public class Converter {

    private static final double MILES_TO_KILOMETERS_MULTIPLIER = 1.60934;

    private double mKilometers;
    private double mMiles;

    public interface ConverterListener {
        void convertValue();
        void onValueTyped(double value);
    }

    public void convert() {
        mKilometers = mMiles * MILES_TO_KILOMETERS_MULTIPLIER;
    }

    public double getKilometers() {
        return mKilometers;
    }

    public void setMiles(double mMiles) {
        this.mMiles = mMiles;
    }
}
