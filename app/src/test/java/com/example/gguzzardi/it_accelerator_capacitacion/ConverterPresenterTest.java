package com.example.gguzzardi.it_accelerator_capacitacion;

import com.example.gguzzardi.it_accelerator_capacitacion.model.Converter;
import com.example.gguzzardi.it_accelerator_capacitacion.model.exceptions.CannotBeNegativeException;
import com.example.gguzzardi.it_accelerator_capacitacion.presenters.ConverterPresenter;
import com.example.gguzzardi.it_accelerator_capacitacion.presenters.ConverterView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ConverterPresenterTest {

    @Mock
    private ConverterView mConverterView;

    @Mock
    private Converter mConverter;

    private ConverterPresenter mPresenter;

    @Before
    public void initPresenter() {
        mPresenter = new ConverterPresenter(mConverterView, mConverter);
    }

    @Test
    public void convertValueCallsConverterConvertMethod() {
        mPresenter.convertValue();
        verify(mConverter, times(1)).convert();
    }

    @Test
    public void convertValueCallsViewShowKilometersMethod() {
        mPresenter.convertValue();
        verify(mConverterView, times(1)).showKilometersValue(mConverter.getKilometers());
    }

    @Test
    public void onValueTypedCallsConverterSetMilesMethod() throws CannotBeNegativeException {
        double valueTyped = 2.0;
        mPresenter.onValueTyped(valueTyped);
        verify(mConverter, times(1)).setMiles(valueTyped);
    }

    @Test
    public void onNegativeValueTypedCallsViewShowErrorMethod() throws CannotBeNegativeException {
        double valueTyped = -2.0;

        doThrow(new CannotBeNegativeException()).when(mConverter).setMiles(valueTyped);

        mPresenter.onValueTyped(valueTyped);
        verify(mConverterView, times(1)).onValueTypedError();
    }
}
