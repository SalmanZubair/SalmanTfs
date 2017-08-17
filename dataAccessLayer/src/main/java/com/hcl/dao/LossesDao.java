package com.hcl.dao;

import java.util.List;

import com.hcl.dao.exception.DaoException;

@FunctionalInterface
public interface LossesDao {

	List<String> getLosses(String boilerId) throws DaoException;
		
}
