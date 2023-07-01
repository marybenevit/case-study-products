package com.myproducts.repository;

import com.myproducts.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query(value = "SELECT * FROM TB_DEPARTMENT WHERE DEPARTMENT_NAME = :departmentName", nativeQuery = true)
    public Optional<Department> findByDepartmentName(@Param("departmentName") String departmentName);

}
