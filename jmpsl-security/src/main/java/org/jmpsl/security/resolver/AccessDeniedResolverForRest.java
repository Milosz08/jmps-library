/*
 * Copyright (c) 2023 by MULTIPLE AUTHORS
 *
 * File name: AccessDeniedResolverForRest.java
 * Last modified: 28/03/2023, 23:09
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

import org.jmpsl.core.i18n.LocaleSet;
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
        final String message = localeMessageService.getMessage(LocaleSet.SECURITY_AUTHENTICATION_EXC);
        final var resDto = new GeneralServerExceptionResDto(ServerExceptionResDto.generate(HttpStatus.FORBIDDEN, req), message);
        res.setStatus(HttpStatus.FORBIDDEN.value());
        res.setContentType(MediaType.APPLICATION_JSON_VALUE);
        res.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        new Gson().toJson(resDto, GeneralServerExceptionResDto.class, res.getWriter());
    }
}
