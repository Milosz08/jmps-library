/*
 * Copyright (c) 2023 by MULTIPLE AUTHORS
 *
 * File name: CorsHeaderFilter.java
 * Last modified: 28/03/2023, 23:14
 * Project name: jmps-library
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at
 *
 *     <http://www.apache.org/license/LICENSE-2.0>
 *
 * Unless required by applicable law or agreed to in writing, software distributed under
 * the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the license.
 */

package org.jmpsl.security.filter;

import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.core.annotation.Order;

import java.util.Arrays;
import java.io.IOException;
import java.util.stream.Collectors;

import org.jmpsl.security.SecurityEnv;

/**
 * Custom servlet {@link Filter} added extra cors headers (available rest methods, allowed authorization header keys and
 * cors client URI). This filter is auto-inserting by Spring Context. Before use this class, insert property:
 * <code>jmpsl.security.cors.client</code> in <code>application.properties</code> file with cors client URL address.
 *
 * @author Miłosz Gilga
 * @since 1.0.2
 */
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsHeaderFilter extends HttpFilter {

    private final String corsClient;
    private String flattedMethods;

    private static final HttpMethod[] REST_METHODS = {
        HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.OPTIONS, HttpMethod.PATCH, HttpMethod.DELETE
    };

    private static final String[] ALLOW_HEADERS = {
        "x-requested-with", "authorization", "Content-Type", "Authorization", "credential", "X-XSRF-TOKEN",
    };

    public CorsHeaderFilter(Environment env) {
        corsClient = SecurityEnv.__SEC_CORS_CLIENT.getProperty(env);
        log.info("Successful loaded CORS HEADERS FILTER into Spring Context");
    }

    @Override
    public void init(FilterConfig filterConfig) {
        flattedMethods = Arrays.stream(REST_METHODS).map(HttpMethod::name).collect(Collectors.joining(","));
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        final HttpServletResponse response = (HttpServletResponse) res;
        final HttpServletRequest request = (HttpServletRequest) req;

        response.setHeader("Access-Control-Allow-Origin", corsClient);
        response.setHeader("Access-Control-Allow-Methods", flattedMethods);
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", String.join(",", ALLOW_HEADERS));

        if (HttpMethod.OPTIONS.name().equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res);
        }
    }
}
