package com.lzl.demo2.config;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.lzl.demo2.mapper.db2",
        sqlSessionFactoryRef ="db2SqlSessionFactory" )
public class Db2Config {


    /**
     * 本地mysql的db2库的数据配置
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    public DataSourceProperties db2SourceProperties() {
        return new DataSourceProperties();
    }
    /**
     * 初始化数据源
     * @return
     */
    @Bean("db2DataSource")
    public DataSource db2DataSource() {
        return db2SourceProperties().initializeDataSourceBuilder().build();
    }




    /**
     * 创建session工厂
     */
    @Bean("db2SqlSessionFactory")
    public SqlSessionFactory db2SqlSessionFactory(@Qualifier("db2DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        //设置数据源
        sqlSessionFactoryBean.setDataSource(dataSource);
        //设置mapper扫描路径
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:/mapper/db2/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }


}
