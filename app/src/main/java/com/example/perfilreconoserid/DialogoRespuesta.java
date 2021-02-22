package com.example.perfilreconoserid;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;

public class DialogoRespuesta extends Dialog {

    String textoRespuesta;

    TextView etObservaciones;
    Button btAceptar, btNuevo;
    Activity activity;

    public DialogoRespuesta(@NonNull Context context,Activity activity,String textoRespuesta) {
        super(context);
        this.activity = activity;
        this.textoRespuesta = textoRespuesta;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialogo);

        etObservaciones = (TextView) findViewById(R.id.etObservaciones);
        btAceptar = (Button) findViewById(R.id.btAceptar);
        btNuevo = (Button) findViewById(R.id.btNuevo);

        etObservaciones.setText(textoRespuesta);

        btAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finishAffinity();
            }
        });

        btNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity,CapturaDatosActivity.class);
                activity.startActivity(intent);
            }
        });

    }
}
