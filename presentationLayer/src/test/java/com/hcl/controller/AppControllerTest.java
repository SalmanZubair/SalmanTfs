package com.hcl.controller;

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
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.hcl.AppInitializer;
import com.hcl.common.AlertDTO;
import com.hcl.common.BoilerInfoDTO;
import com.hcl.common.PlantInfoDTO;
import com.hcl.configuration.BeanConfig;
import com.hcl.dao.ManualParamsDao;
import com.hcl.service.ManualParamsService;

@ContextConfiguration(classes = { BeanConfig.class, AppInitializer.class })
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class AppControllerTest {

	@Autowired
	private AppController controller;

	@Autowired
	AppInitializer appInitializer;

	@InjectMocks
	MockHttpSession session;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Mock
	ManualParamsService manualParamsService;

	@Mock
	ManualParamsDao manualParamsDao;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void boilerHealthTest() {
		ModelAndView result = controller.boilerHealth("B001", new ModelMap());
		Assert.assertTrue(null != result);
	}

	@Test
	public void boilerHealth1Test() {
		ModelAndView result = controller.boilerHealth1();
		Assert.assertTrue(null != result);
	}

	@Test
	public void dataAnalysisTest() {
		ModelAndView result = controller.dataAnalysis();
		Assert.assertTrue(null != result);
	}

	@Test
	public void getAlertsForMapTest() throws Exception {
		String value = null;
		// specify mock behave when method called
		value = controller.getAllertsForMap("P01");
		System.out.println("############  ::  " + value);
		Assert.assertTrue(null != value);
	}

	@Test
	public void getAlertsForMapNullTest() throws Exception {
		String value = null;
		// specify mock behave when method called
		value = controller.getAllertsForMap(null);
		System.out.println("############  ::  " + value);
		Assert.assertTrue(null != value);
	}

	@Test
	public void getAlertsForMapFailTest() throws Exception {
		String value = null;
		// specify mock behave when method called
		value = controller.getAllertsForMap("B01");
		System.out.println("############  ::  " + value);
		System.out.println(null != value + "AAAAAAAAAAAAAAAAAA");
		Assert.assertTrue(true);
	}

	@Test
	public void getForAlertsDirectTest() throws Exception {
		List<AlertDTO> value = new ArrayList<AlertDTO>();
		// specify mock behave when method called
		value = controller.getForAlerts("ALL", "B001", "DIRECT");
		System.out.println("############  ::  " + value);
		Assert.assertTrue(value.size() > 0);
	}

	@Test
	public void getForAlertsIndirectTest() throws Exception {
		List<AlertDTO> value = new ArrayList<AlertDTO>();
		// specify mock behave when method called
		value = controller.getForAlerts("ALL", "B001", "INDIRECT");
		System.out.println("############  ::  " + value);
		Assert.assertTrue(value.size() > 0);
	}

	@Test
	public void getForAlertsFailTest() throws Exception {
		List<AlertDTO> value = new ArrayList<AlertDTO>();
		// specify mock behave when method called
		value = controller.getForAlerts("ALL", "B001", "RANDOM");
		System.out.println("############  ::  " + value);
		Assert.assertFalse(value.size() > 0);
	}

	@Test
	public void calculateBoilerEfficiencyDirectTest() throws Exception {
		String value = null;
		// specify mock behave when method called
		value = controller.calculateBoilerEfficiency("B001", "DIRECT");
		System.out.println("############  ::  " + value);
		Assert.assertTrue(null != value);
	}

	@Test
	public void calculateBoilerEfficiencyIndirectTest() throws Exception {
		String value = null;
		// specify mock behave when method called
		value = controller.calculateBoilerEfficiency("B001", "INDIRECT");
		System.out.println("############  ::  " + value);
		Assert.assertTrue(null != value);
	}

	@Test
	public void calculateBoilerEfficiencyFailTest() throws Exception {
		String value = null;
		// specify mock behave when method called
		value = controller.calculateBoilerEfficiency("B001", "RANDOM");
		System.out.println("############  ::  " + value.length());
		Assert.assertFalse(value.length() > 2);
	}

	@Test
	public void getBoilerInfoTest() throws Exception {
		BoilerInfoDTO value = null;
		// specify mock behave when method called
		value = controller.getBoilerInfo("B001");
		System.out.println("############  ::  " + value);
		Assert.assertTrue(null != value);
	}

	@Test
	public void getBoilerInfoFailTest() throws Exception {
		BoilerInfoDTO value = null;
		// specify mock behave when method called
		value = controller.getBoilerInfo(null);
		System.out.println("############  ::  " + value);
		Assert.assertFalse(null != value);
	}

	@Test
	public void getHistoricalDataIndirectDailyTest() throws Exception {
		String value = null;
		// specify mock behave when method called
		value = controller.getHistoricalData("LS001", "B001", "INDIRECT",
				"TODAY");
		System.out.println("############  ::  " + value);
		Assert.assertTrue(null != value);
	}

	@Test
	public void getHistoricalDataIndirectWeeklyTest() throws Exception {
		String value = null;
		// specify mock behave when method called
		value = controller.getHistoricalData("LS001", "B001", "INDIRECT",
				"WEEKLY");
		System.out.println("############  ::  " + value);
		Assert.assertTrue(null != value);
	}

	@Test
	public void getHistoricalDataIndirectMonthTest() throws Exception {
		String value = null;
		// specify mock behave when method called
		value = controller.getHistoricalData("LS001", "B001", "INDIRECT",
				"MONTH");
		System.out.println("############  ::  " + value);
		Assert.assertTrue(null != value);
	}

	@Test
	public void getHistoricalDataIndirectRandomTest() throws Exception {
		String value = null;
		// specify mock behave when method called
		value = controller.getHistoricalData("LS001", "B001", "INDIRECT",
				"RANDOM");
		System.out.println("############  ::  " + value);
		Assert.assertFalse(value.length() > 2);
	}

	@Test
	public void getHistoricalDataDirectDailyTest() throws Exception {
		String value = null;
		// specify mock behave when method called
		value = controller.getHistoricalData(null, "B001", "DIRECT", "TODAY");
		System.out.println("############  ::  " + value);
		Assert.assertTrue(null != value);
	}

	@Test
	public void getHistoricalDataDirectWeeklyTest() throws Exception {
		String value = null;
		// specify mock behave when method called
		value = controller.getHistoricalData(null, "B001", "DIRECT", "WEEKLY");
		System.out.println("############  ::  " + value);
		Assert.assertTrue(null != value);
	}

	@Test
	public void getHistoricalDataDirectMonthTest() throws Exception {
		String value = null;
		// specify mock behave when method called
		value = controller.getHistoricalData(null, "B001", "DIRECT", "MONTH");
		System.out.println("############  ::  " + value);
		Assert.assertTrue(null != value);
	}

	@Test
	public void getAllPlantsTest() throws Exception {
		List<PlantInfoDTO> value = new ArrayList<PlantInfoDTO>();
		// specify mock behave when method called
		value = controller.getAllPlants();
		System.out.println("############  ::  " + value);
		Assert.assertTrue(value.size() > 0);
	}

	@Test
	public void getAllPlantsInfoForMapTest() throws Exception {
		String value = null;
		// specify mock behave when method called
		value = controller.getAllPlantsInfoForMap();
		System.out.println("############  ::  " + value);
		Assert.assertTrue(null != value);
	}

	@Test
	public void getBoilerListTest() throws Exception {
		String value = null;
		// specify mock behave when method called
		value = controller.getBoilerList("P01");
		System.out.println("############  ::  " + value);
		Assert.assertTrue(null != value);
	}

	@Test
	public void getBoilerListFailTest() throws Exception {
		String value = null;
		// specify mock behave when method called
		value = controller.getBoilerList(null);
		System.out.println("############  ::  " + value);
		Assert.assertFalse(null != value);
	}

	@Test
	public void getParameterDetailsIndirectLossTest() throws Exception {
		String value = null;
		// specify mock behave when method called
		value = controller.getParameterDetails("B001", "LS001", "INDIRECT",
				"2017-07-10 09:10:05.000");
		System.out.println("############  ::  " + value);
		Assert.assertTrue(null != value);
	}

	@Test
	public void getParameterDetailsIndirectTest() throws Exception {
		String value = null;
		// specify mock behave when method called
		value = controller.getParameterDetails("B001", null, "INDIRECT",
				"2017-07-10 09:10:05.000");
		System.out.println("############  ::  " + value);
		Assert.assertTrue(null != value);
	}

	@Test
	public void getParameterDetailsTest() throws Exception {
		String value = null;
		// specify mock behave when method called
		value = controller.getParameterDetails("B001", null, "DIRECT",
				"2017-07-10 09:10:05.000");
		System.out.println("############  ::  " + value);
		Assert.assertTrue(null != value);
	}

	@Test
	public void getParameterDetailsRandomTest() throws Exception {
		String value = null;
		// specify mock behave when method called
		value = controller.getParameterDetails("B001", null, "RANDOM",
				"2017-07-10 09:10:05.000");
		System.out.println("############  ::  " + value);
		Assert.assertFalse(value.length() > 2);
	}

	@Test
	public void getManualParametersTest() throws Exception {
		String value = null;
		// specify mock behave when method called
		value = controller.getManualParams("B001", "2017-06-10 00:00:00.000");
		System.out.println("############  ::  " + value);
		Assert.assertTrue(null != value);
	}

	@Test
	public void getManualParametersTestF() throws Exception {
		String value = null;
		// specify mock behave when method called
		value = controller.getManualParams("B112", "2017-11-10 00:00:00.000");
		System.out.println("############  ::  " + value);
		Assert.assertFalse(null != value);
	}

}