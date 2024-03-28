package com.example.repositiva.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repositiva.Conselho;
import com.example.repositiva.R;

import java.util.List;

public class ConselhosAdapter extends RecyclerView.Adapter<ConselhosAdapter.ViewHolder> {

    private List<Conselho> conselhosList;
    private Context context;

    public ConselhosAdapter(Context context, List<Conselho> conselhosList) {
        this.context = context;
        this.conselhosList = conselhosList;
    }


    public ConselhosAdapter(List<Conselho> conselhosList) {
        this.conselhosList = conselhosList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_favoritos, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Conselho conselho = conselhosList.get(position);
        holder.textConselho.setText(conselho.getTextoConselho());
    }

    @Override
    public int getItemCount() {
        return conselhosList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textConselho;

        public ViewHolder(View view) {
            super(view);
            textConselho = view.findViewById(R.id.textoConse);
        }
    }
}
