package com.grocery.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "grocery")
@EntityScan(basePackages = "grocery")
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/grocery/signin").allowedOrigins("http://localhost:4200");
				registry.addMapping("/grocery/signin").allowCredentials(true);

				registry.addMapping("/grocery/signup").allowedOrigins("http://localhost:4200");
				registry.addMapping("/grocery/signup").allowCredentials(true);

				registry.addMapping("/grocery/signout").allowedOrigins("http://localhost:4200");
				registry.addMapping("/grocery/signout").allowCredentials(true);

				// category
				registry.addMapping("/grocery/category/save").allowedOrigins("*");
				registry.addMapping("/grocery/category/save").allowCredentials(true);
				
				registry.addMapping("/grocery/category/all").allowedOrigins("*");
				registry.addMapping("/grocery/category/all").allowCredentials(true);
				
				
				//product
				registry.addMapping("/grocery/product/add").allowedOrigins("*");
				registry.addMapping("/grocery/product/add").allowCredentials(true);
				
				registry.addMapping("/grocery/product/all").allowedOrigins("*");
				registry.addMapping("/grocery/product/all").allowCredentials(true);
				
				registry.addMapping("/grocery/product/edit").allowedOrigins("*");
				registry.addMapping("/grocery/product/edit").allowCredentials(true);
				
				registry.addMapping("/grocery/product/delete/*").allowedOrigins("*");
				registry.addMapping("/grocery/product/delete/*").allowCredentials(true);
				
				//search product by category
				registry.addMapping("/grocery/product/all/*").allowedOrigins("*");
				registry.addMapping("/grocery/product/all/*").allowCredentials(true);
				
				

			}
		};
	}

}
