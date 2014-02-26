package com.flipkart.automobile.testrunner;

import com.flipkart.automobile.core.Config;
import com.flipkart.automobile.util.DatabaseHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.xml.XmlSuite;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;



/**
 * Created with IntelliJ IDEA.
 * User: saikat
 * Date: 03/06/13
 * Time: 5:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestRunner {

    //Config config = Config.getInstance();
    Config config = Config.getInstance();
    String testHome, testReport;
    Logger logger = LoggerFactory.getLogger(TestRunner.class);
    public TestRunner() {

        testHome = config.configProperties.getProperty("tests.home");
        testReport = config.configProperties.getProperty("tests.report");

    }

    ArrayList<String> testList = new ArrayList<String>();



    public void execute(Test test, UUID batchId,Boolean persist) {

        DatabaseHelper db = new DatabaseHelper();
        try {

            String outputDir = testReport + "/" + batchId + "/" + test.getName();
            TestListenerAdapter tla = new TestListenerAdapter();
            ArrayList<String> suiteFiles = new ArrayList<String>();
            suiteFiles.add(test.getPath());
            XmlSuite xmlSuite = new XmlSuite();
            xmlSuite.setSuiteFiles(suiteFiles);
            TestNG testNG = new TestNG();
            testNG.addListener(tla);
            testNG.setCommandLineSuite(xmlSuite);
            testNG.setOutputDirectory(outputDir);
            testNG.run();

            List failed = tla.getFailedTests();
            List passed = tla.getPassedTests();
            List ex = tla.getSkippedTests();
            test.setPassed(passed.size());
            test.setFailed(failed.size());
            test.setException(ex.size());
            db.addResults(test, batchId, persist);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
