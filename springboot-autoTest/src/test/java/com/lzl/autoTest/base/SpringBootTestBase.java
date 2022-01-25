package com.lzl.autoTest.base;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lzl
 * @description springboot测试基类：基于spring上下文的服务需要继承该类
 * 继承{@link AbstractTransactionalJUnit4SpringContextTests}
 * 实现Junit4默认事务管理
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SpringBootTestBase extends AbstractTransactionalJUnit4SpringContextTests {

    @Before
    public void before(){
        System.out.println("---------开始执行Junit测试代码----------");
        init();
    }

    @After
    public void after(){
        System.out.println("---------执行Junit测试代码结束----------");
    }


    private void init(){

    }
}
