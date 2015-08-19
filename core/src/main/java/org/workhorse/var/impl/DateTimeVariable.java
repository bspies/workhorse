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

import org.joda.time.DateTime;
import org.workhorse.var.QualifiedName;
import org.workhorse.var.constraint.Constraint;

import java.util.Date;

/**
 * @author Brennan Spies
 */
public class DateTimeVariable extends BaseVariable<DateTime> {

    private Date value;

    //internal use only
    public DateTimeVariable() {}

    public DateTimeVariable(QualifiedName qualifiedName) {
        super(qualifiedName);
    }

    public DateTimeVariable(QualifiedName qualifiedName, Constraint<DateTime> dateTimeConstraint) {
        super(qualifiedName, dateTimeConstraint);
    }

    @Override
    protected void setValueInternal(DateTime value) {
        this.value = value.toDate();
    }

    @Override
    public Class<DateTime> getType() {
        return DateTime.class;
    }

    @Override
    public DateTime getValue() {
        return new DateTime(value.getTime());
    }
}
