package com.syl.sm.factory;

import com.syl.sm.dao.AdminDao;
import com.syl.sm.dao.DepartmentDao;
import com.syl.sm.dao.impl.AdminDaoImpl;
import com.syl.sm.dao.impl.DepartmentDaoImpl;
import com.syl.sm.entity.Department;

/**
 * @ClassName DaoFActory
 * @Description TODO
 * @Author admin
 * @Date 2020/11/15
 **/
public class DaoFActory {
    public static AdminDao getAdminDaoInstance(){
        return new AdminDaoImpl();
    }
    public static DepartmentDao getDepartmentDaoInstance(){
        return new DepartmentDaoImpl();
    }
}
