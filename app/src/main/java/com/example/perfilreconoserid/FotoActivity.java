package com.example.perfilreconoserid;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.perfilreconoserid.Utilidades.SharedPreferencesReco;
import com.example.perfilreconoserid.data.DatosEsquema;
import com.example.perfilreconoserid.data.TbDatosSQLHelper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static com.example.perfilreconoserid.Utilidades.Constantes.IMAGEN;

public class FotoActivity extends AppCompatActivity implements View.OnClickListener{

    Button tomar_fotos, btNext, seleccionar_fotos;
    ImageView im_foto;
    static final int FOTO = 1;
    private int FOTO_SELECCION = 2;
    byte[] frontImage = null;
    Bitmap bitmap1;
    private Uri filePath;
    TbDatosSQLHelper datosSQLHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto);
        bindUI();
        datosSQLHelper = new TbDatosSQLHelper(this);
        db = datosSQLHelper.getWritableDatabase();
    }

    public void bindUI() {
        btNext = (Button) findViewById(R.id.btNext);
        tomar_fotos = (Button) findViewById(R.id.tomar_fotos);
        seleccionar_fotos = (Button) findViewById(R.id.seleccionar_fotos);
        im_foto = (ImageView) findViewById(R.id.im_foto);
        btNext.setOnClickListener(this);
        tomar_fotos.setOnClickListener(this);
        seleccionar_fotos.setOnClickListener(this);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btNext:
                guardarDatos();
                break;

            case R.id.tomar_fotos:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, FOTO);
                }
                break;

            case R.id.seleccionar_fotos:
                showFileChooser();
                break;
        }
    }

    public static String encodeTobase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(Bitmap.CompressFormat.PNG,100,baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b,Base64.DEFAULT);

        return imageEncoded;
    }

    public void guardarDatos(){
        if (bitmap1!= null) {
            ContentValues values = new ContentValues();

            values.put(DatosEsquema.DatosEntry.IMAGEN , encodeTobase64(bitmap1));

            String selection = DatosEsquema.DatosEntry.ID + " LIKE ?";
            String[] selectionArgs = {"1"};

            db.update(
                    DatosEsquema.DatosEntry.TABLE_NAME,
                    values,
                    selection,
                    selectionArgs);

            //SharedPreferencesReco.guardarImagen(this,IMAGEN,bitmap1);
            gotoNext();
        }else{
            Toast.makeText(this,"Por favor tomar foto o seleccionar imagen",Toast.LENGTH_LONG).show();
        }
    }

    public void gotoNext(){
        Intent intent = new Intent(FotoActivity.this,UbicacionActivity.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),FOTO_SELECCION);

    }

    public static byte[] convertImageToByteArray(Bitmap bmp) throws IOException {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        //bmp.recycle();
        return byteArray;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data != null) {

                try {
                    if (requestCode == FOTO) {
                        Bitmap bMap = (Bitmap) data.getExtras().get("data");
                        frontImage = convertImageToByteArray(bMap);

                        String encoded = Base64.encodeToString(frontImage, Base64.DEFAULT);
                        bitmap1 = BitmapFactory.decodeByteArray(frontImage,0,frontImage.length);

                    }else if(requestCode == FOTO_SELECCION){
                        filePath = data.getData();
                        try {
                            bitmap1 = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    im_foto.setImageBitmap(bitmap1);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Override
    protected void onDestroy() {
        db.close();
        super.onDestroy();
    }
}