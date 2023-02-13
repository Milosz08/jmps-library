/*
 * Copyright (c) 2022 by multiple authors
 *
 * File name: CapitalizedFirstLetterConverter.java
 * Last modified: 27/10/2022, 04:08
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

package pl.miloszgilga.lib.jmpsl.core.mapper.converter;

import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import pl.miloszgilga.lib.jmpsl.core.mapper.*;

import static org.apache.commons.lang3.StringUtils.capitalize;

/**
 * Custom converter allows to capitalized first letter in mapping string A object.
 *
 * @author Miłosz Gilga
 * @since 1.0.2
 */
@Component
@MappingConverter
public class CapitalizedFirstLetterConverter extends AbstractConverter<String, String> {

    @Override
    public String convert(String source) {
        return capitalize(source);
    }
}
