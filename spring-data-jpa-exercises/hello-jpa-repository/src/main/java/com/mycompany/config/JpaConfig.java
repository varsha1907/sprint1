package com.mycompany.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

/**
 * This class provides spring configuration for {@link javax.persistence.EntityManagerFactory} bean.
 * <p>
 * todo: 1. PLEASE NOTE, THAT SOME REQUIRED STEPS ARE OMITTED IN THE TODO LIST AND YOU HAVE TO DO IT ON YOUR OWN
 * <p>
 * todo: 2. Configure {@link DataSource} bean
 * todo: 3. Configure {@link JpaVendorAdapter} bean
 * todo: 3. Configure {@link javax.persistence.EntityManagerFactory} bean with name "entityManagerFactory"
 * todo: 4. Enable JPA repository, set appropriate package using annotation property "basePackages"
 */

@Configuration
@EnableJpaRepositories(basePackages = "com.mycompany.dao")
public class JpaConfig {

	@Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }
	
	@Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        // todo: create HibernateJpaVendorAdapter
        // todo: set H2 database
        // todo: enable DDL generation
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.H2);
		adapter.setGenerateDdl(true);
        return adapter;
       // throw new UnsupportedOperationException("Application won't start until you provide configs");
    }

	 @Bean("entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean localContainerEMF(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        // todo: create and configure required bean
        // todo: set package "com.mycompany.model" to scan for JPA entities
        //throw new UnsupportedOperationException("Application won't start until you provide configs");   	
    	        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
    	        emf.setDataSource(dataSource);
    	        emf.setJpaVendorAdapter(jpaVendorAdapter);   	  
    	        emf.setPackagesToScan("com.mycompany.model");
    	        return emf;
    	    }
    
    }

