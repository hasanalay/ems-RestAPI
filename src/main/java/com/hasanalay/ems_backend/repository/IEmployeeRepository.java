package com.hasanalay.ems_backend.repository;

import com.hasanalay.ems_backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepository extends JpaRepository<Employee, Long> {

}
