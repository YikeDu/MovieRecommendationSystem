package com.example.dxw.movie.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
@TableName("ratings_norm")
@ApiModel(value="RatingsNormDO对象", description="")
public class RatingsNormDO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long movieId;

    private Double rating;

    private Long userId;


}
