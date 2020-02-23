/*
 * IOUtils.java
 * Copyright (C) 2020 FracPete
 */

package com.github.fracpete.resourceextractor4j;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

/**
 * I/O related utility functions.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class IOUtils {

  /**
   * Closes the stream.
   * 
   * @param stream	the stream to close
   */
  public static void closeQuietly(InputStream stream) {
    if (stream != null) {
      try {
	stream.close();
      }
      catch (Exception e) {
	// ignored
      }
    }
  }

  /**
   * Closes the stream.
   * 
   * @param stream	the stream to close
   */
  public static void closeQuietly(OutputStream stream) {
    if (stream != null) {
      try {
	stream.close();
      }
      catch (Exception e) {
	// ignored
      }
    }
  }

  /**
   * Closes the reader.
   * 
   * @param reader	the reader to close
   */
  public static void closeQuietly(Reader reader) {
    if (reader != null) {
      try {
	reader.close();
      }
      catch (Exception e) {
	// ignored
      }
    }
  }

  /**
   * Closes the writer.
   * 
   * @param writer	the writer to close
   */
  public static void closeQuietly(Writer writer) {
    if (writer != null) {
      try {
	writer.close();
      }
      catch (Exception e) {
	// ignored
      }
    }
  }

  /**
   * Copies the data from the input stream into the output stream.
   * Uses 4k bytes as buffer.
   *
   * @param in		the input stream to read from
   * @param out		the output stream to write to
   * @throws Exception	if copying fails
   */
  public static void copy(InputStream in, OutputStream out) throws Exception {
    copy(in, out, 4 * 1024);
  }

  /**
   * Copies the data from the input stream into the output stream.
   *
   * @param in		the input stream to read from
   * @param out		the output stream to write to
   * @param bufSize 	the size of the buffer to use
   * @throws Exception	if copying fails
   */
  public static void copy(InputStream in, OutputStream out, int bufSize) throws Exception {
    byte[]	buffer;
    int		read;

    buffer = new byte[bufSize];
    while ((read = in.read(buffer, 0, bufSize)) > 0)
      out.write(buffer, 0, read);
  }
}
