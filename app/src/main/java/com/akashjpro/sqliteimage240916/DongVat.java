package com.akashjpro.sqliteimage240916;

import java.io.Serializable;

/**
 * Created by Akashjpro on 9/24/2016.
 */
public class DongVat implements Serializable {
    public Integer id;
    public String ten;
    public String mota;
    public byte[] hinhAnh;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public byte[] getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public DongVat(Integer id, String ten, String mota, byte[] hinhAnh) {
        this.id = id;
        this.ten = ten;
        this.mota = mota;
        this.hinhAnh = hinhAnh;


    }
}
