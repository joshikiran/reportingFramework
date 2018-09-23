package com.logging.core.objects;

import java.util.List;

/**
 * This object is used by FileSystemPlugin to be able to store the content and
 * send it for the processing, which will then be utilized by Processor and does
 * the processing.
 * 
 * @author joshi
 *
 */
public class FileSystemInformation {

	private List<String> relativePaths;

	public List<String> getRelativePaths() {
		return relativePaths;
	}

	public void setRelativePaths(List<String> relativePaths) {
		this.relativePaths = relativePaths;
	}
}
