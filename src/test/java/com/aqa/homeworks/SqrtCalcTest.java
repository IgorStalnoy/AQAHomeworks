package com.aqa.homeworks;

import com.aqa.homeworks.utils.DataSets;
import org.apache.commons.math3.analysis.function.Sqrt;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SqrtCalcTest extends CalculatorTest {

    @BeforeMethod
    public void beforeTestng(ITestResult itestResult) {
        super.beforeTest(itestResult);
    }

    @Test(groups = "criticalPath", dataProvider = "randomSingleLong", dataProviderClass = DataSets.class)
    public void sqrtLongTest(long number) {
        Assert.assertEquals(getCalculator().sqrt(number), new Sqrt().value(number), "Actual result is not equals expected result");
    }

    @Test(groups = "criticalPath", dataProvider = "randomSingleDouble", dataProviderClass = DataSets.class)
    public void sqrtDoubleTest(double number) {
        Assert.assertEquals(getCalculator().sqrt(number), new Sqrt().value(number), "Actual result is not equals expected result");
    }

    @AfterMethod
    public void afterTest(ITestResult itestResult) {
        super.afterTest(itestResult);
    }
}
