package com.example.timeapp.models;

public class Entry {

    String entryDate;
    String entryTime;
    String leaveTime;
    int workerId;

    public Entry(int workerId, String entryDate,String entryTime, String leaveTime) {
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

    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }
}