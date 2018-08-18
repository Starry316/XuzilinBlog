package cn.xuzilin.blog.service;

import cn.xuzilin.blog.dao.ArticlePoMapper;
import cn.xuzilin.blog.po.ArticlePo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleService {
    @Resource
    private ArticlePoMapper articlePoMapper;

    public JSONArray getArticleListByPage(int page){
        List<ArticlePo> list = selectAllByPage(page);
        JSONArray jsonArray = JSON.parseArray(JSON.toJSONString(list));
        return jsonArray;
    }

    public JSONObject getArticleById(long id){
        ArticlePo articlePo = articlePoMapper.selectByPrimaryKey(id);
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(articlePo));
        return jsonObject;
    }

    public List<ArticlePo> selectAllByPage(int page){
        PageHelper.startPage(page, 20);
        List<ArticlePo> list = articlePoMapper.selectAll();
        return list;
    }
}
