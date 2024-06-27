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
@MapperScan(basePackages = "com.lzl.demo2.mapper.db1",
        sqlSessionFactoryRef ="db1SqlSessionFactory" )
public class Db1Config {



    /**
     * 本地mysql的db1库的数据配置
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    public DataSourceProperties db1SourceProperties() {
        return new DataSourceProperties();
    }
    /**
     * 初始化数据源
     * @return
     */
    @Bean("db1DataSource")
    public DataSource db1DataSource() {
        return db1SourceProperties().initializeDataSourceBuilder().build();
    }





    /**
     * 创建session工厂
     */
    @Bean("db1SqlSessionFactory")
    public SqlSessionFactory db1SqlSessionFactory(@Qualifier("db1DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        //设置数据源
        sqlSessionFactoryBean.setDataSource(dataSource);
        //设置mapper扫描路径
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:/mapper/db1/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

}
