package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TraiCayAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<TraiCay> traiCayList;

    public TraiCayAdapter(Context context, int layout, List<TraiCay> traiCayList) {
        this.context = context;
        this.layout = layout;
        this.traiCayList = traiCayList;
    }

    @Override
    public int getCount() { // trả về số dòng hiển thị trên listview
        // reuturn 0;
        return  traiCayList.size();
    }

    @Override
    public Object getItem(int i) { return null; }

    @Override
    public long getItemId(int i) { return 0; }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {// trả về view mỗi dòng trên item khi gọi adapter
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout, null);

        // Ánh xạ view
        TextView txtTen = (TextView) view.findViewById(R.id.textviewTen);
        TextView txtMota = (TextView) view.findViewById(R.id.textviewMoTa);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageViewHinh);

        //Gán giá trị
        TraiCay traiCay = traiCayList.get(i);

        txtTen.setText(traiCay.getTen());
        txtMota.setText(traiCay.getMota());
        imageView.setImageResource(traiCay.getImageResId());

        //return null; trả về view
        return view;
    }
}

