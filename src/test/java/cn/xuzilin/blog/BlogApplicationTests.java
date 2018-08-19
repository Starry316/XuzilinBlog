package cn.xuzilin.blog;

import cn.xuzilin.blog.dao.ArticlePoMapper;
import cn.xuzilin.blog.po.ArticlePo;
import cn.xuzilin.blog.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

    @Resource
    private ArticlePoMapper articlePoMapper;
    @Test
    public void contextLoads() {
        ArticlePo articlePo = articlePoMapper.selectByPrimaryKey(50L);

        System.out.println("fafa"+DateUtil.formatDate(articlePo.getWritten_time()));
    }

}
