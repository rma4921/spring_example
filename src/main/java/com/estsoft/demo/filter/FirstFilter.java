package com.estsoft.demo.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/*
* Request URL 정보 로그로 남기기
* */
@Slf4j
public class FirstFilter implements Filter {
    // @Slf4j lombok을 사용한 것과 같다.
    // private final static Logger log = LoggerFactory.getLogger(FirstFilter.class);

    @Override
    public void init(jakarta.servlet.FilterConfig filterConfig) throws ServletException {
        log.info("### FirstFilter init()");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("### FirstFilter doFilter()");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();

        log.info("### FirstFilter - requestURI: {}", requestURI);

        filterChain.doFilter(request, servletResponse);

        log.info("### FirstFilter Response");
    }

    @Override
    public void destroy() {
        log.info("### FirstFilter destroy()");
    }
}
