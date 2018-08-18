package cn.xuzilin.blog.util;

import cn.xuzilin.blog.consts.CommonConsts;
import cn.xuzilin.blog.vo.ResponseVo;
import org.apache.http.Consts;

public class ResponseUtil {
    public static ResponseVo success(String message){
        ResponseVo responseVo = new ResponseVo();
        responseVo.setMessage(message);
        responseVo.setStatus(CommonConsts.SUCCESS);
        return responseVo;
    }
    public static ResponseVo success(String message,Object o){
        ResponseVo responseVo = new ResponseVo();
        responseVo.setMessage(message);
        responseVo.setStatus(CommonConsts.SUCCESS);
        responseVo.setData(o);
        return responseVo;
    }

    public static ResponseVo error(String message){
        ResponseVo responseVo = new ResponseVo();
        responseVo.setMessage(message);
        responseVo.setStatus(CommonConsts.ERROR);
        return responseVo;
    }
    public static ResponseVo error(String message,Object o){
        ResponseVo responseVo = new ResponseVo();
        responseVo.setMessage(message);
        responseVo.setStatus(CommonConsts.ERROR);
        responseVo.setData(o);
        return responseVo;
    }


}
