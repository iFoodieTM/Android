package com.example.ifoodie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    EditText editName;
    EditText editMail;
    EditText editPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editName = findViewById(R.id.editName);
        editMail = findViewById(R.id.editMail);
        editPass = findViewById(R.id.editPass);
    }


    public void btAccept(View view) {
        String nombre = editName.getText().toString();
        String mail = editMail.getText().toString();
        String pass = editPass.getText().toString();

        if (nombre.isEmpty() || mail.isEmpty() || pass.isEmpty()){
            Log.d("Ruben","Error: faltan campos por rellenar");
        } else {
            Log.d("Ruben","Campos rellenados");
            Intent intent = new Intent();
            intent.putExtra("textoEnviado", nombre);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
