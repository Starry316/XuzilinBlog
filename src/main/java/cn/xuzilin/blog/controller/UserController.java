package cn.xuzilin.blog.controller;

import cn.xuzilin.blog.po.UserPo;
import cn.xuzilin.blog.service.UserService;
import cn.xuzilin.blog.util.ResponseUtil;
import cn.xuzilin.blog.util.SessionUtil;
import cn.xuzilin.blog.vo.ResponseVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public ResponseVo login (HttpServletRequest request,@RequestBody Map<String ,String> map){
        String userName = map.get("userName");
        String pass = map.get("pass");
        boolean res = userService.login(userName,pass);
        if (res){
            UserPo userPo = userService.selectByUserName(userName);
            SessionUtil.save(request,"userToken",userPo);
            return ResponseUtil.success("success");
        }

        return ResponseUtil.error("登录失败，用户名或密码错误");
    }
    @PostMapping("/signUp")
    public ResponseVo signUp (HttpServletRequest request,@RequestBody Map<String ,String> map){
        String userName = map.get("userName");
        String pass = map.get("pass");
        boolean res = userService.checkUserNameUnique(userName);
        if (!res) return ResponseUtil.error("用户名已经被使用！");
        res = userService.signUp(userName,pass);
        if (res){
            UserPo userPo = userService.selectByUserName(userName);
            SessionUtil.save(request,"userToken",userPo);
            return ResponseUtil.success("success");
        }

        return ResponseUtil.error("注册失败！");
    }

    @GetMapping("/checkLogin")
    public ResponseVo checkLogin (HttpServletRequest request){
        UserPo userPo = SessionUtil.get(request,"userToken",UserPo.class);
        if (userPo == null)
            return ResponseUtil.error("未登录");
        return ResponseUtil.success("success",userPo.getUser_name());
    }
}
