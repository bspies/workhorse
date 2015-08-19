/**
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
package org.workhorse.var.impl;

import org.workhorse.var.QualifiedName;

/**
 * Variable for binary data.
 *
 * @author Brennan Spies
 */
public class BinaryVariable extends BaseVariable<byte[]>
{
    private byte[] value;

    //internal use only
    public BinaryVariable() {}

    public BinaryVariable(QualifiedName qualifiedName) { super(qualifiedName); }

    @Override public Class<byte[]> getType() {
        return byte[].class;
    }

    @Override public byte[] getValue() {
        return value;
    }

    @Override protected void setValueInternal(byte[] value) {
        this.value = value;
    }
}
