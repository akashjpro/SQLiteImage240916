package com.akashjpro.sqliteimage240916;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnThem, btnDS;

    public static  SQLite db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDS = (Button) findViewById(R.id.buttonDS);
        btnThem = (Button) findViewById(R.id.buttonThem1);

        db = new SQLite(this, "SuuTam.sqlite", null, 1);

        db.queryData("CREATE TABLE IF NOT EXISTS DongVat(Id INTEGER PRIMARY KEY AUTOINCREMENT, Ten VARCHAR, Mota VARCHAR, Hinh BLOB)");


        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ThemDongVat.class);
                startActivity(intent);
            }
        });

        btnDS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DanhSachDongVat.class);
                startActivity(intent);
            }
        });
    }
}
