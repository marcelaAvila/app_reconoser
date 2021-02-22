package com.example.perfilreconoserid.Utilidades;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ObjectHttp {

    Context mContext;

    public ObjectHttp(Context mContext){
        this.mContext = mContext;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public String obtenerHttpConDatos(String urlStr, String parametros) throws IOException {

        String urlParameters = parametros;
        byte[] postData       = urlParameters.getBytes("utf-8");
        String request        = urlStr;
        URL url            = new URL( request );
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setDoOutput( true );
        conn.setInstanceFollowRedirects( false );
        conn.setRequestMethod( "POST" );
        conn.setRequestProperty( "Content-Type", "application/json");
        conn.setRequestProperty( "Accept", "application/json");
        conn.setUseCaches( false );
        try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
            wr.write( postData );
        }
        int responseCode = conn.getResponseCode();

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line = "";
        StringBuilder responseOutPut = new StringBuilder();

        while ((line = br.readLine()) != null) {
            responseOutPut.append(line);
        }

        br.close();
        Log.v("ObjectHttpS url",request);
        Log.v("ObjectHttpS params",urlParameters);
        Log.v("ObjectHttpS response",responseOutPut.toString());
        return responseOutPut.toString();
    }
}
