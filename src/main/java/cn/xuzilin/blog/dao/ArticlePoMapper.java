package cn.xuzilin.blog.dao;

import cn.xuzilin.blog.po.ArticlePo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ArticlePoMapper {
    int deleteByPrimaryKey(Long article_id);

    int insert(ArticlePo record);

    int insertSelective(ArticlePo record);

    ArticlePo selectByPrimaryKey(Long article_id);

    int updateByPrimaryKeySelective(ArticlePo record);

    int updateByPrimaryKeyWithBLOBs(ArticlePo record);

    int updateByPrimaryKey(ArticlePo record);

    @Select("SELECT * FROM articles")
    List<ArticlePo> selectAll();

    @Select("SELECT COUNT(*) FROM articles ")
    int selectCount();
}