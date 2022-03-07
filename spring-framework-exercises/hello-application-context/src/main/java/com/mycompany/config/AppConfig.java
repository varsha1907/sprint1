package com.mycompany.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.mycompany.TestDataGenerator;

/**
 * This class application context configuration.
 * <p>
 * todo: make this class a Spring configuration class
 * todo: enable component scanning for dao and service packages
 * todo: provide explicit configuration for a bean of type {@link TestDataGenerator} with name "dataGenerator" in this class.
 * todo: Don't specify bean name "dataGenerator" explicitly
 */
@Configuration
@ComponentScan(basePackages = {"com.mycompany.dao","com.mycompany.service"})
public class AppConfig {
	
	 @Bean
	    public TestDataGenerator dataGenerator() {
	        return new TestDataGenerator();
	    }

}
