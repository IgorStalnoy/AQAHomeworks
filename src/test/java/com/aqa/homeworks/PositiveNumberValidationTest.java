package com.aqa.homeworks;

import com.aqa.homeworks.utils.DataSets;
import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PositiveNumberValidationTest {
    private final Calculator calculator = new Calculator();

    @BeforeTest
    @Test(groups = "extendedTest", dataProvider = "randomSingleLong", dataProviderClass = DataSets.class)
    public void isNumberPositiveTest(long number) {
        Assert.assertEquals(calculator.isPositive(number), number > 0, "Actual number is not positive");
    }

    @Test(groups = "extendedTest", dataProvider = "randomSingleLong", dataProviderClass = DataSets.class)
    public void isNumberNegativeTest(long number) {
        Assert.assertEquals(calculator.isNegative(number), number < 0, "Actual number is not negative");
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
