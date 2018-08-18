package cn.xuzilin.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommonController {

    @RequestMapping("/article/{id}")
    public ModelAndView article(@PathVariable("id") long id){
        return new ModelAndView("article");
    }
}
