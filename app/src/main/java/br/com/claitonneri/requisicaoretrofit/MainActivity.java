package br.com.claitonneri.requisicaoretrofit;

import androidx.appcompat.app.AppCompatActivity;
import br.com.claitonneri.requisicaoretrofit.API.CepService;
import br.com.claitonneri.requisicaoretrofit.Model.Cep;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btnRecuperar;
    private TextView txtResultado;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRecuperar = findViewById(R.id.btn_recuperar);
        txtResultado = findViewById(R.id.txt_resultado);

        // Configuracao Retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl("https://viacep.com.br/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        btnRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                recuperarCepRetrofit();
            }
        });
    }

    private void recuperarCepRetrofit(){

        // Chamada
        CepService cepService = retrofit.create(CepService.class);
        Call<Cep> call = cepService.recuperarCEP();

        call.enqueue(new Callback<Cep>() {
            @Override
            public void onResponse(Call<Cep> call, Response<Cep> response) {

                if(response.isSuccessful()){
                    Cep cep = response.body();
                    txtResultado.setText(cep.getLocalidade() + " / " + cep.getUf());
                }
            }

            @Override
            public void onFailure(Call<Cep> call, Throwable t) {

            }
        });
    }
}
