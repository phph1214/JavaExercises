package com.song.JavaSE.kuaisurumen;

public class zifuchuanyinyong {
    public static void main(String[] args) {
        String s = "hello";
        String t = s;
        s = "world";//此处仅改变了t的指向
        System.out.println(t); // t是"hello"还是"world"?
    }
}
