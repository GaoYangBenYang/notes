package com.gy.Dao;

import com.gy.Pojo.Employee;

public interface EmployeeMapper {

    Employee selectEmp(Integer id);

    Employee selectEmpById(Integer id);

}
