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
package org.workhorse.connect;

import org.workhorse.api.LoginApi;

/**
 * @author Brennan Spies
 *
 */
public interface Connector extends LoginApi 
{
	/**
	 * Opens a connection to the process engine.
	 * @throws ConnectorException If the connection fails
	 */
	public void open() throws ConnectorException;
	
	/**
	 * Closes the connection to the process engine.
	 * @throws ConnectorException If an error occurs closing the connector
	 */
	public void close() throws ConnectorException;
}
