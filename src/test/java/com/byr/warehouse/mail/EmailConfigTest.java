package com.byr.warehouse.mail;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.byr.warehouse.pojo.DataModel;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class EmailConfigTest {
    @Autowired
    MailService mailService;

    @Test
    public void sendAttachmentsMail() throws UnsupportedEncodingException, JsonProcessingException {
        List<DataModel> data = new ArrayList<>();
        data.add(new DataModel("111","11"));
        data.add(new DataModel("222","22"));
        System.out.println(JSON.toJSONString(data));
    }

}