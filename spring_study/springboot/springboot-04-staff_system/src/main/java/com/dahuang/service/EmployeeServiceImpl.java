package com.dahuang.service;

import com.dahuang.mapper.EmployeeMapper;
import com.dahuang.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: 黄磊
 * @program: springboot-04-staff_system
 * @create: 2021-01-29 14:21
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;


    @Override
    public List<Employee> getAll() {
        return employeeMapper.getAll();
    }

    @Override
    public Employee getEmployeeById(int id) {
        return employeeMapper.getEmployeeById(id);
    }

    @Override
    public int delete(int id) {
        return employeeMapper.delete(id);
    }

    @Override
    public int save(Employee employee) {
        return employeeMapper.save(employee);
    }

    @Override
    public int updateEmpById(Employee employee) {
        return employeeMapper.updateEmpById(employee);
    }
}
