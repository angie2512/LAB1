package com.example.lab1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EstadisticasActivity extends AppCompatActivity {

    private RecyclerView recyclerViewEstadisticas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("TeleAhorcado");
        Button nuevoJuegoButton = findViewById(R.id.button2);
        nuevoJuegoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Iniciar la actividad Pagina2 para un nuevo juego
                Intent intent = new Intent(EstadisticasActivity.this, Pagina2.class);
                startActivity(intent);
            }
        });
    }
}