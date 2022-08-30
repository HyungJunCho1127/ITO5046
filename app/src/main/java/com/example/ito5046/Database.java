package com.example.ito5046;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {User.class, Competition.class}, version = 1)
public abstract class Database extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract CompetitionDao competitionDao();

    private static volatile Database INSTANCE;

    static Database getINSTANCE(Context context){
        if (INSTANCE == null){
            synchronized (Database.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), Database.class,
                            "User_Database").build();
                }
            }
        }
        return INSTANCE;
    }

}
