package com.hcl.service;

import com.hcl.common.BoilerInfoDTO;
import com.hcl.service.exception.ServiceException;

public interface BoilerInfoService {

	BoilerInfoDTO getBoilerInfo(String boilerId) throws ServiceException;
	
	String getBoilerList(String plantName) throws ServiceException;
	
}
