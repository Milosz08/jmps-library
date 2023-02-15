/*
 * Copyright (c) 2023 by multiple authors
 *
 * File name: OAuth2AutoConfigurationLoader.java
 * Last modified: 18/11/2022, 02:53
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

package org.jmpsl.oauth2;

import org.slf4j.*;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import java.util.*;
import java.util.stream.Collectors;

import static org.jmpsl.oauth2.OAuth2Env.__OAT_SUPPLIERS;

/**
 * OAuth2 auto-configuration loader into spring context. Provide getter for returning available OAuth2 suppliers in selected
 * application. To select available OAuth2 suppliers for Spring application, put <code>jmpsl.oauth2.available-suppliers</code>
 * property in <code>application.properties</code> file. Multiple values insert with comma delimiter.
 *
 * <p>Example: jmpsl.oauth2.available-suppliers=google,facebook,github</p>
 *
 * @author Miłosz Gilga
 * @since 1.0.2
 */
@Configuration
public class OAuth2AutoConfigurationLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(OAuth2AutoConfigurationLoader.class);

    private static Set<OAuth2Supplier> availableOAuth2Suppliers = new HashSet<>();
    private final Environment env;

    OAuth2AutoConfigurationLoader(Environment env) {
        this.env = env;
        final String suppliers = __OAT_SUPPLIERS.getProperty(env);
        availableOAuth2Suppliers = Arrays.stream(suppliers.split(","))
                .map(OAuth2Supplier::checkIfSupplierIsValid)
                .collect(Collectors.toSet());
        LOGGER.info("Successful loaded OAuth2 available providers. Loaded providers: [ {} ]", suppliers);
    }

    public static Set<OAuth2Supplier> getAvailableOAuth2Suppliers() {
        return availableOAuth2Suppliers;
    }

    @Bean
    public CookieOAuth2ReqRepository cookieOAuth2ReqRepository() {
        return new CookieOAuth2ReqRepository(env);
    }

    @Bean
    public OAuth2SupplierPersistenceEnumConverter oAuth2SupplierPersistenceEnumConverter() {
        return new OAuth2SupplierPersistenceEnumConverter();
    }
}
