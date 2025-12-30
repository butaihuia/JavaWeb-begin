package com.awake.service.impl;

import com.awake.mapper.EmpMapper;
import com.awake.pojo.JobOption;
import com.awake.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public JobOption getEmpJobData() {
        //1,调用mapper接口，获取统计数据
        List<Map<String, Object>> list = empMapper.countEmpJobData();//map:pos=职位,count=人数


        //2,组装结果，并返回
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> countList = list.stream().map(dataMap -> dataMap.get("count")).toList();


        return new JobOption(jobList, countList);
    }
}
