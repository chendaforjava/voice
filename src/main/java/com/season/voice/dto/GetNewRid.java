package com.season.voice.dto;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 *
 * @author lihengli
 * @date 2021-04-16
 *
 */
public class GetNewRid {
    private static String localHost = null;
    public String getRid(){
        String rid = Thread.currentThread().getName();
        int len = 6;
        if(rid.length() > len){
            rid = rid.substring(rid.length()-len);
        }
        rid = getIpAddress()  + rid;
        return  rid;
    }
    private    String getIpAddress(){
        if(localHost != null){
            return localHost;
        }
        return getIpAddress1();
    }
    private     synchronized  String getIpAddress1(){
        localHost = "";
        String ip = getIpAddressTmp();
        String[] ipsz = ip.split("\\.");
        for(int i=0;i<ipsz.length;i++){
            if(i>1){
                if ("".equals(localHost)) {
                    localHost = ipsz[i].length()+"";
                }
                localHost = localHost + ipsz[i];
            }
        }
        return localHost;
    }
    private   String getIpAddressTmp() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
                    continue;
                }
                String name = netInterface.getName();
                if (name.contains("docker") || name.contains("lo") || name.contains("veth")) {
                    continue;
                }
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    ip = addresses.nextElement();
                    if (ip != null && ip instanceof Inet4Address) {
                        return ip.getHostAddress();
                    }
                }

            }
        } catch (Exception e) {  }
        return "";
    }

//    public static  void main(String[] ags){
//        Thread.currentThread().setName("1111111111111abcdef");
//        //System.out.println("rid:" + new GetNewRid().getRid());
//        String s ="requestBody=result=%7B%22msg%22%3A%22%E5%BC%A0%E4%B8%89%28110101200303074933%29%E7%9A%84%E8%BA%AB%E4%BB%BD%E4%BF%A1%E6%81%AF%E5%BE%85%E6%A0%B8%E9%AA%8C%EF%BC%8C%E6%82%A8%E5%8F%AF%E4%BB%A5%E6%8C%81%E6%9C%AC%E4%BA%BA%E8%BA%AB%E4%BB%BD%E8%AF%81%E5%88%B0%E7%81%AB%E8%BD%A6%E7%AB%99%E6%88%96%E5%94%AE%E7%A5%A8%E7%AA%97%E5%8F%A3%E5%AE%8C%E6%88%90%E8%BA%AB%E4%BB%BD%E6%A0%B8%E9%AA%8C%22%2C%22orderId%22%3A%22D60420141926577144%22%2C%22errorCode%22%3A%221004%22%2C%22completeTime%22%3A%222021-04-20+14%3A19%3A44%22%2C%22merchantOrderId%22%3A%22TDT1384391234105249792%22%2C%22status%22%3A%22FAILURE%22%7D&timestamp=20210420141945&sid=84469c337a89d0495f0be706c7495fd1";
//
//        //System.out.println(URLDecoder.decode(s));
//
//    }
}
