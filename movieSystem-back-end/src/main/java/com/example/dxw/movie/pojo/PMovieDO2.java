package com.example.dxw.movie.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
@TableName("p_movie")
@ApiModel(value="PMovieDO对象", description="")
public class PMovieDO2 {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer fz;
    private String movid;

//    @Override
//    public int compareTo(PMovieDO2 o) {
////        return this.fz-o.fz>=0?1:-1;
//        return o.fz-this.fz>=0?1:-1;
//    }
}
