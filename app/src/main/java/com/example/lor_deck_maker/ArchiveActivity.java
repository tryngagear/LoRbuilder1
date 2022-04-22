package com.example.lor_deck_maker;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lor_deck_maker.data.LoRdeck;
import com.example.lor_deck_maker.data.LoadingStatus;

import java.util.List;


public class ArchiveActivity extends AppCompatActivity implements LoRDeckAdapter.OnLoRItemClickListener {
    private LoRViewModel lorViewModel;
    private LoRsetViewModel loRsetViewModel;
    private LoRDeckViewModel loRDeckViewModel;
    private RecyclerView cardListRV;
    private TextView errorMessageTV;
    private LoRDeckAdapter lorAdapter;

    private ProgressBar loadingIndicatorPB;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deck_archive);

        this.cardListRV = findViewById(R.id.rv_deck_archive);
        this.cardListRV.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        this.cardListRV.setLayoutManager(new LinearLayoutManager(this));
        this.cardListRV.setHasFixedSize(true);
        this.loadingIndicatorPB = findViewById(R.id.pb_loading_indicator);
        this.errorMessageTV = findViewById(R.id.tv_error_message);


        this.lorAdapter = new LoRDeckAdapter();
        this.cardListRV.setAdapter(this.lorAdapter);
        this.loRDeckViewModel = new ViewModelProvider(this).get(LoRDeckViewModel.class);
        this.loRsetViewModel = new ViewModelProvider(this).get(LoRsetViewModel.class);


        this.loRDeckViewModel.getAlldecks().observe(
                this,
                new Observer<List<LoRdeck>>(){
                    @Override
                    public void onChanged(List<LoRdeck> loRdecks){
                        lorAdapter.updateLoRData(loRdecks);

                    }
        }
        );

        this.loRDeckViewModel.getLoadingStatus().observe(
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
    }

    @Override
    public void onLoRItemClick(LoRdeck loRDeck) {

    }
}
