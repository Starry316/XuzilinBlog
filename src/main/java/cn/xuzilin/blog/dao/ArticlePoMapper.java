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

    @Select("SELECT * FROM articles ORDER BY written_time DESC")
    List<ArticlePo> selectAll();

    /**
     * CONCAT 函数的作用是连接多个字符串
     * 可以使#{title}不会被解析为字符串
     * @param title
     * @return
     */
    @Select("SELECT * FROM articles WHERE title LIKE CONCAT('%',#{title},'%') ORDER BY written_time DESC")
    List<ArticlePo> search(@Param("title") String title);

    @Select("SELECT COUNT(*) FROM articles ")
    int selectCount();

    @Select("SELECT COUNT(*) FROM articles WHERE title LIKE CONCAT('%',#{title},'%') ")
    int selectSearchCount();


    @Update("UPDATE articles SET read_times = read_times+1 WHERE article_id = #{id}")
    int updateReadTimes(@Param("id") long id);
}