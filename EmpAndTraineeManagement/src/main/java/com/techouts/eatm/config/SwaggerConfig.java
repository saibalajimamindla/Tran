package com.techouts.eatm.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				// .apis(RequestHandlerSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("com.techouts.eatm.controller"))
				// .paths(PathSelectors.any())
				.build()
				.apiInfo(apidtails());

	}

	private ApiInfo apidtails()
	{
		return new ApiInfo("Tran Api",
				"Api for Tran",
				"1.0",
				"For Internal Use only",
				new springfox.documentation.service.Contact("Techouts", "https://techouts.com/", "saibalaji.m@techouts.com"),
				"Api license", 
				"https://techouts.com/",
				Collections.emptyList());
	}
}
