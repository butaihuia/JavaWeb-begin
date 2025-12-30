package com.awake.pojo;
//图表数据选项

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOption {

    private List jobList;
    private List dataList;

}
