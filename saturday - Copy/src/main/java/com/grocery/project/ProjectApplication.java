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
				registry.addMapping("/grocery/signin").allowedOrigins("http://172.18.137.44:4200");
				registry.addMapping("/grocery/signin").allowCredentials(true);

				registry.addMapping("/grocery/signup").allowedOrigins("http://172.18.137.44:4200");
				registry.addMapping("/grocery/signup").allowCredentials(true);

				registry.addMapping("/grocery/signout").allowedOrigins("http://172.18.137.44:4200");
				registry.addMapping("/grocery/signout").allowCredentials(true);

				// category
				registry.addMapping("/grocery/category/save").allowedOrigins("http://172.18.137.44:4200");
				registry.addMapping("/grocery/category/save").allowCredentials(true);

				registry.addMapping("/grocery/category/all").allowedOrigins("http://172.18.137.44:4200");
				registry.addMapping("/grocery/category/all").allowCredentials(true);

				registry.addMapping("/grocery/category/delete/*").allowedOrigins("http://172.18.137.44:4200");
				registry.addMapping("/grocery/category/delete/*").allowCredentials(true);

				// product
				registry.addMapping("/grocery/products/save").allowedOrigins("http://172.18.137.44:4200");
				registry.addMapping("/grocery/products/save").allowCredentials(true);

				registry.addMapping("/grocery/products/all").allowedOrigins("http://172.18.137.44:4200");
				registry.addMapping("/grocery/products/all").allowCredentials(true);

				registry.addMapping("/grocery/products/edit").allowedOrigins("http://172.18.137.44:4200");
				registry.addMapping("/grocery/products/edit").allowCredentials(true);

				registry.addMapping("/grocery/products/delete/*").allowedOrigins("http://172.18.137.44:4200");
				registry.addMapping("/grocery/products/delete/*").allowCredentials(true);

				// search product by category
				registry.addMapping("/grocery/products/all/*").allowedOrigins("http://172.18.137.44:4200");
				registry.addMapping("/grocery/products/all/*").allowCredentials(true);

				// add to cart
				registry.addMapping("/grocery/cart/save").allowedOrigins("http://172.18.137.44:4200");
				registry.addMapping("/grocery/cart/save").allowCredentials(true);

				registry.addMapping("/grocery/cart/edit").allowedOrigins("http://172.18.137.44:4200");
				registry.addMapping("/grocery/cart/edit").allowCredentials(true);

				// get item in a cart by user id
				registry.addMapping("/grocery/cart/all/*").allowedOrigins("http://172.18.137.44:4200");
				registry.addMapping("/grocery/cart/all/*").allowCredentials(true);

				registry.addMapping("/grocery/cart/delete/*").allowedOrigins("http://172.18.137.44:4200");
				registry.addMapping("/grocery/cart/delete/*").allowCredentials(true);

				// add to order

				registry.addMapping("/grocery/order/add").allowedOrigins("http://172.18.137.44:4200");
				registry.addMapping("/grocery/order/add").allowCredentials(true);
				// order by userid
				registry.addMapping("/grocery/order/all/*").allowedOrigins("http://172.18.137.44:4200");
				registry.addMapping("/grocery/order/all/*").allowCredentials(true);
				
				// view order details
				registry.addMapping("/grocery/order/details/**").allowedOrigins("http://172.18.137.44:4200");
				registry.addMapping("/grocery/order/details/**").allowCredentials(true);
			}
		};
	}

}
