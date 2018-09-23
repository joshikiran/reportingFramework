package com.logging.core.plugin;

import java.util.List;

import com.logging.core.data.DataPersistance;
import com.logging.core.objects.LoggingInfo;

/**
 * Method which are defined for any module to be considered as PluginProcessing.
 * Reason which this is considered as abstract is there could be modules that
 * are common across all the plugin processing, such features could be
 * considered here.
 * 
 * @author joshi
 *
 */
public abstract class PluginProcessing {

	public abstract <T extends List<LoggingInfo>> T processContent(Object content);

	public abstract boolean checkValidity(Object content);

	public abstract DataPersistance getDataPersistanceObject();
}
