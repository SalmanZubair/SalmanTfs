package com.hcl.service;

import java.util.List;

import com.hcl.common.PlantInfoDTO;
import com.hcl.service.exception.ServiceException;

public interface PlantInfoService {

	List<PlantInfoDTO> getAllPlants() throws ServiceException;
	
	String getAllPlantsInfoForMap() throws ServiceException;
		
}
