package com.hcl.dao;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.hcl.dao.exception.DaoException;


public interface BoilerEfficiencyDao {
	
	JSONArray getHistoricalData(String lossId, String boilerId, String method,String startTime,String endTime)
			throws DaoException;
	
	JSONObject getBoilerEfficiency(String boilerId, List<String> lossId, int records, String method)
			throws DaoException;
	
	
}
