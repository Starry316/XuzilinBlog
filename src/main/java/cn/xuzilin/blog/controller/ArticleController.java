package cn.xuzilin.blog.controller;

import cn.xuzilin.blog.service.ArticleService;
import cn.xuzilin.blog.util.ResponseUtil;
import cn.xuzilin.blog.vo.ResponseVo;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @GetMapping("/articlelist/{page}")
    public ResponseVo getArticleList(@PathVariable("page") int page){
        JSONArray respData = articleService.getArticleListByPage(page);
        return ResponseUtil.success("success",respData);
    }

    @GetMapping("/getArticle/{id}")
    public ResponseVo getArticle(@PathVariable("id") long id){
        JSONObject respData = articleService.getArticleById(id);
        return ResponseUtil.success("success",respData);
    }
}
