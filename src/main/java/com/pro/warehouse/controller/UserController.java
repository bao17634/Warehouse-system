package com.pro.warehouse.controller;

import com.pro.warehouse.Service.IndexService;
import com.pro.warehouse.Service.LogService;
import com.pro.warehouse.dao.*;
import com.pro.warehouse.mail.MailService;
import com.pro.warehouse.myexception.StoreException;
import com.pro.warehouse.pojo.User;
import com.pro.warehouse.sheduler.DaliyComputeShedule;
import com.pro.warehouse.util.EncrypeUtil;
import com.pro.warehouse.util.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class.getName());
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IndexService indexService;
    @Autowired
    MailService mailService;
    @Autowired
    private LogService logService;
    @Autowired
    DaliyComputeShedule reportShedule;


    private Integer pagesize = 15;//每页显示的条数


    // 通过@Resource注解引入JdbcTemplate对象
    @Resource
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private CommonRepository<User> commonRepository;




    @RequestMapping(value = "/user-dologin", method = {RequestMethod.GET, RequestMethod.POST})
    public String UserLogin(User user, ModelMap modelMap, HttpServletRequest request) throws Exception {
        String page = "index";
        int size = 0;
        List<User> userList = userRepository.findUserByusername(user.getUsername());

        if (userList.size() == 0)//此用户不存在
        {
            modelMap.addAttribute("message", "用户名或者密码不正确");
            page = "login";
        } else {
            if (!userList.get(0).getPassword().equals(EncrypeUtil.shaEncode(user.getPassword()))) {
                modelMap.addAttribute("message", "用户名或者密码不正确");
                page = "login";
            } else {
                User loginUser = userList.get(0);
                request.getSession().setAttribute("user", loginUser);
            }
            System.out.println("登陆用户：" + userList.get(0));
        }
        int enterSize = indexService.getYestdayApplyEnterCount();
        int outSize = indexService.getYestdayApplyOutCount();
        int entrepotSize = indexService.getEntrpotSize();
        modelMap.addAttribute("enterSize",enterSize);
        modelMap.addAttribute("outSize",outSize);
        modelMap.addAttribute("entrepotSize",entrepotSize);
        logService.saveOpLog(user.getUsername(),"登录","成功",user.toString());
        return page;
    }

    @RequestMapping("/user-login")
    public String thymeleaftest(ModelMap map) {
        // map.addAttribute("host", "http://www.baidu.com");
        return "login";
    }

    @RequestMapping("/user-updatePwd")
    public String uPwdView(ModelMap map) {
        // map.addAttribute("host", "http://www.baidu.com");
        return "user_change_pwd";
    }



    @RequestMapping("/user-save")
    public String saveUser(User user) throws StoreException {
        List<User> userList = userRepository.findUserByusername(user.getUsername());
        if(userList.size()>0){
            throw new StoreException("该用户已存在，请重新选择不存在的用户名！");
        }
        try {
            user.setPassword(EncrypeUtil.shaEncode(user.getPassword()));
            user.setStatus(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        userRepository.save(user);
        return "redirect:/user-getAll?pagenum=1";
    }



    @RequestMapping("/user-getAll")
    public String getAlllUser(User user, ModelMap modelMap, int pagenum) {
        String page = "user_list";
        if (user != null) {
            StringBuffer sql = null;
            try {
                sql = commonRepository.getFiledValues(user, pagenum);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            sql.append( "1 = 1");
            int totalpage = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(User.class)).size();
            sql.append(" LIMIT " + (pagenum - 1) * pagesize + "," + pagesize);
            List<User> users = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper(User.class));
            System.out.println("已确认的申请" + users);
            modelMap.addAttribute("users", users);
            modelMap.addAttribute("page", pagenum);
            modelMap.addAttribute("totalpage", PageUtil.getTotalPage(totalpage, pagesize));
        }
        return page;

    }

    @RequestMapping("/user-disabledUser")
    public String disabledUser(Long id, ModelMap modelMap, HttpSession session) {
        User user1 = (User)session.getAttribute("user");
        System.out.println("禁用账户"+id);
        if(id==user1.getId()){
            session.setAttribute("message","不能禁用自己");
            return "redirect:/user-getAll?pagenum=1";
        }
        User user = userRepository.findUserByid(id);
        if(null!=user){
            user.setStatus(1);
            userRepository.save(user);
            System.out.println("禁用账户"+user);
        }
        return "redirect:/user-getAll?pagenum=1";
    }

    @RequestMapping("/user-enableUser")
    public String enableUser(Long id, ModelMap modelMa,HttpSession session) {
        User user1 = (User)session.getAttribute("user");
        if(id==(user1.getId())){
            session.setAttribute("message","不能启用自己");
            return "redirect:/user-getAll?pagenum=1";
        }
        User user = userRepository.findUserByid(id);
        if(null!=user){
            user.setStatus(2);
            userRepository.save(user);
            System.out.println("启用账户"+user);
        }
        return "redirect:/user-getAll?pagenum=1";
    }

    public String updateUser(Long id, ModelMap modelMap) {

        return "redirect:user/getAll?pagenum=1";
    }

    @RequestMapping("/user-delete")
    public String deleteUser(Long id,HttpSession session) {
        User user1 = (User)session.getAttribute("user");
        if(id==(user1.getId())){
            session.setAttribute("message","不能删除自己");
            return "redirect:/user-getAll?pagenum=1";
        }
        session.setAttribute("message","删除了用户"+userRepository.findUserByid(id).getUsername());
        userRepository.deleteById(id);
        return "redirect:/user-getAll?pagenum=1";
    }


    @RequestMapping("/user-searchById")
    @ResponseBody
    public User searchUser(Long id) {
        User user = userRepository.findUserByid(id);

        return user;
    }

    @RequestMapping("/user-edit")
    public String userEdit(User user) throws Exception {
        user.setPassword(EncrypeUtil.shaEncode(user.getPassword()));
        System.out.println(user);
        userRepository.save(user);
        System.out.println("编辑用户"+user);
        return "redirect:/user-getAll?pagenum=1";
    }

    @RequestMapping("/user-dopwd")
    public String upPwd(String oldPassword,String newPassword,String rePassword, ModelMap modelMap,HttpSession session) throws Exception {
        logger.debug("修改密码"+oldPassword+"   "+rePassword+"  "+rePassword);
        int i=0;
        if(!newPassword.equals(rePassword)){
            System.err.println("修改密码"+oldPassword+"   "+rePassword+"  "+rePassword);
            throw new StoreException("新密码与确认密码不同，请重新输入");
        }
        User user = (User)session.getAttribute("user");
        if(user==null) {
            throw new StoreException("用户会话过期，请重新登录");
        }
        List<User> userList = userRepository.findUserByusername(user.getUsername());
        if(userList.size()==0){
            throw new StoreException("该账户不存在！");
        }
        System.err.println("查找到的用户"+userList);
        for(User user1:userList){
            if(user1.getPassword().equals(EncrypeUtil.shaEncode(oldPassword))){
                i++;
                break;
            }
        }
        if(i==0){
            throw new StoreException("密码错误！");
        }
        user.setPassword(EncrypeUtil.shaEncode(newPassword));
        userRepository.save(user);
        session.removeAttribute("user");
        return "login";
    }

    /**
     * 推出系统
     * @param session
     * @return
     */
    @RequestMapping("/user-logout")
    public String logOut(HttpSession session) {
        //推出系统，注销seeesion,跳转到登陆页面
        User user = (User)session.getAttribute("user");
        if(user!=null) {
            session.removeAttribute("user");
        }
        return "login";
    }

    @RequestMapping("/mail")
    public void sendMail(){
        reportShedule.sendMail();
    }

}
