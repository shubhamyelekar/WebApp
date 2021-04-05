package com.webApp.webAppBE.Tasks;

public enum TaskEnum {

    RUNNING(0, "Task is running"),
    FAILED(1, "Task has failed"),
    SUCCESS(2, "Task executed successfully");

    private int state;
    private String stateInfo;

    TaskEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }
}
