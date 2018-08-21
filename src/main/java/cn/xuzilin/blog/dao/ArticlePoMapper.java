package cn.xuzilin.blog.dao;

import cn.xuzilin.blog.po.ArticlePo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.PostMapping;

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

    @Update("UPDATE articles SET read_times = read_times+1 WHERE article_id = #{id}")
    int updateReadTimes(@Param("id") long id);
}