package com.example.lor_deck_maker.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LoRcardService {
    @GET("latest/{set}/{lang}/data/{setlang}")
    Call<List<LoRCard>> fetchLor(
        @Path("set") String set,
        @Path("lang") String lang,
        @Path("setlang") String setlang
    );
}
