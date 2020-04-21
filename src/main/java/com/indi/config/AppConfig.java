package com.indi.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.beans.PropertyVetoException;
import java.util.List;

/**
 * @author 华峰
 * @create: 2020-04-06 12:05
 */
@Configuration
@ComponentScan(value={"com.indi.controller","com.indi.service"})
@MapperScan("com.indi.mapper")
@PropertySource("classpath:jdbc.conf")
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {

    @Bean
    public FreeMarkerViewResolver freeMarkerViewResolver(){
        FreeMarkerViewResolver fm = new FreeMarkerViewResolver("",".ftl");
        fm.setContentType("text/html; charset=utf-8");
        //视图先走自己的解析器，再走spring的
        fm.setOrder(1);
        return fm;
    }

    @Bean
    public FreeMarkerConfig freeMarkerConfig(){
        FreeMarkerConfigurer fm = new FreeMarkerConfigurer();
        fm.setTemplateLoaderPath("/WEB-INF/pages/");
        return fm;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter());
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/")
                .addResourceLocations("classpath:/META-INF/resources/");
//                .resourceChain(false)
//                .addResolver(new WebJarsResourceResolver())
//                .addResolver(new PathResourceResolver());
    }

    @Value("${driverClass}")
    private String driver;
    @Value("${url}")
    private String url;
    @Value("${user}")
    private String user;
    @Value("${pwd}")
    private String pwd;
    /**
     * 创建数据源
     * @return
     * @throws PropertyVetoException
     */
    @Bean("dataSource")
    public ComboPooledDataSource comboPooledDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(driver);
        dataSource.setJdbcUrl(url);
        dataSource.setUser(user);
        dataSource.setPassword(pwd);

        dataSource.setInitialPoolSize(3);
        dataSource.setMaxIdleTimeExcessConnections(1000);
        dataSource.setMaxIdleTime(10);
        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
//        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//        factoryBean.setDataSource(comboPooledDataSource());
//        return factoryBean.getObject();
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, comboPooledDataSource());
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration(environment);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        return sqlSessionFactory;
    }

//    @Bean
    public org.apache.ibatis.session.Configuration configuration(){
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        return configuration;
    }
}
