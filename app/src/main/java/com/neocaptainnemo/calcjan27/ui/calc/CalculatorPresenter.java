package com.neocaptainnemo.calcjan27.ui.calc;

import com.neocaptainnemo.calcjan27.domain.Calculator;
import com.neocaptainnemo.calcjan27.domain.Operation;

import java.text.NumberFormat;
import java.util.Locale;

public class CalculatorPresenter {

    private static final int BASE = 10;

    private final NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.ENGLISH);

    private final CalculatorView view;
    private final Calculator calculator;

    private double arg1;
    private double arg2;

    private boolean isRealPart;
    private double realPartMultiplier = 1.0;

    private Operation selectedOperation;

    public CalculatorPresenter(CalculatorView view, Calculator calculator) {
        this.view = view;
        this.calculator = calculator;
    }

    public void onDigitPressed(int digit) {

        if (selectedOperation == null) {

            arg1 = addAddToArg(arg1, digit);

            showFormattedResult(arg1);
        } else {
            arg2 = addAddToArg(arg2, digit);

            showFormattedResult(arg2);
        }
    }

    public void onOperationPressed(Operation operation) {

        if (selectedOperation != null) {

            arg1 = calculator.calculate(arg1, arg2, selectedOperation);

            showFormattedResult(arg1);

            arg2 = 0.0;
        }

        isRealPart = false;
        realPartMultiplier = 1.0;

        selectedOperation = operation;

    }

    public void onDotPressed() {
        isRealPart = true;
    }

    private void showFormattedResult(double value) {
        view.showResult(numberFormat.format(value));
    }

    private double addAddToArg(double inital, int digit) {

        if (isRealPart) {
            realPartMultiplier *= BASE;

            return inital + digit / realPartMultiplier;

        } else {
            return inital * BASE + digit;
        }
    }
}
