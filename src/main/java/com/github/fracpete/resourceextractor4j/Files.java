/*
 * Files.java
 * Copyright (C) 2020 FracPete
 */

package com.github.fracpete.resourceextractor4j;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * For extracting resource files.
 *
 * @author FracPete (fracpete at waikato dot ac dot nz)
 */
public class Files {

  /** for logging. */
  protected static Logger LOGGER = Logger.getLogger(Files.class.getName());

  /**
   * Copies the specified resources to the output directory.
   * Sub-directories in the file list get recreated automatically in the
   * output directory.
   *
   * @param inDir	the resource directory to use
   * @param files	the files to copy (can contain sub-dirs)
   * @param outDir	the output directory
   * @return		the full path
   */
  public static String copyResourcesTo(String inDir, List<String> files, String outDir) {
    File 	currentFile;
    File 	currentOutDir;
    String 	currentInDir;
    String 	currentName;

    for (String file : files) {
      currentFile  = new File(file);
      currentInDir = currentFile.getParent();
      currentName  = currentFile.getName();
      try {
        currentOutDir = new File(outDir + "/" + (currentInDir == null ? "" : currentInDir));
        if (!currentOutDir.exists())
          currentOutDir.mkdirs();
	copyResourceTo(inDir + "/" + (currentInDir == null ? "" : currentInDir), currentName, currentOutDir.getAbsolutePath());
      }
      catch (Exception e) {
        LOGGER.log(Level.SEVERE, "Failed to copy '" + file + "' to '" + outDir + "'!", e);
        return "Failed to copy '" + file + "' to '" + outDir + "': " + e;
      }
    }

    return null;
  }

  /**
   * Copies the specified resource to the output directory.
   *
   * @param inDir	the resource directory to use
   * @param name	the name of the resource
   * @param outDir	the output directory
   * @return		the full path
   */
  public static String copyResourceTo(String inDir, String name, String outDir) throws Exception {
    String			result;
    String			resource;
    InputStream 		is;
    BufferedInputStream 	bis;
    String			outFull;
    File 			out;
    FileOutputStream 		fos;
    BufferedOutputStream 	bos;

    result = null;
    is     = null;
    bis    = null;
    fos    = null;
    bos    = null;

    try {
      resource = inDir;
      if (!resource.endsWith("/"))
	resource += "/";
      resource += name;
      outFull = outDir + File.separator + name;
      LOGGER.info("Copying resource '" + resource + "' to '" + outFull + "'");
      is  = Files.class.getClassLoader().getResourceAsStream(resource);
      bis = new BufferedInputStream(is);
      out = new File(outFull);
      fos = new FileOutputStream(out);
      bos = new BufferedOutputStream(fos);
      IOUtils.copy(bis, bos);
      result = out.getAbsolutePath();
    }
    catch (Exception e) {
      LOGGER.log(Level.SEVERE, "Copying failed!", e);
      throw e;
    }
    finally {
      IOUtils.closeQuietly(bis);
      IOUtils.closeQuietly(is);
      IOUtils.closeQuietly(bos);
      IOUtils.closeQuietly(fos);
    }

    return result;
  }

}
