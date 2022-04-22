package com.example.lor_deck_maker.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "loRDeck")
public class LoRdeck {
    //@SerializedName("DeckCode")
    //public String DeckCode;
  /*  @SerializedName("Card1")
    public String Card1;
    @SerializedName("Card2")
    public String Card2;
    @SerializedName("Card3")
    public String Card3;
    @SerializedName("Card4")
    public String Card4;
    @SerializedName("Card5")
    public String Card5;
    @SerializedName("Card6")
    public String Card6;
    @SerializedName("Card7")
    public String Card7;
    @SerializedName("Card8")
    public String Card8;
    @SerializedName("Card9")
    public String Card9;
    @SerializedName("Card10")
    public String Card10;
    @SerializedName("Card11")
    public String Card11;
    @SerializedName("Card12")
    public String Card12;
    @SerializedName("Card13")
    public String Card13;
    @SerializedName("Card14")
    public String Card14;
    @SerializedName("Card15")
    public String Card15;
    @SerializedName("Card16")
    public String Card16;
    @SerializedName("Card17")
    public String Card17;
    @SerializedName("Card18")
    public String Card18;
    @SerializedName("Card19")
    public String Card19;
    @SerializedName("Card20")
    public String Card20;
    @SerializedName("Card21")
    public String Card21;
    @SerializedName("Card22")
    public String Card22;
    @SerializedName("Card23")
    public String Card23;
    @SerializedName("Card24")
    public String Card24;
    @SerializedName("Card25")
    public String Card25;
    @SerializedName("Card26")
    public String Card26;
    @SerializedName("Card27")
    public String Card27;
    @SerializedName("Card28")
    public String Card28;
    @SerializedName("Card29")
    public String Card29;
    @SerializedName("Card30")
    public String Card30;
    @SerializedName("Card31")
    public String Card31;
    @SerializedName("Card32")
    public String Card32;
    @SerializedName("Card1")
    public String Card33;
    @SerializedName("Card2")
    public String Card34;
    @SerializedName("Card3")
    public String Card35;
    @SerializedName("Card4")
    public String Card36;
    @SerializedName("Card5")
    public String Card37;
    @SerializedName("Card6")
    public String Card38;
    @SerializedName("Card7")
    public String Card39;
    @SerializedName("Card8")
    public String Card40;
*/
    @SerializedName("DeckName")
    @PrimaryKey
    @NonNull
    public String DeckName;
}
