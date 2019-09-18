package me.jysh.springwebfluxcouchbase.service.impl;

import me.jysh.springwebfluxcouchbase.model.Employee;
import me.jysh.springwebfluxcouchbase.repository.EmployeeRepository;
import me.jysh.springwebfluxcouchbase.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Flux<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Mono<Employee> create(Employee employee) {
        //TODO: implement handling where employee already exists.
        return employeeRepository.save(employee);
    }

    @Override
    public Mono<Employee> findById(String employeeId) {
        //TODO: implement switchIfEmpty(doesNotExist(employeeId)) if employee does not exists.
        return employeeRepository.findById(employeeId);
    }

    @Override
    public Mono<Employee> update(String employeeId, Employee employee) {
        return employeeRepository.existsById(employeeId).flatMap(exists -> exists ? employeeRepository.save(new Employee(employeeId, employee.getFirstName(), employee.getLastName())) : employeeRepository.save(employee));
    }

    public Mono<Void> delete(String employeeId) {
//        return employeeRepository.existsById(employeeId).flatMap(exists -> exists ? employeeRepository.deleteById(employeeId) : doSomething());
        return employeeRepository.deleteById(employeeId);

    }

    private Mono<? extends Void> doSomething() {
        return null;
    }
}
