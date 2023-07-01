package com.myproducts.resources;

import com.myproducts.model.Department;
import com.myproducts.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentResource {

    @Autowired
    private DepartmentService service;

    private static final Logger logger = LoggerFactory.getLogger(DepartmentResource.class);

    @GetMapping("/{departmentName}")
    public ResponseEntity<Department> getDepartmentByName(@PathVariable("departmentName") String departmentName) {
        Department department = this.service.getDepartmentByName(departmentName);
        if (department != null) {
            return ResponseEntity.ok(department);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Department>> getAllDepartments () {
        List<Department> departments = this.service.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    @PostMapping("/new")
    public ResponseEntity<String> createDepartment(@RequestBody Department department) {
        try {
            this.service.createDepartment(department);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<String> editDepartment(@RequestBody Department department) {
        try {
            this.service.updateDepartment(department);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{departmentName}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("departmentName") String departmentName) {
        try {
            this.service.deleteDepartmentByName(departmentName);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
