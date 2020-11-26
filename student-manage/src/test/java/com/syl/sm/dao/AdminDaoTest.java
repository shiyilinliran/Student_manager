package com.syl.sm.dao;

import com.syl.sm.entity.Admin;
import com.syl.sm.factory.DaoFActory;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class AdminDaoTest {

    private final AdminDao adminDao= DaoFActory.getAdminDaoInstance();
    @Test
    public void findAdminByAccount() {
            Admin admin;
            try {
                admin=adminDao.findAdminByAccount("shiyilin@qq.com");
                assertEquals("施译霖",admin.getAdminName());
                System.out.println(admin);
            }catch (SQLException e){
                System.err.println(e.getMessage());
            }

        }
    }