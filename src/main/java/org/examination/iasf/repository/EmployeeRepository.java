package org.examination.iasf.repository;

import org.examination.iasf.models.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @Author: HuuNghia
 * @LastModified: 2024/12/12
 */

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByName(String name);
    List<Employee> findByNameContaining(String name);
}
