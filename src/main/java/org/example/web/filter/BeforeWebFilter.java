package org.example.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/hello2",filterName = "001beforeWebFilter")
public class BeforeWebFilter implements Filter, Ordered {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("BeforeWebFilter start");
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("BeforeWebFilter end");
    }

    @Override
    public int getOrder() {
        return 10;
    }
}
