/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Tree
 * Author:   Administrator
 * Date:     2019/8/30 16:53
 * Description: 树
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.tgdz.fkptceshi.entity;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈智能分析相关请求参数〉
 *
 */
public class AIAnalysis {

    private String rtsp;
    private String puid;
    private String findname;
    private String addperson;
    private String facedata;
    private String delperson;
    private String rects;
    private String function;
    private String picdata;

    /*
    * 警示区阈值，1-100
    */
    private String threshold;

    public String getRtsp() {
        return rtsp;
    }

    public void setRtsp(String rtsp) {
        this.rtsp = rtsp;
    }

    public String getPuid() {
        return puid;
    }

    public void setPuid(String puid) {
        this.puid = puid;
    }

    public String getFindname() {
        return findname;
    }

    public void setFindname(String findname) {
        this.findname = findname;
    }

    public String getAddperson() {
        return addperson;
    }

    public void setAddperson(String addperson) {
        this.addperson = addperson;
    }

    public String getRects() {
        return rects;
    }

    public void setRects(String rects) {
        this.rects = rects;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getFacedata() {
        return facedata;
    }

    public void setFacedata(String facedata) {
        this.facedata = facedata;
    }

    public String getDelperson() {
        return delperson;
    }

    public void setDelperson(String delperson) {
        this.delperson = delperson;
    }

    public String getPicdata() {
        return picdata;
    }

    public void setPicdata(String picdata) {
        this.picdata = picdata;
    }

    public String getThreshold() {
        return threshold;
    }

    public void setThreshold(String threshold) {
        this.threshold = threshold;
    }
}
