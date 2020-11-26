package com.syl.sm.service.impl;

import com.syl.sm.dao.DepartmentDao;
import com.syl.sm.entity.Department;
import com.syl.sm.factory.DaoFActory;
import com.syl.sm.service.DepartmentService;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName DepartmentServiceimpL
 * @Description TODO
 * @Author admin
 * @Date 2020/11/20
 **/
public class DepartmentServiceimpL implements DepartmentService {
    private final DepartmentDao departmentDao= DaoFActory.getDepartmentDaoInstance();
    @Override
    public List<Department> selectAll() {
        List<Department> departmentList=null;
        try {
            departmentList=departmentDao.getAll();
        }catch (SQLException e){
            System.err.println("查询院系信息出现异常");
        }
        return departmentList;
    }

    @Override
    public int addDepartment(Department department) {
        int n=0;
        try {
            n=departmentDao.insertDepartment(department);
        } catch (SQLException e) {
            System.err.println("新增院系信息出现异常");
        }
        return n;
    }

    @Override
    public int delDepartment(int department) {
        int n=0;
        try {
            n=departmentDao.deleteDepartment(department);
        } catch (SQLException e) {
            System.err.println("删除院系信息出现异常");
        }
        return n;
    }
}
