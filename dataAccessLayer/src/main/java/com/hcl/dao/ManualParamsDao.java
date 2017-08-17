package com.hcl.dao;

import com.hcl.dao.exception.DaoException;

@FunctionalInterface
public interface ManualParamsDao {
	String getManualParams(String boilerId, String timestamp) throws DaoException;
}
