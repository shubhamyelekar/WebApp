package com.webApp.webAppBE.Services;

import com.webApp.webAppBE.Tasks.TaskEnum;
import com.webApp.webAppBE.Tasks.TaskInfo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.UUID;

@Service
public class TaskService {

    // TaskMap could be persisted if needed
    private HashMap<String, TaskInfo> taskMap = new HashMap<>();


    public TaskInfo getTaskById(String id) {
        if (!taskMap.containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Task ID does not exist");
        }
        return taskMap.get(id);
    }


    public String addTask() {
        TaskInfo taskInfo = new TaskInfo();
        taskInfo.setTaskId(generateId());
        taskInfo.setStatus(TaskEnum.RUNNING);
        taskMap.put(taskInfo.getTaskId(), taskInfo);
        return taskInfo.getTaskId();
    }


    public void changeStatus(String id, TaskEnum status) {
        TaskInfo taskInfo = taskMap.get(id);
        taskInfo.setStatus(status);
        taskMap.put(id, taskInfo);

    }

    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
