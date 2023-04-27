package com.example.dxw.movie.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor//传出去的第一次vo
public class MovieStar implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String star;
    private String cid;

    private String datas;

    private String mid;

    private String movie;

    private String year;

    private String runtime;

    @TableField("imageSrc1")
    private String imagesrc1;

    @TableField("imageSrc2")
    private String imagesrc2;

    private String jj;

    private String language;

    private String title;

    private String name;

    @TableField("voteCount")
    private String votecount;

    @TableField("mtypeLis")
    private String mtypelis;

}
