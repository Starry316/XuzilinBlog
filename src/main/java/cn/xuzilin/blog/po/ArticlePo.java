package cn.xuzilin.blog.po;

import java.util.Date;

public class ArticlePo {
    private Long article_id;

    private Long user_id;

    private Date written_time;

    private Integer read_times;

    private String title;

    private String article;

    public Long getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Long article_id) {
        this.article_id = article_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Date getWritten_time() {
        return written_time;
    }

    public void setWritten_time(Date written_time) {
        this.written_time = written_time;
    }

    public Integer getRead_times() {
        return read_times;
    }

    public void setRead_times(Integer read_times) {
        this.read_times = read_times;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article == null ? null : article.trim();
    }
}