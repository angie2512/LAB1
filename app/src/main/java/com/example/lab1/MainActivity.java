package com.example.lab1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView titleTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        titleTextView = findViewById(R.id.textView2);
        registerForContextMenu(titleTextView);
        // Cambia el texto del título
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

    }

    public void irAJugar(View view){

        Intent intent = new Intent(this, Pagina2.class);
        startActivity(intent);//donde estoy y a donde voy

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu); // R.menu.color_menu es el recurso del menú contextual
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        TextView textView = findViewById(R.id.textView2);

        if (item.getItemId() == R.id.color_blue) {
            textView.setTextColor(ContextCompat.getColor(this, android.R.color.holo_blue_dark));
            return true;
        } else if (item.getItemId() == R.id.color_green) {
            textView.setTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark));
            return true;
        } else if (item.getItemId() == R.id.color_red) {
            textView.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
            return true;
        }

        return super.onContextItemSelected(item);
    }








}