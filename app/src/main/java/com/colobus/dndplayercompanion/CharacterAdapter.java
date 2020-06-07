package com.colobus.dndplayercompanion;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterHolder> {
    private List<CharacterDao.BasicCharacterDetail> basicCharacterDetails = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public CharacterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.character_item, parent, false);
        return new CharacterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterHolder holder, int position) {
        CharacterDao.BasicCharacterDetail basicCharacterDetail = basicCharacterDetails.get(position);
        holder.textViewName.setText(basicCharacterDetail.getCharName());
        int xp = basicCharacterDetail.getXp();
        int level = basicCharacterDetail.getLevelFromXp(xp);
        holder.textViewLevel.setText(String.format("Lvl %d", level));
        holder.textViewClass.setText(basicCharacterDetail.getClassName());
        holder.textViewRace.setText(basicCharacterDetail.getRaceName());
    }

    @Override
    public int getItemCount() {
        return basicCharacterDetails.size();
    }

    public void setCharacters(List<CharacterDao.BasicCharacterDetail> basicCharacterDetails) {
        this.basicCharacterDetails = basicCharacterDetails;
        notifyDataSetChanged();
    }

    public CharacterDao.BasicCharacterDetail getBasicCharacterDetailAt(int position) {
        return basicCharacterDetails.get(position);
    }


    class CharacterHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewRace;
        private TextView textViewClass;
        private TextView textViewLevel;

        CharacterHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_character_name);
            textViewRace = itemView.findViewById(R.id.text_view_race);
            textViewClass = itemView.findViewById(R.id.text_view_class);
            textViewLevel = itemView.findViewById(R.id.text_view_level);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(basicCharacterDetails.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(CharacterDao.BasicCharacterDetail basicCharacterDetail);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
