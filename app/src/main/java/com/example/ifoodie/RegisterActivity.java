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

public class RegisterActivity extends AppCompatActivity {

    EditText editName;
    EditText editMail;
    EditText editPass;
    Button accept;

    Retrofit retrofit;
    BienestarApi bienestarApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editName = findViewById(R.id.editName);
        editMail = findViewById(R.id.editMail);
        editPass = findViewById(R.id.editPass);
        accept = findViewById(R.id.accept);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8888/Ruben/Bienestapp/public/index.php/api/") // URL del servidor (API)
                .addConverterFactory(ScalarsConverterFactory.create()) // Conversor de tipos primitivos
                .addConverterFactory(GsonConverterFactory.create()) // Conversor de JSON
                .build();
        bienestarApi = retrofit.create(BienestarApi.class);

    accept.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            btAccept();
        }
    });

    }


    void btAccept() {
        String nombre = editName.getText().toString();
        String mail = editMail.getText().toString();
        String pass = editPass.getText().toString();

        if (nombre.isEmpty() || mail.isEmpty() || pass.isEmpty()){
            Log.d("Ruben","Error: faltan campos por rellenar");
        } else {
            Call<String> call = bienestarApi.postRegister(nombre, mail, pass);

            Log.d("Ruben","Error: no funciona");

        }
    }
}
