package com.example.gguzzardi.it_accelerator_capacitacion.presenters;

import com.example.gguzzardi.it_accelerator_capacitacion.model.Converter;

public class ConverterPresenter implements Converter.ConverterListener {

    private final ConverterView mView;
    private final Converter mConverter;

    public ConverterPresenter(ConverterView view, Converter converter) {
        mView = view;
        mConverter = converter;
    }

    @Override
    public void convertValue() {
        mConverter.convert();
        mView.showKilometersValue(mConverter.getKilometers());
    }

    @Override
    public void onValueTyped(double value) {
        mConverter.setMiles(value);
    }
}
