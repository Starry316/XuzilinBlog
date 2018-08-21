package cn.xuzilin.blog.service;

import cn.xuzilin.blog.dao.UserPoMapper;
import cn.xuzilin.blog.po.UserPo;
import cn.xuzilin.blog.util.PasswordUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserPoMapper userPoMapper;

    /**
     * 登录
     * @param userName
     * @param pass
     * @return
     */
    public boolean login(String userName,String pass){
        UserPo userPo = userPoMapper.selectByUserName(userName);
        if (userPo == null) return false;
        return PasswordUtil.validatePassword(pass,userPo.getUser_password());
    }

    /**
     * 注册
     * @param userName
     * @param pass
     * @return
     */
    public boolean signUp(String userName,String pass){
        if (!checkUserNameUnique(userName)) return false;
        UserPo userPo= new UserPo();
        userPo.setUser_name(userName);
        userPo.setUser_password(PasswordUtil.createHash(pass));
        userPoMapper.insertSelective(userPo);
        return true;
    }

    /**
     * 检测用户名是否唯一
     * @param userName
     * @return
     */
    public boolean checkUserNameUnique(String userName){
        UserPo userPo = userPoMapper.selectByUserName(userName);
        if (userPo == null) return true;
        return false;
    }

    /**
     * 通过用户名查询用户
     * @param userName
     * @return
     */
    public UserPo selectByUserName(String userName){
        return userPoMapper.selectByUserName(userName);
    }
}
