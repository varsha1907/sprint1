package com.mycompany.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * This class is provides root Java config for Spring application.
 * <p>
 * todo: 1. PLEASE NOTE, THAT SOME REQUIRED STEPS ARE OMITTED IN THE TODO LIST AND YOU HAVE TO DO IT ON YOUR OWN
 * <p>
 * todo: 2. Configure {@link PlatformTransactionManager} bean with name "transactionManager"
 * todo: 3. Enable transaction management
 */

@Configuration
@ComponentScan("com.mycompany")
@EnableTransactionManagement
public class RootConfig {
	 @Bean
	    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
	        return new JpaTransactionManager(entityManagerFactory);
	    }
}

