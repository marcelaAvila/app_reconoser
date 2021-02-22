package com.example.perfilreconoserid;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.perfilreconoserid.Utilidades.SharedPreferencesReco;
import com.example.perfilreconoserid.data.DatosEsquema;
import com.example.perfilreconoserid.data.TbDatosSQLHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.perfilreconoserid.Utilidades.Constantes.*;

public class GuardarDatosActivity extends AppCompatActivity implements View.OnClickListener, progresoInterface {

    Button btGuardar;
    private Gson gson;
    TbDatosSQLHelper datosSQLHelper;
    SQLiteDatabase db;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardar_datos);
        btGuardar = findViewById(R.id.btGuardar);
        btGuardar.setOnClickListener(this);
        gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        datosSQLHelper = new TbDatosSQLHelper(this);
        db = datosSQLHelper.getWritableDatabase();
        progressDialog = new ProgressDialog(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btGuardar:
                crearJSON();
                break;
        }
    }

    public void crearJSON() {
        String query = "select * from " + DatosEsquema.DatosEntry.TABLE_NAME + " WHERE _id=?";
        Cursor c = db.rawQuery(query, new String[]{"1"});
        JSONDatos jsonDatos = new JSONDatos();
        if(c.moveToFirst()){
            while (c.isAfterLast() == false){
                jsonDatos.setNombre(c.getString(c.getColumnIndex(DatosEsquema.DatosEntry.NOMBRE)));
                jsonDatos.setCedula(c.getString(c.getColumnIndex(DatosEsquema.DatosEntry.CEDULA)));
                jsonDatos.setDireccion(c.getString(c.getColumnIndex(DatosEsquema.DatosEntry.DIRECCION)));
                jsonDatos.setCiudad(c.getString(c.getColumnIndex(DatosEsquema.DatosEntry.CIUDAD)));
                jsonDatos.setPais(c.getString(c.getColumnIndex(DatosEsquema.DatosEntry.PAIS)));
                jsonDatos.setCelular(c.getString(c.getColumnIndex(DatosEsquema.DatosEntry.CELULAR)));
                jsonDatos.setImagen(c.getString(c.getColumnIndex(DatosEsquema.DatosEntry.IMAGEN)));
                jsonDatos.setLatitud(c.getString(c.getColumnIndex(DatosEsquema.DatosEntry.LATITUD)));
                jsonDatos.setLongitud(c.getString(c.getColumnIndex(DatosEsquema.DatosEntry.LONGITUD)));
                jsonDatos.setEstadoBT(c.getString(c.getColumnIndex(DatosEsquema.DatosEntry.ESTADOBT)));
                jsonDatos.setEstadoWF(c.getString(c.getColumnIndex(DatosEsquema.DatosEntry.ESTADOWIFI)));
                c.moveToNext();
            }
        }


        /*jsonDatos.setNombre(SharedPreferencesReco.obtenerDatos(this, NOMBRE));
        jsonDatos.setCedula(SharedPreferencesReco.obtenerDatos(this, CEDULA));
        jsonDatos.setDireccion(SharedPreferencesReco.obtenerDatos(this, DIRECCION));
        jsonDatos.setCiudad(SharedPreferencesReco.obtenerDatos(this, CIUDAD));
        jsonDatos.setPais(SharedPreferencesReco.obtenerDatos(this, PAIS));
        jsonDatos.setCelular(SharedPreferencesReco.obtenerDatos(this, CELULAR));
        //jsonDatos.setImagen(SharedPreferencesReco.obtenerDatos(this, IMAGEN));
        jsonDatos.setImagen("Imagen");
        jsonDatos.setLatitud(SharedPreferencesReco.obtenerDatos(this, LATITUD));
        jsonDatos.setLongitud(SharedPreferencesReco.obtenerDatos(this, LONGITUD));
        jsonDatos.setEstadoBT(SharedPreferencesReco.obtenerBoolean(this, ESTADOBT));
        jsonDatos.setEstadoWF(SharedPreferencesReco.obtenerBoolean(this, ESTADOWIFI));
*/
        String jsonString = gson.toJson(jsonDatos);
        System.out.println(jsonString);

        AsynkTaskGuardarDatos asynkTaskGuardarDatos = new AsynkTaskGuardarDatos(this,jsonString, this);
        asynkTaskGuardarDatos.execute();

    }

    @Override
    public void showProgressDialog() {
        progressDialog.setMessage(getResources().getString(R.string.msgProgressDialog));
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
    }

    @Override
    public void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}