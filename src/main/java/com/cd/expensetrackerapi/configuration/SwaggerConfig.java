package com.cd.expensetrackerapi.configuration;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springEmsOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Expense Tracker API")
                        .description("Expense Tracker h2 application")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
//                                .contact(new Contact("Pratik Dimble"))
                        //.contact(new Contact().name("Pratik Dimble", "https://github.com/pratikdimble/expense-tracker-api", "pratik.dimble@niitorinfotech.com", null))
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Expense Tracker REST API Wiki Documentation")
                        .url("https://ems.wiki.github.org/docs"));
    }

}
