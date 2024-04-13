package com.aqa.homeworks.tests;

import com.epam.tat.module4.Calculator;
import org.testng.ITestResult;

public abstract class CalculatorTest {
    private final Calculator calculator = new Calculator();

    protected void beforeTest(ITestResult itestResult) {
        System.out.println("Test " + itestResult.getMethod().getMethodName() + " started");
    }

    protected void afterTest(ITestResult itestResult) {
        if (itestResult.isSuccess()) {
            System.out.println("Test passed " + itestResult.getMethod().getMethodName());
        } else {
            System.out.println("Test failed " + itestResult.getMethod().getMethodName());
        }
    }

    protected Calculator getCalculator() {
        return calculator;
    }

}
