package cn.xuzilin.blog.controller;

import cn.xuzilin.blog.po.UserPo;
import cn.xuzilin.blog.service.UserService;
import cn.xuzilin.blog.util.ResponseUtil;
import cn.xuzilin.blog.util.SessionUtil;
import cn.xuzilin.blog.vo.ResponseVo;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 登录
     * @param request
     * @param map
     * @return
     */
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

    /**
     * 注册
     * @param request
     * @param map
     * @return
     */
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

    /**
     * 检测用户是否登录
     * @param request
     * @return
     */
    @GetMapping("/checkLogin")
    public ResponseVo checkLogin (HttpServletRequest request){
        UserPo userPo = SessionUtil.get(request,"userToken",UserPo.class);
        if (userPo == null)
            return ResponseUtil.error("未登录");
        JSONObject respData = userService.getUserInfo(userPo.getUser_id());
        respData.put("userName",userPo.getUser_name());
        return ResponseUtil.success("success",respData);
    }

    /**
     * 获取用户的详细信息
     * @param request
     * @return
     */
    @GetMapping("/getLoginUserInfo")
    public ResponseVo getLoginUserInfo(HttpServletRequest request){
        UserPo userPo = SessionUtil.get(request,"userToken",UserPo.class);
        if (userPo == null)
            return ResponseUtil.error("未登录");
        JSONObject respData = userService.getUserInfo(userPo.getUser_id());
        return ResponseUtil.success("success",respData);
    }
    @GetMapping("/getUserInfo/{userId}")
    public ResponseVo getUserInfo(HttpServletRequest request, @PathVariable("userId") long userId){
        JSONObject respData = userService.getUserInfo(userId);
        return ResponseUtil.success("success",respData);
    }
}
