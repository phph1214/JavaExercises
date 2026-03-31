package com.song.JavaSE.kuaisurumen;

public class foreachxunhuan {
    public static void main(String[] args) {
        int[] ns = {1, 4, 9, 16, 25};
        int sum = 0;

        for (int n : ns) {
            sum += n;
        }

        System.out.println(sum); // 55
    }
}
