package com.syl.sm.factory;

import com.syl.sm.dao.ClazzDao;
import com.syl.sm.dao.impl.ClazzDaoImp;

/**
 * @ClassName ClazzDao
 * @Description TODO
 * @Author admin
 * @Date 2020/12/3
 **/
public class ClazzDaoFactory {
    public static ClazzDao getClazzDaoInstance(){
        return new ClazzDaoImp();
    }
}
