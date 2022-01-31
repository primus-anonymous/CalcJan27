package com.neocaptainnemo.calcjan27.domain;

public class CalculatorImpl implements Calculator {

    @Override
    public double calculate(double arg1, double arg2, Operation operation) {
        switch (operation) {
            case ADD:
                return arg1 + arg2;
            case SUB:
                return arg1 - arg2;
            case MULT:
                return arg1 * arg2;
            case DIV:
                return arg1 / arg2;
        }
        return 0;
    }
}
