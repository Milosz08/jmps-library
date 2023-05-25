/*
 * Copyright (c) 2023 by MULTIPLE AUTHORS
 *
 * File name: IOAuth2LoaderService.java
 * Last modified: 18/05/2023, 20:49
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

package org.jmpsl.oauth2.service;

import org.jmpsl.oauth2.user.OAuth2UserExtender;

/**
 * Implement this interface in most of the top auth service layer and override method for process OAuth2
 * registration webflow.
 *
 * @author Miłosz Gilga
 * @since 1.0.2
 */
public interface IOAuth2LoaderService {
    OAuth2UserExtender registrationProcessingFactory(final OAuth2RegistrationDataDto registrationData);
}
