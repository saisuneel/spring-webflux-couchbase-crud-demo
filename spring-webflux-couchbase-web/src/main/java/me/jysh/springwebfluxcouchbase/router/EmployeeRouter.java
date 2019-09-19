package me.jysh.springwebfluxcouchbase.router;

import me.jysh.springwebfluxcouchbase.handler.EmployeeHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class EmployeeRouter {
    @Bean
    public RouterFunction<ServerResponse> route(EmployeeHandler handler) {
        return RouterFunctions
                .route(GET("/employees").and(accept(MediaType.APPLICATION_JSON)), handler::getAllEmployees)
                .andRoute(POST("/employees").and(accept(MediaType.APPLICATION_JSON)), handler::createEmployee)
                .andRoute(GET("/employees/{employeeId}").and(accept(MediaType.APPLICATION_JSON)), handler::getEmployee)
                .andRoute(PUT("/employees/{employeeId}").and(accept(MediaType.APPLICATION_JSON)), handler::updateEmployee)
                .andRoute(DELETE("/employees/{employeeId}").and(accept(MediaType.APPLICATION_JSON)), handler::deleteEmployee);
    }
}
