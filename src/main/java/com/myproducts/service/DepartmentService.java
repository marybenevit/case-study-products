package com.myproducts.service;

import com.myproducts.model.Department;
import com.myproducts.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(DepartmentService.class);

    public List<Department> getAllDepartments() {
        List<Department> departments = this.repository.findAll();
        if (departments.isEmpty()) {
           logger.info("There are currently no registered departments");
        }
        return departments;
    }

    public Department getDepartmentByName(String departmentName) {
        return this.repository.findByDepartmentName(departmentName).orElse(null);
    }

    @Transactional
    public void createDepartment(Department department) {
        Department existingDepartment = this.getDepartmentByName(department.getDepartmentName());
        if (existingDepartment == null) {
            this.repository.save(department);
            logger.info("New department created with the name " + department.getDepartmentName());
        }
        else {
            throw new IllegalArgumentException("Error. Department with the name " + department.getDepartmentName() + " already exists");
        }
    }

    @Transactional
    public void updateDepartment(Department department) {
        Department existingDepartment = this.repository.findById(department.getDepartmentId()).orElse(null);
        if (existingDepartment != null) {
            this.repository.save(department);
            logger.info("Department with the name " + department.getDepartmentName() + " updated");
        }
        else {
            throw new IllegalArgumentException("Error. Department with the name " + department.getDepartmentName() + " doesn't exist");
        }
    }

    @Transactional
    public void deleteDepartmentByName(String departmentName) {
        Department existingDepartment = this.getDepartmentByName(departmentName);
        if (existingDepartment != null) {
            this.repository.delete(existingDepartment);
        }
        else {
            throw new IllegalArgumentException("Error. Department with the name " + departmentName + " doesn't exist");
        }
    }
}
