package com.tianxia.log.cid.interceptor;

import com.tianxia.log.cid.utils.MDCIdUtil;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: LogInterceptor
 * @Description:
 * @Author: ice
 */
public class LogInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果有上层调用就用上层的ID
        String cid = request.getHeader(MDCIdUtil.CID);
        if (cid == null) {
            MDCIdUtil.putIfAbsent();
            cid = MDCIdUtil.get();
        }

        MDC.put(MDCIdUtil.CID, cid);
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //调用结束后删除
        MDC.remove(MDCIdUtil.CID);
    }
}
