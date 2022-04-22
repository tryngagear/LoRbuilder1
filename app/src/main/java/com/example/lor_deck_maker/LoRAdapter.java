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
import com.example.lor_deck_maker.data.LoRepo;

import java.util.ArrayList;
import java.util.List;

public class LoRAdapter extends RecyclerView.Adapter<LoRAdapter.LoRItemViewHolder>{

    private List<LoRCard> loRCard;
    private OnLoRItemClickListener onLoRItemClickListener;

    public interface OnLoRItemClickListener {
        void onLoRItemClick(LoRCard loRCard);
    }

    public LoRAdapter(OnLoRItemClickListener onLoRItemClickListener) {
        this.onLoRItemClickListener = onLoRItemClickListener;
    }

    public LoRAdapter(){

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
        holder.bind(this.loRCard.get(position));

    }

    public void updateLoRData(List<LoRCard> loRCard) {
        this.loRCard = loRCard;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (this.loRCard == null) {
            return 0;
        } else {
            return this.loRCard.size();
        }
    }

    class LoRItemViewHolder extends RecyclerView.ViewHolder {
        final private TextView nameTV;
        final private TextView descriptionTV;
        final private TextView rarityTV;
        final private TextView healthTV;
        final private TextView attackTV;
        final private TextView costTV;

        public LoRItemViewHolder(@NonNull View itemView){
            super(itemView);
            nameTV = itemView.findViewById(R.id.tv_name);
            descriptionTV = itemView.findViewById(R.id.tv_description);
            rarityTV = itemView.findViewById(R.id.tv_rarity);
            healthTV = itemView.findViewById(R.id.tv_health);
            attackTV = itemView.findViewById(R.id.tv_attack);
            costTV = itemView.findViewById(R.id.tv_cost);


            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    onLoRItemClickListener.onLoRItemClick(
                            loRCard.get(getAdapterPosition())
                    );
                }
            });

        }

        public void bind(LoRCard loRcards) {
            Context ctx = this.itemView.getContext();

            nameTV.setText(loRcards.getName());
            descriptionTV.setText(loRcards.descriptionRaw);
            rarityTV.setText(loRcards.rarity);
            healthTV.setText(String.valueOf("Health " + loRcards.health));
            attackTV.setText(String.valueOf("Attack " + loRcards.attack));
            costTV.setText(String.valueOf("Cost " + loRcards.cost));

            /*
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
            String langPref = preferences.getString(
                    ctx.getString(R.string.pref_lang_key),
                    ctx.getString(R.string.pref_lang_default)
            );
             */
        }

    }

}



