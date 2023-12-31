package com.example.assignment_9_1;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(version = 1, entities = {Log.class})
@TypeConverters({DateTimeConverter.class})
public abstract class LogAppDataBase extends RoomDatabase {
    public abstract LogDao LogDao();
}
