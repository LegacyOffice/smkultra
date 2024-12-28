package com.tekidoer.sockshttp.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import com.smkultratun.pro.R;
import org.json.JSONException;
import org.json.JSONObject;
import android.widget.CheckBox;
import android.widget.Button;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.tekidoer.sockshttp.util.VPNUtils;

public class CustomServer {

    public static class Server {
        private static EditText sName,sFlag, sHost,udpIP,sObfs,sAuth, sPort, user, pass;

        private Context c;
        private SharedPreferences sp;

        //private SharedPreferences sp;
        //    private Button saveServer;
        private String auto;
        private MaterialAlertDialogBuilder a;

        public Server(Context c) {
            a=new MaterialAlertDialogBuilder(c,R.style.AppCompatAlertDialogStyle);
            sp=PreferenceManager.getDefaultSharedPreferences(c);
            this.c=c;
        }

        public void onServerAdd(Object onAdd) {
        }

        public void add() {
            View v=LayoutInflater.from(c).inflate(R.layout.add_server, null);
            sName = v.findViewById(R.id.sName);
            sFlag = v.findViewById(R.id.sFlag);
            sHost = v.findViewById(R.id.sHost);
            udpIP = v.findViewById(R.id.udpIP);
            sObfs = v.findViewById(R.id.sObfs);
            sAuth = v.findViewById(R.id.sAuth);

            sPort = v.findViewById(R.id.sPort);           
            user = v.findViewById(R.id.user);
            pass = v.findViewById(R.id.pass);
            //  saveServer = (Button) v.findViewById(R.id.saveServer);

            a.setView(v);
        }

        public void onServerEdit(Object onEdit) {
        }

        public void edit(JSONObject json) {
            View v=LayoutInflater.from(c).inflate(R.layout.add_server, null);
            sName = v.findViewById(R.id.sName);
            sFlag = v.findViewById(R.id.sFlag);
            sHost = v.findViewById(R.id.sHost);
            udpIP = v.findViewById(R.id.udpIP);
            sObfs = v.findViewById(R.id.sObfs);
            sAuth = v.findViewById(R.id.sAuth);

            sPort = v.findViewById(R.id.sPort);         
            user = v.findViewById(R.id.user);
            pass = v.findViewById(R.id.pass);
            ///  saveServer = (Button) v.findViewById(R.id.saveServer);

            try {
                sName.setText(json.getString("Name"));
                sFlag.setText(json.getString("FLAG"));
                sHost.setText(VPNUtils.IsangTangangNagDecrypt(json.getString("ServerIP")));
                udpIP.setText(VPNUtils.IsangTangangNagDecrypt(json.getString("UdpIP")));
                sObfs.setText(VPNUtils.IsangTangangNagDecrypt(json.getString("Obfs_Str")));
                sAuth.setText(VPNUtils.IsangTangangNagDecrypt(json.getString("Auth_Str")));
                
                sPort.setText(json.getString("ServerPort"));      
                user.setText(VPNUtils.IsangTangangNagDecrypt(json.getString("ServerUser")));
                pass.setText(VPNUtils.IsangTangangNagDecrypt(json.getString("ServerPass")));

            } catch (JSONException e) {}
            a.setView(v);}

        public void onServerAdd(final SpinnerListener oca){
            a.setNegativeButton("Cancel",null);
            a.setPositiveButton("Save",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface p1, int p2) {
                        JSONObject jo=new JSONObject();
                        try {
                            jo.put("Name", sName.getText().toString());
                            jo.put("FLAG", sFlag.getText().toString()/* + ".png"*/);
                            jo.put("ServerIP", VPNUtils.IsangTangangNagEncrypt(sHost.getText().toString()));
                            jo.put("UdpIP", VPNUtils.IsangTangangNagEncrypt(udpIP.getText().toString()));
                            jo.put("Obfs_Str", VPNUtils.IsangTangangNagEncrypt(sObfs.getText().toString()));
                            jo.put("Auth_Str", VPNUtils.IsangTangangNagEncrypt(sAuth.getText().toString()));

                            jo.put("ServerPort", sPort.getText().toString());                        
                            jo.put("ServerUser", VPNUtils.IsangTangangNagEncrypt(user.getText().toString()));
                            jo.put("ServerPass", VPNUtils.IsangTangangNagEncrypt(pass.getText().toString()));
                            jo.put("sInfo", "Custom Server").toString();
                            sp.edit().putString("sInfo", "Custom Server").apply();	

                            oca.onAdd(jo);
                            Toast.makeText(c, "Added "+sName.getText().toString(), Toast.LENGTH_LONG).show();
                        } catch (JSONException e){
                            Toast.makeText(c,e.getMessage(),1).show();
                        }
                    }
                });
        }

        public void init(){
            a.create().show();
        }
    }

    public interface SpinnerListener {
        void onAdd(JSONObject json);
    }

}
