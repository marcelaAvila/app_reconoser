package com.example.perfilreconoserid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txIngresaInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindUI();

        txIngresaInfo.setOnClickListener(this);
    }

    public void bindUI() {
        txIngresaInfo = (TextView) findViewById(R.id.txIngresaInfo);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.txIngresaInfo:
                Intent intent = new Intent(MainActivity.this,CapturaDatosActivity.class);
                startActivity(intent);
                break;
        }
    }
}