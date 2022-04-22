package com.example.lor_deck_maker.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {LoRCard.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public static volatile AppDatabase INSTANCE;

    private static final int NUM_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUM_THREADS);
    public abstract LoRCardDAO loRCardDAO();

    static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "cards.db"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}
