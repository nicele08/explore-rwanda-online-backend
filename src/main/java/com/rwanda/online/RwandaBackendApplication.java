package com.rwanda.online;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
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
}
