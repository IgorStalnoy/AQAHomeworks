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

public class TimeoutTest extends CalculatorTest {

    @BeforeMethod
    public void beforeTestng(ITestResult itestResult) {
        super.beforeTest(itestResult);
    }

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

    @AfterMethod
    public void afterTest(ITestResult itestResult) {
        super.afterTest(itestResult);
    }
}
