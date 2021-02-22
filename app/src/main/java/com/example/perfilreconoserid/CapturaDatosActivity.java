package com.example.perfilreconoserid;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.perfilreconoserid.Utilidades.SharedPreferencesReco;
import com.example.perfilreconoserid.data.DatosEsquema;
import com.example.perfilreconoserid.data.TbDatosSQLHelper;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.perfilreconoserid.Utilidades.Constantes.CEDULA;
import static com.example.perfilreconoserid.Utilidades.Constantes.CELULAR;
import static com.example.perfilreconoserid.Utilidades.Constantes.CIUDAD;
import static com.example.perfilreconoserid.Utilidades.Constantes.DIRECCION;
import static com.example.perfilreconoserid.Utilidades.Constantes.NOMBRE;
import static com.example.perfilreconoserid.Utilidades.Constantes.PAIS;

public class CapturaDatosActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etNombre, etCedula, etDireccion, etCiudad, etPais, etCelular;
    Button btNext;
    TbDatosSQLHelper datosSQLHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captura_datos);

        bindUI();

        datosSQLHelper = new TbDatosSQLHelper(this);
        db = datosSQLHelper.getWritableDatabase();
    }

    public void bindUI() {
        etNombre = (EditText) findViewById(R.id.etNombre);
        etCedula = (EditText) findViewById(R.id.etCedula);
        etDireccion = (EditText) findViewById(R.id.etDireccion);
        etCiudad = (EditText) findViewById(R.id.etCiudad);
        etPais = (EditText) findViewById(R.id.etPais);
        etCelular = (EditText) findViewById(R.id.etCelular);
        btNext = (Button) findViewById(R.id.btNext);
        btNext.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btNext:
                enviarvalidador();
                break;
        }
    }

    public void enviarvalidador() {
        boolean cancel = false;
        View focusView = null;

        String etNombre1, etCedula1, etDireccion1, etCiudad1, etPais1, etCelular1;
        etNombre1 = etNombre.getText().toString();
        etCedula1 = etCedula.getText().toString();
        etDireccion1 = etDireccion.getText().toString();
        etCiudad1 = etCiudad.getText().toString();
        etPais1 = etPais.getText().toString();
        etCelular1 = etCelular.getText().toString();

        if (TextUtils.isEmpty(etNombre1)) {

            etNombre.setError("Este campo es requerido");
            focusView = etNombre;
            cancel = true;
        } else if (TextUtils.isEmpty(etCedula1)) {
            etCedula.setError("Este campo es requerido");
            focusView = etCedula;
            cancel = true;
        } else if (TextUtils.isEmpty(etDireccion1)) {
            etDireccion.setError("Este campo es requerido");
            focusView = etDireccion;
            cancel = true;
        } else if (TextUtils.isEmpty(etCiudad1)) {
            etCiudad.setError("Este campo es requerido");
            focusView = etCiudad;
            cancel = true;
        } else if (TextUtils.isEmpty(etPais1)) {
            etPais.setError("Este campo es requerido");
            focusView = etPais;
            cancel = true;
        } else if (TextUtils.isEmpty(etCelular1)) {
            etCelular.setError("Este campo es requerido");
            focusView = etCelular;
            cancel = true;
        }


        if (cancel) {
            focusView.requestFocus();
        } else {
            guardarDatos();
        }
    }

    public void guardarDatos() {

        String query = "select * from " + DatosEsquema.DatosEntry.TABLE_NAME + " WHERE _id=?";
        Cursor c = db.rawQuery(query, new String[]{"1"});


        ContentValues values = new ContentValues();
        values.put(DatosEsquema.DatosEntry.ID, "1");
        values.put(DatosEsquema.DatosEntry.NOMBRE,etNombre.getText().toString());
        values.put(DatosEsquema.DatosEntry.CEDULA,etCedula.getText().toString());
        values.put(DatosEsquema.DatosEntry.DIRECCION,etDireccion.getText().toString());
        values.put(DatosEsquema.DatosEntry.CIUDAD,etCiudad.getText().toString());
        values.put(DatosEsquema.DatosEntry.PAIS,etPais.getText().toString());
        values.put(DatosEsquema.DatosEntry.CELULAR,etCelular.getText().toString());
        values.put(DatosEsquema.DatosEntry.IMAGEN, "");
        values.put(DatosEsquema.DatosEntry.LATITUD, "");
        values.put(DatosEsquema.DatosEntry.LONGITUD, "");
        values.put(DatosEsquema.DatosEntry.ESTADOBT, "");
        values.put(DatosEsquema.DatosEntry.ESTADOWIFI, "");

        if (c.getCount()!=0){
            String selection = DatosEsquema.DatosEntry.ID + " LIKE ?";
            String[] selectionArgs = {"1"};

            db.update(
                    DatosEsquema.DatosEntry.TABLE_NAME,
                    values,
                    selection,
                    selectionArgs);
        }else{
            db.insert(
                    DatosEsquema.DatosEntry.TABLE_NAME,
                    null,
                    values);
        }


        /*SharedPreferencesReco.guardarString(this,NOMBRE,etNombre.getText().toString());
        SharedPreferencesReco.guardarString(this,CEDULA,etCedula.getText().toString());
        SharedPreferencesReco.guardarString(this,DIRECCION,etDireccion.getText().toString());
        SharedPreferencesReco.guardarString(this,CIUDAD,etCiudad.getText().toString());
        SharedPreferencesReco.guardarString(this,PAIS,etPais.getText().toString());
        SharedPreferencesReco.guardarString(this,CELULAR,etCelular.getText().toString());*/
        gotoNext();
    }

    public void gotoNext() {
        Intent intent = new Intent(CapturaDatosActivity.this,FotoActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }
}