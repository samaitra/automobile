package com.flipkart.automobile.util;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: shikha.agrawal
 * Date: 23/10/13
 * Time: 7:12 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Device {


    public boolean action();
    public static HashMap<String,Device>  device = new HashMap<String, Device>();

}
