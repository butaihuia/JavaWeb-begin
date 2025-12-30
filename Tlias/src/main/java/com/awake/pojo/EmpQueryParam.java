package com.awake.pojo;
//页数类

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class EmpQueryParam {
    //封装page, pageSize, name, gender, begin, end
    private Integer page = 1;
    private Integer pageSize = 10;
    private String name;
    private Integer gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin;//= LocalDate.parse("1999-01-01");
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;//= LocalDate.parse("2099-12-31");

}
