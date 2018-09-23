package com.logging.core.data;

import java.util.List;

import com.logging.core.objects.LoggingInfo;

/**
 * 
 * This is an interface which determines what functionalities to be performed by
 * any implementor. We have taken the advantage of generic type casting i.e.,
 * LoggingInfo but the actual implementor might retrieve information which
 * extends LoggingInfo object. Hence you would see the actual implementors work
 * on the specific object which extends LoggingInfo by taking advantage of
 * object typecasting.
 * 
 * @author joshi
 *
 */
public interface DataPersistance {
	public void persistData(List<LoggingInfo> logInfoList);
}
