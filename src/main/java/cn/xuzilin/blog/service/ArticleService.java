package cn.xuzilin.blog.service;

import cn.xuzilin.blog.dao.ArticlePoMapper;
import cn.xuzilin.blog.dao.UserPoMapper;
import cn.xuzilin.blog.po.ArticlePo;
import cn.xuzilin.blog.po.UserPo;
import cn.xuzilin.blog.util.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ArticleService {
    @Resource
    private ArticlePoMapper articlePoMapper;

    @Resource
    private UserPoMapper userPoMapper;

    public JSONArray getArticleListByPage(int page){
        List<ArticlePo> list = selectAllByPage(page);
        JSONArray jsonArray = new JSONArray();
        for (ArticlePo i : list){
            String userName = userPoMapper.selectUserNameByUserId(i.getUser_id());
            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(i));
            jsonObject.remove("written_time");
            jsonObject.put("written_time",DateUtil.formatDate(i.getWritten_time()));
            jsonObject.put("userName",userName);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    public JSONObject getArticleById(long id){
        ArticlePo articlePo = articlePoMapper.selectByPrimaryKey(id);
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(articlePo));
        jsonObject.remove("written_time");
        jsonObject.put("written_time",DateUtil.formatDate(articlePo.getWritten_time()));
        return jsonObject;
    }

    /**
     * 检测用户是否为作者
     * @param id
     * @param userId
     * @return
     */
    public boolean checkAuthor(long id ,long userId){
        ArticlePo articlePo = articlePoMapper.selectByPrimaryKey(id);
        return userId == articlePo.getUser_id();
    }

    public List<ArticlePo> selectAllByPage(int page){
        PageHelper.startPage(page, 10);
        List<ArticlePo> list = articlePoMapper.selectAll();
        return list;
    }

    public long saveArticle(String title,String text,long userId){
        ArticlePo articlePo = new ArticlePo();
        articlePo.setRead_times(0);
        articlePo.setArticle(text);
        articlePo.setTitle(title);
        articlePo.setUser_id(userId);
        articlePo.setWritten_time(new Date());
        articlePoMapper.insertSelective(articlePo);
        return articlePo.getArticle_id();
    }
    public long updatePassage(long id ,String title,String text){
        ArticlePo articlePo = new ArticlePo();
        articlePo.setTitle(title);
        articlePo.setArticle(text);
        articlePo.setArticle_id(id);
        articlePoMapper.updateByPrimaryKeySelective(articlePo);
        return articlePo.getArticle_id();
    }
    public void updateReadTimes(long id){
        articlePoMapper.updateReadTimes(id);
    }
    public JSONArray search(String keyword){
        List<ArticlePo> list = articlePoMapper.search(keyword);
        JSONArray jsonArray = new JSONArray();
        for (ArticlePo i : list){
            String userName = userPoMapper.selectUserNameByUserId(i.getUser_id());
            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(i));
            jsonObject.remove("written_time");
            jsonObject.put("written_time",DateUtil.formatDate(i.getWritten_time()));
            jsonObject.put("userName",userName);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
    public int getMaxPage(){
        return (articlePoMapper.selectCount()+9)/10;
    }
}
