package com.example.lor_deck_maker.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DeckDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(LoRdeck deck);

    @Delete
    void delete(LoRdeck deck);

    @Query("SELECT * FROM loRDeck ORDER BY DeckName ASC")
    LiveData<List<LoRdeck>> getAllDecks();
}
