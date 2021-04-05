package com.webApp.webAppBE.Controllers;

import com.webApp.webAppBE.Models.Employee;
import com.webApp.webAppBE.Models.EmployeeDTO;
import com.webApp.webAppBE.Services.EmployeeService;
import com.webApp.webAppBE.Services.TaskService;
import com.webApp.webAppBE.Services.UploadService;
import com.webApp.webAppBE.Tasks.TaskEnum;
import com.webApp.webAppBE.Tasks.TaskInfo;
import com.webApp.webAppBE.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmployeeControllerTest {
    private EmployeeController employeeController;
    private TaskService taskService = mock(TaskService.class);
    private UploadService uploadService = mock(UploadService.class);
    private EmployeeService employeeService = mock(EmployeeService.class);

    @Before
    public void setUp() {
        employeeController = new EmployeeController();
        TestUtils.injectObject(employeeController, "taskService", taskService);
        TestUtils.injectObject(employeeController, "uploadService", uploadService);
        TestUtils.injectObject(employeeController, "employeeService", employeeService);
    }


    @Test
    public void should_get_status() {
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setTaskId("test");
        taskInfo.setStatus(TaskEnum.SUCCESS);
        when(taskService.getTaskById("test")).thenReturn(taskInfo);
        ResponseEntity responseEntity = employeeController.getStatus("test");
        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    public void should_get_employee() {
        Employee employee = new Employee();
        employee.setEmployeeName("test-name");
        when(employeeService.getEmployeeById((long) 1)).thenReturn(employee);
        ResponseEntity<Employee> responseEntity = employeeController.getEmployee((long) 1);
        assertEquals("test-name", responseEntity.getBody().getEmployeeName());
    }

    @Test
    public void should_get_all_employees() {
        Employee[] employees = new Employee[2];
        when(employeeService.getAllEmployees()).thenReturn(employees);
        ResponseEntity<Employee[]> responseEntity = employeeController.getALLEmployees();
        assertEquals(2, responseEntity.getBody().length);
    }

    @Test
    public void should_add_new_employee() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeName("test");
        employeeDTO.setEmployeeAge(12);
        Employee em = new Employee();
        em.setEmployeeName("test");
        when(employeeService.addEmployee(employeeDTO.getEmployeeName(), employeeDTO.getEmployeeAge())).thenReturn(em);
        ResponseEntity<Employee> responseEntity = employeeController.addEmployee(employeeDTO);
        assertEquals("test", responseEntity.getBody().getEmployeeName());
    }

    @Test
    public void should_update_employee() {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeName("test");
        employeeDTO.setEmployeeAge(12);
        Employee em = new Employee();
        em.setEmployeeName("test");
        when(employeeService.updateEmployee((long) 1, employeeDTO.getEmployeeName(), employeeDTO.getEmployeeAge())).thenReturn(em);
        ResponseEntity<Employee> responseEntity = employeeController.updateEmployee((long)1,employeeDTO);
        assertEquals("test", responseEntity.getBody().getEmployeeName());
    }

    @Test
    public void should_delete_employee() {
        ResponseEntity responseEntity = employeeController.deleteEmployee((long) 1);
        assertEquals("Success", responseEntity.getBody());
    }


}
