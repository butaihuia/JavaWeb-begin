package com.awake.service;

import com.awake.pojo.Emp;
import com.awake.pojo.EmpQueryParam;
import com.awake.pojo.PageResult;

import java.util.List;

public interface EmpService {
    /*分页查询
     * page:当前页码
     * pageSize:每页记录数
     * */
    /*PageResult<Emp> page(Integer page, Integer pageSize,String name , Integer gender,
                        LocalDate begin,
                        LocalDate end);
    */
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    /*
     * 新增员工信息
     */

    void save(Emp emp);


    /*
     * 删除员工信息
     */
    void delete(List<Integer> ids);

    /*
     * 获取员工详情
     * */
    Emp getInfo(Integer id);

    /*
     * 更新员工
     * */
    void update(Emp emp);
}
