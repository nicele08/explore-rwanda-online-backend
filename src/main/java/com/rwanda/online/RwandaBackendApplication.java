package com.rwanda.online;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.rwanda.online.service.AuthFilter;

@SpringBootApplication
@EnableJpaAuditing
public class RwandaBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(RwandaBackendApplication.class, args);
	}
	
	@Configuration
	public class CorsConfig implements WebMvcConfigurer {

	    @Override
	    public void addCorsMappings(CorsRegistry registry) {

	        registry.addMapping("/**")
	                .allowedOrigins("http://localhost:3000", "https://cniyindagiriye.github.io/explore-rwanda-online-frontend")
	                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD")
	                .allowCredentials(true);
	    }

	}
	
	@Bean
	public FilterRegistrationBean<AuthFilter> filterRegistrationBean() {
		FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
		AuthFilter authFilter = new AuthFilter();
		registrationBean.setFilter(authFilter);
		registrationBean.addUrlPatterns("/api/v1/locations/*",
				"/api/v1/accomodations/*", 
				"/api/v1/rooms/*", 
				"/api/v1/trips*",
				"/api/v1/comments/*",
				"/api/v1/admin/*");
		return registrationBean;
	}
}
