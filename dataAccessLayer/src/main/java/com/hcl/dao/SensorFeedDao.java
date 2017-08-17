package com.hcl.dao;

import org.json.JSONObject;

import com.hcl.dao.exception.DaoException;

@FunctionalInterface
public interface SensorFeedDao {

	JSONObject getParameterDetails(String boilerId, String lossId, String method, String timeStamp) throws DaoException;
	
}
