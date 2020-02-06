package com.example.ifoodie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

public class FeedActivity extends AppCompatActivity {

    Button nuevaReceta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        nuevaReceta = findViewById(R.id.nuevaReceta);
        nuevaReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newView();
            }
        });
    }

    private void newView(){

        Intent intent = new Intent(this, addRecipe.class);
        startActivity(intent);

    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
*/
}
