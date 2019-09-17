package com.pro.warehouse.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DataConvertUtil {
    public static String value = "<dymx>=苏(2016)苏州市不动产证明第8022335号#宁波银行股份有限公司苏州分行#3360000人民币元#143.1##最高额抵押#2016/9/7--2021/9/7 \n"+
            "<cfmx>=#苏州市相城区人民法院#2018/7/4#有效#查封#@#苏州市相城区人民法院#2018/7/4#有效#轮候查封#";

    public static void main(String[] args){
        convert(value);
    }

    public static void convert(String s){
        String[] vallues = value.split("<cfmx>=");
        String temp1 = vallues[0];
        String temp2 = vallues[1];
        String[] value1 = temp1.replaceAll("<dymx>=","").split("#");
        System.out.println(Arrays.asList(value1));
        String[] value22 = temp2.split("@");
        List<TempData> listValue = new ArrayList<>();
        for(String tempStr : value22){
            tempStr = tempStr.substring(1,tempStr.length()-1);
            String[] tmpValue = tempStr.split("#");
            TempData tempData = new TempData();
            tempData.setLocName(tmpValue[0]);
            tempData.setDate(tmpValue[1]);
            tempData.setIsValid(tmpValue[2]);
            tempData.setStatus(tmpValue[3]);
            listValue.add(tempData);
        }
        System.out.println(listValue);

    }

}

class TempData{
    public String locName;
    public String date;
    public String isValid;
    public String status;

    public String getLocName() {
        return locName;
    }

    public void setLocName(String locName) {
        this.locName = locName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{" +
                "locName='" + locName + '\'' +
                ", date='" + date + '\'' +
                ", isValid='" + isValid + '\'' +
                ", status='" + status + '\'' +
                '}'+"\n";
    }
}


