package com.paw.hrmApp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {
    public static final String department = "Department";
    public static final String employee = "Employee";
    public static final String employeeHistory = "EmployeeHistory";
    public static final String job = "Job";
    public static final String location = "Location";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.paw.hrmApp"))
                .paths(PathSelectors.any())
                .build()
                .tags(new Tag(department, ""), new Tag(employee, ""),
                        new Tag(employeeHistory, ""), new Tag(job, ""),
                        new Tag(location, ""));
    }
}
