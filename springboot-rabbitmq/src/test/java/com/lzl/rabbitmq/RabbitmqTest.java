package com.lzl.rabbitmq;

import com.lzl.rabbitmq.producter.Producter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqTest {

    @Autowired
    private Producter producter;

    @Test
    public void test(){
        producter.send();
    }
}
