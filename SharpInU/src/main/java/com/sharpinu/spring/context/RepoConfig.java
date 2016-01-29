package com.sharpinu.spring.context;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.ejb.HibernatePersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySources({
    @PropertySource("classpath:/com/sharpinu/config/db_config.properties")
})
@EnableJpaRepositories(
	basePackages = "com.sharpinu.persist.repositories")
@ComponentScan({
    "com.sharpinu.persist.repositories"
})
public class RepoConfig {
	private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";  
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";  
    private static final String PROPERTY_NAME_DATABASE_URL = "db.url";  
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";  
    
    private static final String PERSISTENCE_UNIT_NAME = "sharpinu";
    
    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
    
    @Resource  
    private Environment env;
      
    @Bean  
    public DataSource dataSource() {  
        DriverManagerDataSource dataSource = new DriverManagerDataSource();  

        dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));  
        dataSource.setUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));  
        dataSource.setUsername(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));  
        dataSource.setPassword(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));  
        return dataSource;  
    }
    
    @Bean()
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {  
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean(); 
        entityManagerFactoryBean.setPersistenceUnitName(PERSISTENCE_UNIT_NAME);
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPackagesToScan("com.sharpinu.persist.domain");
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistence.class);
		entityManagerFactoryBean.setJpaProperties(hibernateProperties());
        return entityManagerFactoryBean;  
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
        properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
        return properties;        
    }
      
    @Bean  
    public JpaTransactionManager transactionManager() {  
        JpaTransactionManager transactionManager = new JpaTransactionManager();  
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());  
        return transactionManager;  
    }
}
