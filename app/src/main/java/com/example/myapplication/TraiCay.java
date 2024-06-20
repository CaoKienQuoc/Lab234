package com.example.myapplication;

public class TraiCay {
    private String Ten;
    private String Mota;
    private String imageUrl; // Đường dẫn URL hình ảnh
    private int imageResId;  // ID nguồn hình ảnh

    public TraiCay(String ten, String mota, String imageUrl) {
        this.Ten = ten;
        this.Mota = mota;
        this.imageUrl = imageUrl;
    }

    public TraiCay(String ten, String mota, int imageResId) {
        this.Ten = ten;
        this.Mota = mota;
        this.imageResId = imageResId;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        this.Ten = ten;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String mota) {
        this.Mota = mota;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}
