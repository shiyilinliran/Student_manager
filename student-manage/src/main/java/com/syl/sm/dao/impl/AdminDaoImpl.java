package com.syl.sm.dao.impl;

import com.syl.sm.dao.AdminDao;
import com.syl.sm.entity.Admin;
import com.syl.sm.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName AdminDaoImpl
 * @Description TODO
 * @Author admin
 * @Date 2020/11/15
 **/
public class AdminDaoImpl implements AdminDao {
    public Admin findAdminByAccount(String account) throws SQLException {
        JdbcUtil jdbcUtil=JdbcUtil.getInitJdbcUtil();
        Connection connection=jdbcUtil.getConnection();
        String sql="Select*from t_admin where account=?";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1,account);
        ResultSet rs=pstmt.executeQuery();
        Admin admin=null;
        while (rs.next()){
            int id=rs.getInt("id");
           String adminAccount=rs.getString("account");
           String password = rs.getString("password");
           String adminName = rs.getString("admin_name");
           admin=new Admin();
           admin.setId(id);
           admin.setAccount(adminAccount);
           admin.setPassword(password);
           admin.setAdminName(adminName);
        }
        rs.close();
        pstmt.close();
        jdbcUtil.closeConnection();
        return admin;
    }
}
