package com.aruntech.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.netflix.zuul.ZuulFilter;
import javax.servlet.http.HttpServletResponse;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

@Component
public class ErrorFilter extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(PostFilter.class);

    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        HttpServletResponse response = RequestContext.getCurrentContext().getResponse();
        log.info("ErrorFilter: " + String.format("response status is %d", response.getStatus()));
        return null;
    }
}
