package com.aqa.homeworks;

import com.epam.tat.module4.Timeout;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

import static java.lang.Thread.State.TIMED_WAITING;
import static java.lang.Thread.sleep;

public class TimeoutTest {
    @Test(groups = "extendedTest", description = "Positive timeout test")
    public void ThreadSleepPositiveTest() {
        class MyThread extends Thread {
            @Override
            public void run() {
                int seconds = new Random().nextInt(2, 10);
                Timeout.sleep(seconds);
            }
        }
        Thread thread = new MyThread();
        thread.start();
        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(thread.getState(), TIMED_WAITING, "Timeout method does not put the thread to sleep");
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
