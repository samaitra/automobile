package com.flipkart.automobile.core;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.HasTouchScreen;
import org.openqa.selenium.TouchScreen;
import org.openqa.selenium.remote.RemoteTouchScreen;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: saikat
 * Date: 06/06/13
 * Time: 2:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class SwipeableWebDriver extends RemoteWebDriver implements HasTouchScreen {
    private RemoteTouchScreen touch;

    public SwipeableWebDriver(URL remoteAddress, Capabilities desiredCapabilities) {
        super(remoteAddress, desiredCapabilities);
        touch = new RemoteTouchScreen(getExecuteMethod());
    }

    public TouchScreen getTouch() {
        return touch;
    }
}