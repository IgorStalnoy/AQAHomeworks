package com.aqa.homeworks.tests;

import com.aqa.homeworks.utils.DataSets;
import org.apache.commons.math3.analysis.function.Multiply;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MultCalcTest extends CalculatorTest{
    @BeforeMethod
    public void beforeTestng(ITestResult itestResult) {
        super.beforeTest(itestResult);
    }

    @Test(groups = "smoke", dataProvider = "randomLongData", dataProviderClass = DataSets.class)
    public void subLongTest(long firstNumber, long secondNumber) {
        Assert.assertEquals(getCalculator().mult(firstNumber, secondNumber), Math.multiplyExact(firstNumber,secondNumber), "Actual result is not equals expected result");
    }

    @Test(groups = "smoke", dataProvider = "randomDoubleData", dataProviderClass = DataSets.class)
    public void subDoubleTest(double firstNumber, double secondNumber) {
        Assert.assertEquals(getCalculator().mult(firstNumber, secondNumber), new Multiply().value(firstNumber,secondNumber), "Actual result is not equals expected result");
    }

    @AfterMethod
    public void afterTest(ITestResult itestResult) {
        super.afterTest(itestResult);
    }
}
