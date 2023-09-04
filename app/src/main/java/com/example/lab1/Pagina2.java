package com.example.lab1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pagina2 extends AppCompatActivity {

    private String[] palabras;
    private Random random;

    private String repetida;
    private TextView[] charViews;
    private LinearLayout wordLayout;
    private AdaptadorLetras adaptador;
    private GridView gridView;
    private int numeroPartidasJugadas = 0;

    private int numCorr;

    private int numChars;
    private ImageView[]partes;
    private int sizeParts=6;
    private int currPart;
    ArrayList<String> estadisticas = new ArrayList<>();


    private TextView resultadoTextView;

    private TextView tiempoTextView;
    private long tiempoInicio;

    private List<String> resultadosJuegos = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina2);
        palabras=getResources().getStringArray(R.array.palabras);
        wordLayout=findViewById(R.id.palabras);
        gridView=findViewById(R.id.letras);
        random=new Random();
        partes = new ImageView[sizeParts];
        partes[0] = findViewById(R.id.cabeza);
        partes[1] = findViewById(R.id.cuerpo);
        partes[2] = findViewById(R.id.brazoizquierdo);
        partes[3] = findViewById(R.id.brazoderecho);
        partes[4] = findViewById(R.id.piernaderecha);
        partes[5] = findViewById(R.id.piernaizquierda);
        Button nuevoJuegoButton = findViewById(R.id.miBoton);
        nuevoJuegoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarNuevaPartida();
            }
        });


        jugarJuego();
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("TeleAhorcado");
        resultadoTextView = findViewById(R.id.resultado);
        tiempoTextView = findViewById(R.id.tiempo);
    }

    private void jugarJuego(){

        tiempoInicio = System.currentTimeMillis();
        String nuevaPalabra=palabras[random.nextInt(palabras.length)];
        while(nuevaPalabra.equals(repetida))nuevaPalabra=palabras[random.nextInt(palabras.length)];
        repetida = nuevaPalabra;
        charViews=new TextView[repetida.length()];


        for(int i=0; i<repetida.length(); i++){
            charViews[i] = new TextView(this);
            charViews[i].setText(""+repetida.charAt(i));
            charViews[i].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            charViews[i].setGravity(Gravity.CENTER);
            charViews[i].setTextColor(Color.WHITE);
            charViews[i].setBackgroundResource(R.drawable.letter_bg);
            wordLayout.addView(charViews[i]);
        }
        adaptador=new AdaptadorLetras(this);
        gridView.setAdapter(adaptador);
        numCorr=0;
        currPart=0;
        numChars=repetida.length();
    }


    public void letterPressed(View view){
        String letra = ((TextView)view).getText().toString();
        char letraChar = letra.charAt(0);
        view.setEnabled(false);
        boolean correct=false;
        for(int i=0;i<repetida.length();i++){
            if(repetida.charAt(i)==letraChar){
                correct=true;
                numCorr++;
                charViews[i].setTextColor(Color.BLACK);
            }
        }
        if(correct){
            if(numCorr==numChars){
                disableButtons();
                long tiempoTranscurrido = System.currentTimeMillis() - tiempoInicio;
                int segundos = (int) (tiempoTranscurrido / 1000);
                resultadosJuegos.add("Juego " + (resultadosJuegos.size() + 1) + ": Terminó en " + segundos + "s");
                resultadoTextView.setText("Ganó en ");
                estadisticas.add("Terminó en "+ (segundos)+"s");
                tiempoTextView.setText(segundos + "s");
                resultadoTextView.setVisibility(View.VISIBLE);
                tiempoTextView.setVisibility(View.VISIBLE);
            }
        }else if(currPart<sizeParts){
            partes[currPart].setVisibility(View.VISIBLE);
            currPart++;
        }else{
            disableButtons();
            long tiempoTranscurrido = System.currentTimeMillis() - tiempoInicio;
            int segundos = (int) (tiempoTranscurrido / 1000);
            resultadosJuegos.add("Juego " + (resultadosJuegos.size() + 1) + ": Terminó en " + segundos + "s");
            resultadoTextView.setText("Perdió en ");
            estadisticas.add("Terminó en "+ (segundos)+"s");
            tiempoTextView.setText(segundos + "s");
            resultadoTextView.setVisibility(View.VISIBLE);
            tiempoTextView.setVisibility(View.VISIBLE);
        }
    }
    public void disableButtons(){
        for(int i=0; i<gridView.getChildCount();i++){
            gridView.getChildAt(i).setEnabled(false);
        }
    }

    private void iniciarNuevaPartida() {
        resultadoTextView.setVisibility(View.INVISIBLE);
        tiempoTextView.setVisibility(View.INVISIBLE);
        for (TextView charView : charViews) {
            wordLayout.removeView(charView);
        }
        for (ImageView parte : partes) {
            parte.setVisibility(View.INVISIBLE);
        }
        jugarJuego();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.popupmenupag2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_estadisticas) {
            PopupMenu popupMenu = new PopupMenu(this, findViewById(R.id.action_estadisticas));
            getMenuInflater().inflate(R.menu.popupmenupag2, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    // Manejar las opciones de menú aquí
                    if (item.getItemId() == R.id.action_estadisticas) {
                        Intent intent = new Intent(Pagina2.this, EstadisticasActivity.class);
                        intent.putExtra("estadisticas",estadisticas);
                        startActivity(intent);
                        return true;
                    }
                    return false;
                }
            });
            popupMenu.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }





}