package dev.ujjwal.app_3_aws.repository;

import dev.ujjwal.app_3_aws.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}