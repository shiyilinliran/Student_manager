package com.syl.sm.service.impl;

import com.syl.sm.dao.AdminDao;
import com.syl.sm.entity.Admin;
import com.syl.sm.factory.DaoFActory;
import com.syl.sm.service.AdminService;
import com.syl.sm.utils.ResultEntity;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.SQLException;

/**
 * @ClassName AdminServiceImpl
 * @Description TODO
 * @Author admin
 * @Date 2020/11/15
 **/
public class AdminServiceImpl implements AdminService {
    private final AdminDao adminDao= DaoFActory.getAdminDaoInstance();
    @Override
    public ResultEntity adminLogin(String account, String password) {
        ResultEntity resultEntity;
        Admin admin=null;
        try {
            admin=adminDao.findAdminByAccount(account);
        }catch (SQLException e){
            System.err.println("出现sql异常");
        }
        if (admin!=null){
            if(DigestUtils.md5Hex(password).equals(admin.getPassword())){
                resultEntity=ResultEntity.builder().code(0).message("登录成功").data(admin).build();
            }else {
                resultEntity=ResultEntity.builder().code(1).message("密码错误").build();            }
        }else {
            resultEntity=ResultEntity.builder().code(2).message("账号不存在").build();
        }
        return resultEntity;
    }
}
