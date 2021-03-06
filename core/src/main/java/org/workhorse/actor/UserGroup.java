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
package org.workhorse.actor;

import java.util.Collection;

/**
 * A defined group of users.
 *
 * @author Brennan Spies
 */
public interface UserGroup<U extends User> extends Actor {
	/**
	 * Returns all of the users that belong
	 * to this group.
	 * @return The users
	 */
	Collection<U> getUsers();
}
