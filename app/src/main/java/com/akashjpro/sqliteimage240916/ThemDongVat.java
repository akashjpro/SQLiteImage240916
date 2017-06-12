package com.akashjpro.sqliteimage240916;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class ThemDongVat extends AppCompatActivity {

    EditText edtTen, edtMoTa;
    ImageView imgHinh;
    ImageButton ibtnCamera, ibtnFolder;
    Button btnThem;
    int REQUEST_CODE_CAMERA = 123;
    int REQUEST_CODE_FOLDER = 456;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_dong_vat);

        addControls();

        ibtnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intentCamera, REQUEST_CODE_CAMERA);
            }
        });

        ibtnFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentFolder = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intentFolder, REQUEST_CODE_FOLDER);

            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.db.insertData(
                        edtTen.getText().toString(),
                        edtMoTa.getText().toString(),
                        ImageView_To_Byte(imgHinh)
                        );

                Toast.makeText(ThemDongVat.this, "Da them dong vat", Toast.LENGTH_SHORT).show();
                edtTen.setText("");
                edtMoTa.setText("");
                imgHinh.setImageResource(R.drawable.no_camera);
            }
        });

    }

    public byte[] ImageView_To_Byte(ImageView imgv){

        BitmapDrawable drawable = (BitmapDrawable) imgv.getDrawable();
        Bitmap bmp = drawable.getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK &&  data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            imgHinh.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }


        if(requestCode==REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgHinh.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void addControls() {
        edtTen = (EditText) findViewById(R.id.editTextTen);
        edtMoTa = (EditText) findViewById(R.id.editTextMoTa);

        imgHinh = (ImageView) findViewById(R.id.imageViewHinh);
        ibtnCamera = (ImageButton) findViewById(R.id.imageButtonCamera);
        ibtnFolder = (ImageButton) findViewById(R.id.imageButtonFolder);
        btnThem = (Button) findViewById(R.id.buttonThemDongVat);

    }
}
