package com.aqa.homeworks.utils;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Report info : " + result.getName() + " result - SUCCESS");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Report info : " + result.getName() + " result - FAILED");
    }
}
