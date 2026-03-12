package com.vibetrip.vibetripserver.config.docs

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun customOpenApi(): OpenAPI {
        val securitySchemeName = "bearerAuth"
        val scheme = SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT")

        val requirement = SecurityRequirement().addList(securitySchemeName)

        return OpenAPI()
            .info(openApiInfo())
            .components(Components().addSecuritySchemes(securitySchemeName, scheme))
            .addSecurityItem(requirement)
    }

    private fun openApiInfo(): Info {
        return Info()
            .title("Vibe Trip Server API")
            .description("Vibe Trip Server API 명세서")
            .version("1.0")
    }
}