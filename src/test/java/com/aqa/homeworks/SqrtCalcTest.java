package com.aqa.homeworks;

import com.aqa.homeworks.utils.DataSets;
import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SqrtCalcTest {
    private final Calculator calculator = new Calculator();

    @Test(groups = "criticalPath", dataProvider = "randomSingleLong", dataProviderClass = DataSets.class)
    public void sqrtLongTest(long number) {
        Assert.assertEquals(calculator.sqrt(number), Math.sqrt(number), "Actual result is not equals expected result");
    }

    @Test(groups = "criticalPath", dataProvider = "randomSingleDouble", dataProviderClass = DataSets.class)
    public void sqrtDoubleTest(double number) {
        Assert.assertEquals(calculator.sqrt(number), Math.sqrt(number), "Actual result is not equals expected result");
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
