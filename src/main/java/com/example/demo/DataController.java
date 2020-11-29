package com.example.demo;

import com.example.demo.IPdetect.Detect;
import com.example.demo.IPdetect.GenList;
import com.example.demo.IPdetect.IpPool;
import com.example.demo.IPdetect.WorkGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class DataController {
    @RequestMapping("/data")
    @CrossOrigin
    ArrayList<ArrayList<Object>> getData() {
        ArrayList<ArrayList<Object>> ret = new ArrayList<>();

        for (Num num : numMapper.findAll()) {
            ArrayList<Object> oneItem = new ArrayList<>();
            oneItem.add(num.time);
            oneItem.add(num.num);
            ret.add(oneItem);
        }
        return ret;
    }

    NumMapper numMapper;
    Detect detect;

    @Autowired
    public DataController(NumMapper numMapper, Detect detect) {
        this.numMapper = numMapper;
        this.detect = detect;
    }


}
