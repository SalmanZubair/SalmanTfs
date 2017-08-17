package com.hcl.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.common.PlantInfoDTO;
import com.hcl.dao.PlantInfoDao;
import com.hcl.service.PlantInfoService;
import com.hcl.service.exception.ServiceException;

@Service("plantInfoService")
@Transactional
public class PlantInfoServiceImpl implements PlantInfoService {

	private static final Logger logger = Logger
			.getLogger(PlantInfoServiceImpl.class);

	@Autowired
	PlantInfoDao plantInfoDao;

	@Override
	public List<PlantInfoDTO> getAllPlants() throws ServiceException {
		logger.info("Calling Dao for fetching list of plants : ");
		return plantInfoDao.getAllPlants();
	}

	@Override
	public String getAllPlantsInfoForMap() throws ServiceException {
		logger.info("Calling Dao for fetching list of plants : ");
		return plantInfoDao.getAllPlantsInfoForMap();
	}
}
