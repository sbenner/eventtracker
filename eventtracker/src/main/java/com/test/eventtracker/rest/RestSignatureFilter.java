package com.test.eventtracker.rest;

/**
 * Created with IntelliJ IDEA.
 * User: sbenner
 * Date: 8/25/14
 * Time: 1:42 AM
 */


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter
public class RestSignatureFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(RestSignatureFilter.class);

    @Value("${orderservice.apikey}")
    String key;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

//        String apiKey = request.getHeader("apikey");
//
//        if (StringUtils.isEmpty(apiKey) || !apiKey.equals(key)) {
//            logger.error("BAD REQUEST invalid signature or apikey");
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
//            return;
//        }


        filterChain.doFilter(request, response);
    }

}