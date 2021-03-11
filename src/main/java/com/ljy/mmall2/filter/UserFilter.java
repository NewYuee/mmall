package com.ljy.mmall2.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.http.HttpRequest;

public class UserFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request= (HttpServletRequest) req;
        HttpServletResponse response=(HttpServletResponse) resp;
        HttpSession session =request.getSession();
        if(session.getAttribute("user")==null){
            response.sendRedirect("/productCategory/list");
        }else{
            chain.doFilter(req,resp);
        }
    }

}
