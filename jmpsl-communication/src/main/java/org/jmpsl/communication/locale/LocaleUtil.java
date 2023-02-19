/*
 * Copyright (c) 2023 by multiple authors
 *
 * File name: LocaleUtil.java
 * Last modified: 20/02/2023, 00:03
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

import java.util.Map;

import static java.lang.String.valueOf;
import static org.springframework.util.Assert.notNull;

/**
 * Locale class with utilities static method.
 *
 * @author Miłosz Gilga
 * @since 1.0.2
 */
public class LocaleUtil {

    private LocaleUtil() {
    }

    /**
     * Method responsible for replacing locale message variable placeholders with actual values stored in Map as
     * key (placeholder) and value (variable). If message or variables {@link Map} is null, throws
     * {@link IllegalArgumentException}. Otherwise return prepared string.
     *
     * @param message string message with placeholders, ex. <i>"This is a test with {{param1}} variable."</i>
     * @param variables {@link Map} collection of the variables, ex. <i>Map.of("param1", "my testing")</i>
     * @return prepared message, ex. <i>"This is a test with my testing variable."</i>
     * @author Miłosz Gilga
     * @since 1.0.2
     *
     * @throws IllegalArgumentException if passed message or variables are null
     */
    public static String extractVariablesFromMessage(String message, Map<String, Object> variables) {
        notNull(message, "Message object cannot be null");
        notNull(variables, "Variables map cannot be null");
        for (final Map.Entry<String, Object> variable : variables.entrySet()) {
            message = message.replace("{{" + variable.getKey() + "}}", valueOf(variable.getValue()));
        }
        return message;
    }
}
