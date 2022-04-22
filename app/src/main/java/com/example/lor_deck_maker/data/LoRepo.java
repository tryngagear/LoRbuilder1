package com.example.lor_deck_maker.data;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.text.TextUtils.concat;

public class LoRepo {
    private static final String TAG = LoRepo.class.getSimpleName();
    public static final String BASE_URL = "https://dd.b.pvp.net/";

    public MutableLiveData<List<LoRCard>> lorResp;
    public MutableLiveData<LoadingStatus> loadingStatus;
    private CardRepo repo;
    private LoRcardService loRcardService;

    public LoRepo(Application application) {
        this.repo = new CardRepo(application);

        this.lorResp = new MutableLiveData<>();
        this.lorResp.setValue(null);

        this.loadingStatus = new MutableLiveData<>();
        this.loadingStatus.setValue(LoadingStatus.SUCCESS);

        Gson gson = new GsonBuilder()
        .registerTypeAdapter(LoRCard.class, new LoRCard.JsonDeserializer())
        .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        this.loRcardService = retrofit.create(LoRcardService.class);
    }

    public LiveData<LoadingStatus> getLoadingStatus(){return this.loadingStatus;}

    public LiveData<List<LoRCard>> getlorSet(){return this.lorResp;}


    public void loadSet(String setnum, String lang){
        Log.d(TAG,"fetching set " + setnum + " in Lang: " + lang);
        String setlang = setnum.concat("-").concat(lang).concat(".json");
        this.lorResp.setValue(null);
        this.loadingStatus.setValue(LoadingStatus.LOADING);
        Call<List<LoRCard>> req = this.loRcardService.fetchLor(setnum, lang, setlang);
        req.enqueue(new Callback<List<LoRCard>>() {
            @Override
            public void onResponse(Call<List<LoRCard>> call, Response<List<LoRCard>> response) {
                lorResp.setValue(response.body());
                loadingStatus.setValue(LoadingStatus.SUCCESS);
                List<LoRCard> tempList = response.body();

                for(int i = 0; i < tempList.size(); i++){
                    Log.d(TAG,"aquired Card: " + tempList.get(i).name);
                    repo.insertCard(tempList.get(i));
                }

            }
            @Override
            public void onFailure(Call<List<LoRCard>> call, Throwable t) {
                loadingStatus.setValue(LoadingStatus.ERROR);
                Log.d(TAG, "unsuccessful API request: " + call.request().url());
                t.printStackTrace();
            }
        });
    }


}
