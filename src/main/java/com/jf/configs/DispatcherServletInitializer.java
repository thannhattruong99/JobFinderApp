/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jf.configs;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 *
 * @author truongtn
 */
public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

    @Override
    protected Class<?>[] getRootConfigClasses() {
        //Declare class using Configuration annotation without extend from Configurer
        return new Class[]{
            HibernateConfig.class,
            TilesConfig.class,
            SpringSecurityConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        //Declare class that extend from Configurer
        return new Class[]{
            WebApplicationContextConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
    
}
