package com.example.lor_deck_maker;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lor_deck_maker.data.LoRCard;
import com.example.lor_deck_maker.data.LoRdeck;
import com.example.lor_deck_maker.data.LoRepo;

import java.util.ArrayList;
import java.util.List;

public class LoRDeckAdapter extends RecyclerView.Adapter<LoRDeckAdapter.LoRItemViewHolder>{

    private List<LoRdeck> loRDeck;
    private OnLoRItemClickListener onLoRItemClickListener;

    public interface OnLoRItemClickListener {
        void onLoRItemClick(LoRdeck loRDeck);
    }

    public LoRDeckAdapter(OnLoRItemClickListener onLoRItemClickListener) {
        this.onLoRItemClickListener = onLoRItemClickListener;
    }

    public LoRDeckAdapter(){

    }

    @NonNull
    @Override
    public LoRItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.card_layout_rv, parent, false);
        return new LoRItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LoRItemViewHolder holder, int position) {
        holder.bind(this.loRDeck.get(position));

    }

    public void updateLoRData(List<LoRdeck> loRDeck) {
        this.loRDeck = loRDeck;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (this.loRDeck == null) {
            return 0;
        } else {
            return this.loRDeck.size();
        }
    }

    class LoRItemViewHolder extends RecyclerView.ViewHolder {
        final private TextView nameTV;

        public LoRItemViewHolder(@NonNull View itemView){
            super(itemView);
            nameTV = itemView.findViewById(R.id.tv_archive_name);


            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    onLoRItemClickListener.onLoRItemClick(
                            loRDeck.get(getAdapterPosition())
                    );
                }
            });

        }

        public void bind(LoRdeck loRDecks) {

            nameTV.setText(loRDecks.DeckName);

        }

    }

}
