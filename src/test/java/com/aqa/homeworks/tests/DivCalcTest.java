package com.aqa.homeworks.tests;

import com.aqa.homeworks.utils.DataSets;
import org.apache.commons.math3.analysis.function.Divide;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DivCalcTest extends CalculatorTest {

    @BeforeMethod
    public void beforeTestng(ITestResult itestResult) {
        super.beforeTest(itestResult);
    }

    @Test(groups = "smoke", dataProvider = "randomLongWithoutZero", dataProviderClass = DataSets.class)
    public void divLongTest(long firstNumber, long secondNumber) {
        Assert.assertEquals(getCalculator().div(firstNumber, secondNumber), Math.divideExact(firstNumber, secondNumber), "Actual result is not equals expected result");
    }

    @Test(groups = "smoke", dataProvider = "randomDoubleWithoutZero", dataProviderClass = DataSets.class)
    public void divDoubleTest(double firstNumber, double secondNumber) {
        Assert.assertEquals(getCalculator().div(firstNumber, secondNumber), new Divide().value(firstNumber,secondNumber), "Actual result is not equals expected result");
    }

    @Test(groups = "smoke", dataProvider = "randomLongWithZero", dataProviderClass = DataSets.class, expectedExceptions = NumberFormatException.class)
    public void divLongByZeroTest(long firstNumber, long secondNumber) {
        Assert.assertEquals(getCalculator().div(firstNumber, secondNumber), Math.divideExact(firstNumber, secondNumber), "NumberFormatException expected");
    }

    @Test(groups = "smoke", dataProvider = "randomDoubleWithZero", dataProviderClass = DataSets.class, expectedExceptions = NumberFormatException.class)
    public void divDoubleByZeroTest(double firstNumber, double secondNumber) {
        Assert.assertEquals(getCalculator().div(firstNumber, secondNumber), new Divide().value(firstNumber,secondNumber), "NumberFormatException expected");
    }

    @AfterMethod
    public void afterTest(ITestResult itestResult) {
        super.afterTest(itestResult);
    }

}
