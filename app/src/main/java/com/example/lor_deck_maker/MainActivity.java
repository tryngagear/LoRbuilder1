package com.example.lor_deck_maker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.lor_deck_maker.data.LoRCard;
import com.example.lor_deck_maker.data.LoadingStatus;
import com.example.lor_deck_maker.data.LoRepo;

import java.util.List;

public class MainActivity extends AppCompatActivity{
    private static final String TAG = MainActivity.class.getSimpleName();
    private LoRCard lorCard;
    private SharedPreferences sharedPreferences;

    private RecyclerView lorListRV;
    private ProgressBar loadingIndicatorPB;
    private TextView errorMessageTV;

    private LoRAdapter lorAdapter;
    private LoRViewModel lorViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //this.loadingIndicatorPB = findViewById(R.id.);
        //this.errorMessageTV = findViewById(R.id.);
        //this.lorListRV = findViewById(R.id.rv_lor_list);
        //this.lorListRV.setLayoutManager(new LinearLayoutManager(this));
        //this.lorListRV.setHasFixedSize(true);

        this.lorAdapter = new LoRAdapter();
        //this.lorListRV.setAdapter(this.lorAdapter);

        //this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //this.sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        this.lorViewModel = new ViewModelProvider(this).get(LoRViewModel.class);

        //this.loadSearchResults();


        Button button = (Button) findViewById(R.id.build_deck_button);
        button.setOnClickListener(new View.OnClickListener() {
                                      public void onClick(View v) {
                                          Intent intent = new Intent(MainActivity.this, BuildDeckActivity.class);
                                          MainActivity.this.startActivity(intent);
                                      }
                                  }
        );


        Button settingsButton = findViewById(R.id.settings_button);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        Button archiveButton = findViewById(R.id.deck_archive_button);
        archiveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent archiveIntent = new Intent(MainActivity.this, ArchiveActivity.class);
                MainActivity.this.startActivity(archiveIntent);
            }

        });


//        Button deckArchive = findViewById(R.id.deck_archive_button);
//        deckArchive.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, DeckArchive.class);
//                MainActivity.this.startActivity(intent);
//            }
//        });



        this.lorViewModel.getSet().observe(
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
                         //   loadingIndicatorPB.setVisibility(View.VISIBLE);
                            Log.d(TAG,"Loading");
                        } else if (loadingStatus == LoadingStatus.SUCCESS) {
                          //  loadingIndicatorPB.setVisibility(View.INVISIBLE);
                          //  lorListRV.setVisibility(View.VISIBLE);
                          //  errorMessageTV.setVisibility(View.INVISIBLE);
                            Log.d(TAG,"Success");
                        } else {
                           // loadingIndicatorPB.setVisibility(View.INVISIBLE);
                            //lorListRV.setVisibility(View.INVISIBLE);
                            //errorMessageTV.setVisibility(View.VISIBLE);
                            //errorMessageTV.setText(getString(R.string.loading_error, "loading error"));
                            Log.d(TAG,"Failed");
                        }
                    }
                }
        );
        //this.lorCard = new LoRCard();
        this.loadSet();
    }

/*
    public void onLoRItemClick(LoRCard lorCard) {
        Intent intent = new Intent(this, LoRDetailActivity.class);
        intent.putExtra(LoRDetailActivity.EXTRA_FORECAST_DECK, lorCard);
        intent.putExtra(LoRDetailActivity.EXTRA_FORECAST_CARD, this.lorCard);
        startActivity(intent);
    }

 */
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_main, menu);
//        return true;
//    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadSet() {
        Log.d(TAG,"Loading in Set");
        this.lorViewModel.loadSet(
                "set1",
                "en_us"
        );
        this.lorViewModel.loadSet(
                "set2",
                "en_us"
        );
        Log.d(TAG,"item count: " + this.lorAdapter.getItemCount());
    }

}
