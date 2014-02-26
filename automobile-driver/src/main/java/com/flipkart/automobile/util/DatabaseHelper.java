package com.flipkart.automobile.util;

import com.flipkart.automobile.core.Config;
import com.flipkart.automobile.testrunner.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: saikat
 * Date: 04/06/13
 * Time: 12:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class DatabaseHelper {


    Connection conn;

    public DatabaseHelper() {


    }

    public void addResults(Test test, UUID batchId, Boolean persist) {

        if(persist!=null){

            Config config = Config.getInstance();


            String dburl = "jdbc:mysql://" + config.configProperties.getProperty("db.hostname") + ":" + config.configProperties.getProperty("db.port") + "/" + config.configProperties.getProperty("db.dbname") + "?autoReconnect=true";
            String username = config.configProperties.getProperty("db.user");
            String password = config.configProperties.getProperty("db.password");

            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                conn = DriverManager.getConnection(dburl, username, password);


            } catch (Exception e) {
                System.out.println("Could not connect to database.");
                e.printStackTrace();
                //System.exit(0);
            }



            String env = System.getenv("ENV");
            String tableName = env.substring(0, 1).toUpperCase() + env.substring(1).toLowerCase() + "TestResults";
            try {
                String sql = "insert into " + tableName + "(testName,passed,failed,exceptions,batchId,type,createdTime,module,category,subcategory) values ('" + test.getName() + "','" + test.getPassed() + "','" + test.getFailed() + "','" + test.getException() + "','" + batchId + "','" + test.getType() + "','" + getCurrentTime() + "','" + test.getModule() + "','" + test.getFeature() + "','" + test.getSubfeature() + "')";
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
                ps.execute(sql);
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                //conn.close();
            } catch (Exception exp) {
                exp.printStackTrace();

            }
        }
    }



    public String getCurrentTime() {
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = sdf.format(dt);
        return currentTime;
    }

    private void connectDb(){
        Config config = Config.getInstance();
        String dburl = "jdbc:mysql://" + config.configProperties.getProperty("db.hostname") + ":" + config.configProperties.getProperty("db.port") + "/" + config.configProperties.getProperty("db.dbname") + "?autoReconnect=true";

        String username = config.configProperties.getProperty("db.user");
        String password = config.configProperties.getProperty("db.password");

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(dburl, username, password);


        } catch (Exception e) {
            System.out.println("Could not connect to database.");
            e.printStackTrace();

        }
    }

    public int getTestFailures(UUID batchId, Boolean persist){
        int failures=0;
        if(persist){

            connectDb();

            String env = System.getenv("ENV");
            String tableName = env.substring(0, 1).toUpperCase() + env.substring(1).toLowerCase() + "TestResults";
            try {
                String sql = "select sum(failed) as failed from "+tableName+" where batchId='"+batchId+"'";
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery(sql);
                rs.next();
                failures = rs.getInt("failed");


                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                //conn.close();
            } catch (Exception exp) {
                exp.printStackTrace();

            }
        }

        return failures;
    }

    public int getTestExceptions(UUID batchId, Boolean persist){
        int exceptions=0;
        if(persist){

            connectDb();

            String env = System.getenv("ENV");
            String tableName = env.substring(0, 1).toUpperCase() + env.substring(1).toLowerCase() + "TestResults";
            try {
                String sql = "select sum(exceptions) as exceptions from "+tableName+" where batchId='"+batchId+"'";
                PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery(sql);
                rs.next();
                exceptions = rs.getInt("exceptions");


                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {

            } catch (Exception exp) {
                exp.printStackTrace();

            }
        }

        return exceptions;
    }

}
