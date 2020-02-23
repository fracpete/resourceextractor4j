/*
 * Content.java
 * Copyright (C) 2020 FracPete
 */

package com.github.fracpete.resourceextractor4j;

import gnu.trove.list.TByteList;
import gnu.trove.list.array.TByteArrayList;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * For reading content from resource files.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class Content {

  /** for logging. */
  protected static Logger LOGGER = Logger.getLogger(Content.class.getName());

  /**
   * Returns all the lines from the resource file.
   *
   * @return		the lines, null if failed to read
   */
  public static List<String> readLines(String resource) {
    List<String>    	result;
    InputStream in;
    InputStreamReader isr;
    BufferedReader br;
    String		line;

    result = new ArrayList<>();
    in     = null;
    isr    = null;
    br     = null;

    try {
      in  = Content.class.getClassLoader().getResourceAsStream(resource);
      isr = new InputStreamReader(in);
      br  = new BufferedReader(isr);
      while ((line = br.readLine()) != null)
	result.add(line);
    }
    catch (Exception e) {
      LOGGER.log(Level.SEVERE, "Failed to read lines from resource file: " + resource, e);
      return null;
    }
    finally {
      IOUtils.closeQuietly(br);
      IOUtils.closeQuietly(isr);
      IOUtils.closeQuietly(in);
    }

    return result;
  }

  /**
   * Reads the file as single string.
   *
   * @param resource 	the resource to read
   * @return		the string, null if failed to read
   */
  public static String readString(String resource) {
    byte[]	bytes;

    bytes = readBytes(resource);
    if (bytes == null)
      return null;

    try {
      return new String(bytes);
    }
    catch (Exception e) {
      LOGGER.log(Level.SEVERE, "Failed to read string from resource file: " + resource, e);
      return null;
    }
  }

  /**
   * Reads the file as single string.
   *
   * @param resource 	the resource to read
   * @param charset 	the character set to use
   * @return		the string, null if failed to read
   */
  public static String readString(String resource, Charset charset) {
    byte[]	bytes;

    bytes = readBytes(resource);
    if (bytes == null)
      return null;

    try {
      return new String(bytes, charset);
    }
    catch (Exception e) {
      LOGGER.log(Level.SEVERE, "Failed to read string (" + charset + ") from resource file: " + resource, e);
      return null;
    }
  }

  /**
   * Returns all the bytes from the resource file.
   *
   * @return		the bytes, null if failed to read
   */
  public static byte[] readBytes(String resource) {
    TByteList 		result;
    InputStream 	in;
    byte[]		buffer;
    int 		read;

    result = new TByteArrayList();
    in     = null;
    buffer = new byte[4096];

    try {
      in  = Content.class.getClassLoader().getResourceAsStream(resource);
      while ((read = in.read(buffer)) > 0)
	result.add(buffer, 0, read);
    }
    catch (Exception e) {
      LOGGER.log(Level.SEVERE, "Failed to read bytes from resource file: " + resource, e);
      return null;
    }
    finally {
      IOUtils.closeQuietly(in);
    }

    return result.toArray();
  }

  /**
   * Reads the properties file at the specified location.
   *
   * @return		the properties, null if failed to read
   */
  public static Properties readProperties(String resource) {
    Properties 		result;
    InputStream 	in;

    result = new Properties();
    in     = null;

    try {
      in = Content.class.getClassLoader().getResourceAsStream(resource);
      result.load(in);
    }
    catch (Exception e) {
      LOGGER.log(Level.SEVERE, "Failed to read properties from resource file: " + resource, e);
      return null;
    }
    finally {
      IOUtils.closeQuietly(in);
    }

    return result;
  }
}
