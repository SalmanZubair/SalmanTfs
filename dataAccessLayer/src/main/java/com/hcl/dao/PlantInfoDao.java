package com.hcl.dao;

import java.util.List;

import com.hcl.common.PlantInfoDTO;
import com.hcl.dao.exception.DaoException;

public interface PlantInfoDao {
	
	List<PlantInfoDTO> getAllPlants() throws DaoException;
	
	String getAllPlantsInfoForMap() throws DaoException;

}
