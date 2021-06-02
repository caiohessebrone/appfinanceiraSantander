package com.aplicacaofinanceira.AppFinainceira;

import io.swagger.v3.oas.integration.OpenApiConfigurationException;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppFinainceiraApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppFinainceiraApplication.class, args);
	}

	@Bean
	public OpenAPI custonOpenAPI(@Value("${application.description}") String description){
		return new OpenAPI()
				.info(new Info()
						.title(description)
						.version("1.0")
						.termsOfService("https://swagger.io/terms")
						.license(new License().name("Apache 2.0").url("http://springdoc.org"))
				);
	}
}
