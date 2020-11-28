package com.example.demo.IPdetect;

import java.util.ArrayList;
import java.util.HashSet;

public class IpPool {
    public ArrayList<String> list = new ArrayList<>();
    HashSet<String> tempList = new HashSet<>();
    String name;

    public IpPool(String name) {
        this.name = name;
    }

    synchronized public String get() {
        if (list.size() == 0) {
            return null;
        }
        String ret = list.get(0);
        list.remove(0);
        tempList.add(ret);
        return ret;
    }

    synchronized public void save(String ip) {
        if (tempList.contains(ip)) {
            tempList.remove(ip);
        } else {
            if (name.equals("aliveIP")) {
//                System.out.println("alive: "+size());
            }
            aliveIP.tempList.remove(ip);
            deadIP.tempList.remove(ip);
            violentIP.tempList.remove(ip);
        }
        list.add(ip);
    }

    synchronized public int size() {
        return list.size() + tempList.size();
    }

    public static IpPool aliveIP = new IpPool("aliveIP");
    public static IpPool deadIP = new IpPool("deadIP");
    public static IpPool violentIP = new IpPool("violentIP");

}
