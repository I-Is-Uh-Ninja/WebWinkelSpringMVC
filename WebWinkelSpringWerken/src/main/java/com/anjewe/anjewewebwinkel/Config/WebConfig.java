/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjewe.anjewewebwinkel.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 *
 * @author Anne
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.anjewe.anjewewebwinkel.Controller","com.anjewe.anjewewebwinkel.DAOs", "com.anjewe.anjewewebwinkel.DAOGenerics",
    "com.anjewe.anjewewebwinkel.POJO", "com.anjewe.anjewewebwinkel.Service", "com.anjewe.anjewewebwinkel.Config"})
public class WebConfig extends WebMvcConfigurerAdapter {
    
    @Bean
    public ViewResolver viewresolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }
    
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//            registry.addResourceHandler("/resources/**").addResourceLocations("resources/css/");
//        }
    
}