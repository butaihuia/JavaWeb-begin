package com.awake.mapper;
//执行sql语句

import com.awake.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {
    //查询全部部门信息

//    //方式一
//    //注意外面加-s,里面不加
//    @Results({
//            @Result(column = "create_time", property = "createTime"),
//            @Result(column = "update_time", property = "updateTime")}
//    )

//    //方式二 起别名法
//    @Select("select id, name, create_time createTime, update_time updateTime from dept order by update_time desc")

    //方式三 开启驼峰映射，自动命名 见application.yml
    @Select("select id, name, create_time, update_time from dept order by update_time desc")
    List<Dept> findAll(); //这里的 @Select 注解告诉 MyBatis，当调用 findAll() 方法时，应该执行括号内的 SQL 查询语句：

    //删除部门信息
    @Select("delete from dept where id=#{id}")
//#{}为占位符号
    void deleteById(Integer id); //这里的 @Select 注解告诉 MyBatis，当调用 deleteById() 方法时，应该执行括号内的 SQL 删除语句：
//新增部门信息
    @Select("insert into dept(name, create_time, update_time) values(#{name}, #{createTime}, #{updateTime})")
    void insert(Dept dept);

    @Select("select id, name, create_time, update_time from dept where id=#{id}")
    Dept getById(Integer id);

    @Select("update dept set name=#{name}, update_time=#{updateTime} where id=#{id}")
    void update(Dept dept);
}