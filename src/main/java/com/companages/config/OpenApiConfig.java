package com.companages.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
 @Bean OpenAPI companagesOpenApi(){String scheme="bearerAuth";return new OpenAPI().info(new Info().title("Companages API").version("1.0").description("Multi-tenant organization management API")).addSecurityItem(new SecurityRequirement().addList(scheme)).schemaRequirement(scheme,new SecurityScheme().name(scheme).type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"));}
}
