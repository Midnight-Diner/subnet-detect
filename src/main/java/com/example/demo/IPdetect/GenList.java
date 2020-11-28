package com.example.demo.IPdetect;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class GenList {
    /**
     * @param net          "192.168.1.1"
     * @param subNetLength 24
     */
    public List<String> gen(String net, int subNetLength) {
        long base1 = 0b0000000000000000000000000000000011111111111111111111111111111111L;
        long ip = IpTolong(net);
        long num0 = 32 - subNetLength;
        long mask = (base1 >> num0) << num0;
        long ipStart = ip & mask;
        long ipEnd = (ip | (~mask)) & base1;
        ArrayList<String> list = new ArrayList<>();
        for (long i = ipStart; i <= ipEnd; i++) {
            list.add(longToIp(i));
        }
        return list;
    }

    public String longToIp(long ip) {
        String binStr = Long.toBinaryString(ip);
        while (binStr.length() < 32) {
            binStr = "0" + binStr;
        }
        String strOut = "";
        for (int i = 0; i < 32; i += 8) {
            strOut = strOut + Long.parseLong(binStr.substring(i, i + 8), 2) + ".";
        }
        strOut = strOut.substring(0, strOut.length() - 1);
        return strOut;
    }

    public long IpTolong(String IPStr) {
        long ip = 0;
        String[] strs = IPStr.split("\\.");
        for (int i = 0; i < strs.length; i++) {
            ip <<= 8;
            ip += Long.parseLong(strs[i]);
        }
        return ip;
    }

    public void writeToFile(List<String> list) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter("ip.txt");
        for (String s : list) {
            printWriter.println(s);
        }
        printWriter.flush();
    }
}
