package com.pro.warehouse.controller;

import com.pro.warehouse.dao.CommonRepository;
import com.pro.warehouse.dao.FreightSpaceRespository;
import com.pro.warehouse.pojo.FreightSpace;
import com.pro.warehouse.util.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/freightSpace")
public class FreightSpaceController {
    Logger logger = LoggerFactory.getLogger(FreightSpaceController.class.getName());
    @Autowired
    private FreightSpaceRespository freightSpaceRespository;
    @Autowired
    private CommonRepository<FreightSpace> commonRepository;
    @Resource
    private JdbcTemplate jdbcTemplate;
    private Integer pagesize=3;//每页显示的条数
    @RequestMapping("/getAll")
    public String getAlllFreightSpace(FreightSpace freightSpace, int pagenum, ModelMap modelMap)
    {
        String page="location_detail";
        if(freightSpace!=null)
        {
            StringBuffer sql=null;
            try {
                sql=commonRepository.getFiledValues(freightSpace, pagenum);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            int totalpage=jdbcTemplate.query(sql.toString(),new BeanPropertyRowMapper(FreightSpace.class)).size();
            sql.append(" LIMIT "+(pagenum-1)+","+pagesize);
            List<FreightSpace> freightSpaces=jdbcTemplate.query(sql.toString(),new BeanPropertyRowMapper(FreightSpace.class));
            modelMap.addAttribute("applys",freightSpaces);
            modelMap.addAttribute("page",pagenum);
            modelMap.addAttribute("totalpage", PageUtil.getTotalPage(totalpage,pagesize));
        }else {
            Pageable pageable= PageRequest.of(pagenum,pagesize);
            Page<FreightSpace> pager=freightSpaceRespository.findAll(pageable);
            modelMap.addAttribute("applys",pager.getContent());
            modelMap.addAttribute("page",pagenum);
            modelMap.addAttribute("totalpage",pager.getTotalPages());
        }
        return page;
    }

    @RequestMapping("/delete")
    public String DeleteFreightSpace(int id,ModelMap modelMap)
    {
        FreightSpace freightSpace = freightSpaceRespository.findFreightSpaceByid(id);
        freightSpaceRespository.delete(freightSpace);
        return "redirect:freightSpace/getAll?pagenum=1";
    }
    @RequestMapping("/add")
    public String AddFreightSpace(FreightSpace freightSpace,ModelMap modelMap)
    {
        freightSpaceRespository.save(freightSpace);
        return "redirect:freightSpace/getAll?pagenum=1";
    }


}
