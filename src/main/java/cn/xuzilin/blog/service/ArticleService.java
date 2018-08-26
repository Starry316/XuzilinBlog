package cn.xuzilin.blog.service;

import cn.xuzilin.blog.dao.ArticlePoMapper;
import cn.xuzilin.blog.dao.UserInfoPoMapper;
import cn.xuzilin.blog.dao.UserPoMapper;
import cn.xuzilin.blog.po.ArticlePo;
import cn.xuzilin.blog.po.UserInfoPo;
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

    @Resource
    private UserInfoPoMapper userInfoPoMapper;

    /**
     * 按页数获取数据并处理数据，返回文章列表
     * @param page
     * @return
     */
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

    /**
     * 通过id获取文章
     * @param id
     * @return
     */
    public JSONObject getArticleById(long id){
        ArticlePo articlePo = articlePoMapper.selectByPrimaryKey(id);
        if (articlePo == null) return null;
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(articlePo));
        jsonObject.remove("written_time");
        jsonObject.put("written_time",DateUtil.formatDate(articlePo.getWritten_time()));
        UserPo author = userPoMapper.selectByPrimaryKey(articlePo.getUser_id());
        UserInfoPo authorInfo = userInfoPoMapper.selectByPrimaryKey(articlePo.getUser_id());
        jsonObject.put("authorDesc",authorInfo.getUser_describe());
        jsonObject.put("authorName",author.getUser_name());
        jsonObject.put("authorAge",authorInfo.getAge());
        jsonObject.put("authorSigntime",DateUtil.formatDate(authorInfo.getSigntime()));
        jsonObject.put("authorPlace",authorInfo.getPlace());
        jsonObject.put("authorSex",authorInfo.getSex());
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

    /**
     * 按页数查询ArticlePo
     * @param page
     * @return
     */
    public List<ArticlePo> selectAllByPage(int page){
        PageHelper.startPage(page, 10);
        List<ArticlePo> list = articlePoMapper.selectAll();
        return list;
    }

    /**
     * 保存新的文章
     * @param title
     * @param text
     * @param userId
     * @return
     */
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

    /**
     * 更新文章
     * @param id
     * @param title
     * @param text
     * @return
     */
    public long updatePassage(long id ,String title,String text){
        ArticlePo articlePo = new ArticlePo();
        articlePo.setTitle(title);
        articlePo.setArticle(text);
        articlePo.setArticle_id(id);
        articlePoMapper.updateByPrimaryKeySelective(articlePo);
        return articlePo.getArticle_id();
    }

    /**
     * 删除文章
     * @param id
     * @return
     */
    public int deletePassage(long id ){
        return articlePoMapper.deleteByPrimaryKey(id);
    }

    /**
     * 更新浏览次数
     * @param id
     */
    public void updateReadTimes(long id){
        articlePoMapper.updateReadTimes(id);
    }

    /**
     * 通过关键词搜索文章列表
     * @param keyword
     * @return
     */
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

    /**
     * 获取文章最大页数
     * @return
     */
    public int getMaxPage(){
        return (articlePoMapper.selectCount()+9)/10;
    }
}
