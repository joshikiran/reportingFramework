package com.logging.core.data;

import java.util.List;
import java.util.logging.Logger;

import com.logging.core.objects.LoggingInfo;
import com.logging.core.objects.MongoDBInfo;

/**
 * It implements DataPersistance interface which actually tries to persist
 * information into database. Any information which comes to this component
 * should be an instance of MongoDBInfo which will be considered for source to
 * put into MongoDB. This takes care of connecting to mongodb and
 * persisting this information into it.
 * 
 * @author joshi
 *
 */
public class MongoDBPersistance implements DataPersistance {

	private static Logger logger = Logger.getLogger(MongoDBPersistance.class.getName());

	/**
	 * Method which would be implemented for persisting the data into mongodb.
	 */
	@Override
	public void persistData(List<LoggingInfo> obj) {
		// TODO Auto-generated method stub
		logger.info("In MongoDBPersistance PersistData method");
		for (LoggingInfo logInfo : obj) {
			MongoDBInfo mDBInfo = (MongoDBInfo) logInfo;
			if (mDBInfo != null) {
				logger.info("Typecast successful from loggingInfo to MongoDBInfo");
				logger.info("Processing mDBInfo to be persisting into MongoDB");
			}
		}
	}

}
