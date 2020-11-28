package com.example.demo.IPdetect;

public class WorkGroup {
    IpPool workingIpPool;
    Worker[] workers;
    boolean working = true;

    public WorkGroup(IpPool workingIpPool, int workerNum) {
        this.workingIpPool = workingIpPool;
        workers = new Worker[workerNum];
        for (int i = 0; i < workerNum; i++) {
            workers[i] = new Worker(this);
        }
        for (Worker worker : workers) {
            new Thread(worker).start();
        }
    }
}
