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
package org.workhorse.service;

/**
 * Common interface for all services provided to the
 * WorkHorse runtime engine.
 *
 * @author Brennan Spies
 */
public interface Service 
{
	/**
	 * Starts up the service.
	 */
	public void start();
	
	/**
	 * Shuts down the service.
	 */
	public void shutdown();
}
