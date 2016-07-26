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
 * A western name.
 *
 * @author Brennan Spies
 */
public class WesternName implements Name {

    String firstName, middleName, lastName;

    public WesternName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String getFamilyName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    @Override
    public String getGivenName() {
        return firstName;
    }

    @Override
    public String getDisplay(Locale locale) {
        //todo replace with template
        StringBuilder sb = new StringBuilder(firstName);
        if(middleName!=null) {
            sb.append(" ").append(middleName);
        }
        sb.append(" ").append(lastName);
        return sb.toString();
    }

    @Override
    public String toString() {
        return getDisplay(Locale.getDefault()); //todo make Locale dynamic
    }
}
