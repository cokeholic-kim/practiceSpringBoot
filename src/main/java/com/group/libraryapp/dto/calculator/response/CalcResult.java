package com.group.libraryapp.dto.calculator.response;

import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;

public class CalcResult {
    private int add;
    private int minus;
    private int multiply;

    public CalcResult(CalculatorAddRequest request) {
        this.add = request.getNumber1() + request.getNumber2();
        this.minus = request.getNumber1() - request.getNumber2();
        this.multiply = request.getNumber1() * request.getNumber2();
    }

    public int getAdd() {
        return add;
    }

    public int getMinus() {
        return minus;
    }

    public int getMultiply() {
        return multiply;
    }
}
