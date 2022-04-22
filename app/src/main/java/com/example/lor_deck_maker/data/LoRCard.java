package com.example.lor_deck_maker.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

@Entity(tableName = "loRCard")
public class LoRCard implements Serializable{
    @SerializedName("gameAbsolutePath")
    public String gameAbsolutePath;
    @SerializedName("fullAbsolutePath")
    public String fullAbsolutePath;
    @SerializedName("regionRef")
    public String regionRef;
    @SerializedName("attack")
    public int attack;
    @SerializedName("cost")
    public int cost;
    @SerializedName("health")
    public int health;
    @SerializedName("descriptionRaw")
    public String descriptionRaw;
    @SerializedName("levelupDescriptionRaw")
    public String levelupDescriptionRaw;
    @SerializedName("name")
    public String name;
    @SerializedName("cardCode")
    @NonNull
    @PrimaryKey
    public String cardCode;
    @SerializedName("rarity")
    public String rarity;
    @SerializedName("set")
    public String set;
    @SerializedName("collectible")
    public boolean collectible;

    public LoRCard() {
        this.regionRef = null;
        this.descriptionRaw = null;
        this.attack = 0;
        this.health = 0;
        this.cost = 0;
        this.cardCode = null;
        this.name = null;
        this.levelupDescriptionRaw = null;
        this.fullAbsolutePath = null;
        this.gameAbsolutePath = null;
        this.set = null;
        this.rarity = null;
        this.collectible = false;
    }
    public LoRCard(String cardCode, String name, String regionRef, String descriptionRaw, String levelupDescriptionRaw,
                   String gameAbsolutePath, String fullAbsolutePath, String rarity, String set
            , int cost, int attack, int health, boolean collectible) {
        this.regionRef = regionRef;
        this.descriptionRaw = descriptionRaw;
        this.attack = attack;
        this.health = health;
        this.cost = cost;
        this.cardCode = cardCode;
        this.name = name;
        this.levelupDescriptionRaw = levelupDescriptionRaw;
        this.fullAbsolutePath = fullAbsolutePath;
        this.gameAbsolutePath = gameAbsolutePath;
        this.set = set;
        this.rarity = rarity;
        this.collectible = collectible;
    }

    public String getName(){ return name; }

    public static class JsonDeserializer implements com.google.gson.JsonDeserializer<LoRCard> {
        @Override
        public LoRCard deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            //JsonArray listObj = json.getAsJsonArray();
            JsonObject cardObj = json.getAsJsonObject();
            JsonArray assArr = cardObj.getAsJsonArray("assets");
            JsonObject assObj = assArr.get(0).getAsJsonObject();
            return new LoRCard(
                    cardObj.getAsJsonPrimitive("cardCode").getAsString(),
                    cardObj.getAsJsonPrimitive("name").getAsString(),
                    cardObj.getAsJsonPrimitive("regionRef").getAsString(),
                    cardObj.getAsJsonPrimitive("descriptionRaw").getAsString(),
                    cardObj.getAsJsonPrimitive("levelupDescriptionRaw").getAsString(),
                    assObj.getAsJsonPrimitive("gameAbsolutePath").getAsString(),
                    assObj.getAsJsonPrimitive("fullAbsolutePath").getAsString(),
                    cardObj.getAsJsonPrimitive("rarity").getAsString(),
                    cardObj.getAsJsonPrimitive("set").getAsString(),
                    cardObj.getAsJsonPrimitive("cost").getAsInt(),
                    cardObj.getAsJsonPrimitive("attack").getAsInt(),
                    cardObj.getAsJsonPrimitive("health").getAsInt(),
                    cardObj.getAsJsonPrimitive("collectible").getAsBoolean()
            );
        }
    }

}
