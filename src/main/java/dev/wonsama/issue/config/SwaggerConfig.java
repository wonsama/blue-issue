package dev.wonsama.issue.config;

import org.springdoc.core.models.GroupedOpenApi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class SwaggerConfig {

  // http://localhost:8081/swagger-ui/index.html

  final String SWAGGER_TITLE = "issue";
  final String SWAGGER_GROUP = "issue system";
  final String[] packagesToScan = { "dev.wonsama.issue" };

  final String SWAGGER_VERSION = "1.0";
  final String[] paths = { "/api/**" };

  @Bean
  public OpenAPI customOpenAPI() {

    return new OpenAPI()
        .info(new Info()
            .title(SWAGGER_TITLE)
            .version(SWAGGER_VERSION)
            .description(String.format("%s API", SWAGGER_TITLE)));
  }

  @Bean
  public GroupedOpenApi api() {

    return GroupedOpenApi.builder().group(SWAGGER_GROUP)
        .pathsToMatch(paths)
        .packagesToScan(packagesToScan)
        .build();
  }
}