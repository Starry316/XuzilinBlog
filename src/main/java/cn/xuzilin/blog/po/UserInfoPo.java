package cn.xuzilin.blog.po;

import java.util.Date;

public class UserInfoPo {
    private Long user_Id;

    private String user_describe;

    private Date signtime;

    private String sex;

    private String place;

    private Integer age;

    public Long getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(Long user_Id) {
        this.user_Id = user_Id;
    }

    public String getUser_describe() {
        return user_describe;
    }

    public void setUser_describe(String user_describe) {
        this.user_describe = user_describe == null ? null : user_describe.trim();
    }

    public Date getSigntime() {
        return signtime;
    }

    public void setSigntime(Date signtime) {
        this.signtime = signtime;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}