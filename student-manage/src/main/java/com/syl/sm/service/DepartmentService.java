package com.syl.sm.service;

import com.syl.sm.entity.Department;

import java.util.List;

/**
 * @ClassName DepartmentService
 * @Description TODO
 * @Author admin
 * @Date 2020/11/20
 **/
public interface DepartmentService {
    List<Department> selectAll();
    int addDepartment(Department department);
    int delDepartment(int department);
}
