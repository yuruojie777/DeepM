package com.ele5620.deepm.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.DigestUtils;

import java.util.Map;
import java.util.UUID;

public class CommonUtil {

    //generate random string
    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    //MD5 encrypt
    public static String md5(String key) {

        return DigestUtils.md5DigestAsHex(key.getBytes());
    }

    public static String getJSONString(int code, String msg, Map<String, Object> map) {
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);
        if(map != null) {
            for (String key : map.keySet()) {
                json.put(key, map.get(key));
            }
        }
        return json.toString();
    }

    public static String getJSONString(int code, String msg) {
        return getJSONString(code, msg,null);
    }
    public static String getJSONString(int code) {
        return getJSONString(code, null, null);
    }

    public static void main(String[] args) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("name", "haha");
//        map.put("age", 32);
//        System.out.println(getJSONString(0,"ok",map));
        System.out.println(CommonUtil.md5("hello" + "eaabe"));
    }
}
