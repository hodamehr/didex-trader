package com.vahidimehr.didextrader.ui.main;

import androidx.arch.core.util.Function;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vahidimehr.didextrader.data.SymbolRepository;
import com.vahidimehr.didextrader.model.Symbol;
import com.vahidimehr.didextrader.utils.DataHolder;
import com.vahidimehr.didextrader.utils.Event;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;


@HiltViewModel
public class MainViewModel extends ViewModel {

    LiveData<DataHolder<List<Symbol>>> symbolListLiveData;
    MutableLiveData<Boolean> getDataLiveData = new MutableLiveData<>();

    MutableLiveData<Event<String>> navigateToDetail = new MutableLiveData<>();

    @Inject
    public MainViewModel(SymbolRepository symbolRepo) {

        symbolListLiveData =
                Transformations.switchMap(getDataLiveData, new Function<Boolean, LiveData<DataHolder<List<Symbol>>>>() {
                    @Override
                    public LiveData<DataHolder<List<Symbol>>> apply(Boolean input) {
                        return symbolRepo.getSymbol();
                    }
                });
    }

    public LiveData<DataHolder<List<Symbol>>> getSymbolListLiveData() {
        return symbolListLiveData;
    }

    public LiveData<Event<String>> getNavigateToDetail() {
        return navigateToDetail;
    }

    public void onLoadScreen() {
        getDataLiveData.setValue(true);
    }

    public void onItemClick(Symbol symbol) {
        navigateToDetail.setValue(new Event<>(symbol.symbol));
    }


}
