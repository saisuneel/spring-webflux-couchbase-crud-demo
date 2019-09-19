package me.jysh.springwebfluxcouchbase.repository;

import me.jysh.springwebfluxcouchbase.model.Employee;
import org.springframework.data.couchbase.repository.ReactiveCouchbaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends ReactiveCouchbaseRepository<Employee, String> {
}
