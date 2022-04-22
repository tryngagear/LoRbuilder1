package com.example.lor_deck_maker.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CardRepo {
    private LoRCardDAO dao;

    public CardRepo(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        this.dao = db.loRCardDAO();
    }

    public void insertCard(LoRCard card){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.insert(card);
            }
        });
    }

    public void updateCard(LoRCard card){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() { dao.update(card); }
        });
    }

    public LiveData<List<LoRCard>> getALLcards(){return this.dao.getALLcards();}
    public LiveData<LoRCard> getCardBycardCode(String cardCode){return this.dao.getCardBycardCode(cardCode);}
}
