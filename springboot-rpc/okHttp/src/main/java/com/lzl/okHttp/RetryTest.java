package com.lzl.okHttp;

import com.github.rholder.retry.Retryer;
import com.lzl.okHttp.util.CustomRetryerBuilder;
import com.lzl.okHttp.util.RetryerHandler;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ExecutionException;


@Slf4j
public class RetryTest {


    public static void main(String[] args) {

        test3();
    }


    public static void test3(){

        BigDecimal totalAmt = new BigDecimal("111");

        BigDecimal multiply = totalAmt.multiply(new BigDecimal("9")).setScale(2);

        System.out.println(multiply);


    }
    public static void test2(){

        RetryerHandler retryerHandler = new RetryerHandler();

        CustomRetryerBuilder customRetryerBuilder = new CustomRetryerBuilder();


        Retryer retryer = customRetryerBuilder.testRetryBuild();
        try {
            retryerHandler.retry(retryer,()->{

                log.info("进来了");

                return false;

            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void test1(){

        CustomRetryerBuilder customRetryerBuilder = new CustomRetryerBuilder();


        Retryer retryer = customRetryerBuilder.testRetryBuild();

        try {

            retryer.call(()->{

                log.info("进来了");
                throw new RuntimeException("异常了");
//                return false;
            });

        }catch (Exception e){

            log.error("异常报错",e);
        }

    }
}
