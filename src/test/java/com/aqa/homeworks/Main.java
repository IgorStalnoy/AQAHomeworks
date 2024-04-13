package com.aqa.homeworks;

public class Main {
    public static void main(String[] args) {

        TestNGRunner.runTests("smoke");
        TestNGRunner.runTests("criticalPath");
        TestNGRunner.runTests("extendedTest");
    }
}
