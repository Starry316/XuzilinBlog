package cn.xuzilin.blog.util;

import org.apache.catalina.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtil {
    public static <K> K get(HttpServletRequest request, String key , Class<K> clazz){
        //获取session
        HttpSession session = request.getSession();
        K k = (K) session.getAttribute(key);
        return k;
    }
    public static void save(HttpServletRequest request, String key ,Object o){
        HttpSession session = request.getSession();
        session.setAttribute(key,o);
    }
}
