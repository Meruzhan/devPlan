package app.config;

import javax.sql.DataSource;
import java.util.Properties;

import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * Created by srapion.hunanyan on 12/1/2016.
 */

@EnableTransactionManagement
@EnableJpaRepositories(basePackages =  {"model" , "dao", "service"})
@Configuration
@ComponentScan(basePackages ={"model" , "dao", "service","filter"})
@PropertySource("classpath:db.config.properties")
public class EventConfig {
    @Autowired
    Environment env;

    @Bean("dataSource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("db.driver"));
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.userName"));
        dataSource.setPassword(env.getProperty("db.password"));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("model" , "dao", "service");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(getProperties());
        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.put("hibernate.jdbc.max_fetch_depth", Integer.valueOf(env.getProperty("hibernate.jdbc.max_fetch_depth")));
        properties.put("hibernate.jdbc.fetch_size", Integer.valueOf(env.getProperty("hibernate.jdbc.fetch_size")));
        properties.put("hibernate.jdbc.batch_size", Integer.valueOf(env.getProperty("hibernate.jdbc.batch_size")));
        properties.put("hibernate.show_sql", Boolean.valueOf(env.getProperty("hibernate.show_sql")));
        return properties;
    }
}
