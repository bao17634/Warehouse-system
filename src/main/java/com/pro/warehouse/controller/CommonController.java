package com.pro.warehouse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class CommonController {
    Logger logger = LoggerFactory.getLogger(CommonController.class.getName());
    @RequestMapping(value ="/{page}",method = {RequestMethod.GET})
    public String changePage(@PathVariable String page)
    {
             return page;
    }

    @RequestMapping("toSendForm")
    public String toSendForm(){
        return "send_goods_form";
    }

}
