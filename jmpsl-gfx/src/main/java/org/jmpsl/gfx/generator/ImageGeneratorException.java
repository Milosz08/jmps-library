/*
 * Copyright (c) 2023 by multiple authors
 *
 * File name: ImageGeneratorException.java
 * Last modified: 19/02/2023, 19:03
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

package org.jmpsl.gfx.generator;

import org.jmpsl.core.exception.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Custom exceptions (extends {@link RestServiceServerException}) used in image generator JMPS library module.
 *
 * @author Miłosz Gilga
 * @since 1.0.2
 */
public class ImageGeneratorException {

    /**
     * Custom exception extending {@link RestServiceServerException}, returning JSON POJO object message and throw
     * after set auto-generated image dimensions to not supported.
     *
     * @author Miłosz Gilga
     * @since 1.0.2
     */
    public static class ImageNotSupportedDimensionsException extends RestServiceServerException {
        public ImageNotSupportedDimensionsException() {
            super(BAD_REQUEST, "jmpsl.gfx.exception.ImageNotSupportedDimensionsException");
        }
    }

    /**
     * Custom exception extending {@link RestServiceAuthServerException}, returning JSON POJO object message and throw
     * after set auto-generated image font size to not supported dimensions.
     *
     * @author Miłosz Gilga
     * @since 1.0.2
     */
    public static class FontSizeNotSupportedException extends RestServiceServerException {
        public FontSizeNotSupportedException() {
            super(BAD_REQUEST, "jmpsl.gfx.exception.FontSizeNotSupportedException");
        }
    }

    /**
     * Custom exception extending {@link RestServiceAuthServerException}, returning JSON POJO object message and throw
     * after set auto-generated image initials more than 2 characters.
     *
     * @author Miłosz Gilga
     * @since 1.0.2
     */
    public static class TooMuchInitialsCharactersException extends RestServiceServerException {
        public TooMuchInitialsCharactersException() {
            super(BAD_REQUEST, "jmpsl.gfx.exception.TooMuchInitialsCharactersException");
        }
    }

}
