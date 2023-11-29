package com.example.assignment_9_1;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Calendar;

@Entity(tableName = "Logs")
public class Log implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo
    public Calendar dateTime;

    @ColumnInfo
    public String sleepHrs;

    @ColumnInfo
    public String sleepRating;

    @ColumnInfo
    public String exerciseMins;

    @ColumnInfo
    public String weight;

    public Log() {
    }

    public Log(Calendar dateTime, String sleepHrs, String sleepRating, String exerciseMins, String weight) {
        this.dateTime = dateTime;
        this.sleepHrs = sleepHrs;
        this.sleepRating = sleepRating;
        this.exerciseMins = exerciseMins;
        this.weight = weight;
    }

    public Log(long id, Calendar dateTime, String sleepHrs, String sleepRating, String exerciseMins, String weight) {
        this.id = id;
        this.dateTime = dateTime;
        this.sleepHrs = sleepHrs;
        this.sleepRating = sleepRating;
        this.exerciseMins = exerciseMins;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", sleepHrs='" + sleepHrs + '\'' +
                ", sleepRating='" + sleepRating + '\'' +
                ", exerciseMins='" + exerciseMins + '\'' +
                ", weight='" + weight + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Calendar getDateTime() {
        return dateTime;
    }

    public void setDateTime(Calendar dateTime) {
        this.dateTime = dateTime;
    }

    public String getSleepHrs() {
        return sleepHrs;
    }

    public void setSleepHrs(String sleepHrs) {
        this.sleepHrs = sleepHrs;
    }

    public String getSleepRating() {
        return sleepRating;
    }

    public void setSleepRating(String sleepRating) {
        this.sleepRating = sleepRating;
    }

    public String getExerciseMins() {
        return exerciseMins;
    }

    public void setExerciseMins(String exerciseMins) {
        this.exerciseMins = exerciseMins;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
