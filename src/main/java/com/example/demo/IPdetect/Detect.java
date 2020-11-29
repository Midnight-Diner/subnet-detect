package com.example.demo.IPdetect;

import com.example.demo.Num;
import com.example.demo.NumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Component
@PropertySource("classpath:net.properties")
public class Detect implements Runnable {

    @Autowired
    public Detect(NumMapper numMapper,@Value("${network}") String net,@Value("${NetworkPrefix}") int networkPrefix) {
        this.numMapper = numMapper;
        List<String> IPlist = new GenList().gen(net, networkPrefix);
        IpPool.deadIP.list.addAll(IPlist);
        WorkGroup aliveGroup = new WorkGroup(IpPool.aliveIP, 10);
        WorkGroup deadGroup = new WorkGroup(IpPool.deadIP, 100);
        WorkGroup violentGroup = new WorkGroup(IpPool.violentIP, 10);
        Thread detectThread = new Thread(this);
        detectThread.start();
    }

    NumMapper numMapper;
    private static final int TIME_GAP = 1000;
    @Override
    public void run() {
        while (true) {
            int alive = IpPool.aliveIP.size();
            Num num = new Num(new Date().getTime() / TIME_GAP * TIME_GAP + 8 * 3600000, alive);
            numMapper.save(num);
            try {
                Thread.sleep(TIME_GAP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
