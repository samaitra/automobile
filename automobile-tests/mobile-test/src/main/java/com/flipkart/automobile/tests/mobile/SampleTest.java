package com.flipkart.automobile.tests.mobile;


import com.flipkart.automobile.pages.*;
import com.flipkart.automobile.util.DataProviders;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: shikha.agrawal
 * Date: 09/07/13
 * Time: 1:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class SampleTest extends DataProviders {


    @Test(groups = {"regression"},dataProvider = "dataForSample")
    public void MyTest(HashMap hm) throws Exception
    {
    }


}
