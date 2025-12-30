package com.awake.service;


import com.awake.pojo.JobOption;
import org.springframework.stereotype.Service;

@Service
public interface ReportService {
    /*
     * 统计员工职位人数
     * */
    JobOption getEmpJobData();
}
