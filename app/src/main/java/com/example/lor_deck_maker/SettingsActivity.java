package com.example.lor_deck_maker;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_menu);

        Button langButton = (Button) findViewById(R.id.languages_button);
        langButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent langIntent = new Intent(SettingsActivity.this, LanguageActivity.class);
                SettingsActivity.this.startActivity(langIntent);
            }
        });

        Button securityButton = (Button) findViewById(R.id.security_button);
        securityButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent secIntent = new Intent(SettingsActivity.this, SecurityActivity.class);
                startActivity(secIntent);
            }
        });

        Button devButton = (Button) findViewById(R.id.dev_button);
        devButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent devIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://developer.riotgames.com/"));
                startActivity(devIntent);
            }
        });

    }




}
