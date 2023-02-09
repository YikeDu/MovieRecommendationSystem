package com.example.dxw.movie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResBean {
    private long code;
    private String message;
    private Object obj;

    public static ResBean success(String message){
        return new ResBean(200,message,null);
    }

    public static ResBean success(String message,Object obj){
        return new ResBean(200,message,obj);
    }

    public static ResBean error(String message){
        return new ResBean(500,message,null);
    }

    public static ResBean error(String message,Object obj){
        return new ResBean(500,message,obj);
    }
}
