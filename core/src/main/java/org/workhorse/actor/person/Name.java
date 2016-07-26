/*
 * Copyright (c) 2009-2016 Brennan Spies
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.workhorse.actor.person;

import java.util.Locale;

/**
 * Interface to represent the name of a person. See the W3C internationalization notes on
 * <a href="https://www.w3.org/International/questions/qa-personal-names">personal names</a>.
 *
 * @author Brennan Spies
 */
public interface Name {

    /**
     * Returns the family name of the person,
     * which may be multiple (e.g. hyphenated names or
     * space-delimited paternal and maternal family names).
     * @return The family name
     */
    String getFamilyName();

    /**
     * Returns the given name of the individual person. Also
     * known as "first name".
     * @return The given name
     */
    String getGivenName();

    /**
     * Returns the name as it should
     * be normally displayed, e.g. in a UI.
     * The display format may or may not be
     * locale-sensitive.
     * @param locale The display locale
     * @return The name display
     */
    String getDisplay(Locale locale);
}
