package com.example.dxw.movie.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper = false)
public class Movies2 implements Serializable {
    Integer id;
    String movieid;
    String year;
    String runtime;
    String imageSrc1;
    String imageSrc2;
    String jj;
    String language;
    String title;
    String name;
    String mtypeLis;
    String voteCount;
    String tj;
    String xs;
    String hearf;
    String comment;

}
