/*
 * Copyright (c) 2023 by multiple authors
 *
 * File name: AccessDeniedResolverForRest.java
 * Last modified: 20/02/2023, 03:11
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

package org.jmpsl.security.resolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.jmpsl.security.SecurityLocaleSet;
import org.jmpsl.core.i18n.LocaleMessageService;
import org.jmpsl.core.exception.ServerExceptionResDto;
import org.jmpsl.core.exception.GeneralServerExceptionResDto;

/**
 * Custom access denied entry point for REST Spring boot security context. DI instance must be declared in Spring
 * security methods chain in {@link SecurityFilterChain} bean. Override handle method resolve exception and return
 * localized message into client.
 *
 * @author Miłosz Gilga
 * @since 1.0.2
 */
@Component
public class AccessDeniedResolverForRest extends AccessDeniedHandlerImpl {

    private final LocaleMessageService localeMessageService;

    AccessDeniedResolverForRest(LocaleMessageService localeMessageService) {
        this.localeMessageService = localeMessageService;
    }

    @Override
    public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException ex) throws IOException {
        final String message = localeMessageService.getMessage(SecurityLocaleSet.AUTHENTICATION_EXC);
        final var resDto = new GeneralServerExceptionResDto(ServerExceptionResDto.generate(HttpStatus.FORBIDDEN, req), message);
        res.setStatus(HttpStatus.FORBIDDEN.value());
        res.setContentType(MediaType.APPLICATION_JSON_VALUE);
        res.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        new Gson().toJson(resDto, GeneralServerExceptionResDto.class, res.getWriter());
    }
}
