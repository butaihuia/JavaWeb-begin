package com.awake.controller;
//接受前端传回来的信息

import com.awake.pojo.Dept;
import com.awake.pojo.Result;
import com.awake.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")//重复路径合并到RequestMapping后面
@RestController//表示当前是一个请求处理类
public class DeptController {
//不管那个项目都是固定这个日志代码所以有个注解,@Slf4j 来生成这个代码
//    private static final Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

    //@RequestMapping(value = "/depts",method = RequestMethod.GET)//表示当前方法处理的是哪个请求
    @GetMapping//@RequestMapping的衍生注解，与上面一致发送GET请求
    public Result list() {
        //查询操作
//        System.out.println("查询全部的部门信息");
        log.info("查询全部的部门信息");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    //    //第二种方法
//    @DeleteMapping("/dept")
//    public Result delete (@RequestParam(value = "id",required = false) Integer deptId){
//        System.out.println("删除部门信息");
//        deptService.delete(deptId);
//        return Result.success();
//    }
//第二种优化版、
    @DeleteMapping
    public Result delete(@RequestParam("id") Integer id) {
//        System.out.println("删除部门信息 "+ id);
        log.info("删除部门信息 {}", id);
        deptService.deleteById(id);
        return Result.success();
    }

    @PostMapping
    public Result add(@RequestBody Dept dept) {//接受返回的body json数据
//        System.out.println("添加部门信息 "+ dept);
        log.info("添加部门信息 {}", dept);
        deptService.add(dept);
        return Result.success();
    }

    //    //第一种方法
//    @GetMapping("/depts/{id}")
//    public Result getInfo(@PathVariable("id") Integer deptId){
//        System.out.println("根据id查询部门信息 "+ deptId);
//        Dept dept = deptService.getById(deptId);
//        return Result.success(dept);
//    }
    //查询回显
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {//省略后要与路径参数名一致
//        System.out.println("根据id查询部门信息 "+ id);
        log.info("根据id查询部门信息 {}", id);
        Dept dept = deptService.getById(id);//deptService调用service方法 返回值给结果result返回前端
        return Result.success(dept);
    }

    //    修改部门
    @PutMapping
    public Result update(@RequestBody Dept dept) {//@RequestBody接受返回的body封装成json格式传给dept
//        System.out.println("修改部门信息 "+ dept);
        log.info("修改部门信息 {}", dept);
        deptService.update(dept);
        return Result.success();
    }
}

//@RestController//表示当前是一个请求处理类
//public class DeptController {
//
//    @Autowired
//    private DeptService deptService;
//    //@RequestMapping(value = "/depts",method = RequestMethod.GET)//表示当前方法处理的是哪个请求
//    @GetMapping("/depts")//@RequestMapping的衍生注解，与上面一致发送GET请求
//    public Result list(){
//        //查询操作
//        System.out.println("查询全部的部门信息");
//        List<Dept> deptList = deptService.findAll();
//        return Result.success(deptList);
//    }
//    //    //第二种方法
/// /    @DeleteMapping("/dept")
/// /    public Result delete (@RequestParam(value = "id",required = false) Integer deptId){
/// /        System.out.println("删除部门信息");
/// /        deptService.delete(deptId);
/// /        return Result.success();
/// /    }
/// /第二种优化版、
//    @DeleteMapping("/depts")
//    public Result delete(@RequestParam("id") Integer id){
//        System.out.println("删除部门信息 "+ id);
//        deptService.deleteById(id);
//        return Result.success();
//    }
//
//    @PostMapping("/depts")
//    public Result add(@RequestBody Dept dept){//接受返回的body json数据
//        System.out.println("添加部门信息 "+ dept);
//        deptService.add(dept);
//        return Result.success();
//    }
//
/// /    //第一种方法
/// /    @GetMapping("/depts/{id}")
/// /    public Result getInfo(@PathVariable("id") Integer deptId){
/// /        System.out.println("根据id查询部门信息 "+ deptId);
/// /        Dept dept = deptService.getById(deptId);
/// /        return Result.success(dept);
/// /    }
//
//    //查询回显
//    @GetMapping("/depts/{id}")
//    public Result getInfo(@PathVariable Integer id){//省略后要与路径参数名一致
//        System.out.println("根据id查询部门信息 "+ id);
//        Dept dept = deptService.getById(id);//deptService调用service方法 返回值给结果result返回前端
//        return Result.success(dept);
//    }
//
//    //    修改部门
//    @PutMapping("/depts")
//    public Result update(@RequestBody Dept dept){//@RequestBody接受返回的body封装成json格式传给dept
//        System.out.println("修改部门信息 "+ dept);
//        deptService.update(dept);
//        return Result.success();
//    }
//}
