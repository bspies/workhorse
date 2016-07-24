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
package org.workhorse.util;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a version: major, minor, patch, and (optionally)
 * qualifier. Version format follows this pattern:
 * <pre>[major].[minor].[patch_[qualifier]</pre>
 * where the version qualifier is optional. All version components
 * are integers except the qualifier, which may be any string.
 */
public class Version implements Serializable {

    private static final String VERSION_PATTERN = "(\\d+)[.](\\d+)[.](\\d+)(_.+)?";

    private int major, minor, patch;
    private String qualifier;

    /**
     * Creates a version with major, minor, and patch
     * components.
     * @param major The major version
     * @param minor The minor version
     * @param patch The patch version
     */
    public Version(int major, int minor, int patch) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
    }

    public static Version getDefault() {
        return new Version(0, 0, 0);
    }

    /**
     * Parses the version string into a {@code Version}.
     * @param versionString The version string
     * @return The version
     * @throws IllegalArgumentException If the version string is not in the correct format
     */
    public static Version parse(String versionString) {
        Pattern pattern = Pattern.compile(VERSION_PATTERN);
        Matcher matcher = pattern.matcher(versionString);
        if(!matcher.matches()) {
            throw new IllegalArgumentException(
                    String.format("Version string '%s' is not valid. Must match pattern: [0-9]+.[0-9]+.[0-9]+(_str)?",
                            versionString)
            );
        }
        int maj = Integer.parseInt(matcher.group(1)),
            min = Integer.parseInt(matcher.group(2)),
            pch = Integer.parseInt(matcher.group(3));
        Version version = new Version(maj, min, pch);
        if(matcher.groupCount()>3) {
            version.setQualifier(matcher.group(4).replaceFirst("_", ""));
        }
        return version;
    }

    public int getMajor() {
        return major;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    public int getMinor() {
        return minor;
    }

    public void setMinor(int minor) {
        this.minor = minor;
    }

    public int getPatch() {
        return patch;
    }

    public void setPatch(int patch) {
        this.patch = patch;
    }

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Version version = (Version) o;
        if (major != version.major) return false;
        if (minor != version.minor) return false;
        if (patch != version.patch) return false;
        return qualifier != null ? qualifier.equals(version.qualifier) : version.qualifier == null;

    }

    @Override
    public int hashCode() {
        int result = major;
        result = 31 * result + minor;
        result = 31 * result + patch;
        result = 31 * result + (qualifier != null ? qualifier.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return qualifier!=null ?
                String.format("%s.%s.%s_%s", major, minor, patch, qualifier) :
                String.format("%s.%s.%s", major, minor, patch);
    }
}
