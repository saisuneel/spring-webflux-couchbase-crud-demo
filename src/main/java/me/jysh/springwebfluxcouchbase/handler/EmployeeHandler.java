package me.jysh.springwebfluxcouchbase.handler;

import me.jysh.springwebfluxcouchbase.model.Employee;
import me.jysh.springwebfluxcouchbase.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class EmployeeHandler {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeHandler(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Mono<ServerResponse> getAllEmployees(ServerRequest request) {
        return employeeService
                .findAll()
                .collectList()
                .flatMap(employees -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(employees)));
    }

    public Mono<ServerResponse> createEmployee(ServerRequest request) {
        return request
                .bodyToMono(Employee.class)
                .flatMap(employee -> employeeService.create(employee))
                .flatMap(employee -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromObject(employee)));
    }

    public Mono<ServerResponse> getEmployee(ServerRequest request) {
        return employeeService
                .findById(request.pathVariable("employeeId"))
                .flatMap(employee -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromObject(employee)));
    }

    public Mono<ServerResponse> updateEmployee(ServerRequest request) {
        return request.bodyToMono(Employee.class)
                .flatMap(employee -> employeeService.update(request.pathVariable("employeeId"), employee))
                .flatMap(employee -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromObject(employee)));
    }

    public Mono<ServerResponse> deleteEmployee(ServerRequest request) {
        return employeeService
                .delete(request.pathVariable("employeeId"))
                .then(ServerResponse.ok().build());
    }
}