package com.logging.core;

import java.util.List;
import java.util.logging.Logger;

import com.logging.core.data.DataPersistance;
import com.logging.core.objects.LoggingInfo;
import com.logging.core.plugin.PluginDefinition;
import com.logging.core.plugin.PluginProcessing;

/**
 * This is an orchestration process which basically depends on two modules
 * PluginDefinition and other PluginProcessing. Plugin definition can be
 * anything that works on the source of the data i.e., FileSystemPlugin is an
 * example demonstrated here. Other plugins can be DatabasePlugin which
 * retrieves information from Database etc., PluginProcessing can be processing
 * logic which works based on the information that the plugin provides. These
 * both are decoupled because there can be only one or many processing for
 * multiple plugins depending on how plugin forms the data.
 * 
 * @author joshi
 *
 * @param <pluginDef>
 * @param <pluginProc>
 */
@SuppressWarnings("unchecked")
public class Orchestration<pluginDef extends PluginDefinition, pluginProc extends PluginProcessing> {

	private pluginDef pluginDefObj;
	private pluginProc pluginProcObj;
	private DataPersistance dPersistanceObj;
	private static Logger logger = Logger.getLogger(Orchestration.class.getName());

	protected Orchestration(PluginDefinition def, PluginProcessing proc) {
		pluginDefObj = (pluginDef) def;
		pluginProcObj = (pluginProc) proc;
	}

	/**
	 * This is the core process which will perform the duty of finding out proper
	 * plugin component and retrieve information from that and then process the
	 * content according to the persistance object type i.e., it gives flexibility
	 * to retrieve information from either filesystem/database/sftp and then store
	 * it either in database/mongodb/anotherfile etc.,
	 * 
	 * Initialization of this object takes couple of parameters which determines
	 * type of plugin component and processor as well.
	 */
	protected void process() {
		Object pluginObj = null;
		boolean isValid = false;
		List<LoggingInfo> logInfoList;
		try {
			/**
			 * Get the plugin object from the instance of plugin definition
			 */
			logger.info(
					"**********************************[CONTENT RETRIEVAL]*****************************************************");
			logger.info("Getting content from pluginObject");
			pluginObj = pluginDefObj.getContentForProcessing();

			/**
			 * Get this object validated
			 */
			logger.info(
					"***********************************[VALIDATING OBJECT]****************************************************");
			logger.info("Validating the object");
			isValid = pluginProcObj.checkValidity(pluginObj);

			if (isValid) {
				/**
				 * If Valid then process the object
				 */
				logger.info(
						"*************************************[PROCESSING OBJECT]**************************************************");
				logger.info("Processing the object");
				logInfoList = pluginProcObj.processContent(pluginObj);

				logger.info(
						"**************************************[FETCHING PERSISTANCE OBJECT]*************************************************");
				logger.info("Objects retrieved from processing machine");
				dPersistanceObj = pluginProcObj.getDataPersistanceObject();

				logger.info(
						"*************************************[PERSISTING OBJECT]**************************************************");
				logger.info("Persisting the data into associated storage");
				dPersistanceObj.persistData(logInfoList);

			} else {
				/**
				 * If Not-Valid then process the object accordingly
				 */
				logger.info(
						"***********************************[VALIDATION INVALID]****************************************************");
				logger.info("Object content is invalid");
			}
			logger.info("***************************************************************************************");

		} catch (Exception e) {
			/**
			 * Log exception details and throw proper error to stacktrace
			 */
		}
	}

}
