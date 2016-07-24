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
package org.workhorse;

import java.time.LocalDateTime;

/**
 * Interface to capture common measurements for process elements.
 *
 *  @author Brennan Spies
 */
public interface Recordable 
{
	/**
	 * Returns the date that the work was created.
	 * @return The task creation date
	 */
	LocalDateTime getCreationDate();
	
	/**
	 * Returns the date the work was completed.
	 * @return The task completion date
	 */
	LocalDateTime getCompletionDate();
}
