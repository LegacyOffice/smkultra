package com.tekidoer.ultrasshservice;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.VpnService;
import android.os.Build;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.smkultratun.pro.R;
import com.tekidoer.ultrasshservice.config.PasswordCache;
import com.tekidoer.ultrasshservice.config.Settings;
import com.tekidoer.ultrasshservice.logger.ConnectionStatus;
import com.tekidoer.ultrasshservice.logger.SkStatus;
import com.tekidoer.ultrasshservice.tunnel.TunnelManagerHelper;
import com.tekidoer.ultrasshservice.tunnel.TunnelUtils;
import com.tekidoer.sockshttp.activities.BaseActivity;
import com.tekidoer.ultrasshservice.tunnel.TunnelManagerThread;
import com.tekidoer.ultrasshservice.tunnel.UDPThread;

public class LaunchVpn extends AppCompatActivity implements DialogInterface.OnCancelListener {
    public static final String EXTRA_HIDELOG = "com.slipkprojects.sockshttp.showNoLogWindow";
    public static final String CLEARLOG = "clearlogconnect";
    private static final int START_VPN_PROFILE = 70;

    private Settings mConfig;
    //private String mTransientAuthPW;
    private boolean mhideLog = false;
   // private boolean isMostrarSenha = false;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.launchvpn);
        mConfig = new Settings(this);
        startVpnFromIntent();
        //throw new RuntimeException();
    }

    protected void startVpnFromIntent() {
        // Resolve the intent
        final Intent intent = getIntent();
        final String action = intent.getAction();

        // If the intent is a request to create a shortcut, we'll do that and exit
        if (Intent.ACTION_MAIN.equals(action)) {
            // Check if we need to clear the log
            if (mConfig.getAutoClearLog())
                SkStatus.clearLog();
                
            mhideLog = intent.getBooleanExtra(EXTRA_HIDELOG, false);
            launchVPN();
        }
    }

  
    @Override
    public void onCancel(DialogInterface p1) {
        SkStatus.updateStateString("USER_VPN_PASSWORD_CANCELLED", "", R.string.state_user_vpn_password_cancelled, ConnectionStatus.LEVEL_NOTCONNECTED);
        finish();
    }

    private void showLogWindow() {
        Intent updateView = new Intent("com.slipkprojects.sockshttp:openLogs");
        LocalBroadcastManager.getInstance(this).sendBroadcast(updateView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == START_VPN_PROFILE) {
            if (resultCode == Activity.RESULT_OK) {
                SharedPreferences prefs = mConfig.getPrefsPrivate();
                if (!TunnelUtils.isNetworkOnline(this)) {
                    SkStatus.updateStateString("USER_VPN_PASSWORD_CANCELLED", "", R.string.state_user_vpn_password_cancelled,
                                               ConnectionStatus.LEVEL_NOTCONNECTED);
                    Toast.makeText(this, R.string.error_internet_off,
                                   Toast.LENGTH_SHORT).show();
                    finish();
                }
                else if (prefs.getInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_DIRECT) == Settings.bTUNNEL_TYPE_SSH_PROXY &&
                         (mConfig.getPrivString(Settings.PROXY_IP_KEY).isEmpty() || mConfig.getPrivString(Settings.PROXY_PORTA_KEY).isEmpty())) {
                    SkStatus.updateStateString("USER_VPN_PASSWORD_CANCELLED", "", R.string.state_user_vpn_password_cancelled,
                                               ConnectionStatus.LEVEL_NOTCONNECTED);
                    Toast.makeText(this, R.string.error_proxy_invalid,
                                   Toast.LENGTH_SHORT).show();
                    finish();
                }
                else if (!prefs.getBoolean(Settings.PROXY_USAR_DEFAULT_PAYLOAD, true) && mConfig.getPrivString(Settings.CUSTOM_PAYLOAD_KEY).isEmpty()) {
                    SkStatus.updateStateString("USER_VPN_PASSWORD_CANCELLED", "", R.string.state_user_vpn_password_cancelled,
                                               ConnectionStatus.LEVEL_NOTCONNECTED);
                    Toast.makeText(this, R.string.error_empty_payload,
                                   Toast.LENGTH_SHORT).show();
                    finish();
                }
                else if (mConfig.getPrivString(Settings.SERVIDOR_KEY).isEmpty()) {
                    SkStatus.updateStateString("USER_VPN_PASSWORD_CANCELLED", "", R.string.state_user_vpn_password_cancelled,
                                               ConnectionStatus.LEVEL_NOTCONNECTED);
                    Toast.makeText(this, "Server or Host Is Empty",
                                   Toast.LENGTH_SHORT).show();
                    finish();
                }else if (mConfig.getPrivString(Settings.SERVIDOR_PORTA_KEY).isEmpty()) {
                    SkStatus.updateStateString("USER_VPN_PASSWORD_CANCELLED", "", R.string.state_user_vpn_password_cancelled,
                                               ConnectionStatus.LEVEL_NOTCONNECTED);
                    Toast.makeText(this, "Server Port Is Empty",
                                   Toast.LENGTH_SHORT).show();
                    finish();
                }
                else if (mConfig.getPrivString(Settings.USUARIO_KEY).isEmpty()) {
                    SkStatus.updateStateString("USER_VPN_PASSWORD", "", R.string.state_user_vpn_password,
                                               ConnectionStatus.LEVEL_WAITING_FOR_USER_INPUT);
                    Toast.makeText(this, "Username is Empty",
                                   Toast.LENGTH_SHORT).show();
                }else if (mConfig.getPrivString(Settings.SENHA_KEY).isEmpty()) {
                    SkStatus.updateStateString("USER_VPN_PASSWORD", "", R.string.state_user_vpn_password,
                                               ConnectionStatus.LEVEL_WAITING_FOR_USER_INPUT);
                    Toast.makeText(this, "Password is Empty",
                                   Toast.LENGTH_SHORT).show();
                } else {
                    if (!mhideLog) {
                        showLogWindow();
                    }

                    TunnelManagerHelper.startSocksHttp(this);
                    finish();
                }

            } else if (resultCode == Activity.RESULT_CANCELED) {
                // User does not want us to start, so we just vanish
                SkStatus.updateStateString("USER_VPN_PERMISSION_CANCELLED", "", R.string.state_user_vpn_permission_cancelled,
                                           ConnectionStatus.LEVEL_NOTCONNECTED);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                    SkStatus.logError(R.string.nought_alwayson_warning);
                finish();
            }
        }
    }

    private void launchVPN() {
        Intent intent = VpnService.prepare(this);

        if (intent != null) {
            SkStatus.updateStateString("USER_VPN_PERMISSION", "", R.string.state_user_vpn_permission,
                                       ConnectionStatus.LEVEL_WAITING_FOR_USER_INPUT);
            // Start the query
            try {
                startActivityForResult(intent, START_VPN_PROFILE);
            } catch (ActivityNotFoundException ane) {
                // Shame on you Sony! At least one user reported that
                // an official Sony Xperia Arc S image triggers this exception
                SkStatus.logError(R.string.no_vpn_support_image);
                showLogWindow();
            }
        } else {
            onActivityResult(START_VPN_PROFILE, Activity.RESULT_OK, null);
        }
    }

}

