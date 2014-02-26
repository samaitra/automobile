package com.flipkart.automobile.util;

import com.flipkart.automobile.core.BaseTest;
import com.flipkart.automobile.pages.Android;
import com.flipkart.automobile.pages.Selendroid;
import com.flipkart.automobile.pages.iOS;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: shikha.agrawal
 * Date: 19/06/13
 * Time: 5:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class DataProviders extends BaseTest {

    public DataProviders()
    {
        Device.device.put("Android",new Android());
        Device.device.put("Selendroid",new Selendroid());
        Device.device.put("iOS",new iOS());

    }


    @DataProvider(name = "dataForSample")
    public Iterator<Object[]> dataForSampleTests() throws Exception
    {
        List<Object[]> myData = new ArrayList<Object[]>();
        HashMap hm = new HashMap();
        hm.put("mydata","data1");

        myData.add(new Object[]{hm});
        return myData.iterator();
    }



}
