package com.indi.config;

import org.springframework.context.ApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.Enumeration;

/**
 * @author 华峰
 * @create: 2020-04-06 12:03
 */
public class LibraryWebInitializer implements WebApplicationInitializer {
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(AppConfig.class);

        DispatcherServlet servlet = new DispatcherServlet(ac);
        ServletRegistration.Dynamic library = servletContext.addServlet("library", servlet);
        library.addMapping("/Library/*");
        library.setLoadOnStartup(1);
    }
}
