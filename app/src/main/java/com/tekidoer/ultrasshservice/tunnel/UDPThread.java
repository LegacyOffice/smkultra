package com.tekidoer.ultrasshservice.tunnel;

import android.content.Context;
import android.content.SharedPreferences;
import com.tekidoer.ultrasshservice.config.Settings;
import com.tekidoer.ultrasshservice.config.SettingsConstants;
import com.tekidoer.ultrasshservice.logger.SkStatus;
import com.tekidoer.ultrasshservice.tunnel.UDPStreamGobbler;
import com.tekidoer.ultrasshservice.tunnel.UDPThread;
import java.io.File;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class UDPThread extends Thread {
    private String address;
    private String auth;
    private String ca;
    Context context1;
    private String dir;
    private String dwon;
    private UDPStreamGobbler err;
    private File fileConf;
    private File fileDns;
    private File fileca;
    private String listen;
    private String obfs;
    private UDPStreamGobbler out;
    private String rc_conn;
    private String rc_w;
    private String retry;
    private String serv;
    private String server;
    private String port;
   private final SharedPreferences sp;
    private Process udpProcess;
    private Thread udpThread;
    private String up;
    private final Settings mConfig;

    public UDPThread(Context context) {
        context1 = context;
        dir = context.getFilesDir().getPath();
        mConfig = new Settings(context);
        sp = new Settings(context).getPrefsPrivate();
    }
    
    public static Inet4Address getIPv4Addresses(InetAddress[] inetAddressArr) {
        for (InetAddress inetAddress : inetAddressArr) {
            if (inetAddress instanceof Inet4Address) {
                return (Inet4Address) inetAddress;
            }
        }
        return null;
    }


    public void run2(String str) {
        if (!str.contains("ZIVPN UDP running")) {
            if (str.contains("no recent network activity")) {
                if (sp.getBoolean("reconnectup", false)) {
                    SkStatus.logInfo("<font color='red'><strong>Connection Lost retrying </strong></font>");
                    stopVudp2();
                    return;
                }
                SkStatus.logInfo("<font color='red'><strong>UDP connection Lost</strong></font>");
                stopVudp2();
            } else if (str.contains("Connection to server lost") || str.contains("handshake did not complete in time")) {
                //SkStatus.logInfo("<font color='red'><strong>Please wait reconnecting UDP</strong></font>");
                //stopVudp2();
            } else if (str.contains("Failed to parse client configuration")) {
                SkStatus.logInfo("<font color='red'><strong>Invalid UDP setting</strong></font>");
                stopVudp2();
            } else if (str.contains("auth error")) {
                SkStatus.logInfo("<font color='red'><strong>Authentication failed, invalid password/expired/may logged-in on another device</strong></font>");
                stopVudp2();
            } else if (str.contains("too many connections")) {
                SkStatus.logInfo("<font color='red'><strong>Same account on multi-device not support</strong></font>");
                stopVudp2();
            }
           
            return;
        }

        SkStatus.logInfo("<font color='green'><strong>UDP Connected Successfully</strong></font>");
        SkStatus.logInfo("<font color='#FF7F27'><strong>You Have Internet Now</strong></font>");
    }


    public void stopVudp() {
        SkStatus.logInfo("Stopping UDP....");
        interrupt();
        while (true) {
            try {
                Thread.sleep(2000);
                TunnelManagerHelper.stopSocksHttp(context1);
                return;
            } catch (InterruptedException unused) {
            }
        }
    }

    private boolean printToFile(File file, String str) {
        try {
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.println(str);
            printWriter.flush();
            printWriter.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override 
    public void interrupt() {
        UDPStreamGobbler uDPStreamGobbler = out;
        if (uDPStreamGobbler != null) {
            uDPStreamGobbler.setInterrupted(true);
            out.interrupt();
            out = null;
        }
        UDPStreamGobbler uDPStreamGobbler2 = err;
        if (uDPStreamGobbler2 != null) {
            uDPStreamGobbler2.setInterrupted(true);
            err.interrupt();
            err = null;
        }
        Process process = udpProcess;
        if (process != null) {
            process.destroy();
            udpProcess = null;
        }
        File file = fileConf;
        if (file != null && file.exists()) {
            fileConf.delete();
        }
        super.interrupt();
    }

    @Override 
    public void run() {
        fileca = new File(dir, "zi.ca.crt");
        serv = mConfig.getPrivString(SettingsConstants.SERVIDOR_KEY);
//        serv = string;
//        if (string.isEmpty()) {
//            serv = "45.56.74.104";
//            try {
//                sp.edit().putString(SettingsConstants.SERVIDOR_KEY, serv).apply();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        InetAddress[] inetAddressArr = new InetAddress[0];
        try {
            inetAddressArr = InetAddress.getAllByName(serv);
        } catch (UnknownHostException e2) {
            e2.printStackTrace();
        }
        try {
            address = getIPv4Addresses(inetAddressArr).getHostAddress();
        } catch (Exception e3) {
            e3.printStackTrace();
            if (String.valueOf(e3).contains("on a null object reference")) {
                SkStatus.logInfo("<font color='red'><strong>Invalid UDP Server</strong></font>");
              //  stopVudp2();
            }
        }
       port = mConfig.getPrivString(SettingsConstants.SERVIDOR_PORTA_KEY);
        server = address + ":" + port;
        obfs = mConfig.getPrivString(SettingsConstants.SENHA_KEY);
//        obfs = string2;
//        if (string2.isEmpty()) {
//            obfs = "12345678";
//            try {
//                sp.edit().putString(SettingsConstants.UDP_OBFS, obfs).apply();
//            } catch (Exception e4) {
//                e4.printStackTrace();
//            }
//        }
        auth = mConfig.getPrivString(SettingsConstants.USUARIO_KEY);
//        auth = string3;
//        if (string3.isEmpty()) {
//            auth = "movistar";
//            try {
//                sp.edit().putString(SettingsConstants.UDP_AUTH, auth).apply();
//            } catch (Exception e5) {
//                e5.printStackTrace();
//            }
//        }
        String string4 = sp.getString(SettingsConstants.UDP_SPEED_UP, "30");
        up = string4;
        if (string4.isEmpty()) {
            up = "30";
            try{
                sp.edit().putString(SettingsConstants.UDP_SPEED_UP, up).apply();
            }catch(Exception e6){
                e6.printStackTrace();
            }
        }
        String string5 = sp.getString(SettingsConstants.UDP_SPEED_DOWN, "30");
        dwon = string5;
        if (string5.isEmpty()) {
            dwon = "30";
            try{
                sp.edit().putString(SettingsConstants.UDP_SPEED_DOWN, dwon).apply();
            }catch(Exception e7){
                e7.printStackTrace();
            }
        }
        retry = "3";
        String string6 = sp.getString(SettingsConstants.UDP_WINDOW, "196608");
        rc_conn = string6;
        if (string6.isEmpty()) {
            rc_conn = "196608";
            try{
                sp.edit().putString(SettingsConstants.UDP_WINDOW, rc_conn).apply();
            }catch(Exception e8){
                e8.printStackTrace();
            }
        }
        try {
            rc_w = String.valueOf((Integer.parseInt(rc_conn) * 5) / 2);
        } catch (NumberFormatException e6) {
            e6.printStackTrace();
        }
        listen = "127.0.0.1:1080";
        String str = dir + "/zi.ca.crt";
        ca = str;
        String format = String.format("{\n  \"server\": \"%s\",\n  \"obfs\": \"%s\",\n  \"auth_str\": \"%s\",\n  \"up_mbps\": %s,\n  \"down_mbps\": %s,\n  \"retry\": %s,\n  \"retry_interval\": 1,\n  \"socks5\": {\n    \"listen\": \"%s\"\n  },\n  \"insecure\": true,\n  \"ca\": \"%s\",\n  \"recv_window_conn\": %s,\n  \"recv_window\": %s\n}", server, obfs, auth, up, dwon, retry, listen, str, rc_conn, rc_w);
        fileConf = new File(dir, "config.json");
        if (!printToFile(fileca, "-----BEGIN CERTIFICATE-----\nMIIDizCCAnOgAwIBAgIUGxLl5Ou4dR1h3c9lUcaM5bp4ZBswDQYJKoZIhvcNAQEL\nBQAwVTELMAkGA1UEBhMCQ04xCzAJBgNVBAgMAkdEMQswCQYDVQQHDAJTWjEUMBIG\nA1UECgwLWklWUE4sIEluYy4xFjAUBgNVBAMMDVpJVlBOIFJvb3QgQ0EwHhcNMjMw\nMjExMDkwMjM1WhcNMzMwMjA4MDkwMjM1WjBVMQswCQYDVQQGEwJDTjELMAkGA1UE\nCAwCR0QxCzAJBgNVBAcMAlNaMRQwEgYDVQQKDAtaSVZQTiwgSW5jLjEWMBQGA1UE\nAwwNWklWUE4gUm9vdCBDQTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEB\nAMQsHTq2UD4WDOvNUFGQuKd0PEitgQzSh12qH9aJ5jnCtbWjqVNDRQSW0ietg4Po\nqOfKLOBvGOJcGkrYlAAynnwsufdkZd2Jj2+FAXloAbMBK5cjqRANfPJ7ns3S5zL2\nt2+Xv/O6H58NL5QksyIHb2Vcosfelwuvj5Lq+MvyqGZikce5IaykgjjV0OsrBnsC\neK4yAeoxsqVixGwmcJDLGOIJDGYcDdaElqJqFCyOjOhLLDymx9JbeOb3DpiRNFNN\nlwXi2rfvpnmpGNwNt9sclWAQTL3cfV4GsCovT02r1qxcAqqRE4U1nqMRqk0KfyQn\nUebOat/0jNJI9YxJByuVBK0CAwEAAaNTMFEwHQYDVR0OBBYEFGk91bjhFZfcKkpm\n5SxVkqnSGhXBMB8GA1UdIwQYMBaAFGk91bjhFZfcKkpm5SxVkqnSGhXBMA8GA1Ud\nEwEB/wQFMAMBAf8wDQYJKoZIhvcNAQELBQADggEBAEr4aeE0ib5/7neEcRWCE1pg\nw0j/958bdaSdQJJvYEpc7brCHhp5lmNJA+MjVcCXCL4/8KfuEcyGNPPSPo7wbuYJ\nO9jsJmQOklfyvlKGJschvc8AZ0E0AGdrgGam1KApjrb6Xly5bqgV4KPBQ7KttBVw\nwFfTm0yjD3nAjaSXi3I/MG+gMGnUXoTMZa3iS2pomBMHLdTksiujbbH7RP9mzPT3\n7UvyVmtw7eQFEjEYceVWHlhXCjL9gpcJiX/wu9XzREDpNCqY2R3zb+ZGYuQD0L5h\nzv0u1CF+Cfkkg8luxol+aWc+1ac/8TGLV1WOGj4FuEMfxQPXWFqhc8VEyxZ/r/w=\n-----END CERTIFICATE-----") || !printToFile(fileConf, format)) {
          //  interrupt();
          stopVudp2();
            return;
        }
        File file = new File(context1.getApplicationInfo().nativeLibraryDir, "libskyudp.so");
        fileDns = file;
        String[] strArr = {file.getAbsolutePath(), "client", "--config", fileConf.getAbsolutePath()};
        UDPStreamGobbler.OnResultListener onResultListener = new UDPStreamGobbler.OnResultListener() { 
            @Override 
            public final void onResult(String str2) {
                run2(str2);
            }
        };
        try {
            Process start = new ProcessBuilder(new String[0]).command(strArr).redirectErrorStream(true).start();
            udpProcess = start;
            out = new UDPStreamGobbler(start.getInputStream(), onResultListener);
            err = new UDPStreamGobbler(udpProcess.getErrorStream(), onResultListener);
            out.setInterrupted(false);
            out.start();
            err.setInterrupted(false);
            err.start();
            udpProcess.waitFor();
        } catch (Exception e7) {
            SkStatus.logInfo(e7.toString());
            e7.printStackTrace();
        }
    }

    public void stopVudp2() {
        new Thread(new Runnable() { 
                @Override 
                public final void run() {
                    stopVudp();
                }
            }).start();
    }
}
