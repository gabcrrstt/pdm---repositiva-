package com.example.repositiva.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.repositiva.AdviceResponse;
import com.example.repositiva.AdviceService;
import com.example.repositiva.Conselho;
import com.example.repositiva.Helper.ConselhoDatabaseHelper;
import com.example.repositiva.R;
import com.example.repositiva.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuscarConselhoActivity extends AppCompatActivity {

    private EditText inputBuscaNome;
    private EditText inputBuscaId;
    private Button btnBuscarNome;
    private Button btnBuscarId;
    private TextView resultadoTextView;;
    private Button btnFavoritar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buscar_conselho);

        inputBuscaNome = findViewById(R.id.input_busca_nome);
        inputBuscaId = findViewById(R.id.input_busca_id);
        btnBuscarNome = findViewById(R.id.btn_buscar_nome);

        btnBuscarId = findViewById(R.id.btn_buscar_id);

        resultadoTextView = findViewById(R.id.resultadoTextView);

        btnFavoritar = findViewById(R.id.btn_favoritar);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnBuscarNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = inputBuscaNome.getText().toString().trim();
                if (!nome.isEmpty()) {
                    buscarConselhoPorNome(nome);
                } else {
                    Toast.makeText(BuscarConselhoActivity.this, "Por favor, insira um nome para buscar", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBuscarId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idStr = inputBuscaId.getText().toString().trim();
                if (idStr.isEmpty()) {
                    Toast.makeText(BuscarConselhoActivity.this, "Por favor, insira um ID para buscar", Toast.LENGTH_SHORT).show();
                } else {
                    int id = Integer.parseInt(idStr);
                    buscarConselhoPorId(id);
                }
            }
        });

        // No clique do botão para favoritar o conselho na activity BuscarConselhoActivity
        btnFavoritar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConselhoDatabaseHelper dbHelper = new ConselhoDatabaseHelper(getApplicationContext());
                Conselho favorito = new Conselho();
                favorito.setTextoConselho(resultadoTextView.getText().toString());
                dbHelper.adicionarConselho(favorito);
                Toast.makeText(getApplicationContext(), "Conselho favoritado!", Toast.LENGTH_SHORT).show();
 
                Intent intent = new Intent(BuscarConselhoActivity.this, FavoritosActivity.class);

                intent.putExtra("textoConselho", resultadoTextView.getText().toString());

                startActivity(intent);
            }
        });




    }

        private void buscarConselhoPorNome (String nome){
            AdviceService service = RetrofitClientInstance.getRetrofitInstance().create(AdviceService.class);
            Call<AdviceResponse> call = service.searchAdvice(nome);
            call.enqueue(new Callback<AdviceResponse>() {

                @Override
                public void onResponse(Call<AdviceResponse> call, Response<AdviceResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        AdviceResponse adviceResponse = response.body();
                        exibirResultados(adviceResponse);
                    } else {
                        Toast.makeText(BuscarConselhoActivity.this, "Nenhum conselho encontrado para o nome '" + nome + "'", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<AdviceResponse> call, Throwable t) {
                    Toast.makeText(BuscarConselhoActivity.this, "Erro ao buscar conselho por nome", Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void buscarConselhoPorId ( int id){
            AdviceService service = RetrofitClientInstance.getRetrofitInstance().create(AdviceService.class);
            Call<AdviceResponse> call = service.getAdviceById(id);

            call.enqueue(new Callback<AdviceResponse>() {
                @Override
                public void onResponse(Call<AdviceResponse> call, Response<AdviceResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        AdviceResponse adviceResponse = response.body();
                        exibirResultados(adviceResponse);
                    } else {
                        Toast.makeText(BuscarConselhoActivity.this, "Nenhum conselho encontrado para o ID '" + id + "'", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<AdviceResponse> call, Throwable t) {
                    Toast.makeText(BuscarConselhoActivity.this, "Erro ao buscar conselho por ID", Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void exibirResultados (AdviceResponse adviceResponse){
            if (adviceResponse != null && adviceResponse.getSlip() != null) {
                String advice = adviceResponse.getSlip().getAdvice();
                resultadoTextView.setText(advice);
            } else {
                Toast.makeText(this, "Erro ao exibir resultados", Toast.LENGTH_SHORT).show();
            }
        }
    }


