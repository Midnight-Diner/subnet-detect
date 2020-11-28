package com.example.demo.IPdetect;

import java.net.InetAddress;

public class Worker implements Runnable{
    public Worker(WorkGroup workGroup) {
        this.workGroup = workGroup;
    }

    WorkGroup workGroup;

    @Override
    public void run() {
        while (workGroup.working){
            try {
                IpPool workingIpPool=workGroup.workingIpPool;
                String ip=workingIpPool.get();
                if(ip==null){
                    Thread.sleep(1000);
                    continue;
                }
                InetAddress address = InetAddress.getByName(ip);
                boolean reachable = address.isReachable(1000);

                if(reachable){
                    IpPool.aliveIP.save(ip);
//                    System.out.println(ip+"is alive");
                }else {
                    if(workingIpPool.name.equals("deadIP")){
                        IpPool.deadIP.save(ip);
                    }else {
                        IpPool.violentIP.save(ip);
                    }
                }
            }catch (Exception exception){
                exception.printStackTrace();
            }
        }
    }
}
