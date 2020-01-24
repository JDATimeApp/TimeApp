package com.example.timeapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import com.example.timeapp.Database.DBDesign;

@Entity(tableName = DBDesign.EntryDesign.ENTRY_TABLE , foreignKeys = @ForeignKey(entity = Users.class,
                                                                parentColumns = "idU",
                                                                childColumns = "workerId"))
public class Entry {

    @ColumnInfo(name = DBDesign.UserDesign.USER_COLUMN1)
    String workerId;

    @ColumnInfo(name = DBDesign.EntryDesign.ENTRY_COLUMN1)
    String entryDate;

    @ColumnInfo(name = DBDesign.EntryDesign.ENTRY_COLUMN2)
    String entryTime;

    @ColumnInfo(name = DBDesign.EntryDesign.ENTRY_COLUMN3)
    String leaveTime;

    @ColumnInfo(name = DBDesign.EntryDesign.ENTRY_COLUMN4)
    String description;

    public Entry(String workerId, String entryDate,String entryTime, String leaveTime, String description) {
        this.workerId = workerId;
        this.entryDate = entryDate;
        this.entryTime = entryTime;
        this.leaveTime = leaveTime;
        this.description = description;
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

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }
}