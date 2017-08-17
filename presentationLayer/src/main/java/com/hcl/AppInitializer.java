package com.hcl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.log4j.Logger;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.hcl.configuration.AppConfig;


public class AppInitializer implements WebApplicationInitializer {
	
	private static final Logger logger = Logger.getLogger(AppInitializer.class);

	@Override
	public void onStartup(ServletContext container) throws ServletException {
		logger.info("*******************Starting application*******************");
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(AppConfig.class);
		ctx.setServletContext(container);
		logger.info("Starting Appinilializer....");
		ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
	}

}
