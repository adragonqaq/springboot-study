package com.lzl.ribbon.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.MyDataCenterInstanceConfig;
import com.netflix.client.AbstractLoadBalancerAwareClient;
import com.netflix.client.ClientFactory;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.config.ConfigurationManager;
import com.netflix.discovery.DefaultEurekaClientConfig;
import com.netflix.discovery.DiscoveryManager;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import com.netflix.loadbalancer.DynamicServerListLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.ZoneAwareLoadBalancer;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * Ribbon是Netflix发布的开源项目，主要功能是提供客户端的软件负载均衡算法，
 * 将Netflix的中间层服务连接在一起。
 * Ribbon客户端组件提供一系列完善的配置项如连接超时，重试
 *
 *
 * 来自
 * https://blog.csdn.net/zero__007/article/details/83715536
 *
 *
 */
public class Test {


    public static void main(String[] args) throws Exception {
        test1();
//        useEureka();
//        useEureka2();
    }


    /**
     *
     * 如果是非Spring cloud项目，想使用netflix ribbon可以采用上述方式，配置一下已有的服务的列表到listOfServers即可
     *
     * @throws Exception
     */
    public static void test1() throws Exception {

        //加载ribbon配置文件
        ConfigurationManager.loadPropertiesFromResources("ribbon.properties");

        //通过ClientFactory获取指定名称client
        AbstractLoadBalancerAwareClient client = (AbstractLoadBalancerAwareClient) ClientFactory.getNamedClient("sample-client");
        System.out.println("client is " + client.getClass());

        // 获取LoadBalancer
        ILoadBalancer lb = client.getLoadBalancer();
        System.out.println("lb is " + lb.getClass());
        System.out.println("lb stats: " + ((ZoneAwareLoadBalancer) lb).getLoadBalancerStats());
        System.out.println("listOfServers: " + lb.getAllServers());

        //构建请求
        HttpRequest request = HttpRequest.newBuilder().uri(new URI("/")).build();
        for (int i = 0; i < 5; i++) {
            //执行请求, 调用的服务会在listOfServers列表中的数据切换
            HttpResponse response = (HttpResponse) client.executeWithLoadBalancer(request);
            System.out.println("Status code for " + response.getRequestedURI() + ": " + response.getStatus());
        }

        // 修改可用服务列表
        ConfigurationManager.getConfigInstance().setProperty("sample-client.ribbon.listOfServers",
                "www.qq.com:80");
        Thread.sleep(3000);
        for (int i = 0; i < 2; i++) {
            HttpResponse response = (HttpResponse) client.executeWithLoadBalancer(request);
            System.out.println("Status code for " + response.getRequestedURI() + ": " + response.getStatus());
        }
    }


    /**
     *
     *
     * 如果服务列表已经在Eureka注册了的，那么如何做呢？
     *   首先，需要有一个EurekaClient，向Eureka发送请求，获取服务列表。其次，ServerList不能是ConfigurationBasedServerList，应该是DiscoveryEnabledNIWSServerList，这个就是从Eureka上获取ServerList。如何来实现呢？

     * 这里特别要注意的是配置DeploymentContextBasedVipAddresses，
     * 必须是service1服务在Eureka注册的服务名称，不然DiscoveryEnabledNIWSServerList会找不到服务列表
     * @throws Exception
     */
    public static void useEureka() throws Exception {
        //加载ribbon配置文件
        ConfigurationManager.loadPropertiesFromResources("ribbon.properties2");

        // 最关键的代码，加载配置文件，向Eureka发送请求，获取服务列表。
        DiscoveryManager.getInstance().initComponent(
                new MyDataCenterInstanceConfig(),
                new DefaultEurekaClientConfig());

        //ClientFactory.getNamedLoadBalancer会缓存结果, 所以不用担心它每次都会向eureka发起查询
        //参数service1，是ribbon.properties配置文件中配置项的前缀名
        DynamicServerListLoadBalancer lb =
                (DynamicServerListLoadBalancer) ClientFactory.getNamedLoadBalancer("service1");
        System.out.println(lb.getReachableServers());
        System.out.println("choose : " + new RoundRobinRule().choose(lb, null));
    }


    /**
     * 想查看在Eureka的所有服务可以这样
     * @throws Exception
     */
    public static void useEureka2() throws Exception {
        //加载ribbon配置文件
        ConfigurationManager.loadPropertiesFromResources("ribbon.properties2");

// 最关键的代码，加载配置文件，向Eureka发送请求，获取服务列表。
        DiscoveryManager.getInstance().initComponent(
                new MyDataCenterInstanceConfig(),
                new DefaultEurekaClientConfig());

// 在Eureka上设置为UP
// ApplicationInfoManager.getInstance().setInstanceStatus(InstanceInfo.InstanceStatus.UP);
        EurekaClient eurekaClient = DiscoveryManager.getInstance().getEurekaClient();
//获取从Eureka获取的全部的应用列表
        Applications apps = eurekaClient.getApplications();
        for (Application app: apps.getRegisteredApplications()) {
            System.out.println(app.getName());
            List<String> urls = new ArrayList<>();
            List<InstanceInfo> instances = app.getInstances();
            if (instances.size() > 0) {
                //获取其中一个应用实例，这里可以添加路由算法
                InstanceInfo instance = instances.get(0);
                //获取公开的地址和端口
                urls.add("http://" + instance.getIPAddr() + ":" + instance.getPort());
            }
            System.out.println(urls);
        }
    }



}
