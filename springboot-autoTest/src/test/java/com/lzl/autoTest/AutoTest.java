package com.lzl.autoTest;

import com.lzl.autoTest.base.SpringBootTestBase;
import org.junit.Test;

/**
 * @author eren.liao
 * @date 2022/1/25 10:39
 */
public class AutoTest extends SpringBootTestBase {


    @Test
    public void createOrder(){
        System.out.println("第一次测试");
    }



    @Test
    public void createOrder2(){
        System.out.println("第二次测试");
    }

}
