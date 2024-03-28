package com.example.repositiva.Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.repositiva.AdviceResponse;
import com.example.repositiva.AdviceService;
import com.example.repositiva.R;
import com.example.repositiva.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConselhoAleatorioActivity extends AppCompatActivity {
    TextView textoConselho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_conselho_aleatorio);

        textoConselho = findViewById(R.id.textoConse);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

      obterConselhoAleatorio();
    }

    private void obterConselhoAleatorio() {
        AdviceService adviceService = RetrofitClientInstance.getRetrofitInstance().create(AdviceService.class);
        Call<AdviceResponse> call = adviceService.getRandomAdvice();
        call.enqueue(new Callback<AdviceResponse>() {
            @Override
            public void onResponse(Call<AdviceResponse> call, Response<AdviceResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    AdviceResponse adviceResponse = response.body();
                    String advice = adviceResponse.getSlip().getAdvice();
                    textoConselho.setText(advice);
                    Log.d("ConselhoAleatorio", "Conselho: " + advice);
                } else {
                    Toast.makeText(ConselhoAleatorioActivity.this, "Falha ao obter conselho aleatório", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AdviceResponse> call, Throwable t) {
                Toast.makeText(ConselhoAleatorioActivity.this, "Erro de conexão", Toast.LENGTH_SHORT).show();
                Log.e("ConselhoAleatorio", "Erro: " + t.getMessage());
            }
        });
    }

}
