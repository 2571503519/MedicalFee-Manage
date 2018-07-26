package com.jackaroo.spring_boot_demo.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author JackarooZhang
 * @date 2018/6/7 21:20
 */
@Controller
public class IndexController {

    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index/index");

        return mv;
    }

    @GetMapping("/welcome.html")
    public ModelAndView welcome() {
        ModelAndView mv = new ModelAndView("index/welcome");

        return mv;
    }

    @GetMapping("/email.html")
    public ModelAndView emailList() {
        ModelAndView mv = new ModelAndView("index/email");

        return mv;
    }

    @GetMapping("/adminInfo.html")
    public ModelAndView adminInfo() {
        ModelAndView mv = new ModelAndView("index/adminInfo");

        return mv;
    }

}
