package com.mycompany.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * This class provides application root (non-web) configuration.
 * <p>
 * todo: mark this class as config todo: enable component scanning for all
 * packages in "com.mycompany" todo: ignore all web related config and beans
 * (ignore @{@link Controller}, ignore {@link EnableWebMvc}) using exclude
 * filter
 */
@Configuration
@ComponentScan(basePackages = { "com.mycompany" }, excludeFilters = {
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class),
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class) })
public class RootConfig {
}
