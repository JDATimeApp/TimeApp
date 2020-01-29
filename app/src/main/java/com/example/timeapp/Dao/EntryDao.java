package com.example.timeapp.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.timeapp.Database.DBDesign;
import com.example.timeapp.models.Entry;

import java.util.List;

@Dao
public interface EntryDao {

    @Query("SELECT * FROM "+ DBDesign.EntryDesign.ENTRY_TABLE+" WHERE userId = :userId and EntryDate = :entryDay")
    Entry checkEntry(String userId,String entryDay);

    @Query("SELECT * FROM "+ DBDesign.EntryDesign.ENTRY_TABLE+" WHERE userId = :userId and EntryDate = :entryDay" +
            " and Leavetime IS NULL")
    Entry checkEntryDate(String userId,String entryDay);

    @Query("SELECT * FROM "+ DBDesign.EntryDesign.ENTRY_TABLE+" WHERE userId = :userId")
    List<Entry> getUserEntries(String userId);


    @Insert
    void insertEntry(Entry e);

    @Update
    void updateLeaveTime(Entry e);
}
