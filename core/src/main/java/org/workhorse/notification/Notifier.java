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
package org.workhorse.notification;

import org.workhorse.actor.User;

/**
 * @author Brennan Spies
 *
 * <p>Interface for specific notifiers that use a specific channel to communicate
 * with users.</p>
 */
public interface Notifier
{
	/**
	 * Sends the notification.
     * @param user The recipient
	 * @param notice The notification
	 */
	public void send(User user, Notice notice);
}
