package org.pkb.springlogin.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.pkb.springlogin.security.JwtAuthSuccessHandler;
import org.pkb.springlogin.security.JwtAuthenticationTokenFilter;
import org.pkb.springlogin.security.JwtLogoutHandler;
import org.pkb.springlogin.security.JwtTokenUtil;
import org.pkb.springlogin.security.TokenRepository;
import org.pkb.springlogin.utils.EmailService;
import org.pkb.springlogin.utils.EncryptionUtility;
import org.pkb.springlogin.utils.GoogleRecaptchaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("org.pkb.springlogin.*")
@EnableTransactionManagement
// Load to Environment.
@PropertySource(value = "classpath:config.properties")
public class ApplicationContextConfig {

	// The Environment class serves as the property holder
	// and stores all the properties loaded by the @PropertySource
	@Autowired
	private Environment env;

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource rb = new ResourceBundleMessageSource();
		// Load property in message/validator.properties
		rb.setBasenames(new String[] { "messages/validator" });
		return rb;
	}

	@Bean(name = "emailService")
	public EmailService getEmailService() {
		EmailService service = new EmailService(env.getProperty("smtp.username"), env.getProperty("smtp.password"),
				env.getProperty("smtp.host"), env.getProperty("smtp.port"));
		return service;
	}

	@Bean(name = "encryptionUtility")
	public EncryptionUtility getEncryptionUtility() {
		EncryptionUtility utility = new EncryptionUtility(env.getProperty("encryption.key"));
		return utility;
	}

	@Bean(name = "viewResolver")
	public InternalResourceViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Bean(name = "authenticationFilter")
	public JwtAuthenticationTokenFilter getAuthFilter() {
		JwtAuthenticationTokenFilter.tokenParam = env.getProperty("jwt.parameter");
		JwtAuthenticationTokenFilter authenticationTokenFilter = new JwtAuthenticationTokenFilter();
		return authenticationTokenFilter;
	}

	@Bean(name = "logoutHandler")
	public JwtLogoutHandler getLogoutHandler() {
		JwtLogoutHandler logoutHandler = new JwtLogoutHandler(env.getProperty("jwt.parameter"));
		return logoutHandler;
	}

	@Bean(name = "googleRecaptchaUtils")
	public GoogleRecaptchaUtils getRecaptchaUtils() {
		return new GoogleRecaptchaUtils(env.getProperty("google.postUrl"), env.getProperty("google.secret"));
	}

	@Bean(name = "tokenRepository")
	public TokenRepository getTokenRepository(JwtTokenUtil tokenUtil) {
		TokenRepository repository = new TokenRepository(tokenUtil);
		return repository;
	}

	@Bean(name = "tokenUtil")
	public JwtTokenUtil getTokenUtility() {
		JwtTokenUtil.expiration = Long.parseLong(env.getProperty("jwt.expiration"));
		JwtTokenUtil.secret = env.getProperty("jwt.secret");
		return new JwtTokenUtil();
	}

	@Bean(name = "authenticationSuccessHandler")
	public AuthenticationSuccessHandler getSuccessHandler() {
		return new JwtAuthSuccessHandler();
	}

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("ds.database-driver"));
		dataSource.setUrl(env.getProperty("ds.url"));
		dataSource.setUsername(env.getProperty("ds.username"));
		dataSource.setPassword(env.getProperty("ds.password"));

		return dataSource;
	}

	@Bean
	@Autowired
	public HibernateTemplate getHibernateTemplate(SessionFactory sessionFactory) {
		HibernateTemplate hibernateTemplate = new HibernateTemplate(sessionFactory);
		return hibernateTemplate;
	}

	@Bean(name = "sessionFactory")
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(getDataSource());
		sessionFactoryBean.setHibernateProperties(getHibernateProperties());
		sessionFactoryBean.setPackagesToScan(new String[] { "org.pkb.springlogin.model" });
		return sessionFactoryBean;
	}

	@Bean
	public Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.generate_statistics", env.getProperty("hibernate.generate_statistics"));
		return properties;
	}

	// Transaction Manager
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

		return transactionManager;
	}

}