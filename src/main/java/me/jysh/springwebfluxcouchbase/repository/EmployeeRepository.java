package me.jysh.springwebfluxcouchbase.repository;

import me.jysh.springwebfluxcouchbase.model.Employee;
import org.springframework.data.couchbase.repository.ReactiveCouchbaseRepository;

public interface EmployeeRepository extends ReactiveCouchbaseRepository<Employee, String> {
}
