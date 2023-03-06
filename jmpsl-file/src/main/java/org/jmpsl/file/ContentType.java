/*
 * Copyright (c) 2023 by multiple authors
 *
 * File name: ContentType.java
 * Last modified: 03/11/2022, 00:46
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

package org.jmpsl.file;

import lombok.Getter;
import lombok.AllArgsConstructor;

/**
 * Enum set of file content types in "type/notation" (ex. image/jpeg, image/png etc.).
 *
 * @author Miłosz Gilga
 * @since 1.0.2
 */
@Getter
@AllArgsConstructor
public enum ContentType {

    /**
     * Content type for png image.
     *
     * @since 1.0.2
     */
    PNG("image/png", "png"),

    /**
     * Content type for jpeg image.
     *
     * @since 1.0.2
     */
    JPEG("image/jpeg", "jpeg"),

    /**
     * Content type for jpg image.
     *
     * @since 1.0.2
     */
    JPG("image/jpg", "jpg");

    /**
     * Content type file format (image/png, image/jpeg etc.).
     *
     * @since 1.0.2
     */
    private final String contentTypeName;

    /**
     * Regular extension name (png, jpeg, etc.).
     *
     * @since 1.0.2
     */
    private final String regularName;
}
