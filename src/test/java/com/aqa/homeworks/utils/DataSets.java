package com.aqa.homeworks.utils;

import org.testng.annotations.DataProvider;

import java.util.Random;

public class DataSets {

    @DataProvider(name = "randomLongData")
    public static Object[][] getRandomLongNumbers() {
        return new Object[][]{
                {new Random().nextLong(1, 9999), new Random().nextLong(1, 9999)},
                {new Random().nextLong(-9999, -0), new Random().nextLong(1, 9999)},
                {new Random().nextLong(-9999, -0), new Random().nextLong(-9999, -0)},
                {new Random().nextLong(-9999, -0), 0},
                {new Random().nextLong(0, 9999), 0},
                {0, 0}
        };
    }

    @DataProvider(name = "randomDoubleData")
    public static Object[][] getRandomDoubleNumbers() {
        return new Object[][]{
                {new Random().nextDouble(1, 9999), new Random().nextDouble(1, 9999)},
                {new Random().nextDouble(-9999, -0), new Random().nextDouble(1, 9999)},
                {new Random().nextDouble(-9999, -0), new Random().nextDouble(-9999, -0)},
                {new Random().nextDouble(-9999, -0), 0},
                {new Random().nextDouble(0, 9999), 0},
                {0, 0}
        };
    }

    @DataProvider(name = "randomLongWithoutZero")
    public static Object[][] getRandomLongWithoutZero() {
        return new Object[][]{
                {new Random().nextLong(1, 9999), new Random().nextLong(1, 9999)},
                {new Random().nextLong(-9999, -0), new Random().nextLong(1, 9999)},
                {new Random().nextLong(-9999, -0), new Random().nextLong(-9999, -0)},
        };
    }

    @DataProvider(name = "randomDoubleWithoutZero")
    public static Object[][] getRandomDoubleWithoutZero() {
        return new Object[][]{
                {new Random().nextDouble(1, 9999), new Random().nextDouble(1, 9999)},
                {new Random().nextDouble(-9999, -0), new Random().nextDouble(1, 9999)},
                {new Random().nextDouble(-9999, -0), new Random().nextDouble(-9999, -0)}
        };
    }

    @DataProvider(name = "randomLongWithZero")
    public static Object[][] getRandomLongWithZero() {
        return new Object[][]{
                {new Random().nextLong(-9999, -0), 0},
                {new Random().nextLong(0, 9999), 0},
                {0, 0}
        };
    }

    @DataProvider(name = "randomDoubleWithZero")
    public static Object[][] getRandomDoubleWithZero() {
        return new Object[][]{
                {new Random().nextDouble(-9999, -0), 0},
                {new Random().nextDouble(0, 9999), 0},
                {0, 0}
        };
    }

    @DataProvider(name = "randomSingleLong")
    public static Object[][] getRandomSingleLong() {
        return new Object[][]{
                {new Random().nextLong(1, 9999)},
                {new Random().nextLong(-9999, -0)},
                {0}
        };
    }

    @DataProvider(name = "randomSingleDouble")
    public static Object[][] getRandomSingleDouble() {
        return new Object[][]{
                {new Random().nextDouble(1, 9999)},
                {new Random().nextDouble(-9999, -0)},
                {0}
        };
    }


    @DataProvider(name = "trigonometryConstantsData")
    public static Object[][] getTrigonometryConstantsData() {
        return new Object[][]{
                {0}, {Math.PI / 6}, {Math.PI / 4}, {Math.PI / 3}, {Math.PI / 2}, {Math.PI}, {3 * Math.PI / 2}, {2 * Math.PI},
                {Math.PI / 6 * -1}, {Math.PI / 4 * -1}, {Math.PI / 3 * -1}, {Math.PI / 2 * -1}, {Math.PI * -1}, {3 * Math.PI / 2 * -1}, {2 * Math.PI * -1}
        };
    }
}
