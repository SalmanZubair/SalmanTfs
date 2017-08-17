package com.hcl.service;

import org.json.JSONObject;

import com.hcl.service.exception.ServiceException;

@FunctionalInterface 
public interface SensorFeedService {

	JSONObject getParameterDetails(String boilerId, String lossId, String method,String timeStamp) throws ServiceException;
	
}
