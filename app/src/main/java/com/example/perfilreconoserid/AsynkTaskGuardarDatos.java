package com.example.perfilreconoserid;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;

import com.example.perfilreconoserid.Utilidades.ObjectHttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ProtocolException;

import static com.example.perfilreconoserid.Utilidades.Constantes.URL;

public class AsynkTaskGuardarDatos extends AsyncTask<Void, Void, Boolean> {

    private Activity activity;
    private String parametros;
    String respuesta;
    Boolean estado;
    progresoInterface progresoInterface;

    public AsynkTaskGuardarDatos(Activity activity,String parametros, progresoInterface progresoInterface) {
        this.activity = activity;
        this.parametros = parametros;
        this.progresoInterface = progresoInterface;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progresoInterface.showProgressDialog();
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        progresoInterface.dismissProgressDialog();
        if (aBoolean) {
            DialogoRespuesta dialogObservacion = new DialogoRespuesta(activity,activity,respuesta);
            dialogObservacion.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialogObservacion.setCancelable(false);
            dialogObservacion.show();
        }
    }

    @Override
    protected Boolean doInBackground(Void... voids) {

        try {

            ObjectHttp httResponse = new ObjectHttp(activity);
            String responseStr = httResponse.obtenerHttpConDatos(URL,parametros);

            JSONObject jsonObject = new JSONObject(responseStr);

            estado = jsonObject.getBoolean("error");
            respuesta = jsonObject.getString("message");

            return true;

        } catch (ProtocolException e) {
            e.printStackTrace();
            return false;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }
}
