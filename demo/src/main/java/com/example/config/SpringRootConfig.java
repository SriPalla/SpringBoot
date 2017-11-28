package com.example.config;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import javax.xml.ws.Endpoint;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.jaxws.EndpointImpl;
import org.example.math.WsMath;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.demo.ws.MathWebservice;

@SpringBootApplication(scanBasePackages = {"com.example.demo"})
@EnableWebMvc
@EnableTransactionManagement
@EntityScan(basePackages = {"com.example.demo.dataaccess"})
public class SpringRootConfig implements WebApplicationInitializer {
	@Autowired
	private SpringBus bus;
	/*@Autowired
	private DataSource dataSource;*/
	@Autowired 
	private Environment env;

	@Bean 
	public WsMath getMathWebservice() {
		return new MathWebservice();
	}
	
	@Bean
	public Endpoint endpoint(WsMath mathWebservice) {
		EndpointImpl endpoint = new EndpointImpl(bus, mathWebservice);
		endpoint.publish("/math");
		/*LoggingFeature logFeature = new LoggingFeature();
	    logFeature.setPrettyLogging(true);
	    logFeature.initialize(bus);
	    endpoint.getFeatures().add(logFeature);*/
		return endpoint;
	}

	@PostConstruct
	public void init() {
		LoggingFeature logFeature = new LoggingFeature();
	    logFeature.setPrettyLogging(true);
	    logFeature.initialize(bus);
	    bus.getFeatures().add(logFeature);
	}

	 /*@Bean
	   public LocalSessionFactoryBean sessionFactory() {
	      LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	      sessionFactory.setDataSource(dataSource);
	      sessionFactory.setPackagesToScan(
	        new String[] { "com.example.demo" });
	      sessionFactory.setHibernateProperties(hibernateProperties());
	 
	      return sessionFactory;
	   }*/

	Properties hibernateProperties() {
	      return new Properties() {
	         {
	            setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
	         }
	      };
	   }

	public static void main(String[] args) {
		SpringApplication.run(SpringRootConfig.class, args);
	}

	@Override
	public void onStartup(ServletContext context) {
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(SpringRootConfig.class);
		context.addListener(new ContextLoaderListener(rootContext));
        ServletRegistration.Dynamic dispatcher = context.addServlet("dispatcher", new DispatcherServlet(rootContext));
        dispatcher.setLoadOnStartup(110);
        dispatcher.addMapping("/*");
	}
	
	@Bean
	public SessionFactory sessionFactory(@Qualifier("entityManagerFactory") EntityManagerFactory factory) {
		return factory.unwrap(SessionFactory.class);
	}
}
