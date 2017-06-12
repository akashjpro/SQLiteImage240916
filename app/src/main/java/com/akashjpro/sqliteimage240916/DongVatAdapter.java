package com.akashjpro.sqliteimage240916;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Akashjpro on 9/24/2016.
 */
public class DongVatAdapter extends BaseAdapter {
    Context myContext;
    int mylayout;
    List<DongVat> mangDongVat;

    public DongVatAdapter(Context myContext, int mylayout, List<DongVat> mangDongVat) {
        this.myContext = myContext;
        this.mylayout = mylayout;
        this.mangDongVat = mangDongVat;
    }

    @Override

    public int getCount() {
        return mangDongVat.size();
    }

    @Override
    public Object getItem(int i) {
        return mangDongVat.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder{
        TextView txtTen, txtMoTa;
        ImageView imgHinh;
    }



    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowview = view;
        ViewHolder holder = new ViewHolder();

        if(rowview==null){
            rowview = inflater.inflate(this.mylayout, null);
            holder.txtTen = (TextView) rowview.findViewById(R.id.textViewTen);
            holder.txtMoTa = (TextView) rowview.findViewById(R.id.textViewMoTa);
            holder.imgHinh= (ImageView) rowview.findViewById(R.id.imageHinh);
            rowview.setTag(holder);
        }
        else {
            holder = (ViewHolder) rowview.getTag();
        }



        holder.txtTen.setText(mangDongVat.get(i).ten);
        holder.txtMoTa.setText(mangDongVat.get(i).mota);

        //chuyen bye hinh sang bitmap va gan cho imgview
        byte[] byeshinh = mangDongVat.get(i).hinhAnh;
        Bitmap bitmap = BitmapFactory.decodeByteArray(byeshinh, 0, byeshinh.length);
        holder.imgHinh.setImageBitmap(bitmap);

        Animation animation = AnimationUtils.loadAnimation(this.myContext, R.anim.anim_hieu_ung);
        rowview.startAnimation(animation);


        return rowview;
    }
}
