package com.example.ifoodie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    EditText editMail;
    EditText editPass;
    EditText editPass2;

    TextView errorMail;
    TextView errorUser;
    TextView errorPass;

    EditText editUsername;
    Button ok;
    Button back;

    Retrofit retrofit;
    BienestarApi bienestarApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editMail = findViewById(R.id.editMail);
        editPass = findViewById(R.id.editPass);
        editPass2 = findViewById(R.id.editPass2);

        errorMail = findViewById(R.id.mailError);
        errorUser = findViewById(R.id.passError);
        errorPass = findViewById(R.id.userError);

        editUsername = findViewById(R.id.editUsername);
        back = findViewById(R.id.back);
        ok = findViewById(R.id.ok);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8888/APIiFoodie/public/index.php/api/") // URL del servidor (API)
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

    back.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    });
    }


    void btAccept() {
        final String mail = editMail.getText().toString();
        final String pass = editPass.getText().toString();
        final String pass2 = editPass2.getText().toString();
        final String userName = editUsername.getText().toString();

        Call<String> call = bienestarApi.postRegister(mail, userName, pass);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                boolean checkForm = true;
                String message = "";
                if (!mail.isEmpty()){
                    if (!isEmailValid(mail)){
                        // no es un email valido
                        checkForm = false;
                        errorMail.setVisibility(View.VISIBLE);
                        errorMail.setText("El email no es válido.");
                        //message += "El email no es válido. ";
                    }else{

                        errorMail.setVisibility(View.INVISIBLE);
                    }
                }else{
                    checkForm = false;
                    errorMail.setVisibility(View.VISIBLE);
                    errorMail.setText("El email esta vacío.");
                    //message += "El email esta vacío. ";
                }

                if (userName.isEmpty()){
                    // el nombre de susuario esta vacio
                    checkForm = false;
                    errorUser.setVisibility(View.VISIBLE);
                    errorUser.setText("El nombre esta vacío.");
                    //message += "El nombre de usuario esta vacío. ";
                }else{
                    errorUser.setVisibility(View.INVISIBLE);
                }

                if (!pass.isEmpty() && !pass2.isEmpty()){
                    if (!pass.equals(pass2)){
                        Log.d("Ruben","pass: "+ pass);
                        Log.d("Ruben","pass2: "+ pass2);
                        checkForm = false;

                        errorPass.setVisibility(View.VISIBLE);
                        errorPass.setText("Las contraseñas no son iguales.");

                        //message += "Las contraseñas no son iguales. ";
                    }
                }else{
                    checkForm = false;
                    errorPass.setVisibility(View.VISIBLE);
                    errorPass.setText("Debes rellenar los campos de contraseña.");
                   // message += "Debes rellenar los campos de contraseña. ";
                }

                if (!checkForm){
                    Log.d("Ruben","Aviso: "+ message);
                    Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();
                } else {

                    Toast.makeText(RegisterActivity.this, "Registro completado", Toast.LENGTH_LONG).show();
                    finish();

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Algo ha ido mal", Toast.LENGTH_SHORT).show();
            }
        });


    }
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
