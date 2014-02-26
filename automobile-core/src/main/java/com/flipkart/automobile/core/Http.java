package com.flipkart.automobile.core;

/**
 * Created by IntelliJ IDEA.
 * User: sudhanshu.gupta
 * Date: 24/01/13
 * Time: 9:33 AM
 * To change this template use File | Settings | File Templates.
 */

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.Map;

public class Http {

    String nsid,vid;

    public String get(String request, String uri) throws Exception{
        final HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 3000);
        DefaultHttpClient client = new DefaultHttpClient(httpParams);

        HttpGet getRequest = new HttpGet(uri);
        HttpResponse response;
        BufferedReader br = null;
        StringBuffer buffer = new StringBuffer();
        response = client.execute(getRequest);
        int statusCode = response.getStatusLine().getStatusCode();
//            if (statusCode != HttpStatus.SC_OK && statusCode != HttpStatus.SC_BAD_REQUEST) {
//                throw new RuntimeException("Failed : Http error code : "
//                        + statusCode+" for "+request);
//            }
        br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
        String line = null;
        while((line=br.readLine())!=null)
            buffer.append(line);
        br.close();

        return buffer.toString();
    }

    public String get(String request, String uri,String checkSum) {
        final HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, 3000);
        DefaultHttpClient client = new DefaultHttpClient(httpParams);

        HttpGet getRequest = new HttpGet(uri);
        getRequest.addHeader("checksum",checkSum);
        HttpResponse response;
        BufferedReader br = null;
        StringBuffer buffer = new StringBuffer();
        try {
            response = client.execute(getRequest);
            int statusCode = response.getStatusLine().getStatusCode();
//            if (statusCode != HttpStatus.SC_OK && statusCode != HttpStatus.SC_BAD_REQUEST) {
//                throw new RuntimeException("Failed : Http error code : "
//                        + statusCode+" for "+request);
//            }
            br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
            String line = null;
            while((line=br.readLine())!=null)
                buffer.append(line);
            br.close();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ConnectTimeoutException e) {
            System.out.println("Connection Timeout exception for "+request);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            client.getConnectionManager().shutdown();
        }
        return buffer.toString();
    }


    public String getRequest_WithoutSession(String request, String uri) {

        HttpURLConnection connection = null;
        BufferedReader rd  = null;
        StringBuilder sb = null;
        String line = null;


        try {
            java.net.URL URL = new java.net.URL(uri);

            //Set up the initial connection
            connection = (HttpURLConnection)URL.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setReadTimeout(10000);

            connection.connect();

            //read the result from the server
            rd  = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            sb = new StringBuilder();

            while ((line = rd.readLine()) != null)
            {
                sb.append(line + '\n');
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public String getRequest_WithSession(String request, String uri,Map<String,String> response_AddToCart) throws Exception{

        HttpURLConnection connection = null;
        BufferedReader rd  = null;
        StringBuilder sb = null;
        String line = null;

        java.net.URL URL = new java.net.URL(uri);

        //Set up the initial connection
        connection = (HttpURLConnection)URL.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "fk_android_app");
        connection.setRequestProperty("nsid", response_AddToCart.get("nsid").toString());
        connection.setRequestProperty("vid", response_AddToCart.get("vid").toString());
        connection.setDoOutput(true);
        connection.setReadTimeout(10000);
        connection.connect();

        //read the result from the server
        rd  = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        sb = new StringBuilder();

        while ((line = rd.readLine()) != null)
        {
            sb.append(line + '\n');
        }
        return sb.toString();
    }


    public String postRequest(String request, String uri, String data) {

        StringBuffer response = new StringBuffer();

        try
        {

        String url = uri;
        java.net.URL obj = new java.net.URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add request header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "fk_android_app");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");


        String urlParameters = data;

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        }
        catch(MalformedURLException e)
        {

        }
        catch(ProtocolException e)
        {

        }
        catch(IOException e)
        {

        }

        return response.toString();


    }

    public String postRequest_WithSession(String request,String uri,String loginData, Map<String,String> sessionData) throws Exception {

        StringBuffer response = new StringBuffer();

//        try
//        {

            String url = uri;
            java.net.URL obj = new java.net.URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add request header
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "fk_android_app");
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            con.setRequestProperty("nsid", sessionData.get("nsid").toString());
            con.setRequestProperty("vid", sessionData.get("vid").toString());


            String urlParameters = loginData;

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

        //}
//        catch(MalformedURLException e)
//        {
//
//        }
//        catch(ProtocolException e)
//        {
//
//        }
//        catch(IOException e)
//        {
//
//        }


        return response.toString();
    }



}
