package com.hcl.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.hcl.common.AlertDTO;
import com.hcl.common.BoilerInfoDTO;
import com.hcl.common.PlantInfoDTO;
import com.hcl.service.AlertService;
import com.hcl.service.BoilerEfficiencyService;
import com.hcl.service.BoilerInfoService;
import com.hcl.service.ManualParamsService;
import com.hcl.service.PlantInfoService;
import com.hcl.service.SensorFeedService;
import com.hcl.service.exception.ServiceException;

@RestController
@EnableWebMvc
public class AppController {

	private static final Logger logger = Logger.getLogger(AppController.class);

	@Autowired
	AlertService alertService;

	@Autowired
	BoilerEfficiencyService boilerEfficiencyService;

	@Autowired
	BoilerInfoService boilerInfoService;

	@Autowired
	PlantInfoService plantInfoService;

	@Autowired
	SensorFeedService sensorFeedService;

	@Autowired
	ManualParamsService manualParamsService;

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView boilerHealth1() {
		return new ModelAndView("index");
	}

	@RequestMapping(value = "/boilerHealth", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public ModelAndView boilerHealth(@RequestParam("boilerId") String boilerId,
			ModelMap modelMap) {
		modelMap.addAttribute("boilerId", boilerId);
		return new ModelAndView("boiler-health");
	}

	@RequestMapping(value = "/dataAnalysis", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public ModelAndView dataAnalysis() {
		return new ModelAndView("data-analysis");
	}

	@RequestMapping(value = "/getAlerts", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<AlertDTO> getForAlerts(@RequestParam("type") String type,
			@RequestParam("boilerId") String boilerId,
			@RequestParam("method") String method) {

		List<AlertDTO> alertDTO = new ArrayList<>();

		logger.info("In controller  boilerId :: " + boilerId + "  type :: "
				+ type);
		try {
			alertDTO = alertService.getAlerts(type, boilerId, method);
		} catch (ServiceException e) {
			logger.error(e);
		}
		return alertDTO;
	}

	@RequestMapping(value = "/calculateEfficiency", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public String calculateBoilerEfficiency(
			@RequestParam("boilerId") String boilerId,
			@RequestParam("method") String method) {

		logger.info("In controller  boilerId :: " + boilerId);
		JSONObject data = new JSONObject();
		try {
			data = boilerEfficiencyService
					.getBoilerEfficiency(boilerId, method);
		} catch (ServiceException e) {
			logger.error(e);
		}

		return data.toString();
	}

	@RequestMapping(value = "/getBoilerInfo", method = RequestMethod.GET, headers = "Accept=application/json")
	@ResponseBody
	public BoilerInfoDTO getBoilerInfo(@RequestParam("boilerId") String boilerId) {

		logger.info("In controller  boilerId :: " + boilerId);

		BoilerInfoDTO boilerInfo = null;
		try {
			boilerInfo = boilerInfoService.getBoilerInfo(boilerId);
		} catch (ServiceException e) {
			logger.error(e);
		}
		return boilerInfo;
	}

	@RequestMapping(value = "/getHistoricalData", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getHistoricalData(
			@RequestParam(value = "lossId", required = false) String lossId,
			@RequestParam("boilerId") String boilerId,
			@RequestParam("method") String method,
			@RequestParam("type") String type) {

		if (null != lossId)
			logger.info("In controller  boilerId :: " + boilerId);
		else
			logger.info("In controller  boilerId :: " + boilerId
					+ "  lossId :: " + lossId);

		logger.info(method+"    " + type);
		
		JSONArray historicalData = new JSONArray();
		try {
			historicalData = boilerEfficiencyService.getHistoricalData(lossId,
					boilerId, method, type);

		} catch (ServiceException e) {
			logger.error(e);
		}

		return historicalData.toString();
	}

	@RequestMapping(value = "/getAllPlants", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<PlantInfoDTO> getAllPlants() {

		logger.info("In controller going to fetch list of plants.");

		List<PlantInfoDTO> plantInfo = plantInfoService.getAllPlants();
		return plantInfo;
	}

	@RequestMapping(value = "/getBoiler", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getBoilerList(@RequestParam("plantId") String plantId) {

		logger.info("In controller going to fetch list of plants.");

		String boilerList = null;
		try {
			boilerList = boilerInfoService.getBoilerList(plantId);
		} catch (ServiceException e) {
			logger.error(e);
		}
		return boilerList;
	}

	@RequestMapping(value = "/getparameterdetails", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getParameterDetails(
			@RequestParam("boilerId") String boilerId,
			@RequestParam(value = "lossId", required = false) String lossId,
			@RequestParam("method") String method,
			@RequestParam("timeStamp") String timeStamp) {

		// "B001,2017-04-05 00:00:00"
		String result = new String();
		if (null == lossId)
			logger.info("In controller  boilerId :: " + boilerId);
		else
			logger.info("In controller  boilerId :: " + boilerId
					+ "  lossId :: " + lossId);

		try {

			result = sensorFeedService.getParameterDetails(boilerId, lossId,
					method, timeStamp).toString();

		} catch (ServiceException e) {
			logger.error(e);
		}
		return result;
	}

	@RequestMapping(value = "/getAllertsForMap", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getAllertsForMap(
			@RequestParam(value = "plantId", required = false) String plantId) {

		String res = "";

		try {
			res = alertService.getAllertsForMap(plantId);
		} catch (ServiceException e) {
			logger.error(e);
		}

		return res;
	}

	@RequestMapping(value = "/getAllPlantsInfoForMap", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getAllPlantsInfoForMap() {

		String res = plantInfoService.getAllPlantsInfoForMap();
		return res;
	}

	@RequestMapping(value = "/getManualParams", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getManualParams(
			@RequestParam(value = "boilerId") String boilerId,
			@RequestParam("timeStamp") String timeStamp) {

		String res = null;

		try {
			res = manualParamsService.getManualParams(boilerId, timeStamp);
		} catch (ServiceException e) {
			logger.error(e);
		}
		return res;
	}

}