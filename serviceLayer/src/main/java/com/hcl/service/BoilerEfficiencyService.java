package com.hcl.service;

import org.json.JSONArray;
import org.json.JSONObject;

import com.hcl.service.exception.ServiceException;

public interface BoilerEfficiencyService{

	JSONObject getBoilerEfficiency(String boilerId, String method) throws ServiceException;
	
	JSONArray getHistoricalData(String lossId, String boilerId, String method, String type) throws ServiceException;
	
}
