package cn.xuzilin.blog.dao;

import cn.xuzilin.blog.po.UserPo;

public interface UserPoMapper {
    int deleteByPrimaryKey(Long user_id);

    int insert(UserPo record);

    int insertSelective(UserPo record);

    UserPo selectByPrimaryKey(Long user_id);

    int updateByPrimaryKeySelective(UserPo record);

    int updateByPrimaryKey(UserPo record);
}