package com.song.JavaSE.wangluobiancheng.udp;



import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Server {
    public static void main(String[] args) throws IOException {
        //创建一个 UDP 服务端套接字，并绑定到 6666 端口。
        DatagramSocket ds = new DatagramSocket(6666); // 监听6666端口
        System.out.println("server is running...");
        for (;;) {
            // 接收数据:创建一个长度为 1024 的字节数组，用来存放收到的数据。
            byte[] buffer = new byte[1024];
            //创建一个 DatagramPacket 对象，用来接收数据
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            //阻塞等待客户端发来 UDP 数据包。
            //如果没有数据到来，这一行会一直等着。
            //一旦客户端发来数据，就会存进 packet。
            ds.receive(packet);
            //把收到的字节转换成字符串命令
            String cmd = new String(packet.getData(), packet.getOffset(), packet.getLength(), StandardCharsets.UTF_8);

            // 根据命令准备响应:
            String resp = "bad command";
            switch (cmd) {
                case "date":
                    resp = LocalDate.now().toString();
                    break;
                case "time":
                    resp = LocalTime.now().withNano(0).toString();
                    break;
                case "datetime":
                    resp = LocalDateTime.now().withNano(0).toString();
                    break;
                case "weather":
                    resp = "sunny, 10~15 C.";
                    break;
            }

            System.out.println(cmd + " >>> " + resp);

            // 发送响应:
            //把响应字符串转成字节并放入 packet
            packet.setData(resp.getBytes(StandardCharsets.UTF_8));
            //发送响应数据包
            ds.send(packet);
        }
    }
}