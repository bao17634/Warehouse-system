package com.pro.warehouse.controller;

import com.pro.warehouse.constant.ApplyStatus;
import com.pro.warehouse.dao.CommonRepository;
import com.pro.warehouse.dao.EntrepotStatusRepository;
import com.pro.warehouse.pojo.ApplyEnter;
import com.pro.warehouse.pojo.EntrepotStatus;
import com.pro.warehouse.pojo.User;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 库存管理API
 */
@Controller
public class EntrepotStatusController {
    Logger logger = LoggerFactory.getLogger(EntrepotStatusController.class.getName());
    @Autowired
    private EntrepotStatusRepository entrepotStatusRepository;
    // 通过@Resource注解引入JdbcTemplate对象
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CommonRepository<EntrepotStatus> commonRepository;

    private Integer pagesize = 20;//每页显示的条数

    @RequestMapping(value = "/entrepot-list", method = {RequestMethod.GET, RequestMethod.POST})
    public String FindAllApplyEnter(EntrepotStatus entrepotStatus, int pagenum, ModelMap modelMap) {
        String page = "stores_list";

        StringBuffer sql = null;
        try {
            sql = commonRepository.getFiledValues(entrepotStatus, pagenum);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        sql.append(" 1 = 1");
        int totalpage = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(EntrepotStatus.class)).size();
        sql.append(" LIMIT " + (pagenum - 1) * pagesize + "," + pagesize );
        List<EntrepotStatus> entrepots = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(EntrepotStatus.class));
        logger.debug("显示仓库列表：" + entrepots);
        modelMap.addAttribute("entrepot", entrepots);
        modelMap.addAttribute("page", pagenum);
        modelMap.addAttribute("totalpage", PageUtil.getTotalPage(totalpage, pagesize));
        return page;
    }

    @RequestMapping(value = "/entrepot-changestatus", method = {RequestMethod.GET, RequestMethod.POST})
    public String changeStatus(Long id,int goodProducts,int badProducts,String goodPosition, String badPosition ) throws Exception {
//        String goodPosition = (String) request.getAttribute("goodPosition");
//        String badPosition = (String) request.getAttribute("badPosition");
        logger.info("修改库存状态"+id+"  ");
        List<EntrepotStatus> entrepots = entrepotStatusRepository.findEntrepotStatusByid(id);
        logger.info("查找到的库存"+entrepots+"修改良品数量"+goodProducts+"  "+badProducts);
        if(entrepots.size()<=0||entrepots==null){
            throw new Exception("仓库中没有这个货物");
        }else{
            //入库料号
            EntrepotStatus entrepotStatus = entrepots.get(0);
            String enterMaterialCode = entrepotStatus.getMaterialCode();
            //入库编号
            String enterCode = entrepotStatus.getEnterCode();
            if(goodProducts==entrepotStatus.getTotalSize()){
                entrepotStatus.setGoodsStatus("良品");
                entrepotStatus.setUpdateDate(new Date());
                entrepotStatusRepository.save(entrepotStatus);
            }else if(goodProducts==0){
                entrepotStatus.setGoodsStatus("不良品");
                entrepotStatus.setUpdateDate(new Date());
                entrepotStatusRepository.save(entrepotStatus);
            }else {
                //封装一个良品记录
                EntrepotStatus goodStatsus = copyEntrepot(entrepotStatus);
                goodStatsus.setUpdateDate(new Date());
                goodStatsus.setGoodsStatus("良品");
                goodStatsus.setPosition(goodPosition);
                goodStatsus.setTotalSize(goodProducts);

                //封装不良品记录
                EntrepotStatus badStatus = copyEntrepot(entrepotStatus);
                badStatus.setGoodsStatus("不良品");
                badStatus.setPosition(badPosition);
                goodStatsus.setUpdateDate(new Date());
                badStatus.setTotalSize(entrepotStatus.getTotalSize()-goodProducts);
                //删除原先的
                entrepotStatusRepository.delete(entrepotStatus);
                //保存良品和不良品
                entrepotStatusRepository.save(goodStatsus);
                entrepotStatusRepository.save(badStatus);
            }
        }
        return "redirect:/entrepot-list?pagenum=1";
    }


    @RequestMapping(value = "/entrepot-search", method = {RequestMethod.GET, RequestMethod.POST})
    public String doSearch(String searchItem,String searchValue,EntrepotStatus entrepotStatus, int pagenum, ModelMap modelMap) {
        String page = "stores_list";

        StringBuffer sql = null;
        try {
            sql = commonRepository.getFiledValues(entrepotStatus, pagenum);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        int totalpage = 0;
        if(searchValue!=null||!"".equals(searchValue)) {
            if("良品".equals(searchValue)){
                sql.append(searchItem + " = '" + searchValue + "'");
            }else if("不良品".equals(searchValue)){
                sql.append(searchItem + " = '" + searchValue + "'");
            }else{
                sql.append(searchItem + " like '%" + searchValue + "%'");
            }
        }else{
            sql.append(" 1 = 1");
        }
        totalpage = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(EntrepotStatus.class)).size();
        sql.append(" LIMIT " + (pagenum - 1) * pagesize + "," + pagesize);
        List<EntrepotStatus> entrepots = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(EntrepotStatus.class));
        logger.debug("查询结果：" + entrepots+"查询语句"+sql);
        modelMap.addAttribute("entrepot", entrepots);
        modelMap.addAttribute("page", pagenum);
        modelMap.addAttribute("totalpage", PageUtil.getTotalPage(totalpage, pagesize));
        modelMap.addAttribute("searchValue", searchValue);

        return page;
    }

    /**
     * <p>将参数中的对象除了Id,封装成一个新的对象，并返回</p>
     * @return
     */
    public static EntrepotStatus copyEntrepot(EntrepotStatus status){
        EntrepotStatus entrepotStatus = new EntrepotStatus();
        entrepotStatus.setGoodsStatus("未检验");
        entrepotStatus.setProduceDate(status.getProduceDate());
        entrepotStatus.setTotalSize(status.getTotalSize());
        entrepotStatus.setEnterCode(status.getEnterCode());
        entrepotStatus.setUpdateDate(status.getUpdateDate());
        entrepotStatus.setPosition(status.getPosition());
        entrepotStatus.setTaxCode(status.getTaxCode());
        entrepotStatus.setSupplyName(status.getSupplyName());
        entrepotStatus.setProductName(status.getProductName());
        entrepotStatus.setMaterialSpec(status.getMaterialSpec());
        entrepotStatus.setEntrepotType(status.getEntrepotType());
        entrepotStatus.setEntranceDate(status.getEntranceDate());
        entrepotStatus.setMaterialCode(status.getMaterialCode());
        return entrepotStatus;
    }


    /**
     * 根据ID删除
     */
    @RequestMapping("/entrepot-deleteById")
    public String deleteApplyById(Long id, HttpServletRequest request) {
        //System.err.println("删除ID"+enterId);
        List<EntrepotStatus> entrepotStatus = entrepotStatusRepository.findEntrepotStatusByid(id);
        if(entrepotStatus.size()>0) {
            entrepotStatusRepository.delete(entrepotStatus.get(0));
        }
        return "redirect:/entrepot-list?pagenum=1";
    }

    /**
     * 根据ID删除
     */
    @RequestMapping("/entrepot-deleteSearchById")
    public String deleteSearchApplyById(Long id, HttpServletRequest request) {
        //System.err.println("删除ID"+enterId);
        List<EntrepotStatus> entrepotStatus = entrepotStatusRepository.findEntrepotStatusByid(id);
        if(entrepotStatus.size()>0) {
            entrepotStatusRepository.delete(entrepotStatus.get(0));
        }
        return "redirect:/entrepot-list?pagenum=1";
    }
}
