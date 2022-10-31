/*
 * Copyright (c) 2022 by multiple authors
 *
 * File name: BufferedFilePayload.java
 * Last modified: 30/10/2022, 14:41
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

package pl.miloszgilga.lib.jmpsl.file;

import lombok.*;

/**
 * Simple POJO class storing generated file data (bytes representation and SFTP server location).
 *
 * <ul>
 *     <li><code>bytesRepresentation</code> - file as bytes array stream</li>
 *     <li><code>location</code> - file location on external SFTP server</li>
 * </ul>
 *
 * @author Miłosz Gilga
 * @since 1.0.2
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BufferedFileResponse {
    private byte[] bytesRepresentation;
    private String location;
}
