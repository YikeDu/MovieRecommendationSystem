package com.example.dxw.movie.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sim_user")
@ApiModel(value="SimUserDO对象", description="")
public class SimUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String simUser;

    private Long userId;


}
