/*
 * Copyright (c) 2022 by multiple authors
 *
 * File name: HashCodeFormatException.java
 * Last modified: 31/10/2022, 19:09
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

package pl.miloszgilga.lib.jmpsl.file.hashcode;

import pl.miloszgilga.lib.jmpsl.util.exception.BasicServerException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Exception throws after passed invalid directory hash code (generated by {@link FileHashCodeGenerator}). Extended
 * {@link BasicServerException}, so return JSON object in response body part.
 *
 * @author Miłosz Gilga
 * @since 1.0.2
 */
public class HashCodeFormatException extends BasicServerException {

    public HashCodeFormatException() {
        super(BAD_REQUEST, "Passed hash code is invalid.", new Object());
    }
}
