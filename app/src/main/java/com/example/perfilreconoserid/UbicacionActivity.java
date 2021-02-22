package com.example.perfilreconoserid;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.perfilreconoserid.Utilidades.SharedPreferencesReco;
import com.example.perfilreconoserid.data.DatosEsquema;
import com.example.perfilreconoserid.data.TbDatosSQLHelper;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import static com.example.perfilreconoserid.Utilidades.Constantes.LATITUD;
import static com.example.perfilreconoserid.Utilidades.Constantes.LONGITUD;

public class UbicacionActivity extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback {

    Button btNext;
    TextView txLongitud,txLLatitud;

    SupportMapFragment mapFragment;
    private GoogleMap mMap;

    private LocationManager locationManager;
    TbDatosSQLHelper datosSQLHelper;
    SQLiteDatabase db;

    String longitud;
    String latitud;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion);

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);
        bindUI();
        datosSQLHelper = new TbDatosSQLHelper(this);
        db = datosSQLHelper.getWritableDatabase();
    }

    public void bindUI() {
        btNext = (Button) findViewById(R.id.btNext);
        txLLatitud = (TextView) findViewById(R.id.txLLatitud);
        txLongitud = (TextView) findViewById(R.id.txLongitud);
        btNext.setOnClickListener(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1000,0,new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                LatLng place = new LatLng(location.getLatitude(),location.getLongitude());
                agregarMarcador(place);
                longitud = Double.toString(location.getLongitude());
                latitud = Double.toString(location.getLatitude());

                txLLatitud.setText("Latitud: "+latitud);
                txLongitud.setText("Longitud: "+longitud);
            }

            @Override
            public void onStatusChanged(String s,int i,Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        });

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
        if (longitud != null || latitud != null){
            ContentValues values = new ContentValues();

            values.put(DatosEsquema.DatosEntry.LATITUD , latitud);
            values.put(DatosEsquema.DatosEntry.LONGITUD , longitud);

            String selection = DatosEsquema.DatosEntry.ID + " LIKE ?";
            String[] selectionArgs = {"1"};

            db.update(
                    DatosEsquema.DatosEntry.TABLE_NAME,
                    values,
                    selection,
                    selectionArgs);

            //SharedPreferencesReco.guardarString(this,LONGITUD,longitud);
            //SharedPreferencesReco.guardarString(this,LATITUD,latitud);
            gotoNext();
        }else{
            Toast.makeText(this,"Aún no se obtiene información, vuelva a intentarlo",Toast.LENGTH_LONG).show();
        }
    }

    public void gotoNext(){
        Intent intent = new Intent(UbicacionActivity.this,EstadoBTWFActivity.class);
        startActivity(intent);
    }

    public void agregarMarcador(LatLng place) {

        CameraUpdate zoom = CameraUpdateFactory.zoomTo(30);

        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(place).
                draggable(true));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(place));
        mMap.animateCamera(zoom);


    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }

}