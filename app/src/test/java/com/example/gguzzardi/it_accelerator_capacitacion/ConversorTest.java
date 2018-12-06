package com.example.gguzzardi.it_accelerator_capacitacion;

import com.example.gguzzardi.it_accelerator_capacitacion.model.Converter;
import com.example.gguzzardi.it_accelerator_capacitacion.model.exceptions.CannotBeNegativeException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConversorTest {

    private Converter mConverter;

    @Before
    public void initConverter() {
        mConverter = new Converter();
    }

    @Test
    public void convertValueCorrectly() throws CannotBeNegativeException {
        double input = 5.0;
        double expectedOutput = 8.0467;
        mConverter.setMiles(input);
        mConverter.convert();

        Assert.assertTrue( "result: " + mConverter.getKilometers() + " , expected: " + expectedOutput,
                Double.compare(mConverter.getKilometers(), expectedOutput) == 0);    }

    @Test
    public void convertZeroCorrectly() throws CannotBeNegativeException {
        double input = 0.0;
        double expectedOutput = 0.0;
        mConverter.setMiles(input);
        mConverter.convert();

        Assert.assertTrue( "Input: " + input + " , expected: " + expectedOutput,
                Double.compare(mConverter.getKilometers(), expectedOutput) == 0);
    }

    @Test(expected = CannotBeNegativeException.class)
    public void setNegativeMilesThrowsException() throws CannotBeNegativeException {
        double input = -5.0;
        mConverter.setMiles(input);
    }
}
