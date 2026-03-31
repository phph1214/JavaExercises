package com.song.JavaSE.kuaisurumen;

import java.net.SocketOption;
import java.util.Scanner;

public class shurushuchu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入上次成绩：");
        int prev = scanner.nextInt();

        System.out.print("请输入本次成绩：");
        int score = scanner.nextInt();

        double percent = (score - prev) * 100.0 / prev;

        System.out.printf("成绩提高了%.2f%%%n", percent);
    }
}
