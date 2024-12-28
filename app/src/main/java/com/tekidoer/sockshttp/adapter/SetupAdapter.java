package com.tekidoer.sockshttp.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.smkultratun.pro.R;

public class SetupAdapter extends ArrayAdapter<String> {

    private int spinner_id;
    private String[] sName;

    public SetupAdapter(Context context, int spinner_id, String[] list) {
        super(context, R.layout.custom_setup, list);
        this.spinner_id = spinner_id;
        this.sName = list;
    }

    // Use view() method to handle both getView() and getDropDownView()
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return view(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return view(position, convertView, parent);
    }

    // Common method to inflate and populate views
    private View view(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.custom_setup, parent, false);

        TextView tv = v.findViewById(R.id.customName);
        TextView extra = v.findViewById(R.id.customExtra);
        TextView info = v.findViewById(R.id.info);
        TextView pInfos = v.findViewById(R.id.custom_info);
        // Set the text with HTML formatting (bold) and serif font
        String name = sName[position];
        tv.setText(Html.fromHtml("<b>" + name + "</b>"));
        tv.setTypeface(Typeface.SERIF);

        try {
            if (spinner_id == R.id.setupSpinner) {
                getPayloadIcon(position, pInfos, tv, info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return v;
    }

    // This method handles specific conditions for the spinner options
    private void getPayloadIcon(int position, TextView extra, TextView tv, TextView info) throws Exception {
        String name = sName[position];
        tv.setText(name);
        
        // Handle specific labels based on the name content
        if (name.contains("direct")) {
            extra.setText("Internet");
        } else if (name.contains("payload")) {
            extra.setText("HTTP Mode");
        } else if (name.contains("ssl")) {
            extra.setText("SSL/TLS Mode");
        } else if (name.contains("sslpayload")) {
            extra.setText("WebSocket Mode");
        } else if (name.contains("udp")) {
            extra.setText("UDP Tunnel Mode");
        } else if (name.contains("v2")) {
            extra.setText("V2ray Mode");
        }
    }
}