package com.hcl.dao;

import com.hcl.common.BoilerInfoDTO;
import com.hcl.dao.exception.DaoException;

public interface BoilerInfoDao {

	BoilerInfoDTO getBoilerInfo(String boilerId) throws DaoException;

	String getBoilerList(String plantName) throws DaoException;
	
}
