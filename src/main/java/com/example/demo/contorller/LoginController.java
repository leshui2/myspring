package com.example.demo.contorller;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/User")
@Slf4j
public class LoginController {
    @Autowired
    UserMapper userMapper;

    //登录
    @RequestMapping("login")
    public String index() {
        return "index";
    }

    @RequestMapping("getUserInfo")
    public String getUserInfo(User user) {
        User userInfo = userMapper.getUserInfo(user);
        if (userInfo == null) {
            return "此用户不存在!";
        } else if (user.getName() == userInfo.getName() && user.getPassword() == userInfo.getPassword()) {
            return "密码错误!";
        } else {
            log.info("跳转查询页面");
            return "redirect:/student/listData";
        }
    }

    /*@ResponseBody
    @RequestMapping("getUserInfo")
    public JsonResult getUserInfo(User user) {
        User userInfo = userMapper.getUserInfo(user);
        if (userInfo == null) {
            return JsonResult.error("此用户不存在!");
        } else if (user.getName() == userInfo.getName() && user.getPassword() == userInfo.getPassword()) {
            return JsonResult.error("密码错误!");
        } else {
            return JsonResult.ok().put("userInfo", userInfo);
        }
    }*/
 /*   @ResponseBody
    @RequestMapping("getUserInfo")
    public String getUserInfo(User user) {
        User userInfo = userMapper.getUserInfo(user);
        String res = "";
        if (userInfo == null) {
            return "此用户不存在!";
        } else if (user.getName() == userInfo.getName() && user.getPassword() == userInfo.getPassword()) {
            return "密码错误!";
        } else {
            return "student/studentInfo.html";
        }
    }*/

}
