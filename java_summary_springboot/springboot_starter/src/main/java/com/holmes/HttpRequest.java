/*
 * Copyright @ 2015 com.iflysse.trains
 * CodeLib 上午11:16:01
 * All right reserved.
 *
 */
package com.holmes;

import com.alibaba.fastjson.JSON;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * 模拟restful请求工具类
 */
public class HttpRequest {

    /**
     * 模拟GET请求
     *
     * @param url
     * @param param
     * @return
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL URL = new URL(urlNameString);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) URL.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();

//            Map<String, List<String>> map = connection.getHeaderFields();
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 模拟POST请求
     *
     * @param url
     * @param param
     */
    public static String sendPost(String url, String param) {

        String result = null;
        try {
            URL URL = new URL(url);

            HttpURLConnection connection = (HttpURLConnection) URL.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            // 设置请求方式
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");

            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);

            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            connection.connect();

            DataOutputStream out = new DataOutputStream(connection.getOutputStream());

            out.write(param.getBytes());
            out.flush();
            out.close();

            if (connection.getResponseCode() == 200) {

                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = null;
                if ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();
                result = sb.toString();
            }
            // 断开连接
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 模拟PUT请求
     *
     * @param url
     * @param param
     * @return
     */
    public static String sendPut(String url, String param) {

        String result = null;
        try {
            URL URL = new URL(url);

            HttpURLConnection connection = (HttpURLConnection) URL.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            // 设置请求方式
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");

            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);

            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            connection.connect();

            DataOutputStream out = new DataOutputStream(connection.getOutputStream());

            out.write(param.getBytes());
            out.flush();
            out.close();

            if (connection.getResponseCode() == 200) {

                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = null;
                if ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();
                result = sb.toString();
            }
            // 断开连接
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 模拟DELETE请求
     *
     * @param url
     * @param param
     * @return
     */
    public static String sendDelete(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL URL = new URL(urlNameString);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) URL.openConnection();
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();

//            Map<String, List<String>> map = connection.getHeaderFields();
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }


    public static void main(String[] args) {

//        System.out.println(HttpRequest.sendGet("http://localhost:9090/hello/user/get/1", ""));
//        System.out.println(HttpRequest.sendGet("http://localhost:9090/hello/user/get/2", ""));
//
//        HashMap<String, Object> paramMap = new HashMap<>(2);
//        paramMap.put("name", "张三");
//        paramMap.put("age", 26);
//
//        System.out.println(HttpRequest.sendPost("http://localhost:9090/hello/user/addUser", JSON.toJSONString(paramMap)));
//
//        paramMap = new HashMap<>(3);
//        paramMap.put("name", "张三");
//        paramMap.put("age", 28);
//        paramMap.put("id", 1);
//
//        System.out.println(HttpRequest.sendPut("http://localhost:9090/hello/user/update", JSON.toJSONString(paramMap)));
//
//        System.out.println(HttpRequest.sendDelete("http://localhost:9090/hello/user/delete/5", ""));
//        System.out.println(HttpRequest.sendDelete("http://localhost:9090/hello/user/delete/9", ""));

        System.out.println(HttpRequest.sendGet("http://localhost:9090/test/user/getAll", ""));

    }
}
