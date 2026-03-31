package com.song.JavaSE.kuaisurumen;

public class yuanzhoulvjisuan {
    public static void main(String[] args) {
        double pi = 0;
        for (int i = 1; i <= 100000; i += 4) {
            pi += 4.0 / i - 4.0 / (i + 2);
        }
        System.out.println(pi);
    }
}
