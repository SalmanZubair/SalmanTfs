package com.hcl.service.impl;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hcl.common.AlertDTO;
import com.hcl.configuration.BeanConfig;
import com.hcl.dao.AlertsDao;
import com.hcl.service.AlertService;

//Load Spring contexte
@ContextConfiguration(classes = BeanConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class AlertServiceImplTest {

	// Create Mock
	@Mock
	private AlertsDao alertDaoMock;

	@InjectMocks
	@Autowired
	private AlertService alertService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void getAllertsTestFalse() throws Exception {

		// specify mock behave when method called
		AlertDTO al = new AlertDTO();
		al.setSuggestion("Improve parameter");;
		al.setAlertDesc("LOSS");
		List<AlertDTO> value = new ArrayList<AlertDTO>();
		value.add(al);

		when(alertDaoMock.getAlerts("ALL", "B00", "DIRECT")).thenReturn(value);
		Assert.assertNotNull(alertService);
		value = alertService.getAlerts("ALL", "B00", "DIRECT");
		System.out.println("##################    :   "   +value);
		Assert.assertFalse(value.size() > 0);
	}


	@Test
	public void getAllertsTestTrue() throws Exception {

		// specify mock behave when method called
		AlertDTO al = new AlertDTO();
		al.setSuggestion("Improve parameter");;
		al.setAlertDesc("LOSS");
		List<AlertDTO> value = new ArrayList<AlertDTO>();
		value.add(al);

		when(alertDaoMock.getAlerts("ALL", "B001", "DIRECT")).thenReturn(value);
		Assert.assertNotNull(alertService);
		value = alertService.getAlerts("ALL", "B001", "DIRECT");
		Assert.assertTrue(value.size() > 0);
	}
	
	
	@Test
	public void getAllertsTestIndirect() throws Exception {

		// specify mock behave when method called
		AlertDTO al = new AlertDTO();
		al.setSuggestion("Improve parameter");;
		al.setAlertDesc("LOSS");
		List<AlertDTO> value = new ArrayList<AlertDTO>();
		value.add(al);

		when(alertDaoMock.getAlerts("ALL", "B001", "INDIRECT")).thenReturn(value);
		Assert.assertNotNull(alertService);
		value = alertService.getAlerts("ALL", "B001", "INDIRECT");
		Assert.assertTrue(value.size() > 0);
	}
	
	@Test
	public void getAllertsForMapTest() throws Exception {

		// specify mock behave when method called
		AlertDTO al = new AlertDTO();
		al.setSuggestion("Improve parameter");;
		al.setAlertDesc("LOSS");
		String value = "{\"key\":\"value\"}";

		when(alertDaoMock.getAllertsForMap("P01")).thenReturn(value);
		Assert.assertNotNull(alertService);
		value = alertService.getAllertsForMap("P01");
		Assert.assertTrue(null!=value);
	}

}