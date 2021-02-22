package com.example.perfilreconoserid.data;

import android.provider.BaseColumns;

public class DatosEsquema {

    public static abstract class DatosEntry implements BaseColumns {
        public static final String TABLE_NAME ="datos";

        public static final String ID = "id";
        public static final String NOMBRE = "nombre";
        public static final String CEDULA = "cedula";
        public static final String DIRECCION = "direccion";
        public static final String CIUDAD = "ciudad";
        public static final String PAIS = "pais";
        public static final String CELULAR = "celular";
        public static final String IMAGEN = "imagen";
        public static final String LATITUD = "latitud";
        public static final String LONGITUD = "longitud";
        public static final String ESTADOBT = "estadobt";
        public static final String ESTADOWIFI = "estadowifi";
    }
}
