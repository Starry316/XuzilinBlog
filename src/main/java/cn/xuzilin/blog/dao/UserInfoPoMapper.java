package cn.xuzilin.blog.dao;

import cn.xuzilin.blog.po.UserInfoPo;

public interface UserInfoPoMapper {
    int deleteByPrimaryKey(Long user_Id);

    int insert(UserInfoPo record);

    int insertSelective(UserInfoPo record);

    UserInfoPo selectByPrimaryKey(Long user_Id);

    int updateByPrimaryKeySelective(UserInfoPo record);

    int updateByPrimaryKey(UserInfoPo record);
}