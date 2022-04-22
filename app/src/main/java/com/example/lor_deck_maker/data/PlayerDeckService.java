package com.example.lor_deck_maker.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PlayerDeckService {
    @GET("http://127.0.0.1:/{port}/static-decklist")
    Call<LoRdeck> fetchPlayerDeck(
      @Path("port") String port
    );

}
