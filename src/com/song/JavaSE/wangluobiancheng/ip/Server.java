package com.song.JavaSE.wangluobiancheng.ip;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(6666);//监听6666端口
        System.out.println("server is running...");

        //无限循环
        for (;;) {
            //阻塞等待客户端连接，一旦有客户端连接，就会返回一个Socket对象，sock表示服务端和某一个客户端之间的连接通道
            Socket sock = ss.accept();
            //打印是谁连接进来
            System.out.println("connected from " + sock.getRemoteSocketAddress());
            //为当前客户端创建一个处理线程
            Thread t = new Handler(sock);
            //启动线程
            t.start();
        }
    }
}

class Handler extends Thread {//定义一个线程类
    private final Socket sock;

    public Handler(Socket sock) {
        this.sock = sock;
    }

    @Override
    public void run() {//重写run方法，调用t.start()后执行
        try (InputStream input = sock.getInputStream();
             OutputStream output = sock.getOutputStream()) {
            handle(input, output);//获取输入流和输出流，使用try后，代码执行完毕会自动关闭这些流
        } catch (Exception e) {//若上面通信过程中出现任何异常，就会进入catch
            try {
                sock.close();//尝试关闭当前连接
            } catch (IOException ignored) {//如果关闭时又报错了，也不再继续处理，ignored 表示这个异常被忽略。
            }
            System.out.println("client disconnected.");
        }
    }
    //用来处理和客户端之间的数据收发
    private void handle(InputStream input, OutputStream output) throws IOException {
        //把字节输出流 output 转成字符输出流
        //并指定编码为 UTF-8

        //再加一层缓冲
        //写字符串更方便、更高效
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));
        //把字节输入流转成字符输入流
        //编码是 UTF-8

        //支持按行读取
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));

        //先向客户端发送hello
        writer.write("hello\n");
        //立刻刷新缓冲区，把缓冲区数据发送给客户端
        writer.flush();

        for (;;) {
            String s = reader.readLine();
            if ("bye".equals(s)) {
                writer.write("bye\n");
                writer.flush();
                break;
            }
            writer.write("ok: " + s + "\n");
            writer.flush();
        }
    }
}