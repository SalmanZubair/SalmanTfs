package com.hcl.dao;

import java.util.List;

import com.hcl.common.AlertDTO;
import com.hcl.dao.exception.DaoException;

public interface AlertsDao {
	
	List<AlertDTO> getAlerts(String type, String boilerId, String method) throws DaoException;
	String getAllertsForMap(String plantId) throws DaoException;
}
