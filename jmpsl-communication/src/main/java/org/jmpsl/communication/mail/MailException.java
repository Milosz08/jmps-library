/*
 * Copyright (c) 2023 by multiple authors
 *
 * File name: MailException.java
 * Last modified: 14/02/2023, 21:04
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

package org.jmpsl.communication.mail;

import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.Set;

import org.jmpsl.core.exception.RestServiceServerException;

/**
 * Email exceptions generating JSON object from {@link RestServiceServerException} object structure model.
 *
 * @author Miłosz Gilga
 * @since 1.0.2
 */
public class MailException {

    /**
     * Exception for unable to send email (when email template is malformed, SMTP server not responding or other email
     * services malfunctions). Exception generated JSON object from {@link RestServiceServerException} object structure model.
     *
     * @author Miłosz Gilga
     * @since 1.0.2
     */
    public static class UnableToSendEmailException extends RestServiceServerException {
        public UnableToSendEmailException(Set<String> emailRecipents) {
            super(HttpStatus.SERVICE_UNAVAILABLE, "jmpsl.communication.exception.UnableToSendEmailException",
                Map.of("emailAddress", String.join(", ", emailRecipents)));
        }
    }
}
