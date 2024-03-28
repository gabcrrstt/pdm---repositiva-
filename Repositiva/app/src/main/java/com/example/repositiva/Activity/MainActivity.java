package com.example.repositiva.Activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.repositiva.R;

public class MainActivity extends AppCompatActivity {
    private Button btnConselhoAleatorio;
    private Button btnBuscarPorNome;
    private Button btnBuscarPorId;
    private Button btnFavoritos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnConselhoAleatorio = findViewById(R.id.btnConselhoAleatorio);
        btnBuscarPorNome = findViewById(R.id.btnBuscarPorNome);
        btnBuscarPorId = findViewById(R.id.btnBuscarPorId);
        btnFavoritos = findViewById(R.id.btnFavoritos);

        btnConselhoAleatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConselhoAleatorioActivity.class);
                startActivity(intent);
            }
        });

        btnBuscarPorNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BuscarConselhoActivity.class);
                startActivity(intent);
            }
        });

        btnBuscarPorId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BuscarConselhoActivity.class);
                startActivity(intent);
            }
        });

        btnFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FavoritosActivity.class);
                startActivity(intent);
            }
        });
    }

}
