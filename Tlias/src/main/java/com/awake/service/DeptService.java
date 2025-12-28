package com.awake.service;
//调用接口
import com.awake.pojo.Dept;

import java.util.List;

public interface DeptService {
    // 查询全部部门信息
    List<Dept> findAll();// 查询全部部门信息

    //根据id删除部门
    void deleteById(Integer id);

    //添加部门
    void add(Dept dept);

    Dept getById(Integer id);

    void update(Dept dept);
}
