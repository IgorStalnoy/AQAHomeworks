package com.aqa.homeworks;

import com.aqa.homeworks.utils.DataSets;
import org.apache.commons.math3.analysis.function.Cos;
import org.apache.commons.math3.analysis.function.Sin;
import org.apache.commons.math3.analysis.function.Tan;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrigonometryTest extends CalculatorTest {
    @BeforeMethod
    public void beforeTestng(ITestResult itestResult) {
        super.beforeTest(itestResult);
    }

    @Test(groups = "criticalPath", dataProvider = "randomSingleDouble", dataProviderClass = DataSets.class, priority = 1)
    public void sinDoubleTest(double number) {
        Assert.assertEquals(getCalculator().sin(number), new Sin().value(number), "Actual result is not equals expected result");
    }

    @Test(groups = "criticalPath", dataProvider = "trigonometryConstantsData", dataProviderClass = DataSets.class, priority = 1)
    public void sinConstantsTest(double number) {
        Assert.assertEquals(getCalculator().sin(number), new Sin().value(number), "Actual result is not equals expected result");
    }

    @Test(groups = "criticalPath", dataProvider = "randomSingleDouble", dataProviderClass = DataSets.class, priority = 2)
    public void cosDoubleTest(double number) {
        Assert.assertEquals(getCalculator().cos(number),new Cos().value(number), "Actual result is not equals expected result");
    }

    @Test(groups = "criticalPath", dataProvider = "trigonometryConstantsData", dataProviderClass = DataSets.class, priority = 2)
    public void cosConstantsTest(double number) {
        Assert.assertEquals(getCalculator().cos(number), new Cos().value(number), "Actual result is not equals expected result");
    }

    @Test(groups = "criticalPath", dataProvider = "randomSingleDouble", dataProviderClass = DataSets.class, priority = 3)
    public void tgDoubleTest(double number) {
        Assert.assertEquals(getCalculator().tg(number), new Tan().value(number), "Actual result is not equals expected result");
    }

    @Test(groups = "criticalPath", dataProvider = "trigonometryConstantsData", dataProviderClass = DataSets.class, priority = 3)
    public void tgConstantsTest(double number) {
        Assert.assertEquals(getCalculator().tg(number), new Tan().value(number), "Actual result is not equals expected result");
    }

    @Test(groups = "criticalPath", dataProvider = "randomSingleDouble", dataProviderClass = DataSets.class, priority = 4)
    public void ctgDoubleTest(double number) {
        Assert.assertEquals(getCalculator().ctg(number), Math.cos(number) / Math.sin(number), "Actual result is not equals expected result");
    }

    @Test(groups = "criticalPath", dataProvider = "trigonometryConstantsData", dataProviderClass = DataSets.class, priority = 4)
    public void ctgConstantsTest(double number) {
        Assert.assertEquals(getCalculator().ctg(number), Math.cos(number) / Math.sin(number), "Actual result is not equals expected result");
    }

    @AfterMethod
    public void afterTest(ITestResult itestResult) {
        super.afterTest(itestResult);
    }

}
