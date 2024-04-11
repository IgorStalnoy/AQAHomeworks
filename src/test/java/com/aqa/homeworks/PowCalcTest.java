package com.aqa.homeworks;

import com.aqa.homeworks.utils.DataSets;
import org.apache.commons.math3.analysis.function.Pow;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PowCalcTest extends CalculatorTest {

    @BeforeMethod
    public void beforeTestng(ITestResult itestResult) {
        super.beforeTest(itestResult);
    }

    @Test(groups = "criticalPath", dataProvider = "randomLongData", dataProviderClass = DataSets.class)
    public void powLongTest(long firstNumber, long secondNumber) {

        Assert.assertEquals(getCalculator().pow(firstNumber, secondNumber), new Pow().value(firstNumber,secondNumber), "Actual result is not equals expected result");
    }

    @Test(groups = "criticalPath", dataProvider = "randomDoubleData", dataProviderClass = DataSets.class)
    public void powDoubleTest(double firstNumber, double secondNumber) {
        Assert.assertEquals(getCalculator().pow(firstNumber, secondNumber), new Pow().value(firstNumber,secondNumber), "Actual result is not equals expected result");
    }

    @AfterMethod
    public void afterTest(ITestResult itestResult) {
        super.afterTest(itestResult);
    }
}
