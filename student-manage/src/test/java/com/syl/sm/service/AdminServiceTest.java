package com.syl.sm.service;

import com.syl.sm.factory.ServiceFactory;
import com.syl.sm.utils.ResultEntity;
import org.junit.Test;

import java.sql.ResultSet;

import static org.junit.Assert.*;

public class AdminServiceTest {
    public final AdminService adminService = ServiceFactory.getAdminServiceInstance();

    @Test
    public void adminLogin() {
        ResultEntity resultEntity=adminService.adminLogin("shiyilin@qq.com","shiyilin");
        assertEquals(0,resultEntity.getCode());
        System.out.println(resultEntity);
    }
}