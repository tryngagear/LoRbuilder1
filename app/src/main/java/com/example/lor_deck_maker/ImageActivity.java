package com.example.lor_deck_maker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.lor_deck_maker.data.LoRCard;

public class ImageActivity extends AppCompatActivity {
    public static final String EXTRA_CARD_DATA = "ImageActivity.LoRCard";

    private LoRCard lorCard = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);


        Intent intent = getIntent();

        if (intent != null && intent.hasExtra(EXTRA_CARD_DATA)) {
            this.lorCard = (LoRCard) intent.getSerializableExtra(EXTRA_CARD_DATA);
            ImageView lorCardImage = findViewById(R.id.iv_lor_card);
            Glide.with(this)
                    .load(this.lorCard.gameAbsolutePath)
                    .into(lorCardImage);
        }
    }
}
