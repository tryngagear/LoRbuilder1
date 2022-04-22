package com.example.lor_deck_maker;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.lor_deck_maker.data.DeckRepo;
import com.example.lor_deck_maker.data.LoRdeck;
import com.example.lor_deck_maker.data.LoadingStatus;

import java.util.List;

public class LoRDeckViewModel extends AndroidViewModel {
    private DeckRepo repo;

    public LoRDeckViewModel(Application application){
        super(application);
        this.repo = new DeckRepo(application);
    }
    public void insertDeck(LoRdeck deck){this.repo.insertDeck(deck);}

    public LiveData<List<LoRdeck>> getAlldecks(){ return this.repo.getAllDecks();}
    public LiveData<LoadingStatus> getLoadingStatus(){return this.getLoadingStatus();}
}
