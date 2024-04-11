package com.aqa.homeworks;

import com.aqa.homeworks.utils.DataSets;
import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SumCalcTest extends CalculatorTest {
    @BeforeMethod
    public void beforeTestng(ITestResult itestResult) {
        super.beforeTest(itestResult);
    }
    @Test(groups = "smoke", dataProvider = "randomLongData", dataProviderClass = DataSets.class)
    public void sumLongTest(long firstNumber, long secondNumber) {
        Assert.assertEquals(getCalculator().sum(firstNumber, secondNumber), firstNumber + secondNumber, "Actual result is not equals expected result");
    }

    @Test(groups = "smoke", dataProvider = "randomDoubleData", dataProviderClass = DataSets.class)
    public void sumDoubleTest(double firstNumber, double secondNumber) {
        Assert.assertEquals(getCalculator().sum(firstNumber, secondNumber), firstNumber + secondNumber, "Actual result is not equals expected result");
    }

    @AfterMethod
    public void afterTest(ITestResult itestResult) {
        super.afterTest(itestResult);
    }

}
