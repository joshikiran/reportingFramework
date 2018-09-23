package com.logging.plugin.processors;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.logging.core.data.DataPersistance;
import com.logging.core.data.DatabasePersistance;
import com.logging.core.data.MongoDBPersistance;
import com.logging.core.objects.DatabaseInfo;
import com.logging.core.objects.FileSystemInformation;
import com.logging.core.objects.LoggingInfo;
import com.logging.core.objects.MongoDBInfo;
import com.logging.core.plugin.PluginProcessing;

/**
 * 
 * Processing machine which deals with processing of FileSystemInformation. This
 * is compatible with plugins which provide FileSystemInformation as object. It
 * provides the capability to convert the information into relavant persistant
 * object which would be then used for persisting.
 * 
 * @author joshi
 *
 */
@SuppressWarnings("unchecked")
public class FileSystemProcessing extends PluginProcessing {

	private static Logger logger = Logger.getLogger(FileSystemProcessing.class.getName());
	private DataPersistance dPersistanceObj;

	/**
	 * Usage of Singleton Pattern
	 * 
	 * @param dObj
	 */
	private FileSystemProcessing(DataPersistance dObj) {
		dPersistanceObj = dObj;
	}

	public static FileSystemProcessing getInstance(DataPersistance obj) {
		return new FileSystemProcessing(obj);
	}

	/**
	 * Method used by Orchestration process for processing the content.
	 */
	@Override
	public List<LoggingInfo> processContent(Object content) {
		// TODO Auto-generated method stub
		logger.info("Process Content");
		FileSystemInformation fsInfo = (FileSystemInformation) content;
		logger.info("Object parsed Successfully");
		return getLoggingInfoObjects(fsInfo);
	}

	/**
	 * Method used by Orchestration process for checking the validity of the object.
	 */
	@Override
	public boolean checkValidity(Object content) {
		// TODO Auto-generated method stub
		logger.info("Check Validity");
		FileSystemInformation fsInfo = (FileSystemInformation) content;
		if (fsInfo != null)
			logger.info("Object parsed Successfully");
		return true;
	}

	/**
	 * This is specific method implemented by every processing maching which
	 * converts the Information object retrieved from Plugin to data persistant
	 * object. Now that DataPersistance module is loosely coupled with processing,
	 * it is responsibility of processing machine to support necessary persistant
	 * patterns and then have logic which converts the information into associated
	 * objects. Currently this module supports DatabasePersistance and
	 * MongoDBPersistance. Support of any additional persistance techniques should
	 * be determined here.
	 * 
	 * @param fsInfo
	 * @return
	 */
	private List<LoggingInfo> getLoggingInfoObjects(FileSystemInformation fsInfo) {
		logger.info("Forming logInfo objects");
		List<LoggingInfo> logInfoList = new ArrayList<LoggingInfo>();
		LoggingInfo logInfo;
		if (MongoDBPersistance.class.isInstance(dPersistanceObj)) {
			logger.info("Processing the content for persist into Mongo DB");
			logInfo = new MongoDBInfo();
			logInfoList.add(logInfo);
		} else if (DatabasePersistance.class.isInstance(dPersistanceObj)) {
			logger.info("Processing the content for persist into DB");
			logInfo = new DatabaseInfo();
			logInfoList.add(logInfo);
		} else {
			logger.info("This type of processing is not supported by File System Processing");
		}

		return logInfoList;
	}

	/**
	 * Provides the persistance object used by this component.
	 */
	@Override
	public DataPersistance getDataPersistanceObject() {
		// TODO Auto-generated method stub
		return dPersistanceObj;
	}

}
