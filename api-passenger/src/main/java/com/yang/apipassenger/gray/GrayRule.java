package com.yang.apipassenger.gray;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GrayRule extends AbstractLoadBalancerRule {
    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {

    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(),key);
    }

    public Server choose(ILoadBalancer lb,Object key){

        System.out.println("灰度 rule");
        Server server=null;
        while (server==null){
            //获取所有可用服务
            List<Server> reachableServers = lb.getReachableServers();

            //获取当前线程的参数
            Map<String,String> map = RibbonParameters.get();
            String version="";
            String userId="";
            if (map!=null && map.containsKey("version")){
                //获取用户对应的版本号
                version=map.get("version");
            }
            System.out.println("当前rule version:"+version);
            //根据用户选服务
            String finalVersion = version;
            for (int i = 0; i < reachableServers.size(); i++) {
                server=reachableServers.get(i);
                //用户的verison知道，但是服务的自定义meta不知道
                Map<String, String> metadata = ((DiscoveryEnabledServer) server).getInstanceInfo().getMetadata();
                if (metadata!=null && metadata.containsKey("version")){
                    String version1 = metadata.get("version");
                    if (Objects.equals(version1, finalVersion)){
                        break;
                    }
                }
            }
            //TODO 怎么让server取到合适的值？？？
        }

        return server;
    }
}
