package com.logging.processing;

import com.logging.core.Orchestration;
import com.logging.core.data.DatabasePersistance;
import com.logging.core.data.MongoDBPersistance;
import com.logging.core.plugin.PluginDefinition;
import com.logging.core.plugin.PluginProcessing;
import com.logging.plugin.definitions.FileSystemPlugin;
import com.logging.plugin.processors.FileSystemProcessing;

/**
 * This is one of the Processing technique which is demonstrated which uses
 * FileSystemPlugin and FileSystemProcessing as hooks for processing the
 * content.
 * 
 * @author joshi
 *
 */
public class OnDemandFileSystemProcessing extends Orchestration<FileSystemPlugin, FileSystemProcessing> {

	protected OnDemandFileSystemProcessing(PluginDefinition def, PluginProcessing proc) {
		super(def, proc);
		// TODO Auto-generated constructor stub
	}

	public static void main(String args[]) {

		/**
		 * Below block would consider FileSystemPlugin, FileSystemProcessing and
		 * DatabasePersistance as hooks for processing the content.
		 */
		FileSystemPlugin pDef = FileSystemPlugin.getInstance();
		FileSystemProcessing pProcess = FileSystemProcessing.getInstance(new DatabasePersistance());
		OnDemandFileSystemProcessing coreProcess = new OnDemandFileSystemProcessing(pDef, pProcess);
		coreProcess.process();

		/**
		 * Now for example, same information you would want to persist into MongoDB?
		 * Consider writing as below
		 */
		
		pProcess = FileSystemProcessing.getInstance(new MongoDBPersistance());
		coreProcess = new OnDemandFileSystemProcessing(pDef, pProcess);
		coreProcess.process();
	}
}
