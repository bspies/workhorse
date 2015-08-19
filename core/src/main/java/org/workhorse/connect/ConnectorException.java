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

/**
 * @author Brennan Spies
 * <p>General exception thrown when an attempt to connect to the process engine
 * fails.</p>
 */
public class ConnectorException extends Exception 
{
	private static final long serialVersionUID = 496138706426856392L;

	/**
	 * @param message
	 */
	public ConnectorException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ConnectorException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ConnectorException(String message, Throwable cause) {
		super(message, cause);
	}

}
