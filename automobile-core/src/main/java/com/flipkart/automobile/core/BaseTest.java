package com.flipkart.automobile.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: saikat
 * Date: 04/06/13
 * Time: 12:14 PM
 * To change this template use File | Settings | File Templates.
 */

public class BaseTest {

    public static WebDriver driver;
    public static final String NATIVE_APP = "NATIVE_APP";
    public static final String WEBVIEW = "WEBVIEW";

    @BeforeSuite
    public void setup(){
        try{
            if(System.getProperty("device")!=null){
            if(System.getProperty("device").equalsIgnoreCase("Android"))
                driver = new SwipeableWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), getCapabilities());
            else if(System.getProperty("device").equalsIgnoreCase("Selendroid"))
               driver = new SwipeableWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), getSelendroidCapabilities());
            else if(System.getProperty("device").equalsIgnoreCase("iOS"))
                driver = new SwipeableWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), getIosCapabilities());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @AfterSuite
    public void teardown(){
      driver.quit();
    }
    public void startApp(){

    }
    public DesiredCapabilities getCapabilities(){

        File appDir = new File("./app/");
        File app = new File(appDir, "sample_app.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("device", "Android");
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
        //capabilities.setCapability(CapabilityType.VERSION, "4.2");
        capabilities.setCapability(CapabilityType.PLATFORM, "MAC");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("app-package", "com.sample.android");
        capabilities.setCapability("app-activity", "com.sample.android.SplashActivity");

        return capabilities;

    }
    public void captureScreenshot(){

    }

    public DesiredCapabilities getSelendroidCapabilities() throws MalformedURLException{

        File appDir = null;
        appDir = new File("./app/");
        File app = new File(appDir, "sample_app.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("device", "selendroid");
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "selendroid");
        capabilities.setCapability(CapabilityType.PLATFORM, "MAC");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("app-package", "com.sample.android");
        capabilities.setCapability("app-activity", "com.sample.android.SplashActivity");

        return capabilities;
    }

    public DesiredCapabilities getIosCapabilities(){

        File appDir = new File("./app/");
        File app = new File(appDir, "sample.app");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, "iOS");
        capabilities.setCapability(CapabilityType.VERSION, "6.0");
        capabilities.setCapability(CapabilityType.PLATFORM, "Mac");
        capabilities.setCapability("app", app.getAbsolutePath());
        return capabilities;

    }

}
