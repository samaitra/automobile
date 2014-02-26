package com.flipkart.automobile.util;

import com.flipkart.automobile.core.BaseTest;
import com.flipkart.automobile.core.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: shikha.agrawal
 * Date: 19/06/13
 * Time: 6:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class Common extends BaseTest {

    protected static final Logger logger = Logger.getLogger("Common");

    public Common()
    {

    }
    protected int randomNumber(int limit){
        Random randomGenerator=new Random();
        return randomGenerator.nextInt(limit)+1;
    }

    public boolean isElementPresent(By by) {

        try {
            List allElements = driver.findElements(by);
            if ((allElements == null) || (allElements.size() == 0))
                return false;
            else
                return true;
        }
        catch(java.util.NoSuchElementException e)
        {
            return false;
        }

    }

    public By getBy(String locator) {
        By by;
        if(locator.startsWith("//"))
            by = By.xpath(locator);
        else if(locator.startsWith("class="))
            by =By.className(locator.replace("class=", ""));
        else if(locator.startsWith("css="))
            by = By.cssSelector(locator.replace("css=", "").trim());
        else if(locator.startsWith("link="))
            by = By.linkText(locator.replace("link=", ""));
        else if(locator.startsWith("name="))
            by = By.name(locator.replace("name=", "").trim());
        else if(locator.startsWith("tag="))
            by = By.tagName(locator.replace("tag=", "").trim());
        else if(locator.startsWith("partialText="))
            by= By.partialLinkText(locator.replace("partialText=", ""));
        else if(locator.startsWith("id="))
            by= By.id(locator.replace("id=",""));
        else
            by = By.id(locator);
        return by;
    }

    public static By by(String locator) {
        By by;
        if(locator.startsWith("//"))
            by = By.xpath(locator);
        else if(locator.startsWith("class="))
                by =By.className(locator.replace("class=",""));
        else if(locator.startsWith("css="))
            by = By.cssSelector(locator.replace("css=", "").trim());
        else if(locator.startsWith("link="))
            by = By.linkText(locator.replace("link=", ""));
        else if(locator.startsWith("name="))
            by = By.name(locator.replace("name=", "").trim());
        else if(locator.startsWith("tag="))
            by = By.tagName(locator.replace("tag=", "").trim());
        else if(locator.startsWith("partialText="))
            by= By.partialLinkText(locator.replace("partialText=",""));
        else if(locator.startsWith("text="))
            by =By.linkText(locator.replace("text=",""));
        else if(locator.startsWith("id="))
            by= By.id(locator.replace("id=",""));
        else
            by = By.id(locator);
        return by;
    }

    public static void waitForElementPresent(final String locator,int timeToWaitInSeconds) throws Exception {
        try{
            new WebDriverWait(driver, timeToWaitInSeconds)
                    .until(new ExpectedCondition<WebElement>(){
                        public WebElement apply(WebDriver d) {
                            return driver.findElement(by(locator));
                        }});

        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.err.println("There was an issue in finding the WebElement " + locator + "." + e.getMessage().split("waiting")[0]);
        }

    }

    public String textAt(String locator) throws Exception{
        String text = null;
        try {
            waitForElementPresent(locator,3);
            text = driver.findElement(getBy(locator)).getText().trim();
        } catch(NoSuchElementException e) {
            System.err.println(e.getMessage());
        }
        return text;
    }
//
    public boolean textAtContains(String locator, String verificationText) throws Exception {
        boolean isTextMatched = false;
        try {
            String temp = textAt(locator).toLowerCase();
            if(temp.length() < 1)
                return isTextMatched;
            isTextMatched = temp.contains(verificationText.toLowerCase()) || verificationText.toLowerCase().contains(temp);
        } catch(NoSuchElementException e) {
            System.err.println(e.getMessage());
        }
        return isTextMatched;
    }



}
