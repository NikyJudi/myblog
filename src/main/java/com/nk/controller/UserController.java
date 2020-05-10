package com.nk.controller;

import com.nk.model.User;
import com.nk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(String username, String password, HttpServletRequest httpServletRequest) {
        //校验
        if (username == null || password == null) {
            return "login";
        }
        User user = userService.login(username, password);
        if (user == null) {
            return "login";
        } else {
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute("user", user);
            return "/";
        }
    }
}
