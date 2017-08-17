package com.hcl.service;

import com.hcl.service.exception.ServiceException;

@FunctionalInterface 
public interface ManualParamsService {
	
	public String getManualParams(String boilerId, String timestamp) throws ServiceException;
	

}
