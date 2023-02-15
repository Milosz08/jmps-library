/*
 * Copyright (c) 2023 by multiple authors
 *
 * File name: ServletPathUtil.java
 * Last modified: 02/11/2022, 14:32
 * Project name: jmps-library
 *
 * Licensed under the MIT license; you may not use this file except in compliance with the License.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * THE ABOVE COPYRIGHT NOTICE AND THIS PERMISSION NOTICE SHALL BE INCLUDED IN ALL
 * COPIES OR SUBSTANTIAL PORTIONS OF THE SOFTWARE.
 */

package org.jmpsl.core;

import org.javatuples.Pair;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import jakarta.servlet.http.*;

import static org.springframework.util.Assert.notNull;

/**
 * Class storing static methods for utilities path servlet actions. Most of methods in this class include auto-inserting
 * {@link HttpServletRequest} and {@link HttpServletResponse} parameters by Tomcat Servlet Container.
 *
 * @author Miłosz Gilga
 * @since 1.0.2
 */
public class ServletPathUtil {

    private ServletPathUtil() {
    }

    /**
     * Static method responsible for building redirect message URI. Returning {@link URI} object, which contains
     * message query parameter with error query parameter.
     *
     * @param message redirect message parameter
     * @param redirectPageUri redirect address <i>(ex. http://localhost:8080/example)</i>
     * @param error parameter identifing redirect error
     * @return {@link URI} object <i>(ex. http://localhost:8080/example?message=test&amp;error=false)</i>
     * @author Miłosz Gilga
     * @since 1.0.2
     *
     * @throws IllegalArgumentException if message or redirectPageUri parameters are null
     */
    public static URI redirectMessageUri(String message, String redirectPageUri, boolean error) {
        final List<Pair<String, Object>> queryParams = List.of(
                new Pair<>("message", message),
                new Pair<>("error", error));
        return generateBasicUri(queryParams, redirectPageUri);
    }

    /**
     * Static method responsible for building redirect token URI. Returning {@link URI} object, which contains
     * token query parameter with supplier token query parameter.
     *
     * @param token generated bearer token
     * @param redirectPageUri redirect address <i>(ex. http://localhost:8080/example)</i>
     * @param supplier token credentials supplier (ex. google, facebook, local etc.)
     * @return {@link URI} object <i>(ex. http://localhost:8080/example?token=fagg3g&amp;supplier=google)</i>
     * @author Miłosz Gilga
     * @since 1.0.2
     *
     * @throws IllegalArgumentException if token, supplier or redirectPageUri parameters are null
     */
    public static URI redirectTokenUri(String token, String redirectPageUri, String supplier) {
        final List<Pair<String, Object>> queryParams = List.of(
                new Pair<>("token", token),
                new Pair<>("supplier", supplier));
        return generateBasicUri(queryParams, redirectPageUri);
    }

    /**
     * Static method responsible for building redirect error URI. Returning {@link URI} object, which contains only
     * error message parameter.
     *
     * @param message error message as string value
     * @param redirectPageUri redirect address <i>(ex. http://localhost:8080/example)</i>
     * @return {@link URI} object <i>(ex. http://localhost:8080/example?error=thisissampleerrormessage)</i>
     * @author Miłosz Gilga
     * @since 1.0.2
     *
     * @throws IllegalArgumentException if message or redirectPageUri parameters are null
     */
    public static URI redirectErrorUri(String message, String redirectPageUri) {
        final List<Pair<String, Object>> queryParams = List.of(new Pair<>("error", message));
        return generateBasicUri(queryParams, redirectPageUri);
    }

    /**
     * Inside-class private method responsible for generate basic {@link URI} object from queryParams (as list of tuples)
     * and redirectPageUri string value.
     *
     * @param queryParams redirect uri query parameters
     * @param redirectPageUri redirect uri string value
     * @return builded {@link URI} based passed parameters
     * @author Miłosz Gilga
     * @since 1.0.2
     *
     * @throws IllegalArgumentException if redirectPageUri string value are null
     */
    private static URI generateBasicUri(List<Pair<String, Object>> queryParams, String redirectPageUri) {
        notNull(redirectPageUri, "RedirectPageUri cannot be null.");
        final UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(redirectPageUri);
        for(final Pair<String, Object> param : queryParams) {
            notNull(param.getValue1(), "Query parameter cannot be null.");
            uriComponentsBuilder.queryParam(param.getValue0(), param.getValue1());
        }
        return uriComponentsBuilder.build().toUri();
    }
}
