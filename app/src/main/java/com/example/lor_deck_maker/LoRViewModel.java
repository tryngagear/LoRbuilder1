package com.example.lor_deck_maker;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.lor_deck_maker.data.LoRCard;
import com.example.lor_deck_maker.data.LoadingStatus;
import com.example.lor_deck_maker.data.LoRepo;

import java.util.List;

public class LoRViewModel extends AndroidViewModel {
    private LiveData<List<LoRCard>> SetResponse;
    private LiveData<LoadingStatus> loadingStatus;
    private LoRepo repo;

    public LoRViewModel(Application application){
        super(application);
        this.repo = new LoRepo(application);
        SetResponse = repo.getlorSet();
        loadingStatus = repo.getLoadingStatus();
    }

    public void loadSet(String setnum, String lang){this.repo.loadSet(setnum,lang);}

    public LiveData<List<LoRCard>> getSet(){ return this.SetResponse; }

    public LiveData<LoadingStatus> getLoadingStatus(){
        return this.loadingStatus;
    }

}

