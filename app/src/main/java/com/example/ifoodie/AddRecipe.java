package com.example.ifoodie;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class AddRecipe extends AppCompatActivity {
    LinearLayout layoutContainer;
    EditText nuevoPaso;
    ImageView añadirPaso;
    int k = -1;
    int flag;
    int ss = 0;
    ArrayList<String> applnserverinstnos = new ArrayList<String>();
    public static EditText textView[] = new EditText[100];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
/*
        nuevoPaso = findViewById(R.id.textStep);
        añadirPaso = findViewById(R.id.addStep);
        layoutContainer = findViewById(R.id.layoutSteps);

        añadirPaso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    k++;
                    flag = k;
                    final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,50);
                    lparams.setMargins(20,20,20,0);
                    textView[flag] = new EditText(AddEditBoxActivity.this);

                }catch (Exception e){
                    e.printStackTrace();
                }
                layoutContainer.addView(textView[flag]);
            }
        });
    */}


}
