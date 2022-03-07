package com.mycompany;

import com.mycompany.config.RootConfig;
import com.mycompany.config.WebAppInitializer;
import com.mycompany.config.WebConfig;

class WebAppInitializerWrapper extends WebAppInitializer {
	public String[] getServletMappings() {
//        return super.getServletMappings();
		return new String[] { "/" };
	}

	public Class[] getRootConfigClasses() {
//        return super.getRootConfigClasses();
		return new Class<?>[] { RootConfig.class };
	}

	public Class<?>[] getServletConfigClasses() {
//        return super.getServletConfigClasses();
		return new Class<?>[] { WebConfig.class };
	}
}