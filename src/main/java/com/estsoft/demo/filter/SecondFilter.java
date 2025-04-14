package com.estsoft.demo.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class SecondFilter implements Filter {

    @Override
    public void init(jakarta.servlet.FilterConfig filterConfig) throws ServletException {
        log.info("### SecondFilter init()");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("### SecondFilter doFilter()");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String queryString = request.getQueryString();

        log.info("### SecondFilter - queryString = {}", queryString);
        filterChain.doFilter(servletRequest, servletResponse);

        log.info("### SecondFilter Response");
    }

    @Override
    public void destroy() {
        log.info("### SecondFilter destroy()");
    }
}
