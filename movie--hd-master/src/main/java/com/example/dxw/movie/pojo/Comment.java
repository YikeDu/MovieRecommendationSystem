package com.example.dxw.movie.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper = false)
public class Comment implements Serializable {

    static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 电影唯一标识
     */
    private String mid;
    private String uid;

    /**
     * 评论人工号
     */
    private String cid;

    /**
     * 评论的时间字符串
     */
    private String cTimeStr;

    /**
     * 评论内容
     */
    private String cStr;


}
