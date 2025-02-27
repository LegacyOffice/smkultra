package com.tekidoer.ultrasshservice.tunnel;

import android.content.Intent;
import android.os.Build;
import android.content.Context;
import com.tekidoer.ultrasshservice.SocksHttpService;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.tekidoer.ultrasshservice.config.Settings;

public class TunnelManagerHelper
{
	public static void startSocksHttp(Context context) {
        Intent startVPN = new Intent(context, SocksHttpService.class);
		
		if (startVPN != null) {
			TunnelUtils.restartRotateAndRandom();
			
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
			//noinspection NewApi
                context.startForegroundService(startVPN);
            else
                context.startService(startVPN);
        }
    }
	
	public static void stopSocksHttp(Context context) {
		Intent stopTunnel = new Intent(SocksHttpService.TUNNEL_SSH_STOP_SERVICE);
		LocalBroadcastManager.getInstance(context)
			.sendBroadcast(stopTunnel);
	}
}
