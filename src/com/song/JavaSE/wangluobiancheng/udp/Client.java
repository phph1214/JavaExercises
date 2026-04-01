package com.song.JavaSE.wangluobiancheng.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        DatagramSocket ds = new DatagramSocket();
        //设置接收超时时间为 1000 毫秒，也就是 1 秒
        ds.setSoTimeout(1000);
        //注意 UDP 的 connect() 不是 TCP 那种真正建立连接，
        //这里只是帮你固定默认发送目标，并且只接收这个目标返回的数据。
        ds.connect(InetAddress.getByName("localhost"), 6666);
        //先定义一个 DatagramPacket 变量，后面发送和接收都会用到它。
        DatagramPacket packet = null;
        for (int i = 0; i < 5; i++) {
            // 发送命令:
            String cmd = new String[] { "date", "time", "datetime", "weather", "hello" }[i];
            byte[] data = cmd.getBytes(StandardCharsets.UTF_8);
            packet = new DatagramPacket(data, data.length);
            ds.send(packet);

            // 接收响应:
            byte[] buffer = new byte[1024];
            packet = new DatagramPacket(buffer, buffer.length);
            ds.receive(packet);
            String resp = new String(packet.getData(), packet.getOffset(), packet.getLength(), StandardCharsets.UTF_8);

            System.out.println(cmd + " >>> " + resp);
            Thread.sleep(1500);
        }

        ds.disconnect();
        System.out.println("disconnected.");
    }
}