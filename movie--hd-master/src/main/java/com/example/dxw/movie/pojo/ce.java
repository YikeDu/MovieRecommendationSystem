package com.example.dxw.movie.pojo;

/**
 * Classname: ce
 * Package: com.example.dxw.movie.pojo
 * Description:
 *
 * @Author dxw
 * @Create 2023/2/23 15:05
 * Version 1.0
 */
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
