package com.qf.controller;


import com.qf.pojo.Leave;
import com.qf.pojo.User;
import com.qf.service.Service;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes(value = {"user"})
public class Login {
    private int APPLY_LEAVE = 0;

    @Autowired
    private Service service;

    @Autowired
    private SecurityManager securityManager;



    @RequestMapping("login")
    public String login(){
        return "login";
    }

    @RequestMapping("leave")
    public String leave(){
        return "leave";
    }


    @RequestMapping(value = "userLogin" ,method = RequestMethod.POST)
    public String userLogin(Model model,String username,String password){
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        System.out.println("model = [" + model + "], username = [" + username + "], password = [" + password + "]");
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        subject.login(usernamePasswordToken);
        try{
            if(subject.isAuthenticated()){
                User user = service.queryUserByUsername(username);
                model.addAttribute("user",user);
                return "forward:leave";
            }
        }catch (AuthenticationException e){

        }
        return "forward:login";
    }

    @RequestMapping("addLeave1")
    public String addLeave1(){
        return "addLeave";
    }

    @RequestMapping("addleave2")
    public String addLeave2(HttpSession session,Leave leave){
        User user = (User)session.getAttribute("user");
        leave.setUser(user);
        leave.setState(APPLY_LEAVE);
        service.addLeave(leave);

        return "redirect:leave";
    }

    @RequestMapping("checkLeave")
    public String checkLeave(HttpSession session){
        int uid = ((User) session.getAttribute("user")).getUid();
        List<Leave> list = service.queryLeaveListByUid(uid);
        session.setAttribute("list",list);
        return "checkLeave";
    }


    @RequestMapping("managerLeave")
    public String managerLeave(HttpSession session){
        List<Leave> list = service.queryLeaveListByState(APPLY_LEAVE);
        session.setAttribute("list",list);
        return "managerLeave";
    }

    @RequestMapping("managerLeave2")
    public String managerLeave2(int lid){
        service.updateLeaveByLid(lid);
        return "forward:managerLeave";
    }




}
