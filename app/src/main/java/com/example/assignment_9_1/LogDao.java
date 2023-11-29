package com.example.assignment_9_1;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LogDao {

    // Get all notes
    @Query("SELECT * FROM Logs")
    List<Log> getAll();

    // Get a note by id
    @Query("SELECT * FROM Logs WHERE id = :id limit 1")
    Log findById(long id);

    // Insert one note
    @Insert
    void insert(Log log);

    // Delete a note via the id
    @Query("DELETE FROM Logs WHERE id = :id")
    void deleteById(long id);

    // Delete a note
    @Delete
    void delete(Log log);

}
