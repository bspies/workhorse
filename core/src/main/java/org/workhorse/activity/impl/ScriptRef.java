/**
 * 
 */
package org.workhorse.activity.impl;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;

import javax.activation.MimeType;
import javax.activation.MimeTypeParseException;
import javax.activation.URLDataSource;

/**
 * @author Brennan Spies
 *
 */
public abstract class ScriptRef 
{
	/**
	 * Returns the input stream containing the script text.
	 * @return The script input stream
	 * @throws IOException If an error occurs opening the input stream
	 */
	public abstract Reader getReader() throws IOException;
	
	/**
     * Returns a {@code ScriptRef}
	 * @param url The script URL
	 * @return The script reference
	 */
	public static ScriptRef get(URL url) {
		try {
			return new UrlScriptRef(url, 
				new MimeType(new URLDataSource(url).getContentType()));
		} catch (MimeTypeParseException e) {
			throw new RuntimeException("Unable to get valid MIME type from URL: " + url.toString(), e);
		}
	}
	
	/**
     * Returns a {@code ScriptRef} to a script file.
	 * @param file  The script file
	 * @return The script reference
	 */
	public static ScriptRef get(File file) {
		return new FileScriptRef(file);
	}
	
	/**
     * Returns a {@code ScriptRef} to a string script.
	 * @param language The language of the script
	 * @param script The script
	 * @return The script reference
	 */
	public static ScriptRef get(String language, String script) {
		return new StringScriptRef(language, script);
	}
	
	/**
	 * Reference to a script at the given URL.
	 */
	private static class UrlScriptRef extends ScriptRef
	{
		URL url;
		MimeType mimeType;
		
		UrlScriptRef(URL url, MimeType mimeType) {
			this.url = url;
            this.mimeType = mimeType;
		}
		
		@Override public Reader getReader() throws IOException {
			return new InputStreamReader(url.openStream());
		}
	}
	
	/**
	 * Reference to a script at the given file location.
	 */
	private static class FileScriptRef extends ScriptRef
	{
		File file;
		
		FileScriptRef(File file) {
			this.file = file;
		}
		
		@Override public Reader getReader() throws IOException {
			return new FileReader(file);
		}
	}
	
	/**
	 * Reference to the raw script itself (as a String).
	 */
	private static class StringScriptRef extends ScriptRef 
	{
		String script, language;
		
		StringScriptRef(String language, String script) {
			this.script = script;
            this.language = language;
		}
		
		@Override public Reader getReader() throws IOException {
			return new StringReader(script);
		}
	}
}
