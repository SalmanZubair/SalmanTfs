package com.hcl.dao.impl;

import static org.junit.Assert.assertEquals;

import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.hcl.dao.exception.DaoException;

public class SensorFeedDaoImplTest {

	private static SensorFeedDaoImpl daoImpl;
	static JSONObject params;

	@BeforeClass
	public static void setUp() throws DaoException {
		daoImpl = Mockito.mock(SensorFeedDaoImpl.class);
		params = new JSONObject();
		params.put("TStack", (float) 350);
		params.put("O2", (float) 11);

		/*Mockito.when(daoImpl.getParameterDetails("B001", null, "DIRECT"))
				.thenReturn(params);*/
	}

	@Test
	public void getAlertsTest() throws Exception {

	/*//	JSONObject alerts = daoImpl.getParameterDetails("B001", null, "DIRECT");
		System.out.println(alerts.get("O2"));
		assertEquals(2, alerts.length());*/
	}

}
