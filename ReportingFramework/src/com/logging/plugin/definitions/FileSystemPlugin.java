package com.logging.plugin.definitions;

import java.util.logging.Logger;

import com.logging.core.objects.FileSystemInformation;
import com.logging.core.plugin.PluginDefinition;

/**
 * FileSystemPlugin which connects to file system and retrieves the information
 * from file system. It should be using FileSystem Streaming techniques to be
 * able to retrieve the information.
 * 
 * @author joshi
 *
 */
public class FileSystemPlugin extends PluginDefinition {

	private static FileSystemPlugin pluginDef = new FileSystemPlugin();
	private static Logger logger = Logger.getLogger(FileSystemPlugin.class.getName());

	/**
	 * Usage of Singleton pattern
	 */
	private FileSystemPlugin() {

	}

	public static FileSystemPlugin getInstance() {
		return pluginDef;
	}

	/**
	 * 
	 * Method which retrieves content for processing by connecting to File system.
	 */
	@Override
	public Object getContentForProcessing() {
		// TODO Auto-generated method stub
		logger.info("GetContentForProcessing");
		// Creating a dummy FileSystemInformation
		FileSystemInformation fsInfo = new FileSystemInformation();
		logger.info("Connecting to Filesystem using streaming methods and retrieve the information");
		return fsInfo;
	}

}
