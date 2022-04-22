package com.example.lor_deck_maker.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {LoRdeck.class}, version = 1)
public abstract class DecksDatabase extends RoomDatabase {
    public static volatile DecksDatabase INSTANCE;

    private static final int NUM_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUM_THREADS);
    public abstract DeckDAO loRDeckDAO();

    static DecksDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DecksDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            DecksDatabase.class,
                            "decks.db"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}
