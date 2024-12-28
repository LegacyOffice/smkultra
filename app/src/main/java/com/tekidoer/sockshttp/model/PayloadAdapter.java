package com.tekidoer.sockshttp.model;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.smkultratun.pro.R;
import java.io.InputStream;
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.view.animation.AnimationUtils; // <-- Add this import

public class PayloadAdapter extends BaseAdapter {

    public static ImageView im;
    Context context; 
    ArrayList<PayloadModel> arrayList;
    int pos01;

    public PayloadAdapter(Context context, ArrayList<PayloadModel> arrayList, int nethie01) {
        this.context = context;
        this.arrayList = arrayList;
        this.pos01 = nethie01;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.spinner_item, parent, false);
        }

        TextView name, info;
        LinearLayout mainCardz = convertView.findViewById(R.id.spinner_item_layout);
        name = convertView.findViewById(R.id.itemName);
        info = convertView.findViewById(R.id.info);
        im = convertView.findViewById(R.id.itemImage);

        // Apply bold and serif formatting to the server name and info
        name.setText(Html.fromHtml("<b><font face='serif'>" + arrayList.get(position).getServerName() + "</font></b>"));
        info.setText(Html.fromHtml("<b><font face='serif'>" + arrayList.get(position).getServerInfo() + "</font></b>"));
        
        // Add animation
        mainCardz.startAnimation(AnimationUtils.loadAnimation(context, R.anim.grow));

        // Continue with icon setup
        try {
            getPayloadIcon(position, im);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return convertView;
    }
    
    private void getPayloadIcon(int position, ImageView im) throws Exception {
        InputStream inputStream = context.getAssets().open("flags/" + arrayList.get(position).getServerflag() + ".png");
        im.setImageDrawable(Drawable.createFromStream(inputStream, arrayList.get(position).getServerflag() + ".png"));
        if (inputStream != null) {
            inputStream.close();
        }
    }
}