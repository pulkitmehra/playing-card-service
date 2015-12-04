/*
 * COPYRIGHT:     Copyright reserved by Pulkit Mehra
 */
package com.nike.cards.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Playing card spring boot application starting point.
 *
 * @author pulkit.mehra
 */
@Configuration
@RestController
@Import({ WebConfig.class, ServiceConfig.class })
@EnableAutoConfiguration
public class Application {

    private static final String CARD_SERVICE_URL_MAPPING = "/api/services/1.0/*";

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Info.
     *
     * @return the string
     */
    @RequestMapping("/hello")
    public @ResponseBody String info() {
        return "Playing card service is alive";
    }

    /**
     * Handle request mapping to the dispatcherServlet.
     *
     * @param dispatcherServlet the dispatcher servlet
     * @return the servlet registration bean
     */
    @Bean
    public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean reg = new ServletRegistrationBean(dispatcherServlet);
        reg.addUrlMappings(CARD_SERVICE_URL_MAPPING);
        return reg;
    }

}
