package com.example.lab1;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

public class AdaptadorLetras extends BaseAdapter {
    private String[] letras;
    private LayoutInflater letrasInf;
    public AdaptadorLetras(Context context){
        letras=new String[26];
        for(int i=0; i<letras.length;i++){
            letras[i] = ""+(char)(i+'A');
        }
        letrasInf=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return letras.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Button botonesLetras;
        if(view==null) {
            botonesLetras = (Button) letrasInf.inflate(R.layout.letras, viewGroup, false);
        }else{
            botonesLetras=(Button) view;
        }
        botonesLetras.setText(letras[i]);

        botonesLetras.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        int padding = 16;
        botonesLetras.setPadding(padding, padding, padding, padding);
        botonesLetras.setGravity(Gravity.CENTER);
        return botonesLetras;
    }
}
