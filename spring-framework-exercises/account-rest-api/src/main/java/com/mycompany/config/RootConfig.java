package com.mycompany.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * This class provides application root (non-web) configuration.
 * <p>
 * todo: 1. Mark this class as config
 * todo: 2. Enable component scanning for all packages in "com.mycompany" using annotation property "basePackages"
 * todo: 3. Exclude web related config and beans (ignore @{@link Controller}, ignore {@link EnableWebMvc})
 */

@Configuration
@ComponentScan(basePackages = "com.mycompany", excludeFilters = {@Filter(EnableWebMvc.class), @Filter(Controller.class)})
public class RootConfig {
}
