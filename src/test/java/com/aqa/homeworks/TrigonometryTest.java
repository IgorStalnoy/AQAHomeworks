package com.aqa.homeworks;

import com.aqa.homeworks.utils.DataSets;
import com.epam.tat.module4.Calculator;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TrigonometryTest {
    private final Calculator calculator = new Calculator();

    @Test(groups = "criticalPath", dataProvider = "randomSingleDouble", dataProviderClass = DataSets.class, priority = 1)
    public void sinDoubleTest(double number) {
        Assert.assertEquals(calculator.sin(number), Math.sin(number), "Actual result is not equals expected result");
    }

    @Test(groups = "criticalPath", dataProvider = "trigonometryConstantsData", dataProviderClass = DataSets.class, priority = 1)
    public void sinConstantsTest(double number) {
        Assert.assertEquals(calculator.sin(number), Math.sin(number), "Actual result is not equals expected result");
    }

    @Test(groups = "criticalPath", dataProvider = "randomSingleDouble", dataProviderClass = DataSets.class, priority = 2)
    public void cosDoubleTest(double number) {
        Assert.assertEquals(calculator.cos(number), Math.cos(number), "Actual result is not equals expected result");
    }

    @Test(groups = "criticalPath", dataProvider = "trigonometryConstantsData", dataProviderClass = DataSets.class, priority = 2)
    public void cosConstantsTest(double number) {
        Assert.assertEquals(calculator.cos(number), Math.cos(number), "Actual result is not equals expected result");
    }

    @Test(groups = "criticalPath", dataProvider = "randomSingleDouble", dataProviderClass = DataSets.class, priority = 3)
    public void tgDoubleTest(double number) {
        Assert.assertEquals(calculator.tg(number), Math.sin(number) / Math.cos(number), "Actual result is not equals expected result");
    }

    @Test(groups = "criticalPath", dataProvider = "trigonometryConstantsData", dataProviderClass = DataSets.class, priority = 3)
    public void tgConstantsTest(double number) {
        Assert.assertEquals(calculator.tg(number), Math.sin(number) / Math.cos(number), "Actual result is not equals expected result");
    }

    @Test(groups = "criticalPath", dataProvider = "randomSingleDouble", dataProviderClass = DataSets.class, priority = 4)
    public void ctgDoubleTest(double number) {
        Assert.assertEquals(calculator.ctg(number), Math.cos(number) / Math.sin(number), "Actual result is not equals expected result");
    }

    @Test(groups = "criticalPath", dataProvider = "trigonometryConstantsData", dataProviderClass = DataSets.class, priority = 4)
    public void ctgConstantsTest(double number) {
        Assert.assertEquals(calculator.ctg(number), Math.cos(number) / Math.sin(number), "Actual result is not equals expected result");
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
