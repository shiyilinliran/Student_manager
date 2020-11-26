package com.syl.sm.factory;

import com.syl.sm.entity.Department;
import com.syl.sm.service.AdminService;
import com.syl.sm.service.DepartmentService;
import com.syl.sm.service.impl.AdminServiceImpl;
import com.syl.sm.service.impl.DepartmentServiceimpL;

/**
 * @ClassName ServiceFactory
 * @Description TODO
 * @Author admin
 * @Date 2020/11/15
 **/
public class ServiceFactory {
    public static AdminService getAdminServiceInstance(){
        return new AdminServiceImpl();
    }
    public static DepartmentService getDepartmentServiceInstance(){
        return new DepartmentServiceimpL();
    }
}
