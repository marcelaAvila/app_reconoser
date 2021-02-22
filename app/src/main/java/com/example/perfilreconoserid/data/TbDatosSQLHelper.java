package com.example.perfilreconoserid.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TbDatosSQLHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Reconoser.db";
    String sqlCreate = "CREATE TABLE " + DatosEsquema.DatosEntry.TABLE_NAME + " ("
            + DatosEsquema.DatosEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + DatosEsquema.DatosEntry.ID + " TEXT NOT NULL,"
            + DatosEsquema.DatosEntry.NOMBRE + " TEXT NOT NULL,"
            + DatosEsquema.DatosEntry.CEDULA + " TEXT NOT NULL,"
            + DatosEsquema.DatosEntry.DIRECCION + " TEXT NOT NULL,"
            + DatosEsquema.DatosEntry.CIUDAD + " TEXT NOT NULL,"
            + DatosEsquema.DatosEntry.PAIS + " TEXT NOT NULL,"
            + DatosEsquema.DatosEntry.CELULAR + " TEXT NOT NULL,"
            + DatosEsquema.DatosEntry.IMAGEN + " TEXT NOT NULL,"
            + DatosEsquema.DatosEntry.LATITUD + " TEXT NOT NULL,"
            + DatosEsquema.DatosEntry.LONGITUD + " TEXT NOT NULL,"
            + DatosEsquema.DatosEntry.ESTADOBT + " TEXT NOT NULL,"
            + DatosEsquema.DatosEntry.ESTADOWIFI + " TEXT NOT NULL,"
            + "UNIQUE (" + DatosEsquema.DatosEntry.ID + "))";

    public TbDatosSQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,int i,int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DatosEsquema.DatosEntry.TABLE_NAME);
        sqLiteDatabase.execSQL(sqlCreate);
    }
}
