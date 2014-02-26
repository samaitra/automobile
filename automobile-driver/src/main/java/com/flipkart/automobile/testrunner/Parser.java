package com.flipkart.automobile.testrunner;

import com.flipkart.automobile.core.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: saikat
 * Date: 03/06/13
 * Time: 5:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class Parser {

    private static Parser instance = null;
    static Config config;
    ArrayList<Test> testList = new ArrayList<Test>();

    Logger logger = LoggerFactory.getLogger(Parser.class);

    protected Parser() {

        config = config.getInstance();

    }


    public static Parser getInstance() {

        if (instance == null) {
            instance = new Parser();
        }
        return instance;
    }

      public ArrayList<Test>parse(String testFile){
          try{

              String path = getClass().getResource ("/" + testFile).getFile();
              InputStream is = getClass().getResourceAsStream("/" + testFile);
              BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
              File tempfile = new File(testFile);
              tempfile.createNewFile();

              FileWriter fileWriter = new FileWriter(tempfile.getAbsoluteFile());
              BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
              String line;

              while((line = bufferedReader.readLine())!=null){
                  bufferedWriter.write(line);
              }

              bufferedWriter.close();

              Test test = new Test();
              test.setName(testFile.replace(".xml", ""));
              test.setPath(tempfile.getAbsolutePath());
              if(System.getProperty("testFile").equalsIgnoreCase("api-test.xml"))
              {
                  test.setModule("Mobile-Apps");
                  test.setFeature("Apps");
                  test.setType("MobileApp");
                  test.setSubfeature("apis");
              }
              else
              {
                  test.setModule("Mobile-Apps");
                  test.setFeature("Apps");
                  test.setType("MobileApp");
                  test.setSubfeature(System.getProperty("device"));
              }
              testList.add(test);
        } catch (Exception e){
             e.printStackTrace();
          }
        return testList;
    }




}
