package com.example.demo.IPdetect;

import com.example.demo.Num;
import com.example.demo.NumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Component
public class Detect implements Runnable {
    @Autowired
    public Detect(NumMapper numMapper) {
        this.numMapper = numMapper;
    }

    NumMapper numMapper;

    @Override
    public void run() {
        while (true) {
            int alive = IpPool.aliveIP.size();
            Num num = new Num(new Date().getTime() / 1000 * 1000 + 8 * 3600000, alive);
            numMapper.save(num);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
