package com.vahidimehr.didextrader.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.vahidimehr.didextrader.R;
import com.vahidimehr.didextrader.databinding.FragmentMainBinding;
import com.vahidimehr.didextrader.model.Symbol;
import com.vahidimehr.didextrader.utils.DataHolder;
import com.vahidimehr.didextrader.utils.Event;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainFragment extends Fragment {

    private FragmentMainBinding binding;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding.setVm(viewModel);
        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }

    MainViewModel viewModel;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        setObservers(viewModel);

        viewModel.onLoadScreen();
    }

    private void setObservers(MainViewModel viewModel) {
        viewModel.getNavigateToDetail().observe(getViewLifecycleOwner(), new Observer<Event<String>>() {
            @Override
            public void onChanged(Event<String> event) {
                if (!event.hasBeenHandled()) {
                    NavController navController = Navigation.findNavController(binding.getRoot());

                    MainFragmentDirections.ActionToDetail action =
                            MainFragmentDirections.actionToDetail(event.getContentIfNotHandled());
                    navController.navigate(action);
                }
            }
        });
    }


    @BindingAdapter({"symbols", "onItemClick"})
    public static void setData(RecyclerView view, List<Symbol> list
            , SymbolAdapter.SymbolOnClickListener onClickListener) {
        view.setLayoutManager(new LinearLayoutManager(view.getContext()));
        view.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));
        SymbolAdapter adapter = new SymbolAdapter(list != null ? list : new ArrayList<>());
        view.setAdapter(adapter);
        adapter.setListener(onClickListener);
    }

}