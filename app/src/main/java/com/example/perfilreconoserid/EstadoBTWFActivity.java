package com.example.perfilreconoserid;

import android.bluetooth.BluetoothAdapter;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.perfilreconoserid.Utilidades.SharedPreferencesReco;
import com.example.perfilreconoserid.data.DatosEsquema;
import com.example.perfilreconoserid.data.TbDatosSQLHelper;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.perfilreconoserid.Utilidades.Constantes.ESTADOBT;
import static com.example.perfilreconoserid.Utilidades.Constantes.ESTADOWIFI;

public class EstadoBTWFActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txBluetooth, txWifi;
    Button btNext;
    Boolean BT = false, WF = false;

    private BluetoothAdapter bAdapter;
    TbDatosSQLHelper datosSQLHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado_b_t_w_f);

        bindUI();

        datosSQLHelper = new TbDatosSQLHelper(this);
        db = datosSQLHelper.getWritableDatabase();
        //Bluetooth
        bAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bAdapter.isEnabled()) {
            txBluetooth.setText("Bluetooth Prendido");
            BT = true;
        } else {
            txBluetooth.setText("Bluetooth Apagado");
            BT = false;
        }

        //WIFI
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                txWifi.setText("Wifi Prendido");
                WF = true;
            } else {
                txWifi.setText("Wifi Apagado");
                WF = false;
            }
        }
    }

    public void bindUI() {
        txBluetooth = (TextView) findViewById(R.id.txBluetooth);
        txWifi = (TextView) findViewById(R.id.txWifi);
        btNext = (Button) findViewById(R.id.btNext);
        btNext.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btNext:
                guardarDatos();
                break;
        }
    }

    public void guardarDatos(){
        ContentValues values = new ContentValues();

        values.put(DatosEsquema.DatosEntry.ESTADOBT , BT);
        values.put(DatosEsquema.DatosEntry.ESTADOWIFI , WF);

        String selection = DatosEsquema.DatosEntry.ID + " LIKE ?";
        String[] selectionArgs = {"1"};

        db.update(
                DatosEsquema.DatosEntry.TABLE_NAME,
                values,
                selection,
                selectionArgs);
        //SharedPreferencesReco.guardarBoolean(this, ESTADOBT, BT);
        //SharedPreferencesReco.guardarBoolean(this, ESTADOWIFI, WF);
        gotoNext();
    }

    public void gotoNext(){
        Intent intent = new Intent(EstadoBTWFActivity.this,GuardarDatosActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }
}