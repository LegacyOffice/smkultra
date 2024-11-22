package com.tekidoer.sockshttp.model;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.demontunnel.project.R;
import java.io.InputStream;
import java.util.ArrayList;
import androidx.cardview.widget.CardView;
import android.view.animation.AnimationUtils;
import android.app.Activity;
import android.text.Html;
import java.net.PortUnreachableException;
import com.tekidoer.ultrasshservice.tunnel.TunnelUtils;
import com.tekidoer.sockshttp.adapter.ServerFinger;
import android.widget.LinearLayout;

public class ServerAdapter extends BaseAdapter {

    public static ImageView im;
    Context context; 
	Activity c;
    ArrayList<ServerModel> arrayList;
	int nethie00;

    public ServerAdapter(Activity c, Context context, ArrayList<ServerModel> arrayList, int nethie00) {
        this.context = context;
		this.c = c;
        this.arrayList = arrayList;
		this.nethie00 = nethie00;
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
public View getView(final int position, View convertView, ViewGroup parent) {
    if (convertView == null) {
        convertView = LayoutInflater.from(context).inflate(R.layout.spinner_item, parent, false);
    }
    
    TextView name, info, stat, ping;
    LinearLayout mainCardz = convertView.findViewById(R.id.spinner_item_layout);
    name = convertView.findViewById(R.id.itemName);
    info = convertView.findViewById(R.id.info);
    stat = convertView.findViewById(R.id.statusping);
    ping = convertView.findViewById(R.id.pinglatency);
    im = convertView.findViewById(R.id.itemImage);
        
    mainCardz.setBackgroundResource(R.drawable.green_round);    
    
    // Apply bold and serif formatting for the server name and info
    name.setText(Html.fromHtml("<b><font face='serif'>" + arrayList.get(position).getServerName() + "</font></b>"));
    info.setText(Html.fromHtml("<b><font face='serif'>" + arrayList.get(position).getServerInfo() + "</font></b>"));

    mainCardz.startAnimation(AnimationUtils.loadAnimation(context, R.anim.grow));
    
    // Continue with the ping and icon settings
    getPing(c, ping, stat, arrayList.get(position).getServerHost(), arrayList.get(position).getServerPort());
    try {
        getServerIcon(position, im);
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    return convertView;
}
	private void getPing(final Activity c, final TextView ping, final TextView stat, final String Host, final String Port){
		//String html = "<html><font color=\"";
		//String html2 = "\">Neth</font></html>";
		new ServerFinger(new ServerFinger.OnPingListener(){
				@Override
				public void ms(final int i) {
					c.runOnUiThread(new Runnable() {
							@Override
							public void run() {
								String clr0;
								if(i<100){
									clr0 = "#04D000";
								} else {
									clr0 = "#FD1C0D";
								}
								//int in1t = 3;
								//mConfig.getPrefsPrivate().edit().putString("Version", String.valueOf(in1t++)).apply();
								if (TunnelUtils.isNetworkOnline(c)){
									ping.setText(Html.fromHtml("<b><font color=\""+ clr0 + "\">"+ String.valueOf(i) +"ms</font></b>"));
								} else {
									ping.setText(" ");
								}
							}
						});
				}
				@Override
				public void accept(final boolean isOnline) {
					c.runOnUiThread(new Runnable() {
							@Override
							public void run() {
								String clr;
								if(!isOnline){
									clr = "#04D000";
									stat.setText(Html.fromHtml("<font color=\""+ clr + "\">"+"<b>Online</b></font>"));							
								} else {
									clr = "#1c03ff";
									if (TunnelUtils.isNetworkOnline(c)){
										stat.setText(Html.fromHtml("<font color=\""+ clr + "\">"+"<b>Offline</b></font>"));
									} else {
										stat.setText(" ");
									}
								}
							}
						});
				}
				@Override
				public void load() {
					ping.setText(Html.fromHtml(" "));
					stat.setText(Html.fromHtml("Checking"));
				}
			}, Host, Port, 1500);
	}
    
    private void getServerIcon(int position, ImageView im) throws Exception {
        InputStream inputStream = context.getAssets().open("flags/" + arrayList.get(position).getServerflag() + ".png");
        im.setImageDrawable(Drawable.createFromStream(inputStream, arrayList.get(position).getServerflag() + ".png"));
        if (inputStream != null) {
            inputStream.close();
        }
    }
    
}

