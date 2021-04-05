package com.webApp.webAppBE.Services;

import com.webApp.webAppBE.Tasks.TaskEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;



import java.io.*;

@Service
public class UploadService {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    TaskService taskService;

    Logger logger = LoggerFactory.getLogger(UploadService.class);

    @Async
    public void processFile(byte[] bytes, String taskId) throws IOException {
        InputStream is = new ByteArrayInputStream(bytes);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] employeeDetails = line.split(" ");

            if(employeeDetails.length != 2){
                taskService.changeStatus(taskId, TaskEnum.FAILED);
                logger.error("Invalid file");
                return;
            }
            if (employeeService.checkIfEmployeeExists(employeeDetails[0])) {
                taskService.changeStatus(taskId, TaskEnum.FAILED);
                logger.error("Employee with same name already exists");
                return;
            }

            if(!isNumeric(employeeDetails[1])){
                taskService.changeStatus(taskId, TaskEnum.FAILED);
                logger.error("Invalid age");
                return;
            }
            employeeService.addEmployeeFromFile(employeeDetails[0], Integer.parseInt(employeeDetails[1]));
        }
        taskService.changeStatus(taskId, TaskEnum.SUCCESS);
    }

    public static boolean isNumeric(String str) {
        return str != null && str.matches("[-+]?\\d*\\.?\\d+");
    }
}
