package com.taotao.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PageController {
    @RequestMapping("/user/showLogin")
    public String showLogin(String redirectURL, Model model)
    {
        model.addAttribute("redirect", redirectURL);
        System.out.println(redirectURL);
        return "login";
    }

    @RequestMapping("/user/showRegister")
    public String showRegister()
    {
        return "register";
    }


}