package com.awake.mapper;

import com.awake.pojo.EmpExpr;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
//员工工作经历接口

@Mapper
public interface EmpExprMapper {

    //批量保存员工工作经历
    void insertBatch(List<EmpExpr> exprList);

    //根据员工ID删除员工工作经历
    void deleteByEmpIds(List<Integer> empIds);
}
