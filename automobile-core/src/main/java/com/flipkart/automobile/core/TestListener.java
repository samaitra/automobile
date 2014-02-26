package com.flipkart.automobile.core;

/**
 * Created with IntelliJ IDEA.
 * User: saikat
 * Date: 04/06/13
 * Time: 2:06 PM
 * To change this template use File | Settings | File Templates.
 */

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
public class TestListener extends TestListenerAdapter
{
    private int m_count = 0;

    @Override
    public void onTestFailure(ITestResult tr)
    {
        log("F");
    }

    @Override
    public void onTestSkipped(ITestResult tr)
    {
        log("S");
    }

    @Override
    public void onTestSuccess(ITestResult tr)
    {
        log(".");
    }

    private void log(String string)
    {
        System.out.print(string);
        if (++m_count % 10 == 0)
            System.out.println("");
    }

}