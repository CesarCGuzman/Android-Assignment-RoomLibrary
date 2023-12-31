package com.example.assignment_9_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainFragment.MainFragmentListener,
                                                               AddLogFragment.AddLogListener,
        LogAdapter.LogAdapterListener{
    private static final String TAG = "demo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogAppDataBase db = Room.databaseBuilder(this, LogAppDataBase.class, "Log.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        List<Log> logs = db.LogDao().getAll();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.rootView, MainFragment.newInstance(logs))
                .commit();
    }

    @Override
    public void goToAddLog() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new AddLogFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void viewProgress() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new ViewProgressFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void back() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void addLog(Log log) {
        LogAppDataBase db = Room.databaseBuilder(this, LogAppDataBase.class, "Log.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        db.LogDao().insert(log);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, MainFragment.newInstance(db.LogDao().getAll()))
                .commit();
    }

    @Override
    public void deleteLog(Log log) {
        LogAppDataBase db = Room.databaseBuilder(this, LogAppDataBase.class, "Log.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        db.LogDao().delete(log);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, MainFragment.newInstance(db.LogDao().getAll()))
                .commit();
    }
}