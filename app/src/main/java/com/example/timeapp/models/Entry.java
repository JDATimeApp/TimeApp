package com.example.timeapp.models;

public class Entry {

    String entryDate;
    String entryTime;
    String leaveTime;
    String workerId;

    public Entry(String workerId, String entryDate,String entryTime, String leaveTime) {
        this.workerId = workerId;
        this.entryDate = entryDate;
        this.entryTime = entryTime;
        this.leaveTime = leaveTime;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(String leaveTime) {
        this.leaveTime = leaveTime;
    }

    public String getWorkerId() {
        return workerId;
    }

    public void setWorkerId(String workerId) {
        this.workerId = workerId;
    }
}