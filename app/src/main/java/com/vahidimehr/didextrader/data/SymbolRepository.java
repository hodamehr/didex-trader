package com.vahidimehr.didextrader.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.vahidimehr.didextrader.model.Symbol;
import com.vahidimehr.didextrader.utils.DataHolder;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SymbolRepository {


    private Api api;

    @Inject
    public SymbolRepository(Api api) {
        this.api = api;
    }


    public LiveData<DataHolder<List<Symbol>>> getSymbol() {
        MutableLiveData<DataHolder<List<Symbol>>> live = new MutableLiveData<>();
        api.getSymbols().enqueue(new Callback<List<Symbol>>() {
            @Override
            public void onResponse(Call<List<Symbol>> call, Response<List<Symbol>> response) {
                DataHolder<List<Symbol>> data = new DataHolder<>();
                if (response.isSuccessful()) {
                    data.data = response.body();
                } else {
                    data.isError = true;
                    data.error = String.valueOf(response.code());
                }
                live.setValue(data);
            }

            @Override
            public void onFailure(Call<List<Symbol>> call, Throwable t) {

            }
        });
        return live;
    }


    public LiveData<DataHolder<Symbol>> getDetailSymbolByName(String symbolName) {
        DataHolder<Symbol> data = new DataHolder<>();
        MutableLiveData<DataHolder<Symbol>> live = new MutableLiveData<>();
        data.isLoading = true;
        live.setValue(data);
        api.getSymbolByName(symbolName).enqueue(new Callback<Symbol>() {
            @Override
            public void onResponse(Call<Symbol> call, Response<Symbol> response) {
                if (response.isSuccessful()) {
                    data.data = response.body();
                    data.isLoading=false;
                } else {
                    data.isError = true;
                    data.error = String.valueOf(response.code());
                    data.isLoading=false;

                }
                live.setValue(data);
            }

            @Override
            public void onFailure(Call<Symbol> call, Throwable t) {

            }
        });
        return live;
    }

}
