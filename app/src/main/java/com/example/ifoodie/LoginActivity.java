package com.example.ifoodie;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        texto = findViewById(R.id.textEjemplo);
    }

    public void btRegistrar(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivityForResult(intent,1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String text = data.getStringExtra("textoEnviado");
                texto.setText(text);
            } else {
                Log.d("**********", "No se ha escrito nada");
            }
        }

    }

    public void btLogin(View view) {
    }
}
