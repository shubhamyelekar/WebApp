package com.webApp.webAppBE.Services;

import com.webApp.webAppBE.Models.Employee;
import com.webApp.webAppBE.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;


    public Employee addEmployeeFromFile(String name, int age) {
        Employee em = new Employee();
        em.setEmployeeName(name);
        em.setEmployeeAge(age);
        return employeeRepository.save(em);
    }

    public boolean checkIfEmployeeExists(String employeeName) {
        return employeeRepository.findByEmployeeName(employeeName) != null;
    }

    public Employee addEmployee(String employeeName, int employeeAge) {
        Employee employee = employeeRepository.findByEmployeeName(employeeName);
        if (employee != null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Employee with same name alreday exists");
        }
        Employee em = new Employee();
        em.setEmployeeName(employeeName);
        em.setEmployeeAge(employeeAge);
        return employeeRepository.save(em);
    }

    public Employee getEmployeeById(Long employeeId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (!employeeOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Employee with given id doesn't exist");
        }
        return employeeOptional.get();
    }

    public Employee[] getAllEmployees() {
        return employeeRepository.findAll().toArray(new Employee[0]);
    }

    public Employee updateEmployee(Long employeeId, String employeeName, int employeeAge) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (!employeeOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Employee with given id doesn't exist");
        }
        Employee employee = employeeOptional.get();
        employee.setEmployeeName(employeeName);
        employee.setEmployeeAge(employeeAge);
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long employeeId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (!employeeOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Employee with given id doesn't exist");
        }
        employeeRepository.delete(employeeOptional.get());
    }


}
