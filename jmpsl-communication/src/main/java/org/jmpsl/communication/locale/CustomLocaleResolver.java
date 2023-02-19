/*
 * Copyright (c) 2023 by multiple authors
 *
 * File name: CustomLocaleResolver.java
 * Last modified: 19/02/2023, 17:22
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

package org.jmpsl.communication.locale;

import jakarta.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.*;

import static java.util.Locale.*;

/**
 * External configuration class for resolve locale by Http request header "Accept-Language". If header does not exist,
 * setting the default locale from property <code>jmpsl.communication.locale.default-locale</code> from
 * <code>application.properties</code> file.
 *
 * @author Miłosz Gilga
 * @since 1.0.2
 */
public class CustomLocaleResolver extends AcceptHeaderLocaleResolver {

    private final List<Locale> supportedLocales;

    public CustomLocaleResolver(List<Locale> supportedLocales) {
        this.supportedLocales = supportedLocales;
    }

    @Override
    public Locale resolveLocale(HttpServletRequest req) {
        final String acceptLang = req.getHeader("Accept-Language");
        if (StringUtils.isEmpty(acceptLang)) return getDefault();
        final List<Locale.LanguageRange> localeRangeList = Locale.LanguageRange.parse(acceptLang);
        final Locale currentLocale = lookup(localeRangeList, supportedLocales);
        LocaleContextHolder.setLocale(currentLocale);
        return currentLocale;
    }
}
