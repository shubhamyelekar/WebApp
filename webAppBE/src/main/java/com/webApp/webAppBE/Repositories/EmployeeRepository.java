package com.webApp.webAppBE.Repositories;


import com.webApp.webAppBE.Models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    public Employee findByEmployeeName(String employeeName);

}
