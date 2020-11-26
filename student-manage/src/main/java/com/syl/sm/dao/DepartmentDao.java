package com.syl.sm.dao;

import com.syl.sm.entity.Department;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName DepartmentDao
 * @Description TODO
 * @Author admin
 * @Date 2020/11/20
 **/
public interface DepartmentDao {
    List<Department> getAll() throws SQLException;
    int insertDepartment(Department department) throws SQLException;
    int deleteDepartment(int department) throws SQLException;
}
