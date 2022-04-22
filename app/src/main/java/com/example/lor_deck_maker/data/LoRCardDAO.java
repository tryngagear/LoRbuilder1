package com.example.lor_deck_maker.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LoRCardDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(LoRCard card);

    @Update
    void update(LoRCard card);

    @Query("SELECT * FROM loRCard WHERE cardCode =:cardCode LIMIT 1")
    LiveData<LoRCard> getCardBycardCode(String cardCode);

    @Query("SELECT * FROM loRCard WHERE regionRef =:regionRef")
    LiveData<LoRCard> getCardByRegion(String regionRef);

    @Query("SELECT * FROM loRCard WHERE `set` =:set")
    LiveData<LoRCard> getCardByset(String set);

    @Query("SELECT * FROM loRCard WHERE collectible ORDER BY cost ASC")
    LiveData<List<LoRCard>> getALLcards();
}
