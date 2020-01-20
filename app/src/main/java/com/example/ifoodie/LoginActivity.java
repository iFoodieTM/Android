package com.example.ifoodie;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class LoginActivity extends AppCompatActivity {

    EditText editMail;
    EditText editPass;
    Button ok;

    Retrofit retrofit;
    BienestarApi bienestarApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editMail = findViewById(R.id.editMail);
        editPass = findViewById(R.id.editPass);
        ok = findViewById(R.id.ok);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8888/Ruben/Bienestapp/public/index.php/api/") // URL del servidor (API)
                .addConverterFactory(ScalarsConverterFactory.create()) // Conversor de tipos primitivos
                .addConverterFactory(GsonConverterFactory.create()) // Conversor de JSON
                .build();
        bienestarApi = retrofit.create(BienestarApi.class);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btLogin();
            }
        });
    }

    public void btRegistrar(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);

    }

    public void btLogin() {
        final String email = editMail.getText().toString();
        final String pass = editPass.getText().toString();

        Call<String> call = bienestarApi.postLogin(email, pass);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if (email.isEmpty() || pass.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Faltan campos por rellenar", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Login correcto", Toast.LENGTH_SHORT).show();
                    Log.d("Ruben",response.body());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Algo ha ido mal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
