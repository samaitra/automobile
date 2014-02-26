package com.flipkart.automobile.core;

import java.io.*;
import java.net.URI;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: saikat
 * Date: 04/06/13
 * Time: 2:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class Config {

    public static Properties configProperties = new Properties();

    private static final Config instance = new Config();
    public Config(){

        try {

            configProperties.load(Config.class.getResourceAsStream("/config.properties"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Config getInstance(){

    return instance;
    }



}
