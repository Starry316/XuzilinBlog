package cn.xuzilin.blog.dao;

import cn.xuzilin.blog.po.ReviewPo;

public interface ReviewPoMapper {
    int deleteByPrimaryKey(Long review_id);

    int insert(ReviewPo record);

    int insertSelective(ReviewPo record);

    ReviewPo selectByPrimaryKey(Long review_id);

    int updateByPrimaryKeySelective(ReviewPo record);

    int updateByPrimaryKeyWithBLOBs(ReviewPo record);

    int updateByPrimaryKey(ReviewPo record);
}