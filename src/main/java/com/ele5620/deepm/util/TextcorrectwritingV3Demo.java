package com.ele5620.deepm.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;




public class TextcorrectwritingV3Demo {

    private static Logger logger = LoggerFactory.getLogger(TextcorrectwritingV3Demo.class);

    public static final String YOUDAO_URL = "https://openapi.youdao.com/correct_writing_text";

    private static final String APP_KEY = "69e3dafdeadf5295";

    private static final String APP_SECRET = "949ErcsOYRUaemDsKvYX8Didq6AEB50J";

    public static void main(String[] args) throws IOException {


    }

    public static Map<String, Object> requestForAIMarking(String essayContent) throws IOException {

        Map<String,String> params = new HashMap<String,String>();
        String q = essayContent;
        String grade = "default";
        String salt = String.valueOf(System.currentTimeMillis());
        String curtime = String.valueOf(System.currentTimeMillis() / 1000);
        params.put("curtime", curtime);
        String signStr = APP_KEY + truncate(q) + salt + curtime + APP_SECRET;
        String sign = getDigest(signStr);
        params.put("appKey", APP_KEY);
        params.put("q", q);
        params.put("grade", grade);
        params.put("salt", salt);
        params.put("sign", sign);
        params.put("signType", "v3");

        Map<String, Object> result = requestForHttp(YOUDAO_URL, params);
        return result;
    }

    private static Map<String, Object> requestForHttp(String url,Map<String,String> params) throws IOException {

        /** 创建HttpClient */
        CloseableHttpClient httpClient = HttpClients.createDefault();

        /** httpPost */
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
        Iterator<Map.Entry<String,String>> it = params.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String,String> en = it.next();
            String key = en.getKey();
            String value = en.getValue();
            paramsList.add(new BasicNameValuePair(key,value));
        }
        httpPost.setEntity(new UrlEncodedFormEntity(paramsList,"UTF-8"));
        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
        try{
            Header[] contentType = httpResponse.getHeaders("Content-Type");
            logger.info("Content-Type:" + contentType[0].getValue());

            /** 响应不是音频流，直接显示结果 */
            HttpEntity httpEntity = httpResponse.getEntity();
            String json = EntityUtils.toString(httpEntity,"UTF-8");
            EntityUtils.consume(httpEntity);
            logger.info(json);

            JSONObject jsonObj = JSONObject.parseObject(json);
            Map<String, Object> map = new HashMap<String, Object>();

            JSONObject result = jsonObj.getJSONObject("Result");
            JSONObject majorScore = result.getJSONObject("majorScore");

            String essayAdvice = result.getString("essayAdvice");


            Float wordScore = majorScore.getFloat("WordScore");
            Float grammerScore = majorScore.getFloat("GrammarScore");
            Float topicScore = majorScore.getFloat("topicScore");
            Float structureScore = majorScore.getFloat("StructureScore");


            Float totalScore = (wordScore + grammerScore + topicScore + structureScore) / 4;


            map.put("grammarAdvice", essayAdvice);
            map.put("wordScore", wordScore);
            map.put("grammerScore", grammerScore);
            map.put("topicScore", topicScore);
            map.put("totalScore", totalScore);

            return map;

        }finally {
            try{
                if(httpResponse!=null){
                    httpResponse.close();
                }
            }catch(IOException e){
                logger.info("## release resouce error ##" + e);
            }
        }
    }

    /**
     * 生成加密字段
     */
    public static String getDigest(String string) {
        if (string == null) {
            return null;
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        byte[] btInput = string.getBytes();
        try {
            MessageDigest mdInst = MessageDigest.getInstance("SHA-256");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    /**
     *
     * @param result 音频字节流
     * @param file 存储路径
     */
    private static void byte2File(byte[] result, String file) {
        File audioFile = new File(file);
        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream(audioFile);
            fos.write(result);

        }catch (Exception e){
            logger.info(e.toString());
        }finally {
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static String truncate(String q) {
        if (q == null) {
            return null;
        }
        int len = q.length();
        String result;
        return len <= 20 ? q : (q.substring(0, 10) + len + q.substring(len - 10, len));
    }

//    private static JSONObject createJSONObject(String jsonString){
//        JSONObject  jsonObject=new JSONObject();
//        JSONParser jsonParser=new  JSONParser();
//        if ((jsonString != null) && !(jsonString.isEmpty())) {
//            try {
//                jsonObject=(JSONObject) jsonParser.parse(jsonString);
//            } catch (org.json.simple.parser.ParseException e) {
//                e.printStackTrace();
//            }
//        }
//        return jsonObject;
//    }
}

