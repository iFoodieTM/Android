package com.example.ifoodie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
    EditText editUsername;
    ImageButton imagePhoto;
    Button ok;

    Retrofit retrofit;
    BienestarApi bienestarApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editName = findViewById(R.id.editName);
        editMail = findViewById(R.id.editMail);
        editPass = findViewById(R.id.editPass);
        editUsername = findViewById(R.id.editUsername);
        imagePhoto = findViewById(R.id.imagePhoto);
        ok = findViewById(R.id.ok);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8888/Ruben/iFoodie/public/index.php/api/") // URL del servidor (API)
                .addConverterFactory(ScalarsConverterFactory.create()) // Conversor de tipos primitivos
                .addConverterFactory(GsonConverterFactory.create()) // Conversor de JSON
                .build();
        bienestarApi = retrofit.create(BienestarApi.class);

    ok.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            btAccept();
        }
    });



    }


    void btAccept() {
        final String nombre = editName.getText().toString();
        final String mail = editMail.getText().toString();
        final String pass = editPass.getText().toString();
        final String userName = editUsername.getText().toString();
        final String photo = imagePhoto.toString();

        Call<String> call = bienestarApi.postRegister(nombre, mail, userName, pass, photo);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (nombre.isEmpty() || mail.isEmpty() || userName.isEmpty()
                        || pass.isEmpty() || photo.isEmpty()){
                    Log.d("Ruben","Error: faltan campos por rellenar");
                    Toast.makeText(RegisterActivity.this, "Faltan campos por rellenar", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "Registro completado", Toast.LENGTH_SHORT).show();
                    finish();

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Algo ha ido mal", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
