package com.aqa.homeworks.tests;

import com.aqa.homeworks.utils.DataSets;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PositiveNumberValidationTest extends CalculatorTest {

    @BeforeMethod
    public void beforeTestng(ITestResult itestResult) {
        super.beforeTest(itestResult);
    }

    @Test(groups = "extendedTest", dataProvider = "randomSingleLong", dataProviderClass = DataSets.class)
    public void isNumberPositiveTest(long number) {
        Assert.assertEquals(getCalculator().isPositive(number), number > 0, "Actual number is not positive");
    }

    @Test(groups = "extendedTest", dataProvider = "randomSingleLong", dataProviderClass = DataSets.class)
    public void isNumberNegativeTest(long number) {
        Assert.assertEquals(getCalculator().isNegative(number), number < 0, "Actual number is not negative");
    }

    @AfterMethod
    public void afterTest(ITestResult itestResult) {
        super.afterTest(itestResult);
    }
}
