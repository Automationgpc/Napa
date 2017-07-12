package com.napa.online.util;

import java.io.File;

import org.apache.commons.io.FileUtils;

public class FileIO {
	
	public static boolean copyFilesAndFolders(String source, String destination) {
		//Logs.LOGGER.info("Starting copying file : " + source);
		try {
			File Source = new File(source);
			File Destination = new File(destination);
			if (Source.isFile()) {
				FileUtils.copyFile(Source, Destination);
			} else {
				FileUtils.copyDirectoryToDirectory(Source, Destination);
			}
		} catch (Exception e) {
			//Logs.LOGGER.severe("Problem in copy operation" + e);
			return false;
		}
		return true;
	}

}
