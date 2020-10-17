package com.tgdz.fkptceshi;

import com.alibaba.fastjson.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class FkptceshiApplication {

    public static void main(String[] args) {
        System.out.println("00000000000000");
        System.out.println(System.getProperty("java.library.path"));
        String url = "http://127.0.0.1:8090/json/data";
        //post请求
        JSONObject json = new JSONObject();
        json.put("name", "wangru");
        json.put("sex", "男");
        json.put("age", "27");
        json.put("address", "Jinan China");
        json.put("time", new Date());
        System.out.print("发送数据："+json.toString());
        SpringApplication.run(FkptceshiApplication.class, args);
    }

}
