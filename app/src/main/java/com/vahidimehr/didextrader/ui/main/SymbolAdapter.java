package com.vahidimehr.didextrader.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.vahidimehr.didextrader.R;
import com.vahidimehr.didextrader.model.Symbol;
import java.util.List;


public class SymbolAdapter extends RecyclerView.Adapter<SymbolAdapter.ViewHolder> {
    List<Symbol> symbols;
    SymbolOnClickListener listener;

    public SymbolAdapter(List<Symbol> symbols) {
        this.symbols = symbols;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_symbol, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.bind(symbols.get(position));
    }

    @Override
    public int getItemCount() {
        return symbols.size();
    }


    public void setListener(SymbolOnClickListener listener) {
        this.listener = listener;
    }
    public interface SymbolOnClickListener {
        void onClick(Symbol symbol);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.symbolCurrency);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        listener.onClick(symbols.get(getAdapterPosition()));
                    }
                }
            });
        }

        public void bind(Symbol symbol) {
            this.textView.setText(symbol.symbol);
        }

    }

}

