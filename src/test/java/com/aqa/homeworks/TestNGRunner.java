package com.aqa.homeworks;

import com.aqa.homeworks.utils.TestListener;
import org.testng.TestNG;
import org.testng.xml.XmlPackage;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.List;

public class TestNGRunner {

    public static void runTests(String groupName) {

        XmlSuite smokeSuite = new XmlSuite();
        smokeSuite.setName(groupName+ "Suite.xml");
        XmlTest smokeTest = new XmlTest(smokeSuite);
        smokeTest.setName(groupName + " test");
        smokeTest.setXmlPackages(List.of(new XmlPackage("com.aqa.homeworks.tests")));
        smokeTest.addIncludedGroup(groupName);
        List<XmlSuite> suites = new ArrayList<>();
        suites.add(smokeSuite);
        TestNG tng = new TestNG();
        TestListener testListener = new TestListener();
        tng.addListener(testListener);
        tng.setXmlSuites(suites);
        tng.run();


    }

}
