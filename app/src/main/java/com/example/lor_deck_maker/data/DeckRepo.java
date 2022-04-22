package com.example.lor_deck_maker.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class DeckRepo {
    private DeckDAO dao;

    public DeckRepo(Application application){
        DecksDatabase db = DecksDatabase.getDatabase(application);
    }

    public  void insertDeck(LoRdeck deck){
        DecksDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.insert(deck);
            }
        });
    }

    public LiveData<List<LoRdeck>> getAllDecks(){return this.dao.getAllDecks();}
}
