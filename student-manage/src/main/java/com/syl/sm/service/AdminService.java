package com.syl.sm.service;

import com.syl.sm.utils.ResultEntity;

import java.sql.ResultSet;

/**
 * @ClassName AdminService
 * @Description TODO
 * @Author admin
 * @Date 2020/11/15
 **/
public interface AdminService {
    ResultEntity adminLogin(String account, String password);
}
