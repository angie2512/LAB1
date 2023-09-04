package com.example.lab1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class EstadisticasActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);
        setTitle("Juego - Memoria");

        Intent intent = this.getIntent();
        ArrayList<String> estad = intent.getStringArrayListExtra("estadisticas");
        LinearLayout layout = findViewById(R.id.linearLayout1);
        for(int i = 0; i< estad.size(); i++){
            TextView tVEstad = new TextView(this);
            tVEstad.setTextSize(18);
            tVEstad.setText("Juego " +(i+1)+ ": "+estad.get(i));
            if(estad.get(i).equals("Canceló")){
                tVEstad.setTextColor(Color.parseColor("#f20e0e"));
            }else{
                tVEstad.setTextColor(Color.parseColor("#3d9e09"));
            }
            layout.addView(tVEstad);
        }

        Button btnNuevoMemoria = findViewById(R.id.button2);
        btnNuevoMemoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EstadisticasActivity.this, Pagina2.class);
                startActivity(intent);
            }
        });
    }
}