package com.example.lor_deck_maker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lor_deck_maker.data.LoRCard;
import com.example.lor_deck_maker.data.LoRCardDAO;
import com.example.lor_deck_maker.data.LoRepo;
import com.example.lor_deck_maker.data.LoadingStatus;
import com.example.lor_deck_maker.utils.LoRUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BuildDeckActivity extends AppCompatActivity implements LoRAdapter.OnLoRItemClickListener {
    private static final String TAG = BuildDeckActivity.class.getSimpleName();


    private SharedPreferences sharedPreferences;

    private LoRViewModel lorViewModel;
    private LoRsetViewModel loRsetViewModel;
    private RecyclerView cardListRV;
    private TextView errorMessageTV;
    private LoRAdapter lorAdapter;
    private ProgressBar loadingIndicatorPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.build_deck);

        this.cardListRV = findViewById(R.id.cards_rv);
        this.cardListRV.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        this.cardListRV.setLayoutManager(new LinearLayoutManager(this));
        this.cardListRV.setHasFixedSize(true);
        this.loadingIndicatorPB = findViewById(R.id.pb_loading_indicator);
        this.errorMessageTV = findViewById(R.id.tv_error_message);

        this.lorAdapter = new LoRAdapter(this);
        this.cardListRV.setAdapter(this.lorAdapter);
        this.lorViewModel = new ViewModelProvider(this).get(LoRViewModel.class);
        this.loRsetViewModel = new ViewModelProvider(this).get(LoRsetViewModel.class);
        //this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //this.sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        Intent intent = getIntent();

        this.loRsetViewModel.getALLcards().observe(
                this,
                new Observer<List<LoRCard>>(){
                    @Override
                    public void onChanged(List<LoRCard> loRCard){
                        lorAdapter.updateLoRData(loRCard);
                    }
                }
        );

        this.lorViewModel.getLoadingStatus().observe(
                this,
                new Observer<LoadingStatus>() {
                    @Override
                    public void onChanged(LoadingStatus loadingStatus) {
                        if (loadingStatus == LoadingStatus.LOADING) {
                            loadingIndicatorPB.setVisibility(View.VISIBLE);
                        } else if (loadingStatus == LoadingStatus.SUCCESS) {
                            loadingIndicatorPB.setVisibility(View.INVISIBLE);
                            cardListRV.setVisibility(View.VISIBLE);
                            errorMessageTV.setVisibility(View.INVISIBLE);
                        } else {
                            loadingIndicatorPB.setVisibility(View.INVISIBLE);
                            cardListRV.setVisibility(View.INVISIBLE);
                            errorMessageTV.setVisibility(View.VISIBLE);
                            errorMessageTV.setText(getString(R.string.loading_error, "loading error"));
                        }
                    }
                }
        );
<<<<<<< Updated upstream
        List<String> dummy = new ArrayList<String>();
        for(int i = 0; i < 40; i++){
            dummy.add("01SI015");
        }
        LoRUtils.makeFile("testDeck", dummy, this);
        //dummy = LoRUtils.readFile("testDeck.txt");
        File dir = new File(this.getFilesDir(), "mydir");
        File dumfile  = new File(dir, "testDeck.txt");
        dummy = LoRUtils.readFile(dumfile);
        Log.d(TAG,"cardCode " + dummy.get(0));
        //a deck of just ruination
=======
        LoRUtils.m
>>>>>>> Stashed changes
    }

 //   private void loadSet() {
 //       Log.d(TAG, "Loading in Set");
 //       this.lorViewModel.loadSet(
 //               "set4",
 //               "en_us"
 //       );
 //       Log.d(TAG,"build item count " + this.lorAdapter.getItemCount());
 //   }

    public void onLoRItemClick(LoRCard loRCard){
        Intent intent = new Intent(this, ImageActivity.class);
        intent.putExtra(ImageActivity.EXTRA_CARD_DATA, loRCard);
        startActivity(intent);
    }




}


