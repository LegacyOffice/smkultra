package com.tekidoer.ultrasshservice.v2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import android.widget.TextView;
import com.demontunnel.project.R;
import com.tekidoer.ultrasshservice.config.Settings;
import com.tekidoer.ultrasshservice.tunnel.TunnelManagerThread;
import com.tekidoer.ultrasshservice.v2.V2Configs;
import com.tekidoer.ultrasshservice.v2.V2Core;
import com.tekidoer.ultrasshservice.v2.V2Service;
import com.tekidoer.ultrasshservice.v2.V2Config;
import com.tekidoer.ultrasshservice.logger.SkStatus;
import com.tekidoer.ultrasshservice.v2.V2Utilities;
import com.tekidoer.ultrasshservice.v2.V2Listener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import libv2ray.Libv2ray;
import com.tekidoer.sockshttp.MainActivity;

public class V2Tunnel {
    private static V2Listener v2Listener;
    private static Settings mConfig;

    public V2Tunnel(Context context) {
        v2Listener = TunnelManagerThread.getV2rayServicesListener();
        mConfig = new Settings(context);
    }

    public static void init(final Context context, final int app_icon, final String app_name) {
        V2Utilities.copyAssets(context);
        V2Configs.APPLICATION_ICON = app_icon;
        V2Configs.APPLICATION_NAME = app_name;
        context.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                V2Configs.V2RAY_STATE = (V2Configs.V2RAY_STATES) arg1.getExtras().getSerializable("STATE");
            }
        }, new IntentFilter("V2RAY_CONNECTION_INFO"));
    }

    public static void changeConnectionMode(final V2Configs.V2RAY_CONNECTION_MODES connection_mode) {
        if (getConnectionState() == V2Configs.V2RAY_STATES.V2RAY_DISCONNECTED) {
            V2Configs.V2RAY_CONNECTION_MODE = connection_mode;
        }
    }

    public static void StartV2ray(final Context context, final String remark, final String config, final ArrayList<String> blocked_apps) {
        V2Configs.V2RAY_CONFIG = V2Utilities.parseV2rayJsonFile(remark, config, blocked_apps);
        if (V2Configs.V2RAY_CONFIG == null) {
        v2Listener.onError();
        SkStatus.logInfo("V2Ray Error");
    return;
}
        Intent start_intent;
        if (V2Configs.V2RAY_CONNECTION_MODE == V2Configs.V2RAY_CONNECTION_MODES.PROXY_ONLY) {
            start_intent = new Intent(context, V2Proxy.class);
        } else if (V2Configs.V2RAY_CONNECTION_MODE == V2Configs.V2RAY_CONNECTION_MODES.VPN_TUN) {
            start_intent = new Intent(context, V2Service.class);
        } else {
        v2Listener.onError();
        SkStatus.logInfo("V2Ray Error");
    return;
}
        start_intent.putExtra("COMMAND", V2Configs.V2RAY_SERVICE_COMMANDS.START_SERVICE);
        start_intent.putExtra("V2RAY_CONFIG", V2Configs.V2RAY_CONFIG);
        SkStatus.logInfo(V2Tunnel.getCoreVersion());
        v2Listener.startService();
        SkStatus.logInfo("Starting V2ray");
        SkStatus.logInfo("Verifying internet connection");
        siNoInternet();

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1) {
            context.startForegroundService(start_intent);
        } else {
            context.startService(start_intent);
        }
    }

    public static void StopV2ray(final Context context) {
        Intent stop_intent;
        if (V2Configs.V2RAY_CONNECTION_MODE == V2Configs.V2RAY_CONNECTION_MODES.PROXY_ONLY) {
            stop_intent = new Intent(context, V2Proxy.class);
        } else if (V2Configs.V2RAY_CONNECTION_MODE != V2Configs.V2RAY_CONNECTION_MODES.VPN_TUN) {
            return;
        } else {
            stop_intent = new Intent(context, V2Service.class);
        }
        stop_intent.putExtra("COMMAND", V2Configs.V2RAY_SERVICE_COMMANDS.STOP_SERVICE);
        context.startService(stop_intent);
        V2Configs.V2RAY_CONFIG = null;
    }

    public static void getConnectedV2rayServerDelay(final Context context, final TextView tvDelay) {
        Intent check_delay;
        if (V2Configs.V2RAY_CONNECTION_MODE == V2Configs.V2RAY_CONNECTION_MODES.PROXY_ONLY) {
            check_delay = new Intent(context, V2Proxy.class);
        } else if (V2Configs.V2RAY_CONNECTION_MODE == V2Configs.V2RAY_CONNECTION_MODES.VPN_TUN) {
            check_delay = new Intent(context, V2Service.class);
        } else {
            return;
        }
        check_delay.putExtra("COMMAND", V2Configs.V2RAY_SERVICE_COMMANDS.MEASURE_DELAY);
        context.startService(check_delay);
        context.registerReceiver(new BroadcastReceiver() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                String delay = arg1.getExtras().getString("DELAY");
                tvDelay.setText("connected server delay : " + delay);
                context.unregisterReceiver(this);
            }
        }, new IntentFilter("CONNECTED_V2RAY_SERVER_DELAY"));
    }

    public static String getV2rayServerDelay(final String config) {
        final long server_delay = V2Core.getInstance().getV2rayServerDelay(config);
        if (server_delay == -1L) {
            return "Network or Server Error";
        } else {
            return String.valueOf(server_delay);
        }
    }

    private static void vpnService(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            for (Network network : connectivityManager.getAllNetworks()) {
                NetworkInfo networkInfo = connectivityManager.getNetworkInfo(network);
                if (networkInfo != null && networkInfo.getType() == 17 && networkInfo.isConnected()) {
                    if (Build.VERSION.SDK_INT >= 23) {
                        connectivityManager.bindProcessToNetwork(null);
                    }
                    connectivityManager.unregisterNetworkCallback(new ConnectivityManager.NetworkCallback());
                }
            }
        }
    }


    public static V2Configs.V2RAY_CONNECTION_MODES getConnectionMode() {
        return V2Configs.V2RAY_CONNECTION_MODE;
    }

    public static V2Configs.V2RAY_STATES getConnectionState() {
        return V2Configs.V2RAY_STATE;
    }

    public static String getCoreVersion(){
        return Libv2ray.checkVersionX();
    }

    public static void stopAllServices(Activity activity){
        StopV2ray(activity);
        //v2Listener.onError();
    }

    public static void siNoInternet(){
        new Timer().schedule(new TimerTask()
        {
            @Override
            public void run() {
                if (verificarInternet()){
                    Log.d("You have internet", "You have access to the internet");
                    SkStatus.logInfo("Connecting server");
                    SkStatus.logInfo("<strong>" + "Server Message:" + "</strong> " + mConfig.getPrivString("ServerBanner"));
                    v2Listener.onConnected();
                    return;
                }
                Log.d("You do not have Internet", "You do not have internet access");
                SkStatus.logInfo("There is no internet access");
                v2Listener.onError();

            }
        }, 2000);

    }

    public static boolean verificarInternet() {
        try {
            String command = "ping -c 1 google.com";
            return (Runtime.getRuntime().exec(command).waitFor() == 0);
        } catch (Exception e) {
            return false;
        }

    }


}
