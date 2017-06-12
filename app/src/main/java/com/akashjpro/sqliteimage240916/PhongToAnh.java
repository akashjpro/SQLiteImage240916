package com.akashjpro.sqliteimage240916;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class PhongToAnh extends AppCompatActivity {

    ImageView imgAnh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phong_to_anh);
        imgAnh = (ImageView) findViewById(R.id.imageViewAnhTo);

        Intent intent = getIntent();
        DongVat dongVat = (DongVat) intent.getSerializableExtra("Data");
        byte[] bytesHịnh = dongVat.getHinhAnh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytesHịnh, 0, bytesHịnh.length);
        imgAnh.setImageBitmap(bitmap);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_hieu_ung);
        imgAnh.startAnimation(animation);

    }
}
