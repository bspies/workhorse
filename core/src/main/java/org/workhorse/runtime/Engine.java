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
package org.workhorse.runtime;

import org.workhorse.Startable;
import org.workhorse.Stoppable;
import org.workhorse.api.AdminApi;
import org.workhorse.api.ClientApi;

import java.io.Serializable;

/**
 * The WorkHorse runtime engine.
 *
 * @author Brennan Spies
 */
public interface Engine extends Startable, Stoppable
{
	/**
	 * Returns an administrative client to the engine.
	 * @param credentials The user credentials of the admin
	 * @return The admin client
	 */
	public AdminApi getAdminClient(Serializable credentials);

	/**
	 * Returns a client to the engine.
	 * @param credentials The user credentials for the client
	 * @return The client
	 */
	public ClientApi getClient(Serializable credentials);
}
