package com.lzl.demo1.config;


import com.lzl.demo1.bean.DBTypeEnum;
import com.lzl.demo1.bean.MyRoutingDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * 关于数据源配置，参考SpringBoot官方文档第79章《DataAccess》
 *
 *
 * 配置了4个数据源，1个master，2两个slave，1个路由数据源。
 *
 * 前3个数据源都是为了生成第4个数据源，而且后续我们只用这最后一个路由数据源
 *
 */
@Configuration
public class DataSourceConfig {


    @Bean
    @ConfigurationProperties("spring.datasource.master")
    public DataSource masterDataSource(){
    return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.slave1")
    public DataSource slave1DataSource(){
    return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.slave2")
    public DataSource slave2DataSource(){
    return DataSourceBuilder.create().build();
    }

    @Bean
    public DataSource myRoutingDataSource(@Qualifier("masterDataSource")DataSource masterDataSource,
                                        @Qualifier("slave1DataSource")DataSource slave1DataSource,
                                        @Qualifier("slave2DataSource")DataSource slave2DataSource){
    Map<Object,Object> targetDataSources=new HashMap<>();
    targetDataSources.put(DBTypeEnum.MASTER,masterDataSource);
    targetDataSources.put(DBTypeEnum.SLAVE1,slave1DataSource);
    targetDataSources.put(DBTypeEnum.SLAVE2,slave2DataSource);
    MyRoutingDataSource myRoutingDataSource=new MyRoutingDataSource();
    myRoutingDataSource.setDefaultTargetDataSource(masterDataSource);
    myRoutingDataSource.setTargetDataSources(targetDataSources);
    return myRoutingDataSource;
    }







}
