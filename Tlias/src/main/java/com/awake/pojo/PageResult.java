package com.awake.pojo;
/*
* 分页结果封装类
*
*/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {
    private Long total;//变量名不能改与前端api一致
    private List<T> rows;

}

