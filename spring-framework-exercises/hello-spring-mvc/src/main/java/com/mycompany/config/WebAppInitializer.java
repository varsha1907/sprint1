package com.mycompany.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * This class is used to configure DispatcherServlet and links it with application config classes
 * <p>
 * todo: provide default servlet mapping ("/")
 * todo: use {@link WebConfig} as ServletConfig class
 * todo: use {@link RootConfig} as root application config class
 */

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
       // throw new UnsupportedOperationException("Method is not implemented yet!");
    	return new Class[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        //throw new UnsupportedOperationException("Method is not implemented yet!");
    	 return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
       // throw new UnsupportedOperationException("Method is not implemented yet!");
    	 return new String[]{"/"};
    }
}
