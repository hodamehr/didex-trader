package com.vahidimehr.didextrader.ui.detail;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.vahidimehr.didextrader.data.SymbolRepository;
import com.vahidimehr.didextrader.model.Symbol;
import com.vahidimehr.didextrader.utils.DataHolder;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DetailViewModel extends ViewModel {

    private LiveData<DataHolder<Symbol>> liveData;
    private final MutableLiveData<String> LiveDataGetSymbolName = new MutableLiveData<>();

    @Inject
    public DetailViewModel(SymbolRepository repository  ) {
        liveData = Transformations.switchMap(LiveDataGetSymbolName, new Function<String, LiveData<DataHolder<Symbol>>>() {
            @Override
            public androidx.lifecycle.LiveData<DataHolder<Symbol>> apply(String input) {
                return repository.getDetailSymbolByName(input);
            }
        });

    }

    public LiveData<DataHolder<Symbol>> getSymbolDetail() {
        return liveData;
    }

    public void onLoadScreen(String symbolName) {
        LiveDataGetSymbolName.setValue(symbolName);
    }

}
