/*
 * Copyright (c) 2023 by MULTIPLE AUTHORS
 *
 * File name: ExternalFileServerMalfunctionException.java
 * Last modified: 28/03/2023, 23:14
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

package org.jmpsl.file.exception;

import org.springframework.http.HttpStatus;

import org.jmpsl.core.i18n.LocaleSet;
import org.jmpsl.core.exception.RestServiceServerException;

/**
 * Custom exception throws after SFTP image sending malfunction. Extended {@link RestServiceServerException}, so return
 * JSON object in response body part.
 *
 * @author Miłosz Gilga
 * @since 1.0.2
 */
public class ExternalFileServerMalfunctionException extends RestServiceServerException {

    public ExternalFileServerMalfunctionException() {
        super(HttpStatus.SERVICE_UNAVAILABLE, LocaleSet.FILE_EXTERNAL_FILE_SERVER_MALFUNCTION_EXC);
    }
}
