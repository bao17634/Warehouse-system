package com.pro.warehouse.controller;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.pro.warehouse.Service.EntrepotStatusService;
import com.pro.warehouse.Service.ExcelService;
import com.pro.warehouse.Service.LogService;
import com.pro.warehouse.constant.ApplyStatus;
import com.pro.warehouse.constant.Operation;
import com.pro.warehouse.dao.ApplyOutPutRepository;
import com.pro.warehouse.dao.CommonRepository;
import com.pro.warehouse.dao.EntrepotStatusRepository;
import com.pro.warehouse.myexception.StoreException;
import com.pro.warehouse.pojo.ApplyEnter;
import com.pro.warehouse.pojo.ApplyOutPut;
import com.pro.warehouse.pojo.EntrepotStatus;
import com.pro.warehouse.pojo.User;
import com.pro.warehouse.util.PageUtil;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.mail.Store;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Controller
public class ApplyOutController {
    Logger logger = LoggerFactory.getLogger(ApplyOutController.class.getName());
    @Autowired
    private EntrepotStatusRepository entrepotStatusRepository;
    @Autowired
    private ApplyOutPutRepository applyOutPutRepository;
    @Autowired
    private EntrepotStatusService entrepotStatusService;
    @Resource
    private ResourceLoader resourceLoader;
    @Autowired
    private ExcelService<ApplyOutPut> excelService;
    // 通过@Resource注解引入JdbcTemplate对象
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CommonRepository<ApplyOutPut> commonRepository;
    @Autowired
    private LogService logService;
    private Integer pagesize = 20;//每页显示的条数

    /**
     * @param applyOutPut
     * @param pagenum
     * @param modelMap
     * @return
     */
    @RequestMapping("/applyout-getHistory")
    public String getHistory(ApplyOutPut applyOutPut, int pagenum, ModelMap modelMap) {
        String page = "exit_apply_history";
        if (applyOutPut != null) {
            StringBuffer sql = null;
            try {
                sql = commonRepository.getFiledValues(applyOutPut, pagenum);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            sql.append("Status ='已确认' OR Status ='已退回'");
            int totalpage = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(ApplyOutPut.class)).size();
            sql.append(" LIMIT " + (pagenum - 1) * pagesize + "," + pagesize);
            List<ApplyOutPut> applyOutPuts = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(ApplyOutPut.class));
            modelMap.addAttribute("applys", applyOutPuts);
            modelMap.addAttribute("page", pagenum);
            modelMap.addAttribute("totalpage", PageUtil.getTotalPage(totalpage, pagesize));
        } else {
            Pageable pageable = PageRequest.of(pagenum, pagesize);
            Page<ApplyOutPut> pager = applyOutPutRepository.findApplyOutPutByStatus(ApplyStatus.ENSURE, pageable);
            modelMap.addAttribute("applys", pager.getContent());
            modelMap.addAttribute("page", pagenum);
            modelMap.addAttribute("totalpage", pager.getTotalPages());
        }
        return page;
    }

    //获得未确认的当前登陆人的申请信息
    @RequestMapping("/applyout-getNotAllowed")
    public String getNotAllowed(ApplyOutPut applyOutPut, int pagenum, ModelMap modelMap, HttpServletRequest request) throws Exception {
        String page = "exit_apply";
        //获得当前登陆用户的ID
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            throw new StoreException("用户尚未登录");
        }
        Long userId = user.getId();
        if (applyOutPut != null) {
            StringBuffer sql = null;
            try {
                sql = commonRepository.getFiledValues(applyOutPut, pagenum);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            sql.append("  Status !='已确认' AND Status !='已退回' AND applyPersonId = '" + user.getUsername()+"'");
            int totalpage = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(ApplyOutPut.class)).size();
            sql.append(" LIMIT " + (pagenum - 1) * pagesize + "," + pagesize);
            List<ApplyOutPut> applyEnters = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(ApplyOutPut.class));
            modelMap.addAttribute("applys", applyEnters);
            modelMap.addAttribute("page", pagenum);
            modelMap.addAttribute("totalpage", PageUtil.getTotalPage(totalpage, pagesize));
        } else {
            Pageable pageable = PageRequest.of(pagenum, pagesize);
            Page<ApplyOutPut> pager = applyOutPutRepository.findApplyOutPutByStatusNot(ApplyStatus.ENSURE, pageable);
            modelMap.addAttribute("applys", pager.getContent());
            modelMap.addAttribute("page", pagenum);
            modelMap.addAttribute("totalpage", pager.getTotalPages());
        }
        modelMap.addAttribute("username", user.getUsername());
        return page;
    }


    //需要审批的申请
    @RequestMapping("/applyout-getToBeEnsured")
    public String getToBeEnsured(ApplyOutPut applyOutPut, int pagenum, ModelMap modelMap, HttpServletRequest request) {
        String page = "exit_apply_wait";
        //获得当前登陆用户的ID
        if (applyOutPut != null) {
            StringBuffer sql = null;
            try {
                sql = commonRepository.getFiledValues(applyOutPut, pagenum);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            sql.append("  Status !='已确认' AND Status != '被拒绝' AND Status != '已退回'");
            int totalpage = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(ApplyOutPut.class)).size();
            sql.append(" LIMIT " + (pagenum - 1) * pagesize + "," + pagesize);
            List<ApplyOutPut> applyEnters = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(ApplyOutPut.class));
            modelMap.addAttribute("applys", applyEnters);
            modelMap.addAttribute("page", pagenum);
            modelMap.addAttribute("totalpage", PageUtil.getTotalPage(totalpage, pagesize));
        } else {
            Pageable pageable = PageRequest.of(pagenum, pagesize);
            Page<ApplyOutPut> pager = applyOutPutRepository.findApplyOutPutByStatusNot(ApplyStatus.ENSURE, pageable);
            modelMap.addAttribute("applys", pager.getContent());
            modelMap.addAttribute("page", pagenum);
            modelMap.addAttribute("totalpage", pager.getTotalPages());
        }

        return page;
    }

    /**
     * 新增入库申请  跳转待审核页面
     */
    @RequestMapping(value = "/applyout-addapply", method = {RequestMethod.GET, RequestMethod.POST})
    public String saveApply(ApplyOutPut applyOutPut, BindingResult bindingResult, HttpServletRequest request, ModelMap modelMap) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            throw new StoreException("用户尚未登录");
        }
        Long userId = user.getId();
        applyOutPut.setApplyPersonid(user.getUsername());
        List<EntrepotStatus> entrots = entrepotStatusRepository.findByEnterCodeAndMaterialCode(applyOutPut.getEnterCode(),applyOutPut.getMaterialCode().trim().replaceAll(" +","%"));
        logger.debug("出库申请：从库存查找："+ applyOutPut.getEnterCode() + "  " + applyOutPut.getMaterialCode());
        logger.debug("查找结果："+entrots);
        if (entrots.size() > 0) {
            applyOutPut.setProduceDate(entrots.get(0).getProduceDate());
            applyOutPut.setStatus("待审核");
            applyOutPut.setApplyDate(new Date());
            //保存
            applyOutPutRepository.save(applyOutPut);
            request.getSession().setAttribute("message", "申请成功");
        } else {
            request.getSession().setAttribute("message", "仓库没有要申请出库的货物");
        }
        logService.saveOpLog(user.getUsername(), Operation.APPLY_OUT.getOperation(),"成功", JSON.toJSONString(applyOutPut));

        return "redirect:/applyout-getNotAllowed?pagenum=1";
    }

    /**
     * 审核出库申请(将状态改为“已确认”，并增加确认人的id，前台显示以名称显示)
     */
    @RequestMapping("/applyout-updateStatus")
    public String ensureApply(int id, HttpServletRequest request, ModelMap modelMap, HttpSession session) throws Exception {

        String page = "exit_apply_wait";
        //根据id查找出库申请
        ApplyOutPut output = applyOutPutRepository.findApplyOutPutById(id);
        String result = "失败";
        String detail = "";
        //获取料号
        String materialCode = output.getMaterialCode();
        //因为从页面直接复制的内容可能中间空格数量不符合，所以这里对其中的空格替换成%,以适配确认出库时的空格问题
        materialCode = materialCode.trim().replaceAll(" +","%");
        //获取仓位
        String code = output.getEnterCode();
        //output.getp
        //查找仓库中的料号
        EntrepotStatus entrepotTarget = null;
        List<EntrepotStatus> entrepotStatus = entrepotStatusRepository.findByEnterCodeAndMaterialCode(code, materialCode);
        if(entrepotStatus.isEmpty()){
            throw new StoreException("根据入库料号和入库编号找不到该货物");
        }
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "user-login";
        }
        logger.debug("出库确认：从库存查找：enterid"+code+"料号"+materialCode);
        logger.debug("查找结果："+entrepotStatus);
        if(entrepotStatus.size()==0||entrepotStatus==null){
            output.setStatus(ApplyStatus.REFUSED);
            output.setApplyPersonid(user.getUsername());
            output.setApplyDate(new Date());
            request.getSession().setAttribute("message","库存中没有该货物");
            detail = "库存中没有该货物";
        }
        //入库编号和料号可以确定货物，所以查找到的供货商时相同的
        if (output.getDemandName().equals(entrepotStatus.get(0).getSupplyName())) {
            //查找不良品
            EntrepotStatus badGodds = null;
            for(EntrepotStatus status:entrepotStatus){
                if("不良品".equals(status.getGoodsStatus())){
                    badGodds = status;
                }
            }
            if(badGodds.getTotalSize()>output.getSize()){
              //库存大于申请，正常出库
                badGodds.setTotalSize(badGodds.getTotalSize()-output.getSize());
                //修改数量
                entrepotStatusRepository.save(badGodds);
                //修改出库申请状态
                output.setApplyDate(new Date());
                output.setStatus(ApplyStatus.TURN_BACK);
                request.getSession().setAttribute("message","退回申请成功:退回-"+output.getSize()+",还剩不良品-"+badGodds.getTotalSize());
                result = "成功";
                detail = "退回申请成功:退回-"+output.getSize()+",还剩不良品-"+badGodds.getTotalSize();
            }else if(badGodds.getTotalSize()==output.getSize()){
                //库存大于申请，出库并删除记录
                entrepotStatusRepository.delete(badGodds);
                output.setStatus(ApplyStatus.TURN_BACK);
                request.getSession().setAttribute("message","退回申请成功");
                result = "成功";
                detail = "退回申请：";
            }else{
                //库存小于申请，返回异常值
                output.setStatus(ApplyStatus.REFUSED);
                request.getSession().setAttribute("message","申请数量大于库存，已自动拒绝");
                detail = "申请数量大于库存，已自动拒绝";
            }
            //如果提供商和提货商相同,则表示不良品退回
            //更新入库申请状态
            output.setEnsurePersonid(user.getUsername());
            output.setApplyDate(new Date());
            applyOutPutRepository.save(output);
        }else{
            //良品出库
            //查找良品
            EntrepotStatus goodGodds = null;
            for(EntrepotStatus status:entrepotStatus){
                if("良品".equals(status.getGoodsStatus())){
                    goodGodds = status;
                }
            }
            if(null==goodGodds){
                request.getSession().setAttribute("message","请检查货物是否检验");
                return "redirect:/applyout-getToBeEnsured?pagenum=1";
            }
            if(goodGodds.getTotalSize()>output.getSize()){
                //库存大于申请，正常出库
                goodGodds.setTotalSize(goodGodds.getTotalSize()-output.getSize());
                //修改数量
                entrepotStatusRepository.save(goodGodds);
                //修改出库申请状态
                output.setStatus(ApplyStatus.ENSURE);
                request.getSession().setAttribute("message","出库确认成功");
                result="成功";
                detail="申请出库成功:";
            }else if(goodGodds.getTotalSize()==output.getSize()){
                //库存大于申请，出库并删除记录
                entrepotStatusRepository.delete(goodGodds);
                output.setStatus(ApplyStatus.ENSURE);
                request.getSession().setAttribute("message","出库确认成功");
                result="成功";
                detail="申请出库成功:";
            }else{
                //库存小于申请，返回异常值
                output.setStatus(ApplyStatus.REFUSED);
                request.getSession().setAttribute("message","申请数量大于库存，已自动拒绝");
                detail="申请数量大于库存，已自动拒绝";
            }
            //如果提供商和提货商相同,则表示不良品退回
            //更新出库申请状态
            output.setEnsurePersonid(user.getUsername());
            output.setApplyDate(new Date());
            applyOutPutRepository.save(output);
            logService.saveOpLog(user.getUsername(), Operation.ENSURE_ENTER.getOperation(),result, detail+JSON.toJSONString(output));
        }
        return "redirect:/applyout-getToBeEnsured?pagenum=1";
    }



    /**
     * 拒绝
     */
    @RequestMapping("/applyout-turndown")
    public String turndown(int id, HttpServletRequest request) {
        logger.debug("查找ID为" + id + "的出库单");
        ApplyOutPut output = applyOutPutRepository.findApplyOutPutById(id);
        output.setStatus(ApplyStatus.REFUSED);
        User user = (User) request.getSession().getAttribute("user");
        output.setEnsurePersonid(user.getUsername());
        //更新
        applyOutPutRepository.save(output);
        logService.saveOpLog(user.getUsername(), Operation.REFUSE_OUT.getOperation(),"成功", JSON.toJSONString(output));
        return "redirect:/applyout-getToBeEnsured?pagenum=1";
    }

    /**
     * 根据ID删除
     */
    @RequestMapping(value = "/applyout-deleteById",method = {RequestMethod.POST,RequestMethod.GET})
    public String deleteApplyById(int enterId, HttpServletRequest request) throws StoreException {
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            throw new StoreException("用户尚未登录");
        }
        ApplyOutPut applyOutPut = applyOutPutRepository.findApplyOutPutById(enterId);
        applyOutPutRepository.delete(applyOutPut);
        logService.saveOpLog(user.getUsername(), Operation.DELETE_APPLY_OUT.getOperation(),"成功", JSON.toJSONString(applyOutPut));
        return "redirect:/applyout-getNotAllowed?pagenum=1";
    }


    /**
     * 根据ID删除
     */
    @RequestMapping(value = "/applyout-his-deleteById",method = {RequestMethod.POST,RequestMethod.GET})
    public String deleteHisApplyById(int enterId, HttpServletRequest request) throws StoreException {
        User user = (User) request.getSession().getAttribute("user");
        if(user==null){
            throw new StoreException("用户尚未登录");
        }
        ApplyOutPut applyOutPut = applyOutPutRepository.findApplyOutPutById(enterId);
        applyOutPutRepository.delete(applyOutPut);
        logService.saveOpLog(user.getUsername(), Operation.DELETE_APPLY_OUT_HIS.getOperation(),"成功", JSON.toJSONString(applyOutPut));
        return "redirect:/applyout-getHistory?pagenum=1";
    }


    /**
     * 批量申请
     */
    @RequestMapping(value = "/applyout-batchApply")
    public String batchApply(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws InstantiationException, IllegalAccessException, StoreException {
        List<ApplyOutPut> applyOutPuts = excelService.ImportExcelService(file, new ApplyOutPut());
        logger.debug(new Date()+"批量导入出库申请："+new Gson().toJson(applyOutPuts));
        String success = "";
        User user1 = (User) request.getSession().getAttribute("user");
        if(user1==null){
            throw new StoreException("用户尚未登录");
        }
        for(ApplyOutPut applyOutPut:applyOutPuts){
            User user = (User) request.getSession().getAttribute("user");
            applyOutPut.setApplyPersonid(user.getUsername());
            applyOutPut.setApplyDate(new Date());
            applyOutPut.setStatus("待审核");
            applyOutPutRepository.save(applyOutPut);
            success = success+applyOutPut.getEnterCode()+"--";
        }
        logService.saveOpLog(user1.getUsername(), Operation.APPLY_OUT_BATCH.getOperation(),"成功", JSON.toJSONString(applyOutPuts));
        request.getSession().setAttribute("message","导入成功的记录(入库编号)："+success);
        return "redirect:/applyout-getNotAllowed?pagenum=1";
    }

    /**
     * 下载申请模板
     * @param response
     * @param req
     * @throws IOException
     */
    @RequestMapping(value = "/applyout-downloadExcel")
    public void doloadExcel(HttpServletResponse response, HttpServletRequest req) throws IOException {
        InputStream inputStream = null;
        ServletOutputStream servletOutputStream = null;
        try {
            String filename = "批量出库申请模板.xlsx";
            String path = "files/批量出库申请模板.xlsx";
            org.springframework.core.io.Resource resource = resourceLoader.getResource("classpath:"+path);

            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.addHeader("charset", "utf-8");
            response.addHeader("Pragma", "no-cache");
            String encodeName = URLEncoder.encode(filename, StandardCharsets.UTF_8.toString());
            response.setHeader("Content-Disposition", "attachment; filename=\"" + encodeName + "\"; filename*=utf-8''" + encodeName);

            inputStream = resource.getInputStream();
            servletOutputStream = response.getOutputStream();
            IOUtils.copy(inputStream, servletOutputStream);
            response.flushBuffer();
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            try {
                if (servletOutputStream != null) {
                    servletOutputStream.close();
                    servletOutputStream = null;
                }
                if (inputStream != null) {
                    inputStream.close();
                    inputStream = null;
                }
                // 召唤jvm的垃圾回收器
                System.gc();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 搜索
     */
    @RequestMapping(value = "/applyout-search", method = {RequestMethod.GET, RequestMethod.POST})
    public String doSearch(ApplyOutPut outPut, ModelMap modelMap, HttpServletRequest request,@RequestParam(value = "pagenum", required = false)Integer pagenum) {
        String searchItem = request.getParameter("searchItem");
        String searchValue = request.getParameter("searchValue");
        //Integer pagenum = Integer.parseInt(request.getParameter("pagenum"));
        Integer type = Integer.parseInt(request.getParameter("type"));
        String page = "exit_apply_wait";
        System.out.print(searchItem+"   "+searchValue);
        StringBuffer sql = null;
        try {
            sql = commonRepository.getFiledValues(outPut, pagenum);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if(searchValue!=null||!"".equals(searchValue)) {
            if(type==1) {
                page = "exit_apply_wait";
                sql.append(searchItem + " like '%" + searchValue + "%' AND Status !='" + "已确认'");
            }else{
                page = "exit_apply_history";
                sql.append(searchItem + " like '%" + searchValue + "%' AND Status ='" + "已确认'");
            }
        }else{
            sql.append(" 1 = 1");
        }
        int totalpage = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(ApplyOutPut.class)).size();
        List<ApplyEnter> applyouts = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(ApplyOutPut.class));
        logger.debug("待处理的入库申请" + applyouts);
        modelMap.addAttribute("applys", applyouts);
        modelMap.addAttribute("searchValue",searchValue);
        modelMap.addAttribute("searchItem",searchItem);
        modelMap.addAttribute("totalpage", PageUtil.getTotalPage(totalpage, pagesize));

        return page;
    }
}
