package com.lzl.okHttp;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@Slf4j
public class Test {


    public static void main(String[] args) {


        SaleResult result = batchSales();

        batchUsage(result.getSuccessList());

    }

    public static SaleResult batchSales(){

        SaleResult result = new SaleResult();
        List<String> saleResult = new ArrayList<>();

        List<String> details = new ArrayList<>();
        details.add("1");
        details.add("2");
        List<CompletableFuture> futureList = new ArrayList<>();
        for(String detail: details){
            CompletableFuture future = CompletableFuture.runAsync(() -> {
               saleResult.add(detail);
            })
            .exceptionally(ex-> {
                log.error("失败，失败原因：{}",ex.getMessage());
                return null;
            });
            futureList.add(future);
        }

        waitFuture(futureList);
        result.setSuccessList(saleResult);
        return result;
    }


    public static void batchUsage(List<String> saleResult){


        List<CompletableFuture> futureList = new ArrayList<>();
        for(String detail: saleResult){
            CompletableFuture future = CompletableFuture.runAsync(() -> {
                log.info("购买的值：{}",detail);
            })
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
}
