package com.awake.service.impl;

import com.awake.mapper.EmpExprMapper;
import com.awake.mapper.EmpMapper;
import com.awake.pojo.*;
import com.awake.service.EmpLogService;
import com.awake.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    /*
        @Autowired
        private EmpMapper empMapper;

        @Override
        public PageResult<Emp> page(Integer page, Integer pageSize) {
            //1,调用mapper接口，查询总记录数
            Long total = empMapper.count();

            //2,调用mapper接口，查询结果列表
            Integer start = (page-1)*pageSize;
            List<Emp> rows=empMapper.list(start,pageSize);

            //3，封装结果 PageResult
            return new PageResult<Emp>(total, rows);
        }
    */
    /*
     * 注意事项
     * 1，定义的sql语句中，结尾不能加分号
     * 2.仅仅对下面第一个查询语句进行分页处理
     * */
    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService empLogService;


    /*@Override
    public PageResult<Emp> page(Integer page, Integer pageSize,
                                String name , Integer gender,
                                LocalDate begin,
                                LocalDate end) {
        //1.设置分页参数(PageHelper)
        PageHelper.startPage(page, pageSize);
        //2.执行查询
        List<Emp> empList = empMapper.list( name, gender, begin, end);
        //3.解析查询结果并封装
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<>(p.getTotal(), p.getResult());
    }*/
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //1.设置分页参数(PageHelper)
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        //2.执行查询
        List<Emp> empList = empMapper.list(empQueryParam);
        //3.解析查询结果并封装
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Transactional(rollbackFor = {Exception.class})//事务管理 rollbackFor 是指定异常回滚
    @Override
    public void save(Emp emp) {
        try {
            //1.保存员工基本信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            //2.保存员工工作经历信息
            List<EmpExpr> exprList = emp.getExprList();
            if (!CollectionUtils.isEmpty(exprList)) {
                //遍历集合，为empId赋值
                exprList.forEach(empExpr -> {
                    empExpr.setEmpId(emp.getId());
                });
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            //3.记录操作日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "新增员工" + emp);
            empLogService.insertLog(empLog);
        }

    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        //1.删除员工基本信息
        empMapper.deleteByIds(ids);

        //2.删除员工工作经历信息
        empExprMapper.deleteByEmpIds(ids);

        //3.记录操作日志
        EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "删除员工" + ids);
        empLogService.insertLog(empLog);
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        //1.根据id更新员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        //2.根据id更新员工工作经历信息
        //2.1,先删除经历
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));

        //2.2,再新增经历
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            //遍历集合，为empId赋值
            exprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
            empExprMapper.insertBatch(exprList);
        }


        //3.记录操作日志
        EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "更新员工" + emp);
        empLogService.insertLog(empLog);
    }


}
