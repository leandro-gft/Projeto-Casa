package br.com.gft.secureapp.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;



@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	
	@Bean
    public SpringSecurityDialect securityDialect() {
         return new SpringSecurityDialect();
    }

}
