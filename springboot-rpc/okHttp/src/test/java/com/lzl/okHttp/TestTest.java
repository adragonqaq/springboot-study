package com.lzl.okHttp;

import com.alibaba.fastjson2.JSON;
import com.lzl.okHttp.util.GsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@Slf4j
class TestTest {

    @Autowired
    @Qualifier("privilegesThreadPoolExecutor")
    private ThreadPoolTaskExecutor privilegesThreadPoolExecutor;


    @Test
    public void testDeleteByJson(){

        for(int i = 0; i<10; i++){
//            batchSales();
            test();
//            batchUsage(result.getSuccessList());
        }
    }


    public  void test(){

        List<String> details = new ArrayList<>();
        details.add("1");
        details.add("2");
        List<CompletableFuture<List<User>>> futureList = new ArrayList<>();
        for(String detail: details){
            CompletableFuture<List<User>> future = CompletableFuture.supplyAsync(() -> {

                        List<User> result = new ArrayList<>();
                        User user = new User(null, detail);
                        result.add(user);

                        try {
                            TimeUnit.MILLISECONDS.sleep(100L);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        return result;
                    },privilegesThreadPoolExecutor)
                    .exceptionally(ex-> {
                        log.error("失败，失败原因：{}",ex.getMessage());
                        return Collections.emptyList();
                    });
            futureList.add(future);
        }


        List<User> list = waitAndGetData(futureList);

        log.info("购买结果：{}", GsonUtils.toJson(list));

        for(User user : list){


            System.out.println(GsonUtils.toJson(user));
        }
    }




    public  SaleResult batchSales(){

        SaleResult result = new SaleResult();
        List<String> saleResult = new ArrayList<>();

        List<String> details = new ArrayList<>();
        details.add("1");
        details.add("2");
        List<CompletableFuture> futureList = new ArrayList<>();
        for(String detail: details){
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                        try {
                            TimeUnit.MILLISECONDS.sleep(100L);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
//                        saleResult.add(detail);
                        log.info(detail);
                        return detail;
                    },privilegesThreadPoolExecutor)
                    .exceptionally(ex-> {
                        log.error("失败，失败原因：{}",ex.getMessage());
                        return null;
                    });
            futureList.add(future);
        }


//        CompletableFuture.allOf(futureList.get(0),futureList.get(1)).join();
//        List<String> list = waitAndGetData(futureList);

//        log.info("购买结果：{}",JSON.toJSONString(list));
        return result;
    }


    public  void batchUsage(List<String> saleResult){


        List<CompletableFuture> futureList = new ArrayList<>();
        for(String detail: saleResult){
            CompletableFuture future = CompletableFuture.runAsync(() -> {
                        log.info("购买的值：{}",detail);
                    },privilegesThreadPoolExecutor)
                    .exceptionally(ex-> {
                        log.error("失败，失败原因：{}",ex.getMessage());
                        return null;
                    });
            futureList.add(future);
        }
        waitFuture(futureList);

        log.info("使用结束");
    }

    /**
     * 阻塞任务
     * @param futureList
     */
    public static void waitFuture(List<CompletableFuture> futureList){
        for(CompletableFuture future:futureList){
            try {
                future.get();
            } catch (Exception e) {
                log.error("CompletableFuture异常",e);
            }
        }
    }

    public static List<String> waitFuture2(List<CompletableFuture> futureList){

        List<String> result = new ArrayList<>();
        for(CompletableFuture future:futureList){
            try {
                String o = ((String) future.get());
                result.add(o);
            } catch (Exception e) {
                log.error("CompletableFuture异常",e);
            }
        }
        return result;
    }

    /**
     * 阻塞任务
     * @param futureList
     */
    public List<User> waitAndGetData(List<CompletableFuture<List<User>>> futureList){

        List<User> result = new ArrayList<>();
        for(CompletableFuture<List<User>> future:futureList){
            try {
                List<User> o = future.get();
                result.addAll(o);
            } catch (Exception e) {
                log.error("CompletableFuture异常",e);
            }
        }
        return result;
    }
}