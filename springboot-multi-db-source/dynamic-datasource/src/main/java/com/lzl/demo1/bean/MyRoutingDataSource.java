package com.lzl.demo1.bean;

import com.lzl.demo1.bean.DBContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


/**
 * 获取路由key
 *
 * Spring boot提供了AbstractRoutingDataSource根据用户定义的规则选择当前的数据库
 */
public class MyRoutingDataSource extends AbstractRoutingDataSource {


    @Override
    protected Object determineCurrentLookupKey() {
        return DBContextHolder.get();
    }
}
