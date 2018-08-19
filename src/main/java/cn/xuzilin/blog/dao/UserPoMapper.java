package cn.xuzilin.blog.dao;

import cn.xuzilin.blog.po.UserPo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserPoMapper {
    int deleteByPrimaryKey(Long user_id);

    int insert(UserPo record);

    int insertSelective(UserPo record);

    UserPo selectByPrimaryKey(Long user_id);

    int updateByPrimaryKeySelective(UserPo record);

    int updateByPrimaryKey(UserPo record);

    @Select("SELECT * FROM user WHERE user_name = #{userName}")
    UserPo selectByUserName(@Param("userName")String userName);

    @Select("SELECT user_name FROM user WHERE user_id = #{userId}")
    String selectUserNameByUserId(@Param("userId")long userId);

}