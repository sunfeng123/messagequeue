package com.tgdz.fkptceshi.controller;

import com.alibaba.fastjson.JSONObject;
import com.tgdz.fkptceshi.entity.DataResult;
import com.tgdz.fkptceshi.entity.analyseCore;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

@RestController
@ResponseBody
public class JsonTestProvider {
    /**
     * 创建日期:2018年4月6日<br/>
     * 代码创建:黄聪<br/>
     * 功能描述:通过request的方式来获取到json数据<br/>
     * @param * 这个是阿里的 fastjson对象
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/json/data", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getByJSON(@RequestBody JSONObject jsonParam) {
        // 直接将json信息打印出来
        System.out.println(jsonParam.toJSONString());

        // 将获取的json数据封装一层，然后在给返回
        JSONObject result = new JSONObject();
        result.put("msg", "ok");
        result.put("method", "json");
        result.put("data", jsonParam);

        return result.toJSONString();
    }

    /**
     * 创建日期:2018年4月6日<br/>
     * 代码创建:黄聪<br/>
     * 功能描述:通过HttpServletRequest 的方式来获取到json的数据<br/>
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/request/data", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String getByRequest(HttpServletRequest request) {

        //获取到JSONObject
        JSONObject jsonParam = this.getJSONParam(request);
        System.out.println(jsonParam.get("function").toString());
        // 将获取的json数据封装一层，然后在给返回
        JSONObject result = new JSONObject();
        result.put("puid", "ok");
        result.put("method", "request");
        result.put("data", jsonParam);

        return result.toJSONString();
    }

    /**
     * 创建日期:2018年4月6日<br/>
     * 代码创建:黄聪<br/>
     * 功能描述:通过request来获取到json数据<br/>
     * @param request
     * @return
     */
    @PostMapping(path = "/znfxcs")
    public JSONObject getJSONParam(HttpServletRequest request){
        JSONObject jsonParam = null;
        try {
            // 获取输入流
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));

            // 写入数据到Stringbuilder
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = streamReader.readLine()) != null) {
                sb.append(line);
            }
            jsonParam = JSONObject.parseObject(sb.toString());
            // 直接将json信息打印出来
            System.out.println(jsonParam.toJSONString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonParam;
    }

    @GetMapping("/demo/{id}")
    public void demo(@PathVariable(name = "id") String id, @RequestParam(name = "name") String name) {
        System.out.println("id="+id);
        System.out.println("name="+name);
    }

    @PostMapping(path = "/znfx")
    public Map<String, Object> demo1(@RequestBody Map<String, Object> response) {
        System.out.println(response.get("pic_data"));
        DataResult dataResult = new DataResult();
        Object objectFunction = response.get("function");
        if(objectFunction!=null){
           dataResult.setFunction(objectFunction.toString());
        }else {
            dataResult.setFunction("2");
        }

        Object objectnumOfHead = response.get("numOfHead");
        if(objectnumOfHead!=null){
            dataResult.setNumOfHead(objectnumOfHead.toString());
        }else {
            dataResult.setNumOfHead("0");
        }

        Object objectnumOfHelmet = response.get("numOfHelmet");
        if(objectnumOfHelmet!=null){
            dataResult.setNumOfHelmet(objectnumOfHelmet.toString());
        }else {
            dataResult.setNumOfHelmet("0");
        }

        Object numOfNotHelmet = response.get("numOfNotHelmet");
        if(numOfNotHelmet!=null){
            dataResult.setNumOfNotHelmet(numOfNotHelmet.toString());
        }else {
            dataResult.setNumOfNotHelmet("0");
        }

        Object pic_data = response.get("pic_data");
        if(pic_data!=null){
            dataResult.setPic_data(pic_data.toString());
        }else {
            dataResult.setPic_data("null");
        }

        Object objectState = response.get("state");
        if(objectState!=null){
            dataResult.setState(objectState.toString());
        }else {
            dataResult.setState("0");
        }

        Object time_stamp = response.get("time_stamp");
        if(time_stamp!=null){
            dataResult.setTime_stamp(time_stamp.toString());
        }else {
            dataResult.setTime_stamp("0");
        }
        /*List<Object >list=new ArrayList<Object>();
        list= (List<Object>) response.get("data");*/
       /* List<analyseCore> analyseCoreList = new ArrayList<analyseCore>();
        List<Map<String, String> >list= (List<Map<String, String>>) response.get("data");
        Iterator<Map<String, String>> it = list.iterator();
        while (it.hasNext()) {
            Map<String, String> scoreMap = it.next();
        }*/




        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        result.put("data",response.get("data"));
        result.put("function", dataResult.getFunction());
        result.put("numOfHead", dataResult.getNumOfHead());
        result.put("numOfHelmet", dataResult.getNumOfHelmet());
        result.put("numOfNotHelmet", dataResult.getNumOfNotHelmet());
        result.put("pic_data", dataResult.getPic_data());
        result.put("srcpic_data", dataResult.getSrcpic_data());
        result.put("state", dataResult.getState());
        result.put("time_stamp", dataResult.getTime_stamp());
        return result;
    }
}
