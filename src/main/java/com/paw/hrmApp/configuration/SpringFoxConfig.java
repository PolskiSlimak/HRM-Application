package com.paw.hrmApp.configuration;

import com.paw.hrmApp.model.UserDetailsImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestHeader;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {
    public static final String department = "Department";
    public static final String employee = "Employee";
    public static final String employeeHistory = "EmployeeHistory";
    public static final String job = "Job";
    public static final String location = "Location";
    public static final String user = "User";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.paw.hrmApp"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(createApiInfo())
                .tags(new Tag(department, ""), new Tag(employee, ""),
                        new Tag(employeeHistory, ""), new Tag(job, ""),
                        new Tag(location, ""),
                        new Tag(user, ""))
                .ignoredParameterTypes(UserDetailsImpl.class, RequestHeader.class)
                .securityContexts(singletonList(createContext()))
                .securitySchemes(singletonList(createSchema()));
    }

    private ApiInfo createApiInfo() {
        return new ApiInfo("Human Resource Management Application",
                "",
                "1.0.0",
                "",
                new Contact("", "", ""),
                "",
                "",
                Collections.emptyList());
    }
    private SecurityContext createContext() {
        return SecurityContext.builder()
                .securityReferences(createRef())
                .forPaths(PathSelectors.any())
                .build();
    }

    private List<SecurityReference> createRef() {
        AuthorizationScope authorizationScope = new AuthorizationScope(
                "global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return singletonList(new SecurityReference("apiKey", authorizationScopes));
    }

    private SecurityScheme createSchema() {
        return new ApiKey("apiKey", "Authorization", "header");
    }
}
