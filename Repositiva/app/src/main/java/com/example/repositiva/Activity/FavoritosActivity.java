package com.example.repositiva.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.repositiva.Adapter.ConselhosAdapter;
import com.example.repositiva.Conselho;
import com.example.repositiva.Helper.ConselhoDatabaseHelper;
import com.example.repositiva.R;

import java.util.ArrayList;
import java.util.List;

public class FavoritosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ConselhosAdapter adapter;
    private List<Conselho> conselhosList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        recyclerView = findViewById(R.id.recyclerViewFavoritos);
        conselhosList = new ArrayList<>();
        adapter = new ConselhosAdapter(conselhosList); // Corrigido para ConselhosAdapter

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        // Aqui você precisa recuperar os dados do banco de dados e adicionar à lista conselhosList
        ConselhoDatabaseHelper dbHelper = new ConselhoDatabaseHelper(this);
        conselhosList.addAll(dbHelper.getAllConselhos());
        adapter.notifyDataSetChanged();
    }
}
