package com.hcl.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import com.hcl.dao.AbstractDao;
import com.hcl.dao.ManualParamsDao;
import com.hcl.dao.exception.DaoException;

/**
 * 
 * @author salman.z
 *
 */

@Repository("manualParamsDao")
public class ManualParamsDaoImpl extends AbstractDao implements ManualParamsDao {

	private static final Logger logger = Logger.getLogger(ManualParamsDaoImpl.class);

	@SuppressWarnings({ "rawtypes" })
	@Override
	public String getManualParams(String boilerId, String timestamp) throws DaoException {

		logger.info("Going to fetch list of manual params from Database");

		String hql = " select display_name, param_value from manual_params where "
				+ "(boiler_id = :boiler_id AND insert_date = :timestamp) "
				+ "OR (boiler_id = :boiler_id and param_name = 'GCV')";

		logger.info(hql);
		
		String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		
		if(timestamp.compareTo(currentDate)>0)
			throw new DaoException("Invalid Timestamp");
		

		List results = null;
		SQLQuery query = null;
		JSONObject manualParam = new JSONObject();
		try {
			query = getSession().createSQLQuery(hql);
			query.setParameter("boiler_id", boilerId);
			query.setParameter("timestamp", timestamp);
			
			results = query.list();

			for (Object obj : results) {
				Object[] arr = (Object[]) obj;
				manualParam.put((String)arr[0],  Double.parseDouble((String)arr[1]));
				
			}
		} catch (HibernateException e) {
			logger.error("Exception occurred while fetching plant list for given parameter. Reason :- "
					+ e);
			throw new DaoException(
					"Exception occurred while fetching plant list for given parameter.");
		}
		return manualParam.toString();
	
	}

	

}