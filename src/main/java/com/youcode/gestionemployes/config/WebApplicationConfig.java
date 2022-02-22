package com.youcode.gestionemployes.config;

import com.youcode.gestionemployes.Application;
import org.springframework.context.annotation.Scope;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
public class WebApplicationConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
                Application.class,
                PersistenceConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        DelegatingFilterProxy dfp = new DelegatingFilterProxy();
        dfp.setTargetBeanName("authFilter");
        return new Filter[]{dfp};
    }
}
