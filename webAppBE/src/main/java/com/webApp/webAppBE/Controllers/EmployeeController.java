package com.webApp.webAppBE.Controllers;

import com.webApp.webAppBE.Models.Employee;
import com.webApp.webAppBE.Models.EmployeeDTO;
import com.webApp.webAppBE.Services.EmployeeService;
import com.webApp.webAppBE.Services.TaskService;
import com.webApp.webAppBE.Services.UploadService;
import com.webApp.webAppBE.Tasks.TaskEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private TaskService taskService;

    @Autowired
    private UploadService uploadService;

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String taskId = taskService.addTask();
        uploadService.processFile(file.getBytes(), taskId);
        return ResponseEntity.ok(taskId);
    }


    @GetMapping("/getStatus/{taskID}")
    public ResponseEntity<TaskEnum> getStatus(@PathVariable String taskID) {
        return ResponseEntity.ok(taskService.getTaskById(taskID).getStatus());
    }

    @GetMapping("/getEmployee/{employeeId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(employeeService.getEmployeeById(employeeId));
    }

    @GetMapping("/getALLEmployees")
    public ResponseEntity<Employee[]> getALLEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping("/addEmployee")
    public ResponseEntity<Employee> addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee em = employeeService.addEmployee(employeeDTO.getEmployeeName(), employeeDTO.getEmployeeAge());
        return ResponseEntity.ok(em);
    }

    @PutMapping("/updateEmployee/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long employeeId, @RequestBody EmployeeDTO employeeDTO) {
        Employee em = employeeService.updateEmployee(employeeId, employeeDTO.getEmployeeName(), employeeDTO.getEmployeeAge());
        return ResponseEntity.ok(em);
    }

    @DeleteMapping("/deleteEmployee/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Success");
    }


}
