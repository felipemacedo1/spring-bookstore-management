package com.jbs.javabookstore.configuration;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI javaBookStoreOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("JavaBookStore API")
						.description("API para Gerenciamento de uma Loja de Livros - Felipe Macedo")
						.version("v1.0.0")
						.license(new License()
								.name("Felipe Macedo")
								.url("https://www.linkedin.com/in/felipemacedo1/"))
						.contact(new Contact()
								.name("Felipe Macedo")
								.url("https://github.com/FelipeAJdev")
								.email("felipealexandrej@outlook.com.br")))
				.externalDocs(new ExternalDocumentation()
						.description("Repositório no GitHub")
						.url("https://github.com/FelipeAJdev"));
	}

	@Bean
	public OpenApiCustomizer customGlobalHeaderOpenApiCustomiser() {

		return openApi -> {
			openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {

				ApiResponses apiResponses = operation.getResponses();

				apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
				apiResponses.addApiResponse("201", createApiResponse("Objeto Persistido!"));
				apiResponses.addApiResponse("204", createApiResponse("Objeto Excluído!"));
				apiResponses.addApiResponse("400", createApiResponse("Erro na Requisição!"));
				apiResponses.addApiResponse("401", createApiResponse("Acesso Não Autorizado!"));
				apiResponses.addApiResponse("403", createApiResponse("Acesso Proibido!"));
				apiResponses.addApiResponse("404", createApiResponse("Objeto Não Encontrado!"));
				apiResponses.addApiResponse("500", createApiResponse("Erro na Aplicação!"));

			}));
		};
	}

	private ApiResponse createApiResponse(String message) {
		return new ApiResponse().description(message);
	}
}
