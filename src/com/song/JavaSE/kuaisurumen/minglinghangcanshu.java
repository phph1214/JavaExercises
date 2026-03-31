package com.song.JavaSE.kuaisurumen;



/*
    javac ./minglinghangcanshu.java
    java com.song.JavaSE.kuaisurumen.minglinghangcanshu -version
 */
public class minglinghangcanshu {
    public static void main(String[] args) {
        for (String arg : args) {
            if ("-version".equals(arg)) {
                System.out.println("v 1.0");
                break;
            }
        }
    }
}
