package com.indi.controller.login;

import com.indi.bean.login.User;
import com.indi.service.loginservice.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 华峰
 * @create: 2020-04-07 14:08
 */
@Controller()
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping("/login")
    public void login(@RequestBody User user, HttpServletResponse res) throws IOException {
        int userExist = loginService.isExist(user);
        res.getWriter().write("dwdwdw");
    }

    @RequestMapping("/registeruser")
    public void register(@RequestBody User user,HttpServletResponse res) throws IOException {
        int statue = loginService.registerUser(user);
        res.getWriter().write(statue == 1 ? "success" : "fail");
    }

}
