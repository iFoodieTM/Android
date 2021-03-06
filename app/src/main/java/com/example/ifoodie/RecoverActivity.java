package com.example.ifoodie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RecoverActivity extends AppCompatActivity {

    EditText editMail;
    Button ok;
    Button back;
    Retrofit retrofit;
    BienestarApi bienestarApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover);

        editMail = findViewById(R.id.editMail);
        ok = findViewById(R.id.ok);
        back = findViewById(R.id.back);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8888/APIiFoodie/public/index.php/api/") // URL del servidor (API)
                .addConverterFactory(ScalarsConverterFactory.create()) // Conversor de tipos primitivos
                .addConverterFactory(GsonConverterFactory.create()) // Conversor de JSON
                .build();
        bienestarApi = retrofit.create(BienestarApi.class);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btRecover();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    void btRecover() {
        final String mail = editMail.getText().toString();

        Call<String> call = bienestarApi.postRecover(mail);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (mail.isEmpty()){
                    Log.d("Ruben","Error: faltan campos por rellenar");
                    Toast.makeText(RecoverActivity.this, "Faltan campos por rellenar", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RecoverActivity.this, "Revisa tu correo", Toast.LENGTH_SHORT).show();
                    finish();

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}
