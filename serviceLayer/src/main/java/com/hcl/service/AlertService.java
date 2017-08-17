package com.hcl.service;

import java.util.List;

import com.hcl.common.AlertDTO;
import com.hcl.service.exception.ServiceException;

public interface AlertService {

	
  List<AlertDTO> getAlerts(String type, String boilerId, String method) throws ServiceException;
  
  String getAllertsForMap(String plantId) throws ServiceException;
	
}
