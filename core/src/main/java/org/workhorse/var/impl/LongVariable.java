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
import org.workhorse.var.constraint.Constraint;

/**
 * @author Brennan Spies
 */
public class LongVariable extends BaseVariable<Long> {

    private Long value;

    //internal use only
    public LongVariable() {}

    public LongVariable(QualifiedName qualifiedName) {
        super(qualifiedName);
    }

    public LongVariable(QualifiedName qualifiedName, Constraint<Long> longConstraint) {
        super(qualifiedName, longConstraint);
    }

    @Override
    protected void setValueInternal(Long value) {
        this.value = value;
    }

    @Override
    public Class<Long> getType() {
        return Long.class;
    }

    @Override
    public Long getValue() {
        return value;
    }
}
