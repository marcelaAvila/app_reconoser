package com.example.perfilreconoserid.Utilidades;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class SharedPreferencesReco {

    public static final String nombreArchivo = "PreferenciasReconoSer";

    public static void guardarString(Context context,String key,String dato) {
        SharedPreferences prefs = context.getSharedPreferences(nombreArchivo,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key,dato);
        editor.commit();
    }

    public static void guardarNombre(Context context,String dato) {
        SharedPreferences prefs = context.getSharedPreferences(nombreArchivo,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("nombre",dato);
        editor.commit();
    }

    public static String obtenerDatos(Context context,String key) {
        SharedPreferences prefs = context.getSharedPreferences(nombreArchivo,Context.MODE_PRIVATE);
        return prefs.getString(key,"Vacio");
    }

    public static void guardarImagen(Context context,String key,Bitmap imagen) {
        SharedPreferences prefs = context.getSharedPreferences(nombreArchivo,Context.MODE_PRIVATE);
        prefs.edit().clear().commit();
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key,encodeTobase64(imagen));
        editor.commit();

    }

    public static Bitmap obtenerImagen(Context context) {
       SharedPreferences prefs = context.getSharedPreferences(nombreArchivo,Context.MODE_PRIVATE);
        return decodeBase64(prefs.getString("Imagen","Sin Imagen"));
    }

    public static String encodeTobase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG,100,baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b,Base64.DEFAULT);

        return imageEncoded;
    }

    public static Bitmap decodeBase64(String input) {
        byte[] decodedByte = Base64.decode(input,0);
        return BitmapFactory.decodeByteArray(decodedByte,0,decodedByte.length);
    }

    public static void guardarBoolean(Context context,String key,Boolean dato) {
        SharedPreferences prefs = context.getSharedPreferences(nombreArchivo,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key,dato);
        editor.commit();
    }

    public static boolean obtenerBoolean(Context context,String key) {
        SharedPreferences prefs = context.getSharedPreferences(nombreArchivo,Context.MODE_PRIVATE);
        return prefs.getBoolean(key,false);
    }


}
