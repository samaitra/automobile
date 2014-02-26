package com.flipkart.automobile.testrunner;

import com.flipkart.automobile.util.DatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: saikat
 * Date: 04/06/13
 * Time: 12:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class Driver {

    public static String module,testFile,device;
    static ArrayList<Test> tests;
    static ArrayList<Test> testNgBucket = new ArrayList<Test>();
    static HashMap<TestRunner, ArrayList> testMap = new HashMap<TestRunner, ArrayList>();
    static UUID batchId = null;
    static Boolean persist;
    public static final String MODULE = "Mobile-Apps";

    public static void main(String args[]) throws Exception {

        module = MODULE;
        testFile = System.getProperty("testFile");
        if(System.getProperty("device")!=null)
            device = System.getProperty("device");
        if(System.getProperty("persist")!=null)
            persist = Boolean.parseBoolean(System.getProperty("persist"));
        if(System.getProperty("batchId")!=null)
            batchId = UUID.fromString(System.getProperty("batchId"));
        else
            batchId = UUID.randomUUID() ;
        Parser parser = Parser.getInstance();
        tests = parser.parse(testFile);
        createTestBucket(tests);
        startExecution(testMap,persist);
        if(System.getProperty("device")!=null)
            System.setProperty("device",device);


    }



    static void createTestBucket(ArrayList<Test> tests) {

        TestRunner tr = new TestRunner();
        testMap.put(tr, tests);

    }


    static void startExecution(final HashMap<TestRunner, ArrayList> testMap,final Boolean persist) {

        for (Map.Entry<TestRunner, ArrayList> entry : testMap.entrySet()) {
            final TestRunner tr = entry.getKey();
            final ArrayList<Test> tList = entry.getValue();
                    try {

                        for (Test t : tList) {
                            tr.execute(t, batchId, persist);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

        }

    }

}
