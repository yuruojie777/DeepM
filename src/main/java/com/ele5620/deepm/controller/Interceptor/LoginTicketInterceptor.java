package com.ele5620.deepm.controller.Interceptor;

import com.ele5620.deepm.entity.LoginTicket;
import com.ele5620.deepm.entity.User;
import com.ele5620.deepm.service.UserService;
import com.ele5620.deepm.util.CookieUtil;
import com.ele5620.deepm.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginTicketInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;
    @Autowired
    private HostHolder hostHolder;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String ticket = CookieUtil.getValue(request, "ticket");
        System.out.println("ticket is: " + ticket);
        if(ticket != null) {
            LoginTicket loginTicket = userService.findLoginTicket(ticket);
            System.out.println(loginTicket.toString());
            System.out.println("进入");
            if(loginTicket != null && loginTicket.getStatus() == 0 ) {
                User user = userService.findUserById(loginTicket.getUid());
                System.out.println(user);
                hostHolder.setUser(user);
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        User user = hostHolder.getUser();
        if(user != null && modelAndView != null) {
            modelAndView.addObject("loginUser", user);
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        hostHolder.clear();
    }
}
