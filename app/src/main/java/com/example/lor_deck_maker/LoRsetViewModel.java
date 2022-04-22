package com.example.lor_deck_maker;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.lor_deck_maker.data.CardRepo;
import com.example.lor_deck_maker.data.LoRCard;

import java.util.List;

public class LoRsetViewModel extends AndroidViewModel {
    private CardRepo repo;

    public LoRsetViewModel(Application application){
        super(application);
        this.repo = new CardRepo(application);
    }

    public LiveData<List<LoRCard>> getALLcards(){return this.repo.getALLcards();}

    public void insertCard(LoRCard card){this.repo.insertCard(card);}
}
