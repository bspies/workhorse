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
package org.workhorse.script;

import javax.activation.MimeType;
import javax.activation.MimeTypeParseException;
import javax.activation.URLDataSource;
import java.io.*;
import java.net.URL;

/**
 * Reference to a script resource.
 *
 * @author Brennan Spies
 *
 */
public abstract class ScriptResource
{
	/**
	 * Returns the input stream containing the script text.
	 * @return The script input stream
	 * @throws IOException If an error occurs opening the input stream
	 */
	public abstract Reader getReader() throws IOException;
	
	/**
     * Returns a {@code ScriptResource}
	 * @param url The script URL
	 * @return The script reference
	 */
	public static ScriptResource get(URL url) {
		try {
			return new UrlScriptResource(url,
				new MimeType(new URLDataSource(url).getContentType()));
		} catch (MimeTypeParseException e) {
			throw new RuntimeException("Unable to get valid MIME type from URL: " + url.toString(), e);
		}
	}
	
	/**
     * Returns a {@code ScriptResource} to a script file.
	 * @param file  The script file
	 * @return The script reference
	 */
	public static ScriptResource get(File file) {
		return new FileScriptResource(file);
	}
	
	/**
     * Returns a {@code ScriptResource} to a string script.
	 * @param language The language of the script
	 * @param script The script
	 * @return The script reference
	 */
	public static ScriptResource get(String language, String script) {
		return new StringScriptResource(language, script);
	}
	
	/**
	 * Reference to a script at the given URL.
	 */
	private static class UrlScriptResource extends ScriptResource
	{
		URL url;
		MimeType mimeType;
		
		UrlScriptResource(URL url, MimeType mimeType) {
			this.url = url;
            this.mimeType = mimeType;
		}
		
		@Override
		public Reader getReader() throws IOException {
			return new InputStreamReader(url.openStream());
		}
	}
	
	/**
	 * Reference to a script at the given file location.
	 */
	private static class FileScriptResource extends ScriptResource
	{
		File file;
		
		FileScriptResource(File file) {
			this.file = file;
		}
		
		@Override
		public Reader getReader() throws IOException {
			return new FileReader(file);
		}
	}
	
	/**
	 * Reference to the raw script itself (as a String).
	 */
	private static class StringScriptResource extends ScriptResource
	{
		String script, language;
		
		StringScriptResource(String language, String script) {
			this.script = script;
            this.language = language;
		}
		
		@Override
		public Reader getReader() throws IOException {
			return new StringReader(script);
		}
	}
}
