package com.awake.controller;
//员工管理的Controller层,与工作经验外连接

import com.awake.pojo.Emp;
import com.awake.pojo.EmpQueryParam;
import com.awake.pojo.PageResult;
import com.awake.pojo.Result;
import com.awake.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {
    @Autowired
    private EmpService empService;

    //分页查询
    /*@GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10")  Integer pageSize,
                       String name , Integer gender,
                       @DateTimeFormat(pattern = "yyyy/MM/dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy/MM/dd") LocalDate end) {
        log.info("分页查询,当前页码{},每页记录数{},{},{},{},{}",page,pageSize,name,gender,begin,end); // Log the parameters
        PageResult<Emp> pageResult = empService.page(page, pageSize, name, gender, begin, end);
        return Result.success(pageResult);
    }*/
    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        log.info("分页查询,{}", empQueryParam); // Log the parameters
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("保存员工,{}", emp);
        empService.save(emp);
        return Result.success();
    }

    //删除员工-数组
/*
    @DeleteMapping
    public Result delete(Integer[] ids) {
        log.info("删除员工,{}", Arrays.toString(ids));
//        empService.delete(id);
        return Result.success();
    }
*/


    /*
     * 删除员工-List
     * */
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("删除员工,{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    /*
     * 获取员工详情
     * */
    @GetMapping("/{id}")//路径参数绑定给id 加上PathVariable注解
    public Result getInfo(@PathVariable Integer id) {
        log.info("获取员工详情,{}", id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    /*
     * 更新员工
     * */
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("更新员工,{}", emp);
        empService.update(emp);
        return Result.success();
    }

}
