package com.vahidimehr.didextrader.data;

import com.vahidimehr.didextrader.model.Symbol;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {
    @GET("Public/Symbol")
    Call<List<Symbol>> getSymbols();


    @GET("Public/Symbol/{name}")
    Call<Symbol> getSymbolByName(@Path("name") String name);
}
