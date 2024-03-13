package org.ssemchenko.restservice.config;


import jakarta.annotation.Resource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.mapstruct.factory.Mappers;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.ssemchenko.restservice.service.mapper.FacultyDtomapper;
import org.ssemchenko.restservice.service.mapper.StudentDtomapper;
import org.ssemchenko.restservice.service.mapper.TeacherDtomapper;

import javax.sql.DataSource;
import java.util.List;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "org.ssemchenko.restservice")
@EnableWebMvc
@PropertySource("classpath:app.properties")
@EnableJpaRepositories(basePackages = "org.ssemchenko.restservice.repository")
public class AppConfig implements WebMvcConfigurer {

    private static final String DRIVER = "db.driver";
    private static final String URL = "db.url";
    private static final String USERNAME = "db.username";
    private static final String PASSWORD = "db.password";
    private static final String DIALECT = "db.hibernate.dialect";
    private static final String SHOW_SQL = "db.hibernate.show_sql";
    private static final String PACKAGES = "db.entitymanager.packages.to.scan";

    @Resource
    private Environment env;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter());
    }

    @Bean
    public FacultyDtomapper facultyDtomapper() {
        return Mappers.getMapper(FacultyDtomapper.class);
    }
    @Bean
    public StudentDtomapper studentDtomapper() {
        return Mappers.getMapper(StudentDtomapper.class);
    }
    @Bean
    public TeacherDtomapper teacherDtomapper() {
        return Mappers.getMapper(TeacherDtomapper.class);
    }

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(env.getRequiredProperty(DRIVER));
        config.setJdbcUrl(env.getRequiredProperty(URL));
        config.setUsername(env.getRequiredProperty(USERNAME));
        config.setPassword(env.getRequiredProperty(PASSWORD));
        return new HikariDataSource(config);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactory.setPackagesToScan(env.getRequiredProperty(PACKAGES));
        entityManagerFactory.setJpaProperties(getHibernateProperties());
        return entityManagerFactory;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put(DIALECT, env.getRequiredProperty(DIALECT));
        properties.put(SHOW_SQL, env.getRequiredProperty(SHOW_SQL));
        return properties;
    }
}





































