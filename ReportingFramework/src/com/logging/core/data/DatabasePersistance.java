package com.logging.core.data;

import java.util.List;
import java.util.logging.Logger;

import com.logging.core.objects.DatabaseInfo;
import com.logging.core.objects.LoggingInfo;
import com.logging.plugin.processors.FileSystemProcessing;

/**
 * It implements DataPersistance interface which actually tries to persist
 * information into database. Any information which comes to this component
 * should be an instance of DatabaseInfo which will be considered for source to
 * put into database. This takes care of connecting to any specific database and
 * persisting this information into that database.
 * 
 * @author joshi
 *
 */
public class DatabasePersistance implements DataPersistance {

	private static Logger logger = Logger.getLogger(FileSystemProcessing.class.getName());

	/**
	 * Method which would be implemented for persisting the data into proper
	 * database.
	 */
	@Override
	public void persistData(List<LoggingInfo> obj) {
		// TODO Auto-generated method stub
		logger.info("Database Persistance by performing typecasting loggingInfo to DatabaseInfo");
		for (LoggingInfo logInfo : obj) {
			DatabaseInfo dObj = (DatabaseInfo) logInfo;
			if (dObj != null) {
				logger.info("Generic Typecasting from loggingInfo to DBInfo");
				logger.info("Performing database inserts based on the configuration if necessary {}");
			}
		}
	}

}
