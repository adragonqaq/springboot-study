package com.lzl.demo1.bean;

import com.lzl.demo1.bean.DBContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


/**
 * 获取路由key
 */

public class MyRoutingDataSource extends AbstractRoutingDataSource {


    @Override
    protected Object determineCurrentLookupKey() {
        return DBContextHolder.get();
    }
}
