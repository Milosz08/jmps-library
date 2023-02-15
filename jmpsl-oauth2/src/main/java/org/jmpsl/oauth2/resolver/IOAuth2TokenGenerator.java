/*
 * Copyright (c) 2023 by multiple authors
 *
 * File name: IOAuth2TokenGenerator.java
 * Last modified: 19/10/2022, 22:17
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

package org.jmpsl.oauth2.resolver;

import org.springframework.security.core.Authentication;

/**
 * Implement this interface in Spring Bean and override method for generate token for redirect parameter on successful
 * OAuth2 authorize.
 *
 * @author Miłosz Gilga
 * @since 1.0.2
 */
public interface IOAuth2TokenGenerator {
    String generateToken(final Authentication auth);
}
