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
package org.workhorse.activity.impl;

import org.workhorse.Element;
import org.workhorse.activity.Activity;

/**
 * @author Brennan Spies
 */
public abstract class BaseActivity extends Element implements Activity
{
	private String name, description;

    /**
     * Creates the activity with a name.
     * @param name The name of the activity
     */
	public BaseActivity(String name) {
		this.name = name;
	}

    //For JPA only
    public BaseActivity() {}

    /**
	 * @see org.workhorse.activity.Activity#getDescription()
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the activity description
	 * @param description The activity description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @see org.workhorse.activity.Activity#getName()
	 */
	public String getName() {
		return name;
	}
}
