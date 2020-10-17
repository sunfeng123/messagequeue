package com.tgdz.fkptceshi.tools;



import com.alibaba.fastjson.JSONObject;

import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
import com.tgdz.fkptceshi.entity.AIAnalysis;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class AIAnalysistool {

    @Value("${puid}")
    private String puid;
    @Value("${file.path}")
    private String filepath;


    //智能分析测试
    @Scheduled(fixedDelay = 1000*30)
    public void testznfx()  {
        //api url地址
        //String url = "http://127.0.0.1:8080/znfxcs/";
        String url="http://25.34.51.24:8008/safety/cap/predict/";
        //post请求
       HttpMethod method =HttpMethod.POST;
      /*   JSONObject json = new JSONObject();
        StringBuilder rtsp =new StringBuilder();
        rtsp.append("rtsp://25.34.24.163:35102/DevAor=");
        rtsp.append(puid);
        json.put("rtsp",rtsp);
        json.put("puid","python");
        json.put("functn","python");
        String ceshi=json.toString();
        System.out.print("发送数据："+json.toString());*/
        //发送http请求并返回结果
        AIAnalysis aiAnalysis=new AIAnalysis();
        StringBuilder rtsp =new StringBuilder();
        rtsp.append("rtsp://25.34.24.163:35102/DevAor=");
        rtsp.append(puid);
        aiAnalysis.setRtsp(rtsp.toString());
        aiAnalysis.setFunction("python");
        aiAnalysis.setPuid("python");
        String result = null;
        try {
            //System.out.print("发送数据："+json.toString());
            System.out.print("发送数据测试智能识别000：");
            //result = TestHttp.HttpRestClient(url,method, (JSONObject) JSONObject.toJSON(aiAnalysis));
            //result = TestHttpnv.HttpRestClient(url,(com.alibaba.fastjson.JSONObject) com.alibaba.fastjson.JSONObject.toJSON(aiAnalysis));
            //01
            //result = TestHttp.HttpRestClient(url,method,(com.alibaba.fastjson.JSONObject) com.alibaba.fastjson.JSONObject.toJSON(aiAnalysis));
            //02
            //result = TestHttpnv.HttpRestClient(url,(JSONObject) JSONObject.toJSON(aiAnalysis));
            //03
            result = TestHttpnv.HttpRestClient(url,(JSONObject) JSONObject.toJSON(aiAnalysis));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print("接收反馈："+result);
        if(result!=null || result.length()!=0){
            //JSONObject jsonObject = JSONObject.fromObject(result);
            Map<String, Object> mapJ = JSON.parseObject(result, Map.class);

            //JSONObject jsonObject = JSONObjcet.parseObject(result);
            System.out.println("function:"+mapJ.get("function").toString());
            System.out.println("numOfHead:"+mapJ.get("numOfHead").toString());
            System.out.println("numOfHelmet:"+mapJ.get("numOfHelmet").toString());
            System.out.println("numOfNotHelmet:"+mapJ.get("numOfNotHelmet").toString());
            System.out.println("pic_data:"+mapJ.get("pic_data").toString());
            if(mapJ.get("pic_data").toString()!=null || mapJ.get("pic_data").toString().length()>0){
                if(mapJ.get("state").toString().equals("2")){
                    System.out.print("接收反馈："+"开始生成未佩戴安全帽图片");
                    GenerateImage(mapJ.get("pic_data").toString());
                }
                else if(mapJ.get("state").toString().equals("1")){
                    System.out.print("接收反馈："+"佩戴安全帽图片");
                    GenerateImage(mapJ.get("pic_data").toString());
                }else {
                    System.out.print("接收反馈："+"画面中无人员");
                    GenerateImage(mapJ.get("pic_data").toString());
                }

            }


            List<Map<String,String>> items=new ArrayList<Map<String,String>>();
            if(mapJ.get("data")!=null){
                items= (List<Map<String, String>>) mapJ.get("data");
                if(items.size()>0){
                    for(int i=0;i<items.size();i++){
                        System.out.println(String.valueOf(items.get(i).get("alertFlag")));
                        System.out.println(String.valueOf(items.get(i).get("color")));
                        System.out.println(String.valueOf(items.get(i).get("timeStamp")));
                    }
                }

            }



        }
        System.out.print("接收反馈："+result);
    }

    //base64字符串转化成图片
    public boolean GenerateImage(String imgStr)
    {   //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try
        {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
            //生成jpeg图片
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String orderNo = dateFormat.format(new Date());//17位时间戳
            String imgFilePath = filepath+orderNo+".jpg";//新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
}



