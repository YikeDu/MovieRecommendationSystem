package com.example.dxw.movie.pojo;


public class ce {
    public static void main(String[] args) {
        String ss="53,10749,9648,";
        String[] split = ss.split(",");
        System.out.println("split = " + split.length);
        for (String s : split) {
            System.out.println("s = " + s);
        }
    }
}
