package com.akashjpro.sqliteimage240916;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DanhSachDongVat extends AppCompatActivity {

    ListView lvDV;
    ArrayList<DongVat> mangDV;
    DongVatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_dong_vat);

        lvDV = (ListView) findViewById(R.id.listViewDS);

        mangDV = new ArrayList<DongVat>();

        adapter = new DongVatAdapter(DanhSachDongVat.this, R.layout.dong, mangDV);
        lvDV.setAdapter(adapter);

        Cursor cursorDV = MainActivity.db.getData("SELECT * FROM DongVat");
        while (cursorDV.moveToNext()){
            mangDV.add(new DongVat(
                    cursorDV.getInt(0),
                    cursorDV.getString(1),
                    cursorDV.getString(2),
                    cursorDV.getBlob(3)
            ));
            adapter.notifyDataSetChanged();

        }
        lvDV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                xoaDV(i);
                return false;
            }
        });

        lvDV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DanhSachDongVat.this, PhongToAnh.class);
                intent.putExtra("Data", mangDV.get(i));
                startActivity(intent);
            }
        });

    }

    private void xoaDV(int i) {
        int id =  mangDV.get(i).id;
        MainActivity.db.queryData("DELETE FROM DongVat WHERE Id = '"+ id +"'");
        mangDV.remove(i);
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Đã xóa DV thu "+ i, Toast.LENGTH_SHORT).show();
    }
}
