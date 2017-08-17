package com.hcl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.hcl.dao.AbstractDao;
import com.hcl.dao.LossesDao;
import com.hcl.dao.exception.DaoException;
import com.hcl.persistenceModal.Losses;

@SuppressWarnings("unchecked")
@Repository("lossesDao")
public class LossesDaoImpl extends AbstractDao implements LossesDao {

	private static final Logger logger = Logger.getLogger(LossesDaoImpl.class);

	@Override
	public List<String> getLosses(String boilerId) throws DaoException {

		logger.info("Going to fetch attribute and value from Database for losses.");

		Criteria criteria = getSession().createCriteria(Losses.class, "losses");
		criteria.add(Restrictions.eq("losses.boilerInfo.boilerId", boilerId));

		List<String> lossList = new ArrayList<>();

		List<Losses> lst = null;

		try {

			lst = criteria.list();
			
			logger.info("$$$$$ :  "  +  lst.get(0).getLossId());
			logger.info("$$$$$ :  "  +  lst.size());

			for (Losses loss : lst) {
				lossList.add(loss.getLossId());
			}
			
			logger.info(lossList.toString());
			logger.info("$$$$$ :  "  +  lossList.size());

		} catch (HibernateException e) {

			logger.error("Exception occurred while fetching losses for given parameters. Reason :- "
					+ e);
			throw new DaoException(
					"Exception occurred while fetching losses for given parameters.");			
		}

		return lossList;
	}

}
