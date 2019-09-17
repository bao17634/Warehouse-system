package com.pro.warehouse.controller;

import com.pro.warehouse.dao.CommonRepository;
import com.pro.warehouse.pojo.ApplyEnter;
import com.pro.warehouse.pojo.LogOperation;
import com.pro.warehouse.pojo.LogSystem;
import com.pro.warehouse.util.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class LogController {

    Logger logger = LoggerFactory.getLogger(LogController.class.getName());
    @Autowired
    private CommonRepository<LogOperation> commonRepository;

    @Autowired
    private CommonRepository<LogSystem> commonRepository2;

    // 通过@Resource注解引入JdbcTemplate对象
    @Resource
    private JdbcTemplate jdbcTemplate;

    private Integer pagesize = 20;//每页显示的条数

    @RequestMapping(value = "/log-operation",method = {RequestMethod.GET, RequestMethod.POST})
    public String getLogOperationList(LogOperation log,int pagenum, ModelMap modelMap){
        StringBuffer sql = null;
        try {
            sql = commonRepository.getFiledValues(log, pagenum);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        sql.append(" 1 = 1 ORDER BY date DESC");
        int totalpage = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(LogOperation.class)).size();
        sql.append(" LIMIT " + (pagenum - 1) * pagesize + "," + pagesize);
        List<LogOperation> operations = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(LogOperation.class));
        logger.debug("获取操作日志" + operations);
        modelMap.addAttribute("operations",operations);
        modelMap.addAttribute("page", pagenum);
        modelMap.addAttribute("totalpage", PageUtil.getTotalPage(totalpage, pagesize));
        return "log_operation";
    }

    @RequestMapping(value = "/log-system",method = {RequestMethod.GET, RequestMethod.POST})
    public String getLogOperationList(LogSystem log, int pagenum, ModelMap modelMap){
        StringBuffer sql = null;
        try {
            sql = commonRepository2.getFiledValues(log, pagenum);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        sql.append(" 1 = 1 ORDER BY logDate DESC");
        int totalpage = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(LogSystem.class)).size();
        sql.append(" LIMIT " + (pagenum - 1) * pagesize + "," + pagesize);
        List<LogSystem> operations = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(LogSystem.class));
        logger.debug("获取操作日志" + operations);
        modelMap.addAttribute("operations",operations);
        modelMap.addAttribute("page", pagenum);
        modelMap.addAttribute("totalpage", PageUtil.getTotalPage(totalpage, pagesize));
        return "log_system";
    }
}
