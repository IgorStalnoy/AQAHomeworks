package com.aqa.homeworks;

import com.aqa.homeworks.utils.DataSets;
import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SumCalcTest {
    private final Calculator calculator = new Calculator();

    @Test(groups = "smoke", dataProvider = "randomLongData", dataProviderClass = DataSets.class)
    public void sumLongTest(long firstNumber, long secondNumber) {
        Assert.assertEquals(calculator.sum(firstNumber, secondNumber), firstNumber + secondNumber, "Actual result is not equals expected result");
    }

    @Test(groups = "smoke", dataProvider = "randomDoubleData", dataProviderClass = DataSets.class)
    public void sumDoubleTest(double firstNumber, double secondNumber) {
        Assert.assertEquals(calculator.sum(firstNumber, secondNumber), firstNumber + secondNumber, "Actual result is not equals expected result");
    }

    @BeforeMethod
    public void beforeTest(ITestResult itestResult) {
        System.out.println("Test " + itestResult.getMethod().getMethodName() + " started");
    }


    @AfterMethod
    public void afterTest(ITestResult itestResult) {
        if (itestResult.isSuccess()) {
            System.out.println("Test passed " + itestResult.getMethod().getMethodName());
        } else {
            System.out.println("Test failed " + itestResult.getMethod().getMethodName());
        }
    }


}
