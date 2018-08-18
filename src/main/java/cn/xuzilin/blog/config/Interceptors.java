package cn.xuzilin.blog.config;

import cn.xuzilin.blog.consts.CommonConsts;
import cn.xuzilin.blog.po.UserPo;
import cn.xuzilin.blog.util.SessionUtil;
import org.apache.http.Consts;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public class Interceptors implements HandlerInterceptor {
    /**
     * preHandle方法是进行处理器拦截用的，顾名思义，该方法将在Controller处理之前进行调用，SpringMVC中的Interceptor拦截器是链式的，可以同时存在
     * 多个Interceptor，然后SpringMVC会根据声明的前后顺序一个接一个的执行，而且所有的Interceptor中的preHandle方法都会在
     * Controller方法调用之前调用。SpringMVC的这种Interceptor链式结构也是可以进行中断的，这种中断方式是令preHandle的返
     * 回值为false，当preHandle的返回值为false的时候整个请求就结束了。
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        /**
         *  以访问的jsp为：http://localhost:8080/project/course/index.jsp，工程名为/project为例：
         *
         *         request.getContextPath()，得到工程名：/project
         *
         *         request.getServletPath()，返回当前页面所在目录下全名称：/course/index.jsp；
         *
         *         request.getRequestURL()，返回IE地址栏地址：http://localhost:8080/project/course/index.jsp；
         *
         *         request.getRequestURI() ，返回包含工程名的当前页面全路径：/project/course/index.jsp。
         */
        String url = request.getServletPath();
        //不需要登录的路径
        if (!(url.hashCode() == "/edit".hashCode())){
            return true;
        }

        UserPo user = SessionUtil.get(request, "loginToken", UserPo.class);
        if (user == null)
            return false;
        return true;
    }
    /**
     * 这个方法只会在当前这个Interceptor的preHandle方法返回值为true的时候才会执行。postHandle是进行处理器拦截用的，它的执行时间是在处理器进行处理之
     * 后，也就是在Controller的方法调用之后执行，但是它会在DispatcherServlet进行视图的渲染之前执行，也就是说在这个方法中你可以对ModelAndView进行操
     * 作。这个方法的链式结构跟正常访问的方向是相反的，也就是说先声明的Interceptor拦截器该方法反而会后调用，这跟Struts2里面的拦截器的执行过程有点像，
     * 只是Struts2里面的intercept方法中要手动的调用ActionInvocation的invoke方法，Struts2中调用ActionInvocation的invoke方法就是调用下一个Interceptor
     * 或者是调用action，然后要在Interceptor之前调用的内容都写在调用invoke之前，要在Interceptor之后调用的内容都写在调用invoke方法之后。
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }
    /**
     * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。该方法将在整个请求完成之后，也就是DispatcherServlet渲染了视图执行，
     * 这个方法的主要作用是用于清理资源的，当然这个方法也只能在当前这个Interceptor的preHandle方法的返回值为true时才会执行。
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}