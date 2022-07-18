package com.dahuang.service;

import com.dahuang.pojo.Employee;

import java.util.List;

/**
 * @author: 黄磊
 * @program: springboot-04-staff_system
 * @create: 2021-01-29 14:20
 */
public interface EmployeeService {
    List<Employee> getAll();

    //通过ID查询员工
    Employee getEmployeeById(int id);

    //通过ID删除员工
    int delete(int id);

    //增加一个员工
    int save(Employee employee);

    int updateEmpById(Employee employee);
}
