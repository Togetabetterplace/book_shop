package com.book.bookshop.interceptor;

import com.book.bookshop.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * &#064;Author:  zichentian
 * &#064;Date:  2023/12/27
 * &#064;Description:  权限拦截器类，实现了 Spring 框架的 HandlerInterceptor 接口
 */

public class PermissionInterceptor implements HandlerInterceptor {

    // 在处理请求之前执行的方法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取当前请求的 HttpSession 对象
        HttpSession session = request.getSession();

        // 从 HttpSession 中获取名为 "user" 的属性，这里假设该属性存储了用户信息
        User user = (User) session.getAttribute("user");

        // 判断用户是否存在且用户名不为 null
        if (user != null && user.getUsername() != null) {
            // 用户存在且已登录，允许请求继续执行
            return true;
        } else {
            // 用户不存在或未登录，重定向到指定路径，这里假设 "/book/index" 是登录页面的路径
            response.sendRedirect(request.getContextPath() + "/book/index");
            // 阻止请求继续执行
            return false;
        }
    }

    // 在处理请求之后执行的方法
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 在此方法中可以对请求进行进一步处理，比如修改视图或模型
    }

    // 在请求处理完成后（包括异常情况）执行的方法
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 在此方法中进行一些清理工作，释放资源
    }
}
