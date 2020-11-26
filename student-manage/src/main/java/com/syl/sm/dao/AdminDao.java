package com.syl.sm.dao;

import com.syl.sm.entity.Admin;

import java.sql.SQLException;

/**
 * 根据账号查找管理员
 *
 * @Description TODO
 * @Author admin
 * @Date 2020/11/15
 **/
public interface AdminDao {
    Admin findAdminByAccount(String account) throws SQLException;
}
