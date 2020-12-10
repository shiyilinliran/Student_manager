package com.syl.sm.service.impl;

import com.syl.sm.entity.Clazz;
import com.syl.sm.factory.ClazzDaoFactory;
import com.syl.sm.factory.DaoFActory;
import com.syl.sm.service.ClazzService;

import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName ClazzServiceImpl
 * @Description TODO
 * @Author admin
 * @Date 2020/12/4
 **/
public class ClazzServiceImpl implements ClazzService {
    @Override
    public List<Clazz> getClazzByDepId(int depId) {
        List<Clazz> list = null;
        try {
            list = ClazzDaoFactory.getClazzDaoInstance().selectByDepartmentId(depId);
        } catch (SQLException e) {
            System.err.println("根据院系id查询班级列表出现异常");
        }
        return list;
    }

    @Override
    public List<Clazz> selectAll() {
        List<Clazz> list = null;
        try {
            list = DaoFActory.getClazzDaoInstance().selectAll();
        } catch (SQLException e) {
            System.err.println("查询所有班级列表出现异常");
        }
        return list;
    }

    @Override
    public int addClazz(Clazz clazz) {
        int n = 0;
        try {
            n = DaoFActory.getClazzDaoInstance().insertClazz(clazz);
        } catch (SQLException throwables) {
            System.err.println("新增班级出现异常");
        }
        return n;
    }

    @Override
    public int deleteClazz(int clazzId) {
        int n = 0;
        try {
            n = DaoFActory.getClazzDaoInstance().deleteClazz(clazzId);
        } catch (SQLException throwables) {
            System.err.println("删除班级出现异常");
        }
        return n;
    }
}
