package com.aqa.homeworks;

import com.aqa.homeworks.utils.DataSets;
import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DivCalcTest {
    private final Calculator calculator = new Calculator();

    @Test(groups = "smoke", dataProvider = "randomLongWithoutZero", dataProviderClass = DataSets.class)
    public void divLongTest(long firstNumber, long secondNumber) {
        Assert.assertEquals(calculator.div(firstNumber, secondNumber), firstNumber / secondNumber, "Actual result is not equals expected result");
    }

    @Test(groups = "smoke", dataProvider = "randomDoubleWithoutZero", dataProviderClass = DataSets.class)
    public void divDoubleTest(double firstNumber, double secondNumber) {
        Assert.assertEquals(calculator.div(firstNumber, secondNumber), firstNumber / secondNumber, "Actual result is not equals expected result");
    }

    @Test(groups = "smoke", dataProvider = "randomLongWithZero", dataProviderClass = DataSets.class, expectedExceptions = NumberFormatException.class)
    public void divLongByZeroTest(long firstNumber, long secondNumber) {
        Assert.assertEquals(calculator.div(firstNumber, secondNumber), firstNumber / secondNumber, "NumberFormatException expected");
    }

    @Test(groups = "smoke", dataProvider = "randomDoubleWithZero", dataProviderClass = DataSets.class, expectedExceptions = NumberFormatException.class)
    public void divDoubleByZeroTest(double firstNumber, double secondNumber) {
        Assert.assertEquals(calculator.div(firstNumber, secondNumber), firstNumber / secondNumber, "NumberFormatException expected");
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
