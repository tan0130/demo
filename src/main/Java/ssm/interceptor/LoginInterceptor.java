package ssm.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * create by 1311230692@qq.com on 2018/10/16 09:15
 * 登录拦截器
 **/
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 获取 session 中保存登录 id 的值

        Object id = httpServletRequest.getSession().getAttribute("loginId");
//       System.out.println("id:" + id);
        if (id == null) {
            //System.out.println("尚未登录，调到登录页面");
            httpServletResponse.sendRedirect("/login.html");
//            System.out.println("请重新登录");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        // System.out.print("postHandler");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        // System.out.print("afterCompletion");
    }
}
