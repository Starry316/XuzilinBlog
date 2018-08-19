package cn.xuzilin.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommonController {

    @RequestMapping("/article/{id}")
    public ModelAndView article(@PathVariable("id") long id){
        return new ModelAndView("article");
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") long id){
        return new ModelAndView("edit");
    }
    @RequestMapping("/write")
    public ModelAndView write(){
        return new ModelAndView("write");
    }
}
