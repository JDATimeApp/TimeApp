package com.example.timeapp.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.timeapp.Database.DBDesign;
import com.example.timeapp.models.Entry;

@Dao
public interface EntryDao {

    @Query("SELECT * FROM "+ DBDesign.EntryDesign.ENTRY_TABLE+" WHERE userId = :userId and EntryDate = :entryDay")
    Entry checkEntry(String userId,String entryDay);

    @Insert
    void insertEntry(Entry e);
}