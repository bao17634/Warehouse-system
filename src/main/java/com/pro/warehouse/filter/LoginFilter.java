package com.pro.warehouse.filter;


import com.pro.warehouse.pojo.User;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@WebFilter(urlPatterns = "/*")
@Order(value = 1)
public class LoginFilter implements Filter {

    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("/user-login")));
    @Override
    public void init(FilterConfig filterConfig){
        System.out.println("init-----------filter");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
        boolean allowedPath = ALLOWED_PATHS.contains(path);

        if (allowedPath) {
            chain.doFilter(req, res);
        }else{
            User user = (User) ((HttpServletRequest) req).getSession().getAttribute("user");
            if(null==user){
                ((HttpServletResponse) res).sendRedirect("user-login");
                chain.doFilter(req, res);
            }
        }
    }

    @Override
    public void destroy() {
        System.out.println("destroy----------filter");
    }
}