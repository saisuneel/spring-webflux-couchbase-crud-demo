package me.jysh.springwebfluxcouchbase.service;

import me.jysh.springwebfluxcouchbase.model.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {
    Flux<Employee> findAll();

    Mono<Employee> create(Employee employee);

    Mono<Employee> findById(String employeeId);

    Mono<Employee> update(String employeeId, Employee employee);

    Mono<Void> delete(String employeeId);
}
