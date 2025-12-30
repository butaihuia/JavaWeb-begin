package com.awake.mapper;

import com.awake.pojo.Emp;
import com.awake.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;


import java.util.List;
import java.util.Map;

//员工基本信息的接口
@Mapper
public interface EmpMapper {
    /*
    //查询总记录数
    @Select("select count(*) from emp e left join dept d on e.dept_id = d.id;")
    public Long count();
    //分页查询
    @Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id "
        + "order by e.update_time desc limit #{start}, #{pageSize};")
//    public List<Emp> page(Integer start, Integer pageSize);
    public List<Emp> list(Integer start, Integer pageSize);
    ---------------------------原始分页查询方式---------------------------------------
*/

//    @Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id "
//            + "order by e.update_time desc")

//    public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);

    /*
     * 条件查询员工信息
     * */

    List<Emp> list(EmpQueryParam empQueryParam);


    /*
     *新增员工基本信息
     * */
    //数据库自动增长的id是不会在emp这个对象里面的，
    // 通过注解可以获取数据库里面自动增长的id，
    // 这样就可以获取对象里的id然后再关联工作经历
    @Options(useGeneratedKeys = true, keyProperty = "id")//获取到生成的主键，主键返回
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)" +
            " values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime});")
    void insert(Emp emp);

    /*
     * 根据员工ID删除员工基本信息
     * */
    void deleteByIds(List<Integer> ids);

    /*
     * 根据员工ID查询员工详情
     * */
    Emp getById(Integer id);

    /*
     * 根据员工ID更新员工基本信息
     * */
    void updateById(Emp emp);


    /*
     * 统计员工职位人数
     * */
    @MapKey("pos")
    List<Map<String, Object>> countEmpJobData();
}
