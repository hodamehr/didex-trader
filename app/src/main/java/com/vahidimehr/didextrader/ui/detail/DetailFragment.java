package com.vahidimehr.didextrader.ui.detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vahidimehr.didextrader.R;
import com.vahidimehr.didextrader.databinding.FragmentDetailBinding;
import com.vahidimehr.didextrader.model.Symbol;
import com.vahidimehr.didextrader.utils.DataHolder;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetailFragment extends Fragment {

    FragmentDetailBinding binding;

    public DetailFragment() {
        // Required empty public constructor
    }

    DetailViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false);

        viewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        binding.setVm(viewModel);
        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        setObserver(viewModel);

        String symbolName = DetailFragmentArgs.fromBundle(getArguments()).getSymbol();

        viewModel.onLoadScreen(symbolName);
//        binding.symbolName.setText(symbolName);

    }


    private void setObserver(DetailViewModel viewModel) {
//        viewModel.getSymbolDetail().observe(getViewLifecycleOwner(), new Observer<DataHolder<Symbol>>() {
//            @Override
//            public void onChanged(DataHolder<Symbol> dataHolder) {
//                binding.progress.setVisibility(View.INVISIBLE);
//
//                if (dataHolder.isLoading) {
//                    binding.progress.setVisibility(View.VISIBLE);
//                } else if (dataHolder.isError) {
//
//                } else {
//                    binding.symbolName.setText(dataHolder.data.symbol);
//                    binding.baseCurrencyValue.setText(dataHolder.data.baseCurrencyShortName);
//                    binding.quoteCurrencyValue.setText(dataHolder.data.quoteCurrencyShortName);
//                }
//            }
//        });
    }
}