package com.haijun.conf;


import java.sql.SQLException;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/**
 * mybatis数据源配置
 * @author CW5320
 *
 */
@Configuration
@EnableConfigurationProperties(DruidDataSourceProperties.class)
//mybaits mapper 搜索路径
@MapperScan("com.haijun.mapper")
public class MybatisDataSource {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private DruidDataSourceProperties config;
	//mybaits mapper xml搜索路径
	//private final static String mapperLocations="classpath:mapper/*.xml"; 

	@Bean
	public DataSource duridDataSource() {		
		DruidDataSource datasource= new DruidDataSource();		
		datasource.setDriverClassName(config.getDriverClassName());
		datasource.setUrl(config.getUrl());
		datasource.setUsername(config.getUsername());
		datasource.setPassword(config.getPassword());
		datasource.setInitialSize(config.getInitialSize()); 
		datasource.setMinIdle(config.getMinIdle()); 
		datasource.setMaxActive(config.getMaxActive()); 
		datasource.setMaxWait(config.getMaxWait()); 
		datasource.setTimeBetweenEvictionRunsMillis(config.getTimeBetweenEvictionRunsMillis()); 
		datasource.setMinEvictableIdleTimeMillis(config.getMinEvictableIdleTimeMillis()); 
		datasource.setValidationQuery(config.getValidationQuery()); 
		datasource.setTestWhileIdle(config.isTestWhileIdle()); 
		datasource.setTestOnBorrow(config.isTestOnBorrow()); 
		datasource.setTestOnReturn(config.isTestOnReturn()); 
		datasource.setPoolPreparedStatements(config.isPoolPreparedStatements()); 

		try { 
			datasource.setFilters(config.getFilters()); 
		} catch (SQLException e) { 
			logger.error("druid configuration initialization filter", e); 
		} 

		return datasource;
	}


	@Bean
	public ServletRegistrationBean druidServlet() {
		ServletRegistrationBean reg = new ServletRegistrationBean();
		reg.setServlet(new StatViewServlet());
		reg.addUrlMappings("/druid/*");
		reg.addInitParameter("loginUsername", "druid");
		reg.addInitParameter("loginPassword", "123456");
		return reg;
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		filterRegistrationBean.addInitParameter("profileEnable", "true");
		filterRegistrationBean.addInitParameter("principalCookieName", "USER_COOKIE");
		filterRegistrationBean.addInitParameter("principalSessionName", "USER_SESSION");
		return filterRegistrationBean;
	}

/*	@Bean
	public SqlSessionFactory sqlSessionFactoryBean() throws Exception {		
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(duridDataSource());		
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources(mapperLocations));
		return sqlSessionFactoryBean.getObject();
	}*/


	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(duridDataSource());
	}
}