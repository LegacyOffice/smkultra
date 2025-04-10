package com.tekidoer.sockshttp;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.pm.PackageInfo;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.PersistableBundle;
import android.os.StrictMode;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.Spanned;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.tekidoer.sockshttp.activities.BaseActivity;
import com.tekidoer.sockshttp.activities.ConfigGeralActivity;
import com.tekidoer.sockshttp.adapter.LogsAdapter;
import com.tekidoer.sockshttp.adapter.SetupAdapter;
import com.tekidoer.sockshttp.model.PayloadAdapter;
import com.tekidoer.sockshttp.model.PayloadModel;
import com.tekidoer.sockshttp.model.ServerAdapter;
import com.tekidoer.sockshttp.model.ServerModel;
import com.tekidoer.sockshttp.ui.ProxySettings;
import com.tekidoer.sockshttp.util.AESCrypt;
import com.tekidoer.sockshttp.util.ConfigUtil;
import com.tekidoer.sockshttp.util.DataBaseHelper;
import com.tekidoer.sockshttp.util.RetrieveData;
import com.tekidoer.sockshttp.util.StoredData;
import com.tekidoer.sockshttp.util.Utils;
import com.tekidoer.sockshttp.view.CustomServer;
import com.tekidoer.sockshttp.view.ExportActivity;
import com.tekidoer.sockshttp.view.PayloadGeneratorDialog;
import com.tekidoer.ultrasshservice.LaunchVpn;
import com.tekidoer.ultrasshservice.SocksHttpService;
import com.tekidoer.ultrasshservice.config.ConfigParser;
import com.tekidoer.ultrasshservice.config.Settings;
import com.tekidoer.ultrasshservice.logger.ConnectionStatus;
import com.tekidoer.ultrasshservice.logger.SkStatus;
import com.tekidoer.ultrasshservice.tunnel.TunnelManagerHelper;
import com.tekidoer.ultrasshservice.tunnel.TunnelUtils;
import com.tekidoer.ultrasshservice.tunnel.UDPThread;
import com.tekidoer.ultrasshservice.util.TekidProtect;
import com.tekidoer.ultrasshservice.util.securepreferences.SecurePreferences;
import com.tekidoer.sockshttp.util.ConfigUpdate;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import android.preference.PreferenceManager;
import java.net.Proxy;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Base64;
import java.nio.charset.StandardCharsets;
import com.smkultratun.pro.BuildConfig;
import com.smkultratun.pro.R;
import io.michaelrocks.paranoid.Obfuscate;
import remoteUpdate.RemoteDialog;
import remoteUpdate.RemoteUtil;

import androidx.core.view.MenuItemCompat;
import androidx.appcompat.widget.ShareActionProvider;
import com.tekidoer.sockshttp.util.VPNUtils;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.tekidoer.sockshttp.util.ResultGetter;
import androidx.appcompat.widget.PopupMenu;

@Obfuscate
public class MainActivity extends BaseActivity implements  SkStatus.StateListener,View.OnClickListener ,View.OnLongClickListener{

    private static final String UPDATE_VIEWS = "MainUpdate";
    public static final String OPEN_LOGS = "com.tekidoer.sockshttp:openLogs";
    private static final long COUNTER_TIME = 10;
    private boolean isLoading;
    private Settings mConfig;
    private Toolbar toolbar_main;
    private Handler mHandler;
    private LinearLayout mainLayout;
    private Button starterButton;
    private AdView adsBannerView;
    private ConfigUtil config;
    FirebaseRemoteConfig firebaseRemoteConfig;

    public static final String TODAY_DATA = "todaydata";
    private SharedPreferences myData;
    private SecurePreferences sp;
    private SecurePreferences.Editor edit;
    private LogsAdapter mLogAdapter;
    private RecyclerView logList;
    private TextView config_ver_txt;
    private ImageView connectionStatus;
    private InterstitialAd interstitialAd;
    private boolean mShown, mShown2;

    private static final String[] tabTitle = {"HOME","LOGS", "STATS"};
    private ViewPager vp;
    private TabLayout tabs;
    private RewardedAd rewardedAd;

    private TextView mTextViewCountDown;
    private CountDownTimer mCountDownTimer;
    private CountDownTimer countDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis;
    private long mEndTime;
    private long saved_ads_time;
    private boolean mTimerEnabled;
    
    private SharedPreferences prefs;
    public static boolean mConnected;
    private DrawerPanelMain mDrawerPanel;

    public static final String ll ="http://noloadbalance.globe.com.ph";
    public static final String ill ="104.16.213.74";
    private AppUpdateManager mAppUpdateManager;
    private static final int RC_APP_UPDATE = 100;
    public static int PICK_FILE = 1;
    private static final int S_ONSTART_CALLED = 2;
    private static final int S_BIND_CALLED = 1;
    private AdView mAdView;

    String[] setupList = {"Direct Connection", "Custom Payload", "Custom SSL", "SSL + PAYLOAD", "SlowDNS","Custom V2Ray", "UDP Connection","Imported Config"};
    private EditText edPayload;
    private EditText edSsl;
    private EditText sslPayload;
    private EditText edSslpayload;
    private TextInputLayout pay_lay, ssl_lay;

    private Switch reconnect, imgFavorite;
    private LinearLayout messLay;
    private TextView tvMess;

    private TextInputLayout ws_sni_lay;
    private TextInputLayout ws_payload_lay;
    private TextInputLayout user_lay;
    private TextInputLayout pass_lay;
    private TextInputLayout bug_lay;
    private TextInputLayout chave_key_lay;
    private TextInputLayout serv_key_lay;
    private TextInputLayout dns_key_lay;
    private TextInputLayout dns_user_lay;
    private TextInputLayout dns_pass_lay;
    private TextInputLayout udpSerLay;
    private TextInputLayout udpObfsLay;
    private TextInputLayout udpAuthLay;
    private TextInputLayout v2lay;

    private EditText webuser;
    private EditText webpass;
    private EditText bug_host;
    private EditText chaKey;
    private EditText sersKey;
    private EditText dnsKeys;
    private EditText dnssuser;
    private EditText dnsspass;
    private EditText udpServ;
    private EditText udpObfs;
    private EditText udpAuth;
    private EditText v2link;

    private ArrayList<ServerModel> serverList;
    private ArrayList<PayloadModel> payList;
    private AppCompatSpinner serverSpinner;
    private AppCompatSpinner payloadSpinner;
    private AppCompatSpinner setupSpinner;
    private ServerAdapter serverAdapter;
    private PayloadAdapter payloadAdapter;

    private TextView bytesIn;
    private TextView bytesOut;
    private boolean gameOver;
    private boolean gamePaused;
    private android.app.AlertDialog alert;
    private Button retryButton;
    private long timeRemaining;
    private SharedPreferences spp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        mDrawerPanel = new DrawerPanelMain(this);
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        FirebaseAnalytics.getInstance(this);
        MobileAds.initialize(this);
        mHandler = new Handler(Looper.getMainLooper());
        mConfig = new Settings(this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        prefs = mConfig.getPrefsPrivate();
        sp = mConfig.getPrefsPrivate();
        spp=PreferenceManager.getDefaultSharedPreferences(this);
        myData = getSharedPreferences(TODAY_DATA, Context.MODE_PRIVATE);
        edit = sp.edit();
        if (new Boolean(sp.getBoolean("firstStart", true)).booleanValue()) {
           // sp.edit().putBoolean("reconnect_udp", true).commit();
            sp.edit().putBoolean("firstStart", false).commit();
            Settings.setDefaultConfig(this);
        }
        requestNotificationPermission();
        telegram();
        doLayout();
        checkUpdate();

        mAppUpdateManager = AppUpdateManagerFactory.create(this);   mAppUpdateManager.getAppUpdateInfo().addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>(){
                @Override
                public void onSuccess(AppUpdateInfo result) {
                    if(result.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE && result.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)){
                        try {
                            mAppUpdateManager.startUpdateFlowForResult(result, AppUpdateType.IMMEDIATE, MainActivity.this, RC_APP_UPDATE);
                        } catch (IntentSender.SendIntentException e) {}}
                }
            });
        this.mAppUpdateManager.registerListener(installUpdatelistener);

        TekidProtect.CharlieProtect();
        tekidoer_sign_check(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction(UPDATE_VIEWS);
        filter.addAction(OPEN_LOGS);
        LocalBroadcastManager.getInstance(this).registerReceiver(mActivityReceiver, filter);

        int permissionCheck = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
        }

        doUpdateLayout();
        updateConfig(true);

        if(!StoredData.isSetData) {
            StoredData.setZero();
        }
        liveData();
        requestNotificationPermission();
firebaseRemoteConfigGet();

    }
    private void firebaseRemoteConfigGet() {
        HashMap<String, Object> map = new HashMap<>();
        map.put(RemoteUtil.VERSION, BuildConfig.VERSION_CODE);
        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings firebaseRemoteConfigSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(5)
                .build();
        firebaseRemoteConfig.setConfigSettingsAsync(firebaseRemoteConfigSettings);
        firebaseRemoteConfig.setDefaultsAsync(map);
        firebaseRemoteConfig.fetchAndActivate()
                .addOnCompleteListener(new OnCompleteListener<Boolean>() {
                    @Override
                    public void onComplete(@NonNull Task<Boolean> task) {
                        showRemoteDialog();

                    }
                });


    }
    private void showRemoteDialog() {
        if (firebaseRemoteConfig.getLong(RemoteUtil.VERSION) <= BuildConfig.VERSION_CODE) return;
        RemoteDialog dialog = new RemoteDialog(this, firebaseRemoteConfig);
        dialog.show();

    }
    void telegram() {
    Spanned message = Html.fromHtml(
        "<b>Join Us On Telegram?</b><br/><br/>" +
        "We have a telegram support group where we post and discuss about settings, new features and also assist Our users.<br/><br/>" +
        "<i>Would you like to join us there?</i>"
    );

    new MaterialAlertDialogBuilder(this)
        .setTitle("Join Us On Telegram?")
        .setMessage(message)
        .setIcon(R.drawable.icon)
        .setPositiveButton("YES PLEASE", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://t.me/smk_ultra_tun")));
            }
        })
        .setNeutralButton("LATER", null)
        .create().show();
}

    private void requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (this.checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 22);
            }
        }
    }


    private void doLayout() {
    setContentView(R.layout.activity_main_drawer);
    toolbar_main = (Toolbar) findViewById(R.id.toolbar_main);
    setSupportActionBar(toolbar_main);

    // Initialize DrawerPanelMain
    if (mDrawerPanel != null) {
        mDrawerPanel.setDrawer(toolbar_main);
    } else {
        Log.e("MainActivity", "mDrawerPanel is null in doLayout");
    }

    mTextViewCountDown = (TextView) findViewById(R.id.timerTextView);
    retryButton = findViewById(R.id.btnAddTime);
    retryButton.setOnClickListener(this);

    // More layout initialization



        prefs.edit().putBoolean(Settings.PROXY_USAR_DEFAULT_PAYLOAD, false).apply();
        prefs.edit().putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_PROXY).apply();
        config = new ConfigUtil(this);

        TextView configVer = (TextView) findViewById(R.id.config_v);
        configVer.setText(config.getVersion());

        TelephonyManager telephonyManager = ((TelephonyManager) getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE));
        String simOperatorName = telephonyManager.getSimOperatorName();
        String SimRegion = telephonyManager.getSimCountryIso();

        TextView netReg = (TextView) findViewById(R.id.operator);
        netReg.setText(simOperatorName + " (" + SimRegion.toUpperCase() + ")");

        config_ver_txt = (TextView)findViewById(R.id.config_version_txt);
        config_ver_txt.setText(config.getVersion());
        bytesIn = (TextView) findViewById(R.id.bytes_in);
        bytesOut = (TextView) findViewById(R.id.bytes_out);
        connectionStatus = (ImageView)findViewById(R.id.connect_status);
        mainLayout = (LinearLayout) findViewById(R.id.activity_mainLinearLayout);
        starterButton = (Button) findViewById(R.id.activity_starterButtonMain);
        starterButton.setOnClickListener(this);
        doSaveData();

        CardView btn = (CardView) findViewById(R.id.tek);
        btn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://t.me/smk_ultra_tun")));
                }
            });
        reconnect = (Switch) findViewById(R.id.reconnect);
        reconnect.setChecked(this.sp.getBoolean("reconnect_udp", false));
        reconnect.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    sp.edit().putBoolean("reconnect_udp", isChecked).apply();
                }
            });

        pay_lay = (TextInputLayout) findViewById(R.id.payloadLayout);
        ssl_lay = (TextInputLayout) findViewById(R.id.sniLayout);
        dns_user_lay = (TextInputLayout) findViewById(R.id.UserDns);
        dns_pass_lay = (TextInputLayout) findViewById(R.id.PassDns);
        ws_payload_lay = (TextInputLayout) findViewById(R.id.sslpayloadLayout);
        ws_sni_lay = (TextInputLayout) findViewById(R.id.snipayloadLayout);
        user_lay = (TextInputLayout) findViewById(R.id.UserLayout);
        pass_lay = (TextInputLayout) findViewById(R.id.PassLayout);
        chave_key_lay = (TextInputLayout) findViewById(R.id.pubLayout);
        serv_key_lay = (TextInputLayout) findViewById(R.id.svLayout);
        dns_key_lay = (TextInputLayout) findViewById(R.id.DnsLayout);
        bug_lay = (TextInputLayout) findViewById(R.id.bugLayout);
        udpSerLay = (TextInputLayout) findViewById(R.id.udpServerlay);
        udpObfsLay = (TextInputLayout) findViewById(R.id.udpObfslay);
        udpAuthLay = (TextInputLayout) findViewById(R.id.udpAuthlay);
        v2lay = (TextInputLayout) findViewById(R.id.v2lay);

        edPayload = (EditText) findViewById(R.id.edCustomPayload);
        //edPayload.setText(prefs.getString("SavedHTTP", ""));
        edSsl = (EditText) findViewById(R.id.edCustomSSL);
        //edSsl.setText(prefs.getString("SavedSSL", ""));
        
        v2link = (EditText) findViewById(R.id.v2link);
        //v2link.setText(prefs.getString("SavedV2Ray", ""));

        sslPayload = (EditText) findViewById(R.id.sslCustomPayload);
        //sslPayload.setText(prefs.getString("SavedHTTP + SSL", ""));
        edSslpayload = (EditText) findViewById(R.id.sniCustomSSL);
        //edSslpayload.setText(prefs.getString("SavedSSL + HTTP", ""));

        webuser = (EditText) findViewById(R.id.webUser);
        //webuser.setText(prefs.getString("SavedUSER", ""));
        webpass = (EditText) findViewById(R.id.webPass);
        //webpass.setText(prefs.getString("SavedPASS", ""));

        chaKey = (EditText) findViewById(R.id.textPub);
        //chaKey.setText(prefs.getString("SavedCHAVE", ""));
        sersKey = (EditText) findViewById(R.id.textServer);
        //sersKey.setText(prefs.getString("SavedSERKEY", ""));
        dnsKeys = (EditText) findViewById(R.id.textDns);
        //dnsKeys.setText(prefs.getString("SavedDNS", ""));

        dnssuser = (EditText) findViewById(R.id.textUser);
        //dnssuser.setText(prefs.getString("SavedUSER", ""));
        dnsspass = (EditText) findViewById(R.id.textPass);
        //dnsspass.setText(prefs.getString("SavedPASS", ""));

        bug_host = (EditText) findViewById(R.id.webBug);
        //bug_host.setText(prefs.getString("SavedBUG", ""));

        udpServ = (EditText) findViewById(R.id.udpIp);
        //udpServ.setText(prefs.getString("SavedUDP", ""));
        udpObfs = (EditText) findViewById(R.id.udpUser);
        //udpObfs.setText(prefs.getString("SavedOBFS", ""));
        udpAuth = (EditText) findViewById(R.id.udpPass);
        //udpAuth.setText(prefs.getString("SavedAUTH", ""));

        messLay = (LinearLayout) findViewById(R.id.messageLayout);
        tvMess = (TextView) findViewById(R.id.tvMessage);

        setupSpinner = (androidx.appcompat.widget.AppCompatSpinner) findViewById(R.id.setupSpinner);
        SetupAdapter setupAdapter = new SetupAdapter(this, R.id.setupSpinner, setupList);
        setupSpinner.setAdapter(setupAdapter);
        setupSpinner.setSelection(prefs.getInt("SavedPos",0));
        setupSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> p1, View p2, int p3, long p4)  {
                    prefs.edit().putInt("SavedPos", p3).apply();
                    if (p3 == 0) {
                        bug_lay.setVisibility(View.GONE);
                        pay_lay.setVisibility(View.GONE);
                        ssl_lay.setVisibility(View.GONE);
                        messLay.setVisibility(View.GONE);
                        ws_payload_lay.setVisibility(View.GONE);
                        ws_sni_lay.setVisibility(View.GONE);
                        user_lay.setVisibility(View.GONE);
                        pass_lay.setVisibility(View.GONE);
                        chave_key_lay.setVisibility(View.GONE);
                        serv_key_lay.setVisibility(View.GONE);
                        dns_key_lay.setVisibility(View.GONE);
                        dns_user_lay.setVisibility(View.GONE);
                        dns_pass_lay.setVisibility(View.GONE);
                        udpSerLay.setVisibility(View.GONE);
                        udpObfsLay.setVisibility(View.GONE);
                        udpAuthLay.setVisibility(View.GONE);
                        v2link.setVisibility(View.GONE);
                        v2lay.setVisibility(View.GONE);
                    } else if (p3 == 1) {
                        ws_payload_lay.setVisibility(View.GONE);
                        ws_sni_lay.setVisibility(View.GONE);
                        pay_lay.setVisibility(View.VISIBLE);
                        ssl_lay.setVisibility(View.GONE);
                        messLay.setVisibility(View.GONE);
                        user_lay.setVisibility(View.GONE);
                        pass_lay.setVisibility(View.GONE);
                        bug_lay.setVisibility(View.GONE);
                        chave_key_lay.setVisibility(View.GONE);
                        serv_key_lay.setVisibility(View.GONE);
                        dns_key_lay.setVisibility(View.GONE);
                        dns_user_lay.setVisibility(View.GONE);
                        dns_pass_lay.setVisibility(View.GONE);
                        udpSerLay.setVisibility(View.GONE);
                        udpObfsLay.setVisibility(View.GONE);
                        udpAuthLay.setVisibility(View.GONE);
                        v2link.setVisibility(View.GONE);
                        v2lay.setVisibility(View.GONE);
                    } else if (p3 == 2) {
                        pay_lay.setVisibility(View.GONE);
                        ssl_lay.setVisibility(View.VISIBLE);
                        messLay.setVisibility(View.GONE);
                        ws_payload_lay.setVisibility(View.GONE);
                        ws_sni_lay.setVisibility(View.GONE);
                        user_lay.setVisibility(View.GONE);
                        pass_lay.setVisibility(View.GONE);
                        bug_lay.setVisibility(View.GONE);
                        chave_key_lay.setVisibility(View.GONE);
                        serv_key_lay.setVisibility(View.GONE);
                        dns_key_lay.setVisibility(View.GONE);
                        dns_user_lay.setVisibility(View.GONE);
                        dns_pass_lay.setVisibility(View.GONE);
                        udpSerLay.setVisibility(View.GONE);
                        udpObfsLay.setVisibility(View.GONE);
                        udpAuthLay.setVisibility(View.GONE);
                        v2link.setVisibility(View.GONE);
                        v2lay.setVisibility(View.GONE);
                    }else if(p3 == 3) {
                        prefs.edit().putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_PAY_SSL).apply();
                        ws_payload_lay.setVisibility(View.VISIBLE);
                        ws_sni_lay.setVisibility(View.VISIBLE);
                        user_lay.setVisibility(View.VISIBLE);
                        pass_lay.setVisibility(View.VISIBLE);
                        bug_lay.setVisibility(View.VISIBLE);
                        pay_lay.setVisibility(View.GONE);
                        ssl_lay.setVisibility(View.GONE);
                        messLay.setVisibility(View.GONE);
                        chave_key_lay.setVisibility(View.GONE);
                        serv_key_lay.setVisibility(View.GONE);
                        dns_key_lay.setVisibility(View.GONE);
                        dns_user_lay.setVisibility(View.GONE);
                        dns_pass_lay.setVisibility(View.GONE);
                        //dnsspass.setVisibility(View.GONE);
                        udpSerLay.setVisibility(View.GONE);
                        udpObfsLay.setVisibility(View.GONE);
                        udpAuthLay.setVisibility(View.GONE);
                        v2link.setVisibility(View.GONE);
                        v2lay.setVisibility(View.GONE);
                    }else if(p3 == 4) {
                        chave_key_lay.setVisibility(View.VISIBLE);
                        serv_key_lay.setVisibility(View.VISIBLE);
                        dns_key_lay.setVisibility(View.VISIBLE);
                        dns_user_lay.setVisibility(View.VISIBLE);
                        dns_pass_lay.setVisibility(View.VISIBLE);
                        pay_lay.setVisibility(View.GONE);
                        ssl_lay.setVisibility(View.GONE);
                        messLay.setVisibility(View.GONE);
                        ws_payload_lay.setVisibility(View.GONE);
                        ws_sni_lay.setVisibility(View.GONE);
                        user_lay.setVisibility(View.GONE);
                        pass_lay.setVisibility(View.GONE);
                        bug_lay.setVisibility(View.GONE);
                        udpSerLay.setVisibility(View.GONE);
                        udpObfsLay.setVisibility(View.GONE);
                        udpAuthLay.setVisibility(View.GONE);
                        v2link.setVisibility(View.GONE);
                        v2lay.setVisibility(View.GONE);
                        
                    }else if(p3 == 5) {
                        chave_key_lay.setVisibility(View.GONE);
                        serv_key_lay.setVisibility(View.GONE);
                        dns_key_lay.setVisibility(View.GONE);
                        dns_user_lay.setVisibility(View.GONE);
                        dns_pass_lay.setVisibility(View.GONE);
                        pay_lay.setVisibility(View.GONE);
                        ssl_lay.setVisibility(View.GONE);
                        messLay.setVisibility(View.GONE);
                        ws_payload_lay.setVisibility(View.GONE);
                        ws_sni_lay.setVisibility(View.GONE);
                        user_lay.setVisibility(View.GONE);
                        pass_lay.setVisibility(View.GONE);
                        bug_lay.setVisibility(View.GONE);
                        udpSerLay.setVisibility(View.GONE);
                        udpObfsLay.setVisibility(View.GONE);
                        udpAuthLay.setVisibility(View.GONE);
                        v2link.setVisibility(View.VISIBLE);
                        v2lay.setVisibility(View.VISIBLE);

                    } else if(p3 == 6) {
                        chave_key_lay.setVisibility(View.GONE);
                        serv_key_lay.setVisibility(View.GONE);
                        dns_key_lay.setVisibility(View.GONE);
                        dns_user_lay.setVisibility(View.GONE);
                        dns_pass_lay.setVisibility(View.GONE);
                        pay_lay.setVisibility(View.GONE);
                        ssl_lay.setVisibility(View.GONE);
                        messLay.setVisibility(View.GONE);
                        ws_payload_lay.setVisibility(View.GONE);
                        ws_sni_lay.setVisibility(View.GONE);
                        user_lay.setVisibility(View.GONE);
                        pass_lay.setVisibility(View.GONE);
                        bug_lay.setVisibility(View.GONE);
                        udpSerLay.setVisibility(View.VISIBLE);
                        udpObfsLay.setVisibility(View.VISIBLE);
                        udpAuthLay.setVisibility(View.VISIBLE);
                        v2link.setVisibility(View.GONE);
                        v2lay.setVisibility(View.GONE);

                    } else if (p3 == 7) {
                        chave_key_lay.setVisibility(View.GONE);
                        serv_key_lay.setVisibility(View.GONE);
                        dns_key_lay.setVisibility(View.GONE);
                        dns_user_lay.setVisibility(View.GONE);
                        dns_pass_lay.setVisibility(View.GONE);
                        pay_lay.setVisibility(View.GONE);
                        ssl_lay.setVisibility(View.GONE);

                        ws_payload_lay.setVisibility(View.GONE);
                        ws_sni_lay.setVisibility(View.GONE);
                        user_lay.setVisibility(View.GONE);
                        pass_lay.setVisibility(View.GONE);
                        bug_lay.setVisibility(View.GONE);
                        udpSerLay.setVisibility(View.GONE);
                        udpObfsLay.setVisibility(View.GONE);
                        udpAuthLay.setVisibility(View.GONE);
                        v2link.setVisibility(View.GONE);
                        v2lay.setVisibility(View.GONE);
                        if (prefs.getBoolean("isMsg", false)) {
                            messLay.setVisibility(View.VISIBLE);
                            tvMess.setText(Html.fromHtml(prefs.getString("Mess", "").replace("\n", "<br/>")));
                        }
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> p1) {

                }
            });

        
        imgFavorite = (Switch) findViewById(R.id.ckSetup);
        imgFavorite.setChecked(prefs.getBoolean("SavedSetup", false));
        imgFavorite.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton p1, boolean p2)  {
                    prefs.edit().putBoolean("SavedSetup", p2).apply();
                    if (p2) {
                        payloadSpinner.setVisibility(View.GONE);
                        setupSpinner.setVisibility(View.VISIBLE);
                        int p3 = prefs.getInt("SavedPos", 0);
                        if (p3 == 0) {
                            bug_lay.setVisibility(View.GONE);
                            pay_lay.setVisibility(View.GONE);
                            ssl_lay.setVisibility(View.GONE);
                            messLay.setVisibility(View.GONE);
                            ws_payload_lay.setVisibility(View.GONE);
                            ws_sni_lay.setVisibility(View.GONE);
                            user_lay.setVisibility(View.GONE);
                            pass_lay.setVisibility(View.GONE);
                            chave_key_lay.setVisibility(View.GONE);
                            serv_key_lay.setVisibility(View.GONE);
                            dns_key_lay.setVisibility(View.GONE);
                            dns_user_lay.setVisibility(View.GONE);
                            dns_pass_lay.setVisibility(View.GONE);
                            udpSerLay.setVisibility(View.GONE);
                            udpObfsLay.setVisibility(View.GONE);
                            udpAuthLay.setVisibility(View.GONE);
                            v2link.setVisibility(View.GONE);
                            v2lay.setVisibility(View.GONE);
                        } else if (p3 == 1) {
                            ws_payload_lay.setVisibility(View.GONE);
                            ws_sni_lay.setVisibility(View.GONE);
                            pay_lay.setVisibility(View.VISIBLE);
                            ssl_lay.setVisibility(View.GONE);
                            messLay.setVisibility(View.GONE);
                            user_lay.setVisibility(View.GONE);
                            pass_lay.setVisibility(View.GONE);
                            bug_lay.setVisibility(View.GONE);
                            chave_key_lay.setVisibility(View.GONE);
                            serv_key_lay.setVisibility(View.GONE);
                            dns_key_lay.setVisibility(View.GONE);
                            dns_user_lay.setVisibility(View.GONE);
                            dns_pass_lay.setVisibility(View.GONE);
                            udpSerLay.setVisibility(View.GONE);
                            udpObfsLay.setVisibility(View.GONE);
                            udpAuthLay.setVisibility(View.GONE);
                            v2link.setVisibility(View.GONE);
                            v2lay.setVisibility(View.GONE);
                        } else if (p3 == 2) {
                            pay_lay.setVisibility(View.GONE);
                            ssl_lay.setVisibility(View.VISIBLE);
                            messLay.setVisibility(View.GONE);
                            ws_payload_lay.setVisibility(View.GONE);
                            ws_sni_lay.setVisibility(View.GONE);
                            user_lay.setVisibility(View.GONE);
                            pass_lay.setVisibility(View.GONE);
                            bug_lay.setVisibility(View.GONE);
                            chave_key_lay.setVisibility(View.GONE);
                            serv_key_lay.setVisibility(View.GONE);
                            dns_key_lay.setVisibility(View.GONE);
                            dns_user_lay.setVisibility(View.GONE);
                            dns_pass_lay.setVisibility(View.GONE);
                            udpSerLay.setVisibility(View.GONE);
                            udpObfsLay.setVisibility(View.GONE);
                            udpAuthLay.setVisibility(View.GONE);
                            v2link.setVisibility(View.GONE);
                            v2lay.setVisibility(View.GONE);
                        }else if(p3 == 3) {
                            prefs.edit().putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_PAY_SSL).apply();
                            ws_payload_lay.setVisibility(View.VISIBLE);
                            ws_sni_lay.setVisibility(View.VISIBLE);
                            user_lay.setVisibility(View.VISIBLE);
                            pass_lay.setVisibility(View.VISIBLE);
                            bug_lay.setVisibility(View.VISIBLE);
                            pay_lay.setVisibility(View.GONE);
                            ssl_lay.setVisibility(View.GONE);
                            messLay.setVisibility(View.GONE);
                            chave_key_lay.setVisibility(View.GONE);
                            serv_key_lay.setVisibility(View.GONE);
                            dns_key_lay.setVisibility(View.GONE);
                            dns_user_lay.setVisibility(View.GONE);
                            dns_pass_lay.setVisibility(View.GONE);
                            //dnsspass.setVisibility(View.GONE);
                            udpSerLay.setVisibility(View.GONE);
                            udpObfsLay.setVisibility(View.GONE);
                            udpAuthLay.setVisibility(View.GONE);
                            v2link.setVisibility(View.GONE);
                            v2lay.setVisibility(View.GONE);
                        }else if(p3 == 4) {
                            chave_key_lay.setVisibility(View.VISIBLE);
                            serv_key_lay.setVisibility(View.VISIBLE);
                            dns_key_lay.setVisibility(View.VISIBLE);
                            dns_user_lay.setVisibility(View.VISIBLE);
                            dns_pass_lay.setVisibility(View.VISIBLE);
                            pay_lay.setVisibility(View.GONE);
                            ssl_lay.setVisibility(View.GONE);
                            messLay.setVisibility(View.GONE);
                            ws_payload_lay.setVisibility(View.GONE);
                            ws_sni_lay.setVisibility(View.GONE);
                            user_lay.setVisibility(View.GONE);
                            pass_lay.setVisibility(View.GONE);
                            bug_lay.setVisibility(View.GONE);
                            udpSerLay.setVisibility(View.GONE);
                            udpObfsLay.setVisibility(View.GONE);
                            udpAuthLay.setVisibility(View.GONE);
                            v2link.setVisibility(View.GONE);
                            v2lay.setVisibility(View.GONE);

                        }else if(p3 == 5) {
                            chave_key_lay.setVisibility(View.GONE);
                            serv_key_lay.setVisibility(View.GONE);
                            dns_key_lay.setVisibility(View.GONE);
                            dns_user_lay.setVisibility(View.GONE);
                            dns_pass_lay.setVisibility(View.GONE);
                            pay_lay.setVisibility(View.GONE);
                            ssl_lay.setVisibility(View.GONE);
                            messLay.setVisibility(View.GONE);
                            ws_payload_lay.setVisibility(View.GONE);
                            ws_sni_lay.setVisibility(View.GONE);
                            user_lay.setVisibility(View.GONE);
                            pass_lay.setVisibility(View.GONE);
                            bug_lay.setVisibility(View.GONE);
                            udpSerLay.setVisibility(View.GONE);
                            udpObfsLay.setVisibility(View.GONE);
                            udpAuthLay.setVisibility(View.GONE);
                            v2link.setVisibility(View.VISIBLE);
                            v2lay.setVisibility(View.VISIBLE);

                        } else if(p3 == 6) {
                            chave_key_lay.setVisibility(View.GONE);
                            serv_key_lay.setVisibility(View.GONE);
                            dns_key_lay.setVisibility(View.GONE);
                            dns_user_lay.setVisibility(View.GONE);
                            dns_pass_lay.setVisibility(View.GONE);
                            pay_lay.setVisibility(View.GONE);
                            ssl_lay.setVisibility(View.GONE);
                            messLay.setVisibility(View.GONE);
                            ws_payload_lay.setVisibility(View.GONE);
                            ws_sni_lay.setVisibility(View.GONE);
                            user_lay.setVisibility(View.GONE);
                            pass_lay.setVisibility(View.GONE);
                            bug_lay.setVisibility(View.GONE);
                            udpSerLay.setVisibility(View.VISIBLE);
                            udpObfsLay.setVisibility(View.VISIBLE);
                            udpAuthLay.setVisibility(View.VISIBLE);
                            v2link.setVisibility(View.GONE);
                            v2lay.setVisibility(View.GONE);

                        } else if (p3 == 7) {
                            chave_key_lay.setVisibility(View.GONE);
                            serv_key_lay.setVisibility(View.GONE);
                            dns_key_lay.setVisibility(View.GONE);
                            dns_user_lay.setVisibility(View.GONE);
                            dns_pass_lay.setVisibility(View.GONE);
                            pay_lay.setVisibility(View.GONE);
                            ssl_lay.setVisibility(View.GONE);

                            ws_payload_lay.setVisibility(View.GONE);
                            ws_sni_lay.setVisibility(View.GONE);
                            user_lay.setVisibility(View.GONE);
                            pass_lay.setVisibility(View.GONE);
                            bug_lay.setVisibility(View.GONE);
                            udpSerLay.setVisibility(View.GONE);
                            udpObfsLay.setVisibility(View.GONE);
                            udpAuthLay.setVisibility(View.GONE);
                            v2link.setVisibility(View.GONE);
                            v2lay.setVisibility(View.GONE);
                            if (prefs.getBoolean("isMsg", false)) {
                                messLay.setVisibility(View.VISIBLE);
                                tvMess.setText(Html.fromHtml(prefs.getString("Mess", "").replace("\n", "<br/>")));
                            }
                        }
                    } else {
                        setupSpinner.setVisibility(View.GONE);
                        payloadSpinner.setVisibility(View.VISIBLE);
                        bug_lay.setVisibility(View.GONE);
                        pay_lay.setVisibility(View.GONE);
                        ssl_lay.setVisibility(View.GONE);
                        messLay.setVisibility(View.GONE);
                        ws_payload_lay.setVisibility(View.GONE);
                        ws_sni_lay.setVisibility(View.GONE);
                        user_lay.setVisibility(View.GONE);
                        pass_lay.setVisibility(View.GONE);
                        chave_key_lay.setVisibility(View.GONE);
                        serv_key_lay.setVisibility(View.GONE);
                        dns_key_lay.setVisibility(View.GONE);
                        dns_user_lay.setVisibility(View.GONE);
                        dns_pass_lay.setVisibility(View.GONE);
                        udpSerLay.setVisibility(View.GONE);
                        udpObfsLay.setVisibility(View.GONE);
                        udpAuthLay.setVisibility(View.GONE);
                        v2link.setVisibility(View.GONE);
                        v2lay.setVisibility(View.GONE);
                    }
                }
            });

        serverList = new ArrayList<ServerModel>();
        serverSpinner = (AppCompatSpinner) findViewById(R.id.serverSpinner);
        serverSpinner.setLongClickable(true);
        serverSpinner.setOnLongClickListener(this);

        payList = new ArrayList<PayloadModel>();
        payloadSpinner = (AppCompatSpinner) findViewById(R.id.payloadSpinner);

        serverAdapter = new ServerAdapter(this, this, serverList, mConfig.getPrefsPrivate().getInt("Selected1",0));
        payloadAdapter = new PayloadAdapter(this, payList, mConfig.getPrefsPrivate().getInt("Selected2",0));
        if (config.getJSONConfig() != null){
            serverSpinner.setAdapter(serverAdapter);
            payloadSpinner.setAdapter(payloadAdapter);
        } else {
            toast(this, R.color.red, "No Server & Payload Detected.");
        }
        serverSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                @Override
                public void onItemSelected(AdapterView<?> p1, View p2, int p3, long p4) {
                    mConfig.getPrefsPrivate().edit().putInt("Selected1", p3).apply();
                    showInterstitial();
                }
                @Override
                public void onNothingSelected(AdapterView<?> p1) {
                    Toast.makeText(getApplicationContext(), "Nothing selected!",0).show();
                }
            });
        payloadSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                @Override
                public void onItemSelected(AdapterView<?> p1, View p2, int p3, long p4) {
                    mConfig.getPrefsPrivate().edit().putInt("Selected2", p3).apply();
                    showInterstitial();
                }
                @Override
                public void onNothingSelected(AdapterView<?> p1) {
                    Toast.makeText(getApplicationContext(), "Nothing selected!",0).show();
                }
            });
        serverSpinner.setSelection(mConfig.getPrefsPrivate().getInt("Selected1",0));
        payloadSpinner.setSelection(mConfig.getPrefsPrivate().getInt("Selected2",0));
        try {
            JSONArray jsonArray = config.getServersArray();
            for (int i = 0; i < jsonArray.length(); i++) {
                TextView serverCount = (TextView) findViewById(R.id.serverCount);
                serverCount.setText("Server(s): " + jsonArray.length());
            }
        } catch (Exception err){
            err.printStackTrace();
            toast(MainActivity.this, R.color.red, err.getMessage());
        }
        try {
            JSONArray jsonArray2 = config.getNetworksArray();
            for (int i = 0; i < jsonArray2.length(); i++) {
                TextView payCount = (TextView) findViewById(R.id.payCount);
                payCount.setText("Tweak(s): " + jsonArray2.length());
            }
        } catch (Exception err){
            err.printStackTrace();
            toast(MainActivity.this, R.color.red, err.getMessage());
        }

        loadServer();
        loadNetworks();
        onbanner();
        int p3 = setupSpinner.getSelectedItemPosition();
        if (prefs.getBoolean("SavedSetup", false)) {
            setupSpinner.setVisibility(View.VISIBLE);
            payloadSpinner.setVisibility(View.GONE);
            if (p3 == 0) {
                bug_lay.setVisibility(View.GONE);
                pay_lay.setVisibility(View.GONE);
                ssl_lay.setVisibility(View.GONE);
                messLay.setVisibility(View.GONE);
                ws_payload_lay.setVisibility(View.GONE);
                ws_sni_lay.setVisibility(View.GONE);
                user_lay.setVisibility(View.GONE);
                pass_lay.setVisibility(View.GONE);
                chave_key_lay.setVisibility(View.GONE);
                serv_key_lay.setVisibility(View.GONE);
                dns_key_lay.setVisibility(View.GONE);
                dns_user_lay.setVisibility(View.GONE);
                dns_pass_lay.setVisibility(View.GONE);
                udpSerLay.setVisibility(View.GONE);
                udpObfsLay.setVisibility(View.GONE);
                udpAuthLay.setVisibility(View.GONE);
                v2link.setVisibility(View.GONE);
                v2lay.setVisibility(View.GONE);
            } else if (p3 == 1) {
                ws_payload_lay.setVisibility(View.GONE);
                ws_sni_lay.setVisibility(View.GONE);
                pay_lay.setVisibility(View.VISIBLE);
                ssl_lay.setVisibility(View.GONE);
                messLay.setVisibility(View.GONE);
                user_lay.setVisibility(View.GONE);
                pass_lay.setVisibility(View.GONE);
                bug_lay.setVisibility(View.GONE);
                chave_key_lay.setVisibility(View.GONE);
                serv_key_lay.setVisibility(View.GONE);
                dns_key_lay.setVisibility(View.GONE);
                dns_user_lay.setVisibility(View.GONE);
                dns_pass_lay.setVisibility(View.GONE);
                udpSerLay.setVisibility(View.GONE);
                udpObfsLay.setVisibility(View.GONE);
                udpAuthLay.setVisibility(View.GONE);
                v2link.setVisibility(View.GONE);
                v2lay.setVisibility(View.GONE);
            } else if (p3 == 2) {
                pay_lay.setVisibility(View.GONE);
                ssl_lay.setVisibility(View.VISIBLE);
                messLay.setVisibility(View.GONE);
                ws_payload_lay.setVisibility(View.GONE);
                ws_sni_lay.setVisibility(View.GONE);
                user_lay.setVisibility(View.GONE);
                pass_lay.setVisibility(View.GONE);
                bug_lay.setVisibility(View.GONE);
                chave_key_lay.setVisibility(View.GONE);
                serv_key_lay.setVisibility(View.GONE);
                dns_key_lay.setVisibility(View.GONE);
                dns_user_lay.setVisibility(View.GONE);
                dns_pass_lay.setVisibility(View.GONE);
                udpSerLay.setVisibility(View.GONE);
                udpObfsLay.setVisibility(View.GONE);
                udpAuthLay.setVisibility(View.GONE);
                v2link.setVisibility(View.GONE);
                v2lay.setVisibility(View.GONE);
            }else if(p3 == 3) {
                prefs.edit().putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_PAY_SSL).apply();
                ws_payload_lay.setVisibility(View.VISIBLE);
                ws_sni_lay.setVisibility(View.VISIBLE);
                user_lay.setVisibility(View.VISIBLE);
                pass_lay.setVisibility(View.VISIBLE);
                bug_lay.setVisibility(View.VISIBLE);
                pay_lay.setVisibility(View.GONE);
                ssl_lay.setVisibility(View.GONE);
                messLay.setVisibility(View.GONE);
                chave_key_lay.setVisibility(View.GONE);
                serv_key_lay.setVisibility(View.GONE);
                dns_key_lay.setVisibility(View.GONE);
                dns_user_lay.setVisibility(View.GONE);
                dns_pass_lay.setVisibility(View.GONE);
                //dnsspass.setVisibility(View.GONE);
                udpSerLay.setVisibility(View.GONE);
                udpObfsLay.setVisibility(View.GONE);
                udpAuthLay.setVisibility(View.GONE);
                v2link.setVisibility(View.GONE);
                v2lay.setVisibility(View.GONE);
            }else if(p3 == 4) {
                chave_key_lay.setVisibility(View.VISIBLE);
                serv_key_lay.setVisibility(View.VISIBLE);
                dns_key_lay.setVisibility(View.VISIBLE);
                dns_user_lay.setVisibility(View.VISIBLE);
                dns_pass_lay.setVisibility(View.VISIBLE);
                pay_lay.setVisibility(View.GONE);
                ssl_lay.setVisibility(View.GONE);
                messLay.setVisibility(View.GONE);
                ws_payload_lay.setVisibility(View.GONE);
                ws_sni_lay.setVisibility(View.GONE);
                user_lay.setVisibility(View.GONE);
                pass_lay.setVisibility(View.GONE);
                bug_lay.setVisibility(View.GONE);
                udpSerLay.setVisibility(View.GONE);
                udpObfsLay.setVisibility(View.GONE);
                udpAuthLay.setVisibility(View.GONE);
                v2link.setVisibility(View.GONE);
                v2lay.setVisibility(View.GONE);

            }else if(p3 == 5) {
                chave_key_lay.setVisibility(View.GONE);
                serv_key_lay.setVisibility(View.GONE);
                dns_key_lay.setVisibility(View.GONE);
                dns_user_lay.setVisibility(View.GONE);
                dns_pass_lay.setVisibility(View.GONE);
                pay_lay.setVisibility(View.GONE);
                ssl_lay.setVisibility(View.GONE);
                messLay.setVisibility(View.GONE);
                ws_payload_lay.setVisibility(View.GONE);
                ws_sni_lay.setVisibility(View.GONE);
                user_lay.setVisibility(View.GONE);
                pass_lay.setVisibility(View.GONE);
                bug_lay.setVisibility(View.GONE);
                udpSerLay.setVisibility(View.GONE);
                udpObfsLay.setVisibility(View.GONE);
                udpAuthLay.setVisibility(View.GONE);
                v2link.setVisibility(View.VISIBLE);
                v2lay.setVisibility(View.VISIBLE);

            } else if(p3 == 6) {
                chave_key_lay.setVisibility(View.GONE);
                serv_key_lay.setVisibility(View.GONE);
                dns_key_lay.setVisibility(View.GONE);
                dns_user_lay.setVisibility(View.GONE);
                dns_pass_lay.setVisibility(View.GONE);
                pay_lay.setVisibility(View.GONE);
                ssl_lay.setVisibility(View.GONE);
                messLay.setVisibility(View.GONE);
                ws_payload_lay.setVisibility(View.GONE);
                ws_sni_lay.setVisibility(View.GONE);
                user_lay.setVisibility(View.GONE);
                pass_lay.setVisibility(View.GONE);
                bug_lay.setVisibility(View.GONE);
                udpSerLay.setVisibility(View.VISIBLE);
                udpObfsLay.setVisibility(View.VISIBLE);
                udpAuthLay.setVisibility(View.VISIBLE);
                v2link.setVisibility(View.GONE);
                v2lay.setVisibility(View.GONE);

            } else if (p3 == 7) {
                chave_key_lay.setVisibility(View.GONE);
                serv_key_lay.setVisibility(View.GONE);
                dns_key_lay.setVisibility(View.GONE);
                dns_user_lay.setVisibility(View.GONE);
                dns_pass_lay.setVisibility(View.GONE);
                pay_lay.setVisibility(View.GONE);
                ssl_lay.setVisibility(View.GONE);

                ws_payload_lay.setVisibility(View.GONE);
                ws_sni_lay.setVisibility(View.GONE);
                user_lay.setVisibility(View.GONE);
                pass_lay.setVisibility(View.GONE);
                bug_lay.setVisibility(View.GONE);
                udpSerLay.setVisibility(View.GONE);
                udpObfsLay.setVisibility(View.GONE);
                udpAuthLay.setVisibility(View.GONE);
                v2link.setVisibility(View.GONE);
                v2lay.setVisibility(View.GONE);
                if (prefs.getBoolean("isMsg", false)) {
                    messLay.setVisibility(View.VISIBLE);
                    tvMess.setText(Html.fromHtml(prefs.getString("Mess", "").replace("\n", "<br/>")));
                }
            }
        } else {
            setupSpinner.setVisibility(View.GONE);
            payloadSpinner.setVisibility(View.VISIBLE);
            bug_lay.setVisibility(View.GONE);
            pay_lay.setVisibility(View.GONE);
            ssl_lay.setVisibility(View.GONE);
            messLay.setVisibility(View.GONE);
            ws_payload_lay.setVisibility(View.GONE);
            ws_sni_lay.setVisibility(View.GONE);
            user_lay.setVisibility(View.GONE);
            pass_lay.setVisibility(View.GONE);
            chave_key_lay.setVisibility(View.GONE);
            serv_key_lay.setVisibility(View.GONE);
            dns_key_lay.setVisibility(View.GONE);
            dns_user_lay.setVisibility(View.GONE);
            dns_pass_lay.setVisibility(View.GONE);
            udpSerLay.setVisibility(View.GONE);
            udpObfsLay.setVisibility(View.GONE);
            udpAuthLay.setVisibility(View.GONE);
            v2link.setVisibility(View.GONE);
            v2lay.setVisibility(View.GONE);
        }

        if (imgFavorite.isChecked()) {
            if (p3 == 0) {
                bug_lay.setVisibility(View.GONE);
                pay_lay.setVisibility(View.GONE);
                ssl_lay.setVisibility(View.GONE);
                messLay.setVisibility(View.GONE);
                ws_payload_lay.setVisibility(View.GONE);
                ws_sni_lay.setVisibility(View.GONE);
                user_lay.setVisibility(View.GONE);
                pass_lay.setVisibility(View.GONE);
                chave_key_lay.setVisibility(View.GONE);
                serv_key_lay.setVisibility(View.GONE);
                dns_key_lay.setVisibility(View.GONE);
                dns_user_lay.setVisibility(View.GONE);
                dns_pass_lay.setVisibility(View.GONE);
                udpSerLay.setVisibility(View.GONE);
                udpObfsLay.setVisibility(View.GONE);
                udpAuthLay.setVisibility(View.GONE);
                v2link.setVisibility(View.GONE);
                v2lay.setVisibility(View.GONE);
            } else if (p3 == 1) {
                ws_payload_lay.setVisibility(View.GONE);
                ws_sni_lay.setVisibility(View.GONE);
                pay_lay.setVisibility(View.VISIBLE);
                ssl_lay.setVisibility(View.GONE);
                messLay.setVisibility(View.GONE);
                user_lay.setVisibility(View.GONE);
                pass_lay.setVisibility(View.GONE);
                bug_lay.setVisibility(View.GONE);
                chave_key_lay.setVisibility(View.GONE);
                serv_key_lay.setVisibility(View.GONE);
                dns_key_lay.setVisibility(View.GONE);
                dns_user_lay.setVisibility(View.GONE);
                dns_pass_lay.setVisibility(View.GONE);
                udpSerLay.setVisibility(View.GONE);
                udpObfsLay.setVisibility(View.GONE);
                udpAuthLay.setVisibility(View.GONE);
                v2link.setVisibility(View.GONE);
                v2lay.setVisibility(View.GONE);
            } else if (p3 == 2) {
                pay_lay.setVisibility(View.GONE);
                ssl_lay.setVisibility(View.VISIBLE);
                messLay.setVisibility(View.GONE);
                ws_payload_lay.setVisibility(View.GONE);
                ws_sni_lay.setVisibility(View.GONE);
                user_lay.setVisibility(View.GONE);
                pass_lay.setVisibility(View.GONE);
                bug_lay.setVisibility(View.GONE);
                chave_key_lay.setVisibility(View.GONE);
                serv_key_lay.setVisibility(View.GONE);
                dns_key_lay.setVisibility(View.GONE);
                dns_user_lay.setVisibility(View.GONE);
                dns_pass_lay.setVisibility(View.GONE);
                udpSerLay.setVisibility(View.GONE);
                udpObfsLay.setVisibility(View.GONE);
                udpAuthLay.setVisibility(View.GONE);
                v2link.setVisibility(View.GONE);
                v2lay.setVisibility(View.GONE);
            }else if(p3 == 3) {
                prefs.edit().putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_PAY_SSL).apply();
                ws_payload_lay.setVisibility(View.VISIBLE);
                ws_sni_lay.setVisibility(View.VISIBLE);
                user_lay.setVisibility(View.VISIBLE);
                pass_lay.setVisibility(View.VISIBLE);
                bug_lay.setVisibility(View.VISIBLE);
                pay_lay.setVisibility(View.GONE);
                ssl_lay.setVisibility(View.GONE);
                messLay.setVisibility(View.GONE);
                chave_key_lay.setVisibility(View.GONE);
                serv_key_lay.setVisibility(View.GONE);
                dns_key_lay.setVisibility(View.GONE);
                dns_user_lay.setVisibility(View.GONE);
                dns_pass_lay.setVisibility(View.GONE);
                //dnsspass.setVisibility(View.GONE);
                udpSerLay.setVisibility(View.GONE);
                udpObfsLay.setVisibility(View.GONE);
                udpAuthLay.setVisibility(View.GONE);
                v2link.setVisibility(View.GONE);
                v2lay.setVisibility(View.GONE);
            }else if(p3 == 4) {
                chave_key_lay.setVisibility(View.VISIBLE);
                serv_key_lay.setVisibility(View.VISIBLE);
                dns_key_lay.setVisibility(View.VISIBLE);
                dns_user_lay.setVisibility(View.VISIBLE);
                dns_pass_lay.setVisibility(View.VISIBLE);
                pay_lay.setVisibility(View.GONE);
                ssl_lay.setVisibility(View.GONE);
                messLay.setVisibility(View.GONE);
                ws_payload_lay.setVisibility(View.GONE);
                ws_sni_lay.setVisibility(View.GONE);
                user_lay.setVisibility(View.GONE);
                pass_lay.setVisibility(View.GONE);
                bug_lay.setVisibility(View.GONE);
                udpSerLay.setVisibility(View.GONE);
                udpObfsLay.setVisibility(View.GONE);
                udpAuthLay.setVisibility(View.GONE);
                v2link.setVisibility(View.GONE);
                v2lay.setVisibility(View.GONE);

            }else if(p3 == 5) {
                chave_key_lay.setVisibility(View.GONE);
                serv_key_lay.setVisibility(View.GONE);
                dns_key_lay.setVisibility(View.GONE);
                dns_user_lay.setVisibility(View.GONE);
                dns_pass_lay.setVisibility(View.GONE);
                pay_lay.setVisibility(View.GONE);
                ssl_lay.setVisibility(View.GONE);
                messLay.setVisibility(View.GONE);
                ws_payload_lay.setVisibility(View.GONE);
                ws_sni_lay.setVisibility(View.GONE);
                user_lay.setVisibility(View.GONE);
                pass_lay.setVisibility(View.GONE);
                bug_lay.setVisibility(View.GONE);
                udpSerLay.setVisibility(View.GONE);
                udpObfsLay.setVisibility(View.GONE);
                udpAuthLay.setVisibility(View.GONE);
                v2link.setVisibility(View.VISIBLE);
                v2lay.setVisibility(View.VISIBLE);

            } else if(p3 == 6) {
                chave_key_lay.setVisibility(View.GONE);
                serv_key_lay.setVisibility(View.GONE);
                dns_key_lay.setVisibility(View.GONE);
                dns_user_lay.setVisibility(View.GONE);
                dns_pass_lay.setVisibility(View.GONE);
                pay_lay.setVisibility(View.GONE);
                ssl_lay.setVisibility(View.GONE);
                messLay.setVisibility(View.GONE);
                ws_payload_lay.setVisibility(View.GONE);
                ws_sni_lay.setVisibility(View.GONE);
                user_lay.setVisibility(View.GONE);
                pass_lay.setVisibility(View.GONE);
                bug_lay.setVisibility(View.GONE);
                udpSerLay.setVisibility(View.VISIBLE);
                udpObfsLay.setVisibility(View.VISIBLE);
                udpAuthLay.setVisibility(View.VISIBLE);
                v2link.setVisibility(View.GONE);
                v2lay.setVisibility(View.GONE);

            } else if (p3 == 7) {
                chave_key_lay.setVisibility(View.GONE);
                serv_key_lay.setVisibility(View.GONE);
                dns_key_lay.setVisibility(View.GONE);
                dns_user_lay.setVisibility(View.GONE);
                dns_pass_lay.setVisibility(View.GONE);
                pay_lay.setVisibility(View.GONE);
                ssl_lay.setVisibility(View.GONE);

                ws_payload_lay.setVisibility(View.GONE);
                ws_sni_lay.setVisibility(View.GONE);
                user_lay.setVisibility(View.GONE);
                pass_lay.setVisibility(View.GONE);
                bug_lay.setVisibility(View.GONE);
                udpSerLay.setVisibility(View.GONE);
                udpObfsLay.setVisibility(View.GONE);
                udpAuthLay.setVisibility(View.GONE);
                v2link.setVisibility(View.GONE);
                v2lay.setVisibility(View.GONE);
                if (prefs.getBoolean("isMsg", false)) {
                    messLay.setVisibility(View.VISIBLE);
                    tvMess.setText(Html.fromHtml(prefs.getString("Mess", "").replace("\n", "<br/>")));
                }
            }
        }
        doTabs();
        try {
            if (getIntent().getScheme() == null) {
                return;
            }
            if (getIntent().getScheme().equals("file") || getIntent().getScheme().equals("content")) {
                try {
                    importConfig(read(getIntent().getData()));
                    imgFavorite.setChecked(true);
                    setupSpinner.setSelection(6);
                } catch (Exception e) {
                    Toast.makeText(this, e.toString(), 0).show();
                }
            }
        } catch (Exception unused2) {
            Toast.makeText(MainActivity.this, "Invalid Config", Toast.LENGTH_SHORT).show();
        }
    }

    private void liveData(){
        new Timer().schedule(new TimerTask(){
                @Override
                public void run(){
                    mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                getData();
                            }
                        });
                }
            }, 0,1000);
    }


    @Override
    protected void onStop() {
        if(mAppUpdateManager != null) mAppUpdateManager.unregisterListener(installUpdatelistener);
        super.onStop();
    }

    private InstallStateUpdatedListener installUpdatelistener = new InstallStateUpdatedListener() {
        @Override
        public void onStateUpdate(InstallState state) {
            if(state.installStatus() == InstallStatus.DOWNLOADED){
                showCompleterUpdate();
            }
        }
    };

    private void showCompleterUpdate(){
        Snackbar snacks = Snackbar.make(findViewById(android.R.id.content), "New app is ready!", Snackbar.LENGTH_INDEFINITE);
        snacks.setAction("Install", new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    mAppUpdateManager.completeUpdate();
                }
            });
        snacks.setActionTextColor(Color.parseColor("#ffffff"));
        snacks.show();
    }

    
   void setCloudid() {
	LayoutInflater inflater = getLayoutInflater();
View dialogView = inflater.inflate(R.layout.cloud_dialog, null);
final EditText editText = dialogView.findViewById(R.id.edit_text);
AlertDialog.Builder builder = new AlertDialog.Builder(this);
builder.setView(dialogView);
builder.setPositiveButton("Import", new DialogInterface.OnClickListener() {
    public void onClick(DialogInterface dialogInterface, int i) {
        if (editText.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "Cannot be Empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        getResults(editText.getText().toString());
    }
});

builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialog, int which) {
        
    }
});

AlertDialog dialog = builder.create();
dialog.show();
		
		
		}
	
	
	private void getResults(String ee) {
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReference();
    StorageReference imageRef = storageRef.child( ee + ".sut");
    imageRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
        @Override
        public void onComplete(Task<Uri> task) {
            if (task.isSuccessful()) {
                String nn = task.getResult().toString();
                new ResultGetter(MainActivity.this, (result) -> {
                    try {
                        if (!result.contains("Error on getting data")) {
                            importConfig(result);
                           doLayout();
							doUpdateLayout();
                        } else {
                            // Show toast for invalid key
                            runOnUiThread(() -> Toast.makeText(MainActivity.this, "Invalid key", Toast.LENGTH_SHORT).show());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start(false, true, nn);
            } else {
                // Handle error getting download URL
                // For example, if file is not found, show a toast
                runOnUiThread(() -> Toast.makeText(MainActivity.this, "Config not found", Toast.LENGTH_SHORT).show());
            }
        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(Exception e) {
            // Handle other failures
        }
    });
}

    public void getData2() {
        String jSONArray = config.getServersArray().toString();
        String jSONArray2 = config.getNetworksArray().toString();
        String version = config.getVersion();
        String notes = config.getNote();
        
        
        sp.edit().putString("Version", version).apply();
        sp.edit().putString("ReleaseNotes", notes).apply();
        sp.edit().putString("Servers", jSONArray).apply();
        sp.edit().putString("Networks", jSONArray2).apply();
    }

    public void getData() {
        boolean isRunning = SkStatus.isTunnelActive();
        long mUpload, mDownload, saved_Send ,saved_Down/*,up, down*/;
        String saved_date, tDate;
        List<Long> allData;
        allData = RetrieveData.findData();
        mDownload = allData.get(0);
        mUpload = allData.get(1);
        StoredData.storedData(mDownload, mUpload);
        //down = mDownload;
        //up = mUpload;
        Calendar ca = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        tDate = sdf.format(ca.getTime());
        saved_date = myData.getString("today_date", "empty");
        SharedPreferences.Editor editor = myData.edit();
        if (saved_date.equals(tDate)) {
            saved_Send = myData.getLong("UP_DATA", 0);
            saved_Down = myData.getLong("DOWN_DATA", 0);
            editor.putLong("UP_DATA", mUpload + saved_Send);
            editor.putLong("DOWN_DATA", mDownload + saved_Down);
            editor.apply();
        } else {
            editor.clear();
            editor.putString("today_date", tDate);
            editor.apply();
        }
        if(isRunning){
            bytesOut.setText(render_bandwidth(myData.getLong("UP_DATA", 0)));
            bytesIn.setText(render_bandwidth(myData.getLong("DOWN_DATA", 0)));
        }else{
            myData.edit().putLong("UP_DATA", 0).apply();
            myData.edit().putLong("DOWN_DATA", 0).apply();
        }
    }


    public void doTabs() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mLogAdapter = new LogsAdapter(layoutManager,this);
        logList = (RecyclerView) findViewById(R.id.recyclerLog);
        logList.setAdapter(mLogAdapter);
        logList.setLayoutManager(layoutManager);
        mLogAdapter.scrollToLastPosition();
        vp = (ViewPager)findViewById(R.id.viewpager);
        tabs = (TabLayout)findViewById(R.id.tablayout);
        vp.setAdapter(new MyzAdapter(Arrays.asList(tabTitle)));
        vp.setOffscreenPageLimit(3);
        tabs.setTabMode(TabLayout.MODE_FIXED);
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);
        tabs.setupWithViewPager(vp);
        vp.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                @Override
                public void onPageSelected(int position) {
                    if (position == 0) {
                        toolbar_main.getMenu().clear();
                        getMenuInflater().inflate(R.menu.main_menu, toolbar_main.getMenu());
                    } else if (position == 1) {
                        toolbar_main.getMenu().clear();
                        getMenuInflater().inflate(R.menu.logs_menu, toolbar_main.getMenu());
                    } else if (position == 2) {
                        toolbar_main.getMenu().clear();
                        getMenuInflater().inflate(R.menu.main_menu, toolbar_main.getMenu());
                    }
                }
            });
    }

    public class DrawerPanelMain implements NavigationView.OnNavigationItemSelectedListener {

        private AppCompatActivity mActivity;
        public DrawerPanelMain(AppCompatActivity activity) {
            mActivity = activity;
        }
        private DrawerLayout drawerLayout;
        private ActionBarDrawerToggle toggle;

        public void setDrawer(Toolbar toolbar) {
            NavigationView drawerNavigationView = (NavigationView) mActivity.findViewById(R.id.drawerNavigationView);
            drawerLayout = (DrawerLayout) mActivity.findViewById(R.id.drawerLayoutMain);

            // set drawer
            toggle = new ActionBarDrawerToggle(mActivity, drawerLayout, toolbar, R.string.open, R.string.cancel);
            drawerLayout.setDrawerListener(toggle);
            toggle.syncState();

            // set navigation view
            drawerNavigationView.setNavigationItemSelectedListener(this);
        }

        public ActionBarDrawerToggle getToogle() {
            return toggle;
        }

        public DrawerLayout getDrawerLayout() {
            return drawerLayout;
        }

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            showInterstitial();
            int id = item.getItemId();
            switch(id) {
                case R.id.mPayloadGen:
                    if (!imgFavorite.isChecked()) {
                        Toast.makeText(MainActivity.this, "Please Enable Custom Setup", 0).show();
                    } else {
                        int pos = setupSpinner.getSelectedItemPosition();
                        if (pos == 1 || pos == 3) {
                            showPayloadGenerator();
                        } else {
                            Toast.makeText(MainActivity.this, "Please Select Custom Payload", 0).show();
                        }
                    }
                    break;

                case R.id.mImport:
                    if (!TunnelUtils.isActiveVpn(MainActivity.this)) {
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType("*/*");
                        startActivityForResult(intent, PICK_FILE);
                    } else {
                        toast(getApplicationContext(), R.color.colorPrimary, "stop tunnel first");         }
                    break;

                case R.id.mExport:
                    if (!TunnelUtils.isActiveVpn(MainActivity.this)) {
                        if (imgFavorite.isChecked()) {
                            int i = setupSpinner.getSelectedItemPosition();
                            if (i == 0) {
                                toast(getApplicationContext(), R.color.red, "Failed export profile");
                            } else if (i == 7) {
                                toast(getApplicationContext(), R.color.red, "Failed export profile");
                            } else {
                                doSaveData();
                                startActivity(new Intent(MainActivity.this, ExportActivity.class));
                            }
                        } else {
                            toast(getApplicationContext(), R.color.green, "switch custom first");
                        }
                    } else {
                        toast(getApplicationContext(), R.color.red, "stop the tunnel first");
                    }
                    break;

                case R.id.wifitether:
                    startActivity(new Intent(MainActivity.this, ProxySettings.class));
                    break;

                case R.id.configUpdate:
                    updateConfig(false);
                Toast.makeText(MainActivity.this, "Checking Config Updates please wait.....", Toast.LENGTH_LONG).show();
                    break;
                
               case R.id.miCloud:
                setCloudid();
                    break;

                case R.id.pogs:
                    Changelogs();
                    break;

                case R.id.pogs1:
                    Changelogs1();
                    break;

                case R.id.pogse:
                    iphunt();
                    break;

                case R.id.xchan:
                    startActivity(new Intent("android.intent.action.VIEW",
                                             Uri.parse("https://t.me/smk_ultra_tun")));
                    break;
                case R.id.xchat:
                    startActivity(new Intent("android.intent.action.VIEW",
                                             Uri.parse("https://t.me/smk_ultra_tun")));
                    break;

                case R.id.customserver:
                    addserver();
                    break;

                case R.id.hardware:
                    MaterialAlertDialogBuilder mBuilder = new MaterialAlertDialogBuilder(MainActivity.this);
                    mBuilder.setTitle("Hardware ID");
                    mBuilder.setMessage(Utils.getHWID());
                    mBuilder.setCancelable(false);
                    mBuilder.setPositiveButton("COPY", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface mDialogInterface, int mInt) {
                                ((ClipboardManager)getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("HWID", Utils.getHWID()));
                            }
                        });
                    mBuilder.setNeutralButton("CANCEL", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface mDialog, int mInt) {
                                mDialog.cancel();
                            }
                        });
                    mBuilder.show();
                    break;

                case R.id.miSettings:
                    Intent intent = new Intent(mActivity, ConfigGeralActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mActivity.startActivity(intent);
                    break;

            }
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawers();
            }
            return true;
        }
    }

	
    public String getJson() {
        String str = "";
        try {
            str = AESCrypt.encrypt(config.PASSWORD, custom().toString());
        } catch (GeneralSecurityException e) {
            //  GeneralSecurityException generalSecurityException = e;
        }
        return str;
    }

    private JSONObject custom() {
        String ja=sp.getString("Config", "{}");
        try {
            JSONArray a=new JSONArray(sp.getString("Servers", "[]"));
            JSONArray b=new JSONArray(sp.getString("Networks", "[]"));
            return new JSONObject(ja).put("Version",sp.getString("Version", "")).put("AppVersion",sp.getString("AppVersion", "")).put("fUpdate",sp.getString("fUpdate", "")).put("ReleaseNotes",sp.getString("ReleaseNotes", "")).put("Servers", a).put("Networks", b);
        } catch (JSONException e) {
            return null;
        }
    }

    public void saveData() {
        try {
            //   uri = getData();
            //  String intentData = importer(uri);
            //String cipter = AESCrypt.decrypt(c.PASSWORD, intentData);
            File file = new File(getFilesDir(), "Config.json");
            OutputStream out = new FileOutputStream(file);
            out.write(getJson().getBytes());
            out.flush();
            out.close();
            restart_app();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	@Override
    public boolean onLongClick(View p1) {
        int id = p1.getId();

        if (id == R.id.serverSpinner) {
            editServer(p1, serverSpinner.getSelectedItemPosition());
            return true;
        }

        return false;
    }
   
    public void addserver() {
        CustomServer.Server a=new CustomServer.Server(MainActivity.this);
        try {
            final JSONArray ja=new JSONArray(sp.getString("Servers", "[]"));
            a.add();
            a.onServerAdd(new CustomServer.SpinnerListener(){
                    @Override
                    public void onAdd(JSONObject json) {
                        try {
                            getData2();
                            JSONArray a=new JSONArray(sp.getString("Servers", "[]"));
                            a.put(json);
                            sp.edit().putString("Servers", a.toString()).apply();
                            saveData();
                        } catch (JSONException e) {
                            Toast.makeText(getBaseContext(), e.getMessage(), 1).show();
                        }
                    }
                });
            a.init();
        } catch (JSONException e) {
            Toast.makeText(getBaseContext(), e.getMessage(), 1).show();
        }}

		
    private void editServer(View p1,final int selectedItemPosition) {
        PopupMenu pupup =new PopupMenu(this, p1);
        pupup.getMenu().add(0, 0, 0, "Edit");
        pupup.getMenu().add(1, 1, 1, "Delete");
        pupup.getMenu().add(2, 2, 2, "Add Server");
        pupup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem p1) {
                    switch (p1.getItemId()) {
                        case 0:
                            CustomServer.Server a=new CustomServer.Server(MainActivity.this);
                            try {
                                final JSONArray ja=new JSONArray(sp.getString("Servers", "[]"));
                                a.edit(ja.getJSONObject(selectedItemPosition));
                                a.onServerAdd(new CustomServer.SpinnerListener()
                                    {
                                        @Override
                                        public void onAdd(JSONObject json) {
                                            try {
                                                String[] ob={"Name","FLAG", "ServerIP", "ServerPort", "UdpIP", "Obfs_Str", "Auth_Str", "ServerUser", "ServerPass", "sInfo"};
                                                for (int i=0;i < ob.length;i++) {
                                                    ja.getJSONObject(selectedItemPosition).remove(ob[i]);
                                                }
                                                for (int i=0;i < json.length();i++) {
                                                    ja.getJSONObject(selectedItemPosition).put(ob[i], json.getString(ob[i]));
                                                }
                                                sp.edit().putString("Servers", ja.toString()).apply();           
                                                saveData();
                                            } catch (JSONException e) {
                                                Toast.makeText(getBaseContext(), e.getMessage(), 1).show();
                                            }
                                        }
                                    });
                                a.init();
                            } catch (JSONException e) {
                                Toast.makeText(getBaseContext(), e.getMessage(), 1).show();
                            }
                            return true;
                        case 1:  
                            deleteServer(serverSpinner.getSelectedItemPosition());   
                            return true;
                        case 2:
                            addserver();
							return true;
                            
                        default:
							return false;
                            
                            }
                }});
                
        String str = "";
        try {
            str = config.getServersArray().getJSONObject(serverSpinner.getSelectedItemPosition()).getString("sInfo");
        } catch (JSONException e) {
           
        }

        if (str.contains("Custom Server")) {
            pupup.show();
        }
              
      
    }  

    public void deleteServer(final int position) {

        try {
            JSONArray ja=new JSONArray(sp.getString("Servers", "[]"));
            ja.remove(position);
            sp.edit().putString("Servers", ja.toString()).apply();
            saveData();
        } catch (JSONException e) {
            Toast.makeText(getBaseContext(), e.getMessage(), 1).show();
        }

    }
        

    public class MyzAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            // TODO: Implement this method
            return 3;
        }

        @Override
        public boolean isViewFromObject(View p1, Object p2) {
            // TODO: Implement this method
            return p1 == p2;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int[] ids = new int[]{R.id.tab1, R.id.tab2, R.id.tab3};
            int id = 0;
            id = ids[position];
            // TODO: Implement this method
            return findViewById(id);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // TODO: Implement this method
            return titles.get(position);
        }

        private List<String> titles;
        public MyzAdapter(List<String> str) {
            titles = str;
        }
    }

    private void doUpdateLayout() {
        boolean isRunning = SkStatus.isTunnelActive();
        if (config.time()){
           // toast(getApplicationContext(), R.color.red, "Unlimited Time Active ⏳");
        } else {
            //toast(getApplicationContext(), R.color.green, "Unlimited Time Active ⏳");
            retryButton.setText("Disabled");
            retryButton.setEnabled(false);
        }
        setStarterButton(starterButton, this);
        isRunning(isRunning);
    }

    private synchronized void doSaveData() {
        try {
            SharedPreferences prefs = mConfig.getPrefsPrivate();
            SharedPreferences.Editor edit = prefs.edit();

            if (imgFavorite.isChecked()) {
                int pos = setupSpinner.getSelectedItemPosition();
                int pos1 = serverSpinner.getSelectedItemPosition();
                switch (pos) {
                    case 0:
                        prefs.edit().putBoolean(Settings.PROXY_USAR_DEFAULT_PAYLOAD, false).apply();
                        prefs.edit().putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_DIRECT).apply();
                        break;

                    case 1:
                        prefs.edit().putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_PROXY).apply();
                        String payload = edPayload.getText().toString();
                        edit.putString(Settings.CUSTOM_PAYLOAD_KEY, payload);
                        String ssh_port = config.getServersArray().getJSONObject(pos1).getString("ServerPort");
                        edit.putString(Settings.SERVIDOR_PORTA_KEY, ssh_port); 
                        edit.putString(Settings.PROXY_IP_KEY, VPNUtils.IsangTangangNagDecrypt(config.getServersArray().getJSONObject(pos1).getString("ServerIP"))).apply();
                        edit.putString(Settings.PROXY_PORTA_KEY, ssh_port).apply();            
                        prefs.edit().putString("SavedHTTP", payload).apply();
                        edit.apply();
                        break;

                    case 2:
                        prefs.edit().putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_SSLTUNNEL).apply();
                        edit.putString(Settings.CUSTOM_SNI, VPNUtils.IsangTangangNagDecrypt(config.getServersArray().getJSONObject(pos1).getString("ServerIP")));
                        edit.putString(Settings.CUSTOM_PAYLOAD_KEY, "GET / HTTP/1.1[crlf]Host: www.bug.com[crlf]Connection: Upgrade[crlf]User-Agent: [ua][crlf]Upgrade: websocket[crlf][crlf]".replace("www.bug.com", VPNUtils.IsangTangangNagDecrypt(config.getServersArray().getJSONObject(pos1).getString("ServerIP"))));
                        edit.putString(Settings.USUARIO_KEY, VPNUtils.IsangTangangNagDecrypt(config.getServersArray().getJSONObject(pos1).getString("ServerUser")));
                        edit.putString(Settings.SENHA_KEY, VPNUtils.IsangTangangNagDecrypt(config.getServersArray().getJSONObject(pos1).getString("ServerPass")));
                        edit.putString(Settings.SERVIDOR_KEY, this.edSsl.getText().toString());
                        edit.putString(Settings.SERVIDOR_PORTA_KEY, "443");
                        edit.apply();

                        prefs.edit().putString("SavedSSL", this.edSsl.getText().toString()).apply();
                        prefs.edit().putString("SavedHTTP + SSL", "GET / HTTP/1.1[crlf]Host: www.bug.com[crlf]Connection: Upgrade[crlf]User-Agent: [ua][crlf]Upgrade: websocket[crlf][crlf]".replace("www.bug.com", VPNUtils.IsangTangangNagDecrypt(config.getServersArray().getJSONObject(pos1).getString("ServerIP")))).apply();
                        prefs.edit().putString("SavedSSL + HTTP", VPNUtils.IsangTangangNagDecrypt(config.getServersArray().getJSONObject(pos1).getString("ServerIP"))).apply();
                        prefs.edit().putString("SavedUSER", VPNUtils.IsangTangangNagDecrypt(config.getServersArray().getJSONObject(pos1).getString("ServerUser"))).apply();
                        prefs.edit().putString("SavedPASS", VPNUtils.IsangTangangNagDecrypt(config.getServersArray().getJSONObject(pos1).getString("ServerPass"))).apply();
                        break;

                    case 3:
                        prefs.edit().putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_PAY_SSL).apply();
                        String sslpayload = sslPayload.getText().toString();
                        String snipayload = edSslpayload.getText().toString();
                        String user = webuser.getText().toString();
                        String pass = webpass.getText().toString();
                        String bughost1 = bug_host.getText().toString();

                        edit.putString(Settings.CUSTOM_SNI, snipayload);
                        edit.putString(Settings.CUSTOM_PAYLOAD_KEY, sslpayload);
                        edit.putString(Settings.USUARIO_KEY, user);
                        edit.putString(Settings.SENHA_KEY, pass);
                        edit.putString(Settings.SERVIDOR_KEY, bughost1);                    
                        edit.putString(Settings.SERVIDOR_PORTA_KEY, "443");

                        prefs.edit().putString("SavedHTTP + SSL", sslpayload).apply();
                        prefs.edit().putString("SavedSSL + HTTP", snipayload).apply();
                        prefs.edit().putString("SavedUSER", user).apply();
                        prefs.edit().putString("SavedPASS", pass).apply();
                        prefs.edit().putString("SavedBUG", bughost1).apply();
                        break;

                    case 4:
                        prefs.edit().putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SLOWDNS).apply();
                        String chaveKey = chaKey.getText().toString();
                        String serverNameKey = sersKey.getText().toString();
                        String dnsKey = dnsKeys.getText().toString();
                        String userDns = dnssuser.getText().toString();
                        String passDns = dnsspass.getText().toString();

                        edit.putString(Settings.CHAVE_KEY, chaveKey);
                        edit.putString(Settings.NAMESERVER_KEY, serverNameKey);
                        edit.putString(Settings.DNS_KEY, dnsKey);
                        edit.putString(Settings.USUARIO_KEY, userDns);
                        edit.putString(Settings.SENHA_KEY, passDns);
                        edit.putString(Settings.SERVIDOR_KEY, "127.0.0.1");
                        edit.putString(Settings.SERVIDOR_PORTA_KEY, "2222");
                        prefs.edit().putString("SavedCHAVE", chaveKey).apply();
                        prefs.edit().putString("SavedSERKEY", serverNameKey).apply();
                        prefs.edit().putString("SavedDNS", dnsKey).apply();
                        prefs.edit().putString("SavedUSER", userDns).apply();
                        prefs.edit().putString("SavedPASS", passDns).apply();
                        break;
                        
                    case 5:
                        prefs.edit().putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_V2RAY).apply();
                        String v2 = v2link.getText().toString();  
                           
                        edit.putString(Settings.V2RAY_JSON, v2);
                        edit.putString(Settings.SERVIDOR_KEY, "nazzy_ng");
                        edit.putString(Settings.SERVIDOR_PORTA_KEY, "80");
                        edit.putString(Settings.USUARIO_KEY, "nazzy_ng");
                        edit.putString(Settings.SENHA_KEY, "nazzy_ng");
                        edit.putString(Settings.CUSTOM_SNI, "nazzy_ng");
                        edit.putString(Settings.CUSTOM_PAYLOAD_KEY, "nazzy_ng");
                        edit.apply();
                        prefs.edit().putString("SavedV2Ray", v2).apply();                   
                        break;

                    case 6:
                        prefs.edit().putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_UDP).apply();
                        String udpS = udpServ.getText().toString();
                        String obfs = udpObfs.getText().toString();
                        String auth = udpAuth.getText().toString();

                        edit.putString(Settings.SERVIDOR_KEY, udpS);
                        edit.putString(Settings.SERVIDOR_PORTA_KEY, "20000-50000");
                        edit.putString(Settings.USUARIO_KEY, obfs);
                        edit.putString(Settings.SENHA_KEY, auth);
                        edit.putString(Settings.CUSTOM_SNI, "tekidoer.devz");
                        edit.putString(Settings.CUSTOM_PAYLOAD_KEY, "tekidoer.devz");
                        edit.apply();
                        prefs.edit().putString("SavedUDP", udpS).apply();
                        prefs.edit().putString("SavedOBFS", obfs).apply();
                        prefs.edit().putString("SavedAUTH", auth).apply();
                        break;

                    case 7:
                        DataBaseHelper db = new DataBaseHelper(this, "ImportedConfig");
                        JSONObject obj = new JSONObject(db.getData());
                        String sP = obj.getString("CPayload");
                        String sS = obj.getString("Bug");
                        String pls = obj.getString("SNI");
                        String slp = obj.getString("Payload");
                        String us = obj.getString("ServerUser");
                        String ps = obj.getString("ServerPass");
                        String bh = obj.getString("ServerIP");
                        String udpSer = obj.getString("UdpServer");
                        String Obfs = obj.getString("Obfs");
                        String Auth = obj.getString("Auth");
                        String v2l = obj.getString("V2Ray");

                        String chavezKey = obj.getString("chaveKey");
                        String serverzNameKey = obj.getString("serverNameKey");
                        String dnszKey = obj.getString("dnsKey");
                        String userzDns = obj.getString("ServerUser");
                        String passzDns = obj.getString("ServerPass");
                        int tun = obj.getInt("TunnelType");

                        switch (tun) {

                            case Settings.bTUNNEL_TYPE_SSH_PROXY:
                                prefs.edit().putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_PROXY).apply();
                                edit.putString(Settings.CUSTOM_PAYLOAD_KEY, sP);
                                String ssh_ports = config.getServersArray().getJSONObject(pos1).getString("ServerPort");
                                edit.putString(Settings.PROXY_IP_KEY, VPNUtils.IsangTangangNagDecrypt(config.getServersArray().getJSONObject(pos1).getString("ServerIP"))).apply();
                                edit.putString(Settings.PROXY_PORTA_KEY, ssh_ports).apply();
                                edit.putString(Settings.SERVIDOR_PORTA_KEY, ssh_ports);
                                edit.apply();
                                break;

                            case Settings.bTUNNEL_TYPE_SSH_SSLTUNNEL:
                                prefs.edit().putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_SSLTUNNEL).apply();
                                edit.putString(Settings.CUSTOM_SNI, VPNUtils.IsangTangangNagDecrypt(config.getServersArray().getJSONObject(pos1).getString("ServerIP")));
                                edit.putString(Settings.CUSTOM_PAYLOAD_KEY, new StringBuffer().append(new StringBuffer().append("GET / HTTP/1.1[crlf]Host: ").append(VPNUtils.IsangTangangNagDecrypt(config.getServersArray().getJSONObject(pos1).getString("ServerIP"))).toString()).append("[crlf]Connection: Upgrade[crlf]User-Agent: [ua][crlf]Upgrade: websocket[crlf][crlf]").toString());
                                edit.putString(Settings.USUARIO_KEY, VPNUtils.IsangTangangNagDecrypt(config.getServersArray().getJSONObject(pos1).getString("ServerUser")));
                                edit.putString(Settings.SENHA_KEY, VPNUtils.IsangTangangNagDecrypt(config.getServersArray().getJSONObject(pos1).getString("ServerPass")));
                                edit.putString(Settings.SERVIDOR_KEY, sS);
                                edit.putString(Settings.SERVIDOR_PORTA_KEY, "443");
                                edit.apply();
                                break;

                            case Settings.bTUNNEL_TYPE_PAY_SSL:
                                prefs.edit().putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_PAY_SSL).apply();
                                edit.putString(Settings.CUSTOM_SNI, pls);
                                edit.putString(Settings.CUSTOM_PAYLOAD_KEY, slp);
                                edit.putString(Settings.USUARIO_KEY, us);
                                edit.putString(Settings.SENHA_KEY, ps);
                                edit.putString(Settings.SERVIDOR_KEY, bh);
                                edit.putString(Settings.SERVIDOR_PORTA_KEY, "443");
                                edit.apply();
                                break;

                            case Settings.bTUNNEL_TYPE_SLOWDNS:
                                prefs.edit().putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SLOWDNS).apply();
                                edit.putString(Settings.CHAVE_KEY, chavezKey);
                                edit.putString(Settings.NAMESERVER_KEY, serverzNameKey);
                                edit.putString(Settings.DNS_KEY, dnszKey);
                                edit.putString(Settings.USUARIO_KEY, userzDns);
                                edit.putString(Settings.SENHA_KEY, passzDns);
                                edit.putString(Settings.SERVIDOR_KEY, "127.0.0.1");
                                edit.putString(Settings.SERVIDOR_PORTA_KEY, "2222");
                                edit.apply();
                                break;
                                
                            case Settings.bTUNNEL_TYPE_V2RAY:
                                prefs.edit().putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_V2RAY).apply();
                                            
                                edit.putString(Settings.V2RAY_JSON, v2l);
                                edit.putString(Settings.SERVIDOR_KEY, "nazzy_ng");
                                edit.putString(Settings.SERVIDOR_PORTA_KEY, "80");
                                edit.putString(Settings.USUARIO_KEY, "nazzy_ng");
                                edit.putString(Settings.SENHA_KEY, "nazzy_ng");
                                edit.putString(Settings.CUSTOM_SNI, "nazzy_ng");
                                edit.putString(Settings.CUSTOM_PAYLOAD_KEY, "nazzy_ng");
                                edit.apply(); 
                                
                                break;
                                       

                            case Settings.bTUNNEL_TYPE_UDP:
                                prefs.edit().putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_UDP).apply();
                                edit.putString(Settings.USUARIO_KEY, Obfs);
                                edit.putString(Settings.SENHA_KEY, Auth);
                                edit.putString(Settings.SERVIDOR_KEY, udpSer);
                                edit.putString(Settings.SERVIDOR_PORTA_KEY, "20000-50000");
                                edit.putString(Settings.CUSTOM_SNI, "tekidoer.devz");
                                edit.putString(Settings.CUSTOM_PAYLOAD_KEY, "tekidoer.devz");
                                edit.apply();
                                
                                break;
                        }
                }
            } else {
                int pos = payloadSpinner.getSelectedItemPosition();
                int pos1 = serverSpinner.getSelectedItemPosition();

                boolean inject = this.config.getNetworksArray().getJSONObject(pos).getBoolean("isInject");
                boolean direct = this.config.getNetworksArray().getJSONObject(pos).getBoolean("isDirect");
                boolean sslType = this.config.getNetworksArray().getJSONObject(pos).getBoolean("isSSL");
                boolean sslpayload = this.config.getNetworksArray().getJSONObject(pos).getBoolean("wsPayload");
                boolean slow = this.config.getNetworksArray().getJSONObject(pos).getBoolean("SlowDns");
                boolean udp = this.config.getNetworksArray().getJSONObject(pos).getBoolean("isUdp");
                boolean v2ray = this.config.getNetworksArray().getJSONObject(pos).getBoolean("isv2ray");

                if (direct) {
                    prefs.edit().putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_PROXY).apply();
                    String payload = config.getNetworksArray().getJSONObject(pos).getString("Payload");
                    edit.putString(Settings.CUSTOM_PAYLOAD_KEY, VPNUtils.IsangTangangNagDecrypt(payload));
                    edit.apply();

                } //INJECT
                else if (inject) {
                    prefs.edit().putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_PROXY).apply();
                    String payload = config.getNetworksArray().getJSONObject(pos).getString("Payload");
                    String ssh_server = config.getServersArray().getJSONObject(pos1).getString("ServerIP");
                    edit.putString(Settings.CUSTOM_PAYLOAD_KEY, VPNUtils.IsangTangangNagDecrypt(payload).replace("www.bug.com", VPNUtils.IsangTangangNagDecrypt(ssh_server)));
                    edit.apply();

                } //SSH SSL
                else if (sslType)  {
                    prefs.edit().putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_SSLTUNNEL).apply();
                    edit.putString(Settings.CUSTOM_PAYLOAD_KEY, "GET / HTTP/1.1[crlf]Host: www.bug.com[crlf]Connection: Upgrade[crlf]User-Agent: [ua][crlf]Upgrade: websocket[crlf][crlf]".replace("www.bug.com", VPNUtils.IsangTangangNagDecrypt(config.getServersArray().getJSONObject(serverSpinner.getSelectedItemPosition()).getString("ServerIP"))));
                    edit.putString(Settings.SERVIDOR_KEY, VPNUtils.IsangTangangNagDecrypt(config.getNetworksArray().getJSONObject(payloadSpinner.getSelectedItemPosition()).getString("SNI")));
                    edit.apply();

                } //SSL PAYLOAD
                else if (sslpayload) {
                    prefs.edit().putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_PAY_SSL).apply();
                    String payload = config.getNetworksArray().getJSONObject(pos).getString("Payload");
                    String ssh_server = config.getServersArray().getJSONObject(pos1).getString("ServerIP");
                    String snissl = config.getNetworksArray().getJSONObject(pos).getString("SNI");
                    edit.putString(Settings.CUSTOM_PAYLOAD_KEY, VPNUtils.IsangTangangNagDecrypt(payload).replace("www.bug.com", VPNUtils.IsangTangangNagDecrypt(ssh_server)));
                    edit.putString(Settings.SERVIDOR_KEY, VPNUtils.IsangTangangNagDecrypt(snissl));
                    edit.apply();
                    
                    
                } //SLOWDNS
                else if (slow) {
                    prefs.edit().putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SLOWDNS).apply();
                    edit.putString(Settings.DNS_KEY,  VPNUtils.IsangTangangNagDecrypt(config.getNetworksArray().getJSONObject(pos).getString(Settings.DNS_KEY)));
                    edit.apply();

                } //UDP
                else if (udp) {
                    edit.putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_UDP).apply();
                    String udpS = config.getServersArray().getJSONObject(serverSpinner.getSelectedItemPosition()).getString("UdpIP");
                    String obfs = config.getServersArray().getJSONObject(serverSpinner.getSelectedItemPosition()).getString("Obfs_Str");
                    String auth = config.getServersArray().getJSONObject(serverSpinner.getSelectedItemPosition()).getString("Auth_Str");

                    edit.putString(Settings.SERVIDOR_KEY, VPNUtils.IsangTangangNagDecrypt(udpS));
                    edit.putString(Settings.USUARIO_KEY, VPNUtils.IsangTangangNagDecrypt(obfs));
                    edit.putString(Settings.SENHA_KEY, VPNUtils.IsangTangangNagDecrypt(auth));
                    edit.putString(Settings.CUSTOM_SNI, "tekidoer.devz");
                    edit.putString(Settings.CUSTOM_PAYLOAD_KEY, "tekidoer.devz");
                    edit.apply();

                }
                   //v2ray
                else if (v2ray) {
                    edit.putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_V2RAY).apply();
                    String payload = config.getNetworksArray().getJSONObject(pos).getString("Payload");
                    String deff = VPNUtils.IsangTangangNagDecrypt(payload);          
                    edit.putString(Settings.V2RAY_JSON, deff);
                    edit.putString(Settings.CUSTOM_PAYLOAD_KEY, "skyproject");
                    edit.apply();

                }
            }
            edit.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadServerData() {
        try {
            int pos1 = serverSpinner.getSelectedItemPosition();
            int pos2 = payloadSpinner.getSelectedItemPosition();

            String ssh_server = config.getServersArray().getJSONObject(pos1).getString("ServerIP");
            String ssh_user = config.getServersArray().getJSONObject(pos1).getString("ServerUser");
            String ssh_pass = config.getServersArray().getJSONObject(pos1).getString("ServerPass");

            SharedPreferences prefs = mConfig.getPrefsPrivate();
            SharedPreferences.Editor edit = prefs.edit();

            boolean inject = this.config.getNetworksArray().getJSONObject(pos2).getBoolean("isInject");
            boolean direct = this.config.getNetworksArray().getJSONObject(pos2).getBoolean("isDirect");
            boolean sslType = this.config.getNetworksArray().getJSONObject(pos2).getBoolean("isSSL");
            boolean sslpayload = this.config.getNetworksArray().getJSONObject(pos2).getBoolean("wsPayload");
            boolean slow = this.config.getNetworksArray().getJSONObject(pos2).getBoolean("SlowDns");
            boolean udp = this.config.getNetworksArray().getJSONObject(pos2).getBoolean("isUdp");
            boolean v2ray = this.config.getNetworksArray().getJSONObject(pos2).getBoolean("isv2ray");

            if (direct) {
                String ssh_port = config.getServersArray().getJSONObject(pos1).getString("ServerPort");
                edit.putString(Settings.SERVIDOR_PORTA_KEY, ssh_port);
                edit.putString(Settings.PROXY_IP_KEY, VPNUtils.IsangTangangNagDecrypt(ssh_server)).apply();
                edit.putString(Settings.PROXY_PORTA_KEY, ssh_port).apply();

            } //INJECT
            else if (inject) {
                String ssh_port = config.getServersArray().getJSONObject(pos1).getString("ServerPort");
                String remote_proxy = config.getNetworksArray().getJSONObject(pos2).getString("ProxyIP");
                String proxy_port = config.getNetworksArray().getJSONObject(pos2).getString("ProxyPort");
                edit.putString(Settings.SERVIDOR_PORTA_KEY, ssh_port);
                edit.putString(Settings.PROXY_IP_KEY, VPNUtils.IsangTangangNagDecrypt(remote_proxy)).apply();
                edit.putString(Settings.PROXY_PORTA_KEY, proxy_port).apply();

            } //SSH SSL
            else if (sslType)  {
                edit.putString(Settings.SERVIDOR_PORTA_KEY, "443");
                edit.putString(Settings.CUSTOM_SNI, VPNUtils.IsangTangangNagDecrypt(ssh_server));

            } //SSL PAYLOAD
            else if (sslpayload) {
                edit.putString(Settings.SERVIDOR_PORTA_KEY, "443");
                edit.putString(Settings.CUSTOM_SNI, VPNUtils.IsangTangangNagDecrypt(ssh_server));

            } //SLOWDNS
            else if (slow) {
                edit.putString(Settings.SERVIDOR_KEY, "127.0.0.1");
                edit.putString(Settings.SERVIDOR_PORTA_KEY, "2222");
                edit.putString(Settings.CHAVE_KEY, VPNUtils.IsangTangangNagDecrypt(config.getServersArray().getJSONObject(pos1).getString(Settings.CHAVE_KEY)));
                edit.putString(Settings.NAMESERVER_KEY,  VPNUtils.IsangTangangNagDecrypt(config.getServersArray().getJSONObject(pos1).getString(Settings.NAMESERVER_KEY)));

            } //UDP
            else if (udp) {
                edit.putString(Settings.SERVIDOR_PORTA_KEY, "20000-50000");
                edit.putString(Settings.CUSTOM_PAYLOAD_KEY, "tekidoer.devz");
                edit.apply();
                
                //V2ray
            }else if (v2ray) {
                edit.putString(Settings.SERVIDOR_KEY, "127.0.0.1");
                edit.putString(Settings.SERVIDOR_PORTA_KEY, "2222");
                edit.apply();
            }

            edit.putString("ServerName", config.getServersArray().getJSONObject(pos1).getString("Name"));
            edit.putString("PayloadName", config.getNetworksArray().getJSONObject(pos2).getString("Name"));
            edit.putString(Settings.USUARIO_KEY, VPNUtils.IsangTangangNagDecrypt(ssh_user));
            edit.putString(Settings.SENHA_KEY, VPNUtils.IsangTangangNagDecrypt(ssh_pass));
            edit.putString(Settings.SERVIDOR_KEY, VPNUtils.IsangTangangNagDecrypt(ssh_server));
            edit.putString("myMsg", config.getServersArray().getJSONObject(pos1).getString("servermessage"));
            edit.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void iphunt () {
        View inflate = LayoutInflater.from(this).inflate(R.layout.notif2, null);
        MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(this);
        alertDialogBuilder.setView(inflate);
        TextView title = inflate.findViewById(R.id.notiftext1);
        final TextView ms = inflate.findViewById(R.id.confimsg);
        final TextView ok = inflate.findViewById(R.id.appButton1);
        TextView cancel = inflate.findViewById(R.id.appButton2);
        title.setText("GTM IP Hunter");
        ms.setText("To connect to GTM No Load No Blocking, Make sure that you are now in the Magic IP. Click the button to check your IP!");
        ok.setText("Check Now");
        cancel.setText("Close");
        final AlertDialog alert = alertDialogBuilder.create();
        alert.setCanceledOnTouchOutside(false);
        alert.getWindow().setGravity(Gravity.CENTER);
        alert.show();
        ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View p1){
                    ms.setText("Please wait while we are checking your IP...");
                    ok.setEnabled(false);
                    ok.setText("Checking...");
                    new Handler().postDelayed(new Runnable(){
                            @Override
                            public void run() {
                                try {
                                    int l = 0;
                                    URL whatismyip = new URL(MainActivity.ll);
                                    String magic = "✅ Congrats!! You are now connected to MAGIC IP.";
                                    String fail = "🚫 Disconnected. Please Airplane Mode On/Off and Try Again.";
                                    try{
                                        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(MainActivity.ill, 80));
                                        HttpURLConnection connection = (HttpURLConnection) whatismyip.openConnection(proxy);
                                        connection.setRequestMethod("GET");
                                        connection.connect();
                                        connection.getContentLength();
                                        connection.setConnectTimeout(3000);
                                        InputStream in = connection.getInputStream();
                                        byte[] buffer = new byte[4096];
                                        int countBytesRead;
                                        while((countBytesRead = in.read(buffer)) != -1) {
                                            l += countBytesRead;
                                        }
                                        in.markSupported();
                                        if (l == 333){
                                            ms.setText(magic);
                                            ok.setText("Check Again");
                                            ok.setEnabled(true);
                                            return;
                                        }
                                        if (connection.getResponseCode() == 200){
                                            ms.setText(magic);
                                            ok.setText("Check Again");
                                            ok.setEnabled(true);
                                            return;
                                        }
                                        in.close();
                                        ms.setText(fail);;
                                        ok.setText("Check Again");
                                        ok.setEnabled(true);
                                    } catch (IOException e) {
                                        ok.setText("Check Again");
                                        ok.setEnabled(true);
                                        ms.setText(fail);
                                    }

                                }catch (MalformedURLException e) {}}
                        }, 1000);

                }

            });

        cancel.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View p1) {
                    alert.dismiss();
                }
            });
        alert.show();
    }

    private void onbanner(){
        if (config.time()){
        mAdView = findViewById(R.id.adBannerMainView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                }

                @Override
                public void onAdFailedToLoad(LoadAdError adError) {
                    // Code to be executed when an ad request fails.
                    onbanner();
                }

                @Override
                public void onAdOpened() {
                    // Code to be executed when an ad opens an overlay that
                    // covers the screen.
                }

                @Override
                public void onAdClicked() {
                    //   mAdView.setVisibility(View.GONE);
                }

                @Override
                public void onAdClosed() {
                    // Code to be executed when the user is about to return
                    // to the app after tapping on an ad.
                }
            });
}
    }

    private void loadServer() {
        try {
            if (serverList.size() > 0) {
                serverList.clear();
                serverAdapter.notifyDataSetChanged();
            }
            for (int i = 0; i < config.getServersArray().length(); i++) {
                JSONObject jsonObject = config.getServersArray().getJSONObject(i);
                SharedPreferences prefs = mConfig.getPrefsPrivate();
                SharedPreferences.Editor edit = prefs.edit();
                String flag = jsonObject.getString("FLAG");
                String sname = jsonObject.getString("Name");
                String sHost = jsonObject.getString("ServerIP");
                String sPort = jsonObject.getString("ServerPort");
                String sinfo = jsonObject.getString("sInfo");
                ServerModel model = new ServerModel();
                model.setServerName(sname);
                model.setServerInfo(sinfo);
                model.setServerHost(sHost);
                model.setServerPort(sPort);
                model.setServerflag(flag);
                serverList.add(model);
                edit.apply();
                serverAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadNetworks() {
        try {
            if (payList.size() > 0) {
                payList.clear();
                payloadAdapter.notifyDataSetChanged();
            }
            for (int i = 0; i < config.getNetworksArray().length(); i++) {
                JSONObject jsonObject = config.getNetworksArray().getJSONObject(i);
                SharedPreferences prefs = mConfig.getPrefsPrivate();
                SharedPreferences.Editor edit = prefs.edit();
                String sname = jsonObject.getString("Name");
                String flag = jsonObject.getString("FLAG");
                String sinfo = jsonObject.getString("pInfo");
                PayloadModel model = new PayloadModel();
                model.setServerName(sname);
                model.setServerflag(flag);
                model.setServerInfo(sinfo);
                payList.add(model);
                edit.apply();
                payloadAdapter.notifyDataSetChanged();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FILE) {
            if (resultCode == RESULT_OK) {
                try {
                    importConfig(read(data.getData()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String read(Uri uri) {
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(getContentResolver().openInputStream(uri)));

            String line = "";
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            reader.close();
        }
        catch (IOException e) {e.printStackTrace();}
        return builder.toString();
    }

    public void importConfig(final String str) {
        final SharedPreferences.Editor editor = new Settings(this).getPrefsPrivate().edit();
        try {
            DataBaseHelper db = new DataBaseHelper(this, "ImportedConfig");
            String data = AESCrypt.decrypt(new String(new byte[]{115,109,107,115,97,121,115,111}), str);
            final JSONObject obj = new JSONObject(data);
            if (obj.optBoolean("isPassON", false)) {
                View inflate = getLayoutInflater().inflate(R.layout.input_text, null);
                final EditText editText = (EditText) inflate.findViewById(R.id.input);
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(MainActivity.this);
                builder.setMessage("Enter Config Password");
                builder.setCancelable(false);
                builder.setView(inflate);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            try{
                                if (!editText.getText().toString().isEmpty()) {
                                    if (editText.getText().toString().equals(obj.getString("MyPass"))) {
                                        if (obj.getLong("ExpireDate") > 0) {
                                            long mValidade = obj.getLong("ExpireDate");
                                            if (mValidade > 0) {
                                                SkStatus.logWarning(R.string.log_settings_valid, DateFormat.getDateFormat(MainActivity.this).format(mValidade));
                                            }
                                            if (ConfigParser.isValidadeExpirou(obj.getLong("ExpireDate"))) {
                                                Toast.makeText(MainActivity.this, "Config Expired", Toast.LENGTH_SHORT).show();
                                                return;
                                            }
                                        }
                                        if (!obj.getString("DeviceID").isEmpty()) {
                                            if (!obj.getString("DeviceID").equals(Utils.getHWID())) {
                                                Toast.makeText(MainActivity.this, "Device ID does not match", Toast.LENGTH_SHORT).show();
                                                return;
                                            }
                                        }
                                        if (db.isExist("1")) {
                                            db.updateData(data);
                                        } else {
                                            db.insertData(data);
                                        }
                                        boolean isShowMessage = obj.getBoolean("isMsg");
                                        editor.putBoolean("isMsg", isShowMessage).apply();
                                        editor.putString("DeviceID", obj.getString("DeviceID")).apply();
                                        editor.putLong("ExpireDate", obj.getLong("ExpireDate")).apply();
                                        String cMessage = obj.getString("Message");
                                        editor.putString("Mess", cMessage).apply();                             
                                        editor.putInt("SavedPos", 7).apply();
                                        editor.putBoolean("SavedSetup", true).apply();
                                        toast(getApplicationContext(), R.color.colorPrimary, "Config imported!");
                                    } else {
                                        imgFavorite.setChecked(false);
                                        Toast.makeText(MainActivity.this, "Wrong Password Config", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    imgFavorite.setChecked(false);
                                    Toast.makeText(MainActivity.this, "Password Empty!", Toast.LENGTH_SHORT).show();
                                }
                                dialogInterface.dismiss();
                            } catch (Exception unused) {
                                Toast.makeText(MainActivity.this, "Config Error: " + unused.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            imgFavorite.setChecked(false);
                            dialogInterface.dismiss();
                        }
                    });
                builder.create().show();
                return;
            }
            if (obj.getLong("ExpireDate") > 0) {
                long mValidade = obj.getLong("ExpireDate");
                if (mValidade > 0) {
                    SkStatus.logWarning(R.string.log_settings_valid, DateFormat.getDateFormat(this).format(mValidade));
                }
                if (ConfigParser.isValidadeExpirou(obj.getLong("ExpireDate"))) {
                    Toast.makeText(this, "Config Expired", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            if (!obj.getString("DeviceID").isEmpty()) {
                if (!obj.getString("DeviceID").equals(Utils.getHWID())) {
                    Toast.makeText(this, "Device ID does not match", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            if (db.isExist("1")) {
                db.updateData(data);
            } else {
                db.insertData(data);
            }
            boolean isShowMessage = obj.getBoolean("isMsg");
            editor.putBoolean("isMsg", isShowMessage).apply();
            editor.putString("DeviceID", obj.getString("DeviceID")).apply();
            editor.putLong("ExpireDate", obj.getLong("ExpireDate")).apply();

            String cMessage = obj.getString("Message");
            editor.putString("Mess", cMessage).apply();
            Toast.makeText(this,"Import Success!",0).show();
            editor.putInt("SavedPos", 7).apply();
            editor.putBoolean("SavedSetup", true).apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Tunnel SSH
     */

    void isRunning(boolean z) {
        if (z) {
            udpAuth.setEnabled(false);
            udpServ.setEnabled(false);
            udpObfs.setEnabled(false);
            payloadSpinner.setEnabled(false);
            serverSpinner.setEnabled(false);
            setupSpinner.setEnabled(false);
            imgFavorite.setEnabled(false);
            reconnect.setEnabled(false);
            edPayload.setEnabled(false);
            edSsl.setEnabled(false);
            sslPayload.setEnabled(false);
            edSslpayload.setEnabled(false);
            webuser.setEnabled(false);
            webpass.setEnabled(false);
            chaKey.setEnabled(false);
            sersKey.setEnabled(false);
            bug_host.setEnabled(false);
            dnsKeys.setEnabled(false);
            dnssuser.setEnabled(false);
            dnsspass.setEnabled(false);
            v2link.setEnabled(false);
            v2lay.setEnabled(false);
        } else {
            prefs.edit().putBoolean("isConnected", true).apply();
            payloadSpinner.setEnabled(true);
            serverSpinner.setEnabled(true);
            setupSpinner.setEnabled(true);
            imgFavorite.setEnabled(true);
            reconnect.setEnabled(true);
            edPayload.setEnabled(true);
            edSsl.setEnabled(true);
            udpAuth.setEnabled(true);
            udpServ.setEnabled(true);
            udpObfs.setEnabled(true);
            sslPayload.setEnabled(true);
            edSslpayload.setEnabled(true);
            webuser.setEnabled(true);
            webpass.setEnabled(true);
            chaKey.setEnabled(true);
            sersKey.setEnabled(true);
            bug_host.setEnabled(true);
            dnsKeys.setEnabled(true);
            dnssuser.setEnabled(true);
            dnsspass.setEnabled(true);
            v2link.setEnabled(true);
            v2lay.setEnabled(true);
        }
    }


    public void startOrStopTunnel(Activity activity) {
        boolean isRunning = SkStatus.isTunnelActive();
        UDPThread mUdpThread = new UDPThread(this);
        if (SkStatus.isTunnelActive()) {
            if (prefs.getInt(Settings.TUNNELTYPE_KEY, 0) == Settings.bTUNNEL_TYPE_UDP){
                UDPThread tek = new UDPThread(this);
                tek.stopVudp();
            }
            if (mUdpThread != null && mUdpThread.isAlive()) {
                try {
                    mUdpThread.interrupt();
                    mUdpThread = null;
                } catch (Exception e) {
                    SkStatus.logInfo("mUdpThread: " + e);
                }
            }
            TunnelManagerHelper.stopSocksHttp(activity);
            showInterstitial();

        } else {
            //doSaveData(serverSpinner.getSelectedItemPosition(),payloadSpinner.getSelectedItemPosition());
            if (imgFavorite.isChecked()) {
                if (prefs.getInt("SavedPos", 0)== 7) {
                    if (prefs.getLong("ExpireDate", 0) > 0 && ConfigParser.isValidadeExpirou(prefs.getLong("ExpireDate", 0))) {
                        Toast.makeText(MainActivity.this, "Config file expired", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (!prefs.getString("DeviceID", "").isEmpty()) {
                        if (!prefs.getString("DeviceID", "").equals(Utils.getHWID())) {
                            Toast.makeText(MainActivity.this, "Error, Wrong Device ID", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                }
            }

            vp.setCurrentItem(1);
            if (isRunning) {
                mShown = false;
                mShown2 = false;
            }
            if (prefs.getInt(Settings.TUNNELTYPE_KEY, 0) == Settings.bTUNNEL_TYPE_UDP) {
                UDPThread uDPThread = new UDPThread(this);
                uDPThread.start();
            }
            Intent intent = new Intent(activity, LaunchVpn.class);
            //  intent.putExtra("ThroughUDP", prefs.getInt(Settings.TUNNELTYPE_KEY, 0) == Settings.bTUNNEL_TYPE_UDP ? true : false);
            intent.setAction(Intent.ACTION_MAIN);
            activity.startActivity(intent);

            isRunning(true);
            showInterstitial();
        }
    }

    public void setStopperButton(Activity activity){
            try {
                Intent stopVPN = new Intent(SocksHttpService.TUNNEL_SSH_STOP_SERVICE);
                LocalBroadcastManager.getInstance(MainActivity.this).sendBroadcast(stopVPN);
                TunnelManagerHelper.stopSocksHttp(activity);
                showInterstitial();
            } catch (Exception e){
                Toast.makeText(getApplicationContext(),"erorr: "+e,Toast.LENGTH_SHORT).show();
            }
    }

    public void setStarterButton(Button starterButton, Activity activity) {
    String state = SkStatus.getLastState();
    boolean isRunning = SkStatus.isTunnelActive();
    if (starterButton != null) {
        int resId;
        if (SkStatus.SSH_INICIANDO.equals(state)) {
            resId = R.string.stop;
            serverSpinner.setEnabled(false);
            payloadSpinner.setEnabled(false);
            starterButton.setEnabled(false);
        } else if (SkStatus.SSH_PARANDO.equals(state)) {
            serverSpinner.setEnabled(true);
            payloadSpinner.setEnabled(true);
            resId = R.string.state_stopping;
            starterButton.setEnabled(true);
        } else {
            resId = isRunning ? R.string.stop : R.string.start;
            starterButton.setEnabled(true);
        }
        starterButton.setText(resId);
    }
}

    
    private void checkUpdate() {
    PackageManager manager = getPackageManager();
    try {
        PackageInfo info = manager.getPackageInfo(getPackageName(), 0);
        String versionName = info.versionName != null ? info.versionName.trim() : ""; // Current app version
        String latestVersion = sp.getString("AppVersion", ""); // Latest version from server
        String mforce = sp.getString("fUpdate", "");

        if (latestVersion == null) latestVersion = "";
        if (mforce == null) mforce = "";

        latestVersion = latestVersion.trim().replaceAll("[^0-9.]", "");
        versionName = versionName.replaceAll("[^0-9.]", "");

      
        if (!latestVersion.isEmpty() && isVersionGreater(latestVersion, versionName)) {
            if ("true".equalsIgnoreCase(mforce)) {
                forceUpdate();
            } else {
                updateApp();
            }
        }

    } catch (PackageManager.NameNotFoundException e) {
        e.printStackTrace();
        Log.e("UpdateCheck", "Error checking version.", e);
    }
}

private boolean isVersionGreater(String latestVersion, String currentVersion) {
    String[] latestParts = latestVersion.split("\\.");
    String[] currentParts = currentVersion.split("\\.");

    int length = Math.max(latestParts.length, currentParts.length);
    
    for (int i = 0; i < length; i++) {
        int latest = (i < latestParts.length) ? Integer.parseInt(latestParts[i]) : 0;
        int current = (i < currentParts.length) ? Integer.parseInt(currentParts[i]) : 0;

        if (latest > current) return true; // Latest version is greater
        if (latest < current) return false; // Current version is already newer
    }
    
    return false; // Versions are equal
}

private void updateApp() {
    AlertDialog.Builder d = new AlertDialog.Builder(this);
    d.setTitle("New App Update Available");
    d.setMessage("A new update is available. Please update to the latest version to enjoy new features.");

    d.setPositiveButton("Website", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface _dialog, int _which) {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://smk-xi.vercel.app"));
            startActivity(i);
        }
    });

    d.setNeutralButton("Telegram", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface _dialog, int _which) {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/smk_ultra_tun"));
            startActivity(i);
        }
    });

    d.setNegativeButton("Cancel", null); // Allows users to dismiss the dialog

    // Create and show the dialog (cancelable)
    AlertDialog alertDialog = d.create();
    alertDialog.setCancelable(true);
    alertDialog.setCanceledOnTouchOutside(true);
    alertDialog.show();
}

private void forceUpdate() {
    AlertDialog.Builder d = new AlertDialog.Builder(this);
    d.setTitle("New App Update Required");
    d.setMessage("A new mandatory update is available. You must update to continue using the app.");

    d.setPositiveButton("Website", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface _dialog, int _which) {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://smk-xi.vercel.app"));
            startActivity(i);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
            if (TunnelUtils.isActiveVpn(MainActivity.this)) {
                Utils.exitAll(MainActivity.this);
            }
        }
    });

    d.setNeutralButton("Telegram", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface _dialog, int _which) {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/smk_ultra_tun"));
            startActivity(i);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
            if (TunnelUtils.isActiveVpn(MainActivity.this)) {
                Utils.exitAll(MainActivity.this);
            }
        }
    });

    // Create dialog and make it non-dismissible
    AlertDialog alertDialog = d.create();
    alertDialog.setCancelable(false);
    alertDialog.setCanceledOnTouchOutside(false); // Prevent dismissing by tapping outside
    alertDialog.show();
}

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        if (mDrawerPanel.getToogle() != null)
            mDrawerPanel.getToogle().syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mDrawerPanel.getToogle() != null)
            mDrawerPanel.getToogle().onConfigurationChanged(newConfig);
    }

    @Override
public void onClick(View p1) {
    switch (p1.getId()) {
        case R.id.activity_starterButtonMain:
            String buttonText = starterButton.getText().toString();

            if (buttonText.equalsIgnoreCase("START")) {
                starterButton.setText("STOP");
                loadServerData();
                doSaveData();
                startOrStopTunnel(this);
            } else if (buttonText.equalsIgnoreCase("STOP")) {
                starterButton.setText("START");
                setStopperButton(this);
            }
            break;

        case R.id.btnAddTime:
            loadRewardedAd();
            startGame();
            Toast.makeText(MainActivity.this, "Requesting Ad Please wait...", Toast.LENGTH_SHORT).show();
            break;
    }
}


    private String render_bandwidth(double bw) {
        String postfix;
        float div;
        Object[] objArr;
        float bwf = (float) bw;
        if (bwf >= 1.0E12f) {
            postfix = "TB";
            div = 1.0995116E12f;
        } else if (bwf >= 1.0E9f) {
            postfix = "GB";
            div = 1.0737418E9f;
        } else if (bwf >= 1000000.0f) {
            postfix = "MB";
            div = 1048576.0f;
        } else if (bwf >= 1000.0f) {
            postfix = "KB";
            div = 1024.0f;
        } else {
            objArr = new Object[S_BIND_CALLED];
            objArr[0] = Float.valueOf(bwf);
            return String.format("%.0f", objArr);
        }
        objArr = new Object[S_ONSTART_CALLED];
        objArr[0] = Float.valueOf(bwf / div);
        objArr[S_BIND_CALLED] = postfix;
        return String.format("%.2f %s", objArr);
    }

    @Override
    public void updateState(final String state, String msg, int localizedResId, final ConnectionStatus level, Intent intent) {
        mHandler.post(new Runnable() {
                @Override
                public void run() {
                    doUpdateLayout();
                    if (SkStatus.isTunnelActive()){

                        if (level.equals(ConnectionStatus.LEVEL_CONNECTED)){
                            connectionStatus.setImageResource(R.drawable.green_dot);

                            isRunning(true);

                            if (!mShown){
                                if (config.time()){
                                    start();
                                    loadAd();
                                } else {
                                    toast(getApplicationContext(), R.color.green, "Unlimited Time Active ⏳");
                                    retryButton.setText("Disabled");
                                    retryButton.setEnabled(false);
                                }
                                showInterstitial();
                                MainActivity.toast(getApplicationContext(), R.color.green, "Connected");
                                mShown = true;
                            }
                        }
                        if (level.equals(ConnectionStatus.LEVEL_NOTCONNECTED)){
                            connectionStatus.setImageResource(R.drawable.red_dot);
                            isRunning(false);
                        }
                        if (level.equals(ConnectionStatus.LEVEL_CONNECTING_SERVER_REPLIED)){
                            connectionStatus.setImageResource(R.drawable.yellow_dot);
                        }
                        if (level.equals(ConnectionStatus.LEVEL_CONNECTING_NO_SERVER_REPLY_YET)){
                            connectionStatus.setImageResource(R.drawable.yellow_dot);
                            //@interface  ImageView con =(ImageView)findViewById(R.id.ic);
                            // con.setImageResource(R.drawable.ic_connecting);
                        }
                        if (level.equals(ConnectionStatus.LEVEL_AUTH_FAILED)){
                            connectionStatus.setImageResource(R.drawable.red_dot);
                            isRunning(false);
                        }
                        if (level.equals(ConnectionStatus.UNKNOWN_LEVEL)){
                            connectionStatus.setImageResource(R.drawable.red_dot);
                            isRunning(false);
                            if (!mShown2){
                                MainActivity.toast(getApplicationContext(), R.color.red, "Disconnected");
                                mShown2 = true;
                                showInterstitial();
                            }
                            if (config.time()){
                                stop();
                                loadAd();
                            } else {
                                retryButton.setText("Disabled");
                                retryButton.setEnabled(false);
                            }

                        }
                    }
                    if (level.equals(ConnectionStatus.LEVEL_NONETWORK)){
                        connectionStatus.setImageResource(R.drawable.unknown_dot);
                        isRunning(false);
                    }
                }
            });

        switch (state) {
            case SkStatus.SSH_CONECTADO:
                if (config.time()){
                // carrega ads banner
                if (adsBannerView != null && TunnelUtils.isNetworkOnline(MainActivity.this)) {
                    adsBannerView.setAdListener(new AdListener() {
                            @Override
                            public void onAdLoaded() {
                                if (adsBannerView != null && !isFinishing()) {
                                    adsBannerView.setVisibility(View.VISIBLE);
                                }
                            }
                        });
                }}
                break;
        }
    }

    /**
     * Recebe locais Broadcast
     */

    private BroadcastReceiver mActivityReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == null)
                return;

            if (action.equals(UPDATE_VIEWS) && !isFinishing()) {
                doUpdateLayout();
            }

        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerPanel.getToogle() != null && mDrawerPanel.getToogle().onOptionsItemSelected(item)) {
            return true;
        }

        // Menu Itens
        switch (item.getItemId()) {

            case R.id.share_vpn:
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String body = "https://play.google.com/store/apps/details?id="+getPackageName();
                String sub = "Download SMK ULTRA TUN on Google Play Store!";
                myIntent.putExtra(Intent.EXTRA_SUBJECT,sub);
                myIntent.putExtra(Intent.EXTRA_TEXT,body);
                startActivity(Intent.createChooser(myIntent, "Share Using"));
                break;

            case R.id.importt:
                importFromFile();
                break;
                
            case R.id.importtclip:
                importFromClipboard();
                break;

            case R.id.exportt:
                if (imgFavorite.isChecked()) {
                    int i = setupSpinner.getSelectedItemPosition();
                    if (i == 0) {
                        toast(getApplicationContext(), R.color.red, "Failed export profile");
                    } else if (i == 7) {
                        toast(getApplicationContext(), R.color.red, "Failed export profile");
                    } else {
                        doSaveData();
                        startActivity(new Intent(MainActivity.this, ExportActivity.class));
                    }
                } else {
                    toast(getApplicationContext(), R.color.green, "switch custom first");
                }
                break;

            case R.id.miClearData:
                //Code mo dito
                try {
                    // clearing app data
                    String packageName = getApplicationContext().getPackageName();
                    Runtime runtime = Runtime.getRuntime();
                    runtime.exec("pm clear "+packageName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case R.id.miSettings1:
                showExitDialog();
                break;

            case R.id.darkMode:
                darkModes();
                break;

            case R.id.miLimparLogs:
                mLogAdapter.clearLog();
                SkStatus.logInfo("Log Clear!");
                break;

        }
        return super.onOptionsItemSelected(item);
    }

	
	private void importFromClipboard() {
		ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
		if (clipboard.hasPrimaryClip()) {
			ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
			String clipboardText = item.getText().toString();	
            importConfig(clipboardText);
            doLayout();
            doUpdateLayout();
		} else {
			toast(getApplicationContext(), R.color.colorPrimary, "Clipboard is empty");
		}
	}

	private void importFromFile() {
		if (!TunnelUtils.isActiveVpn(MainActivity.this)) {
			Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
			intent.setType("*/*");
			startActivityForResult(intent, PICK_FILE);
		} else {
			toast(getApplicationContext(), R.color.colorPrimary, "Stop tunnel first");
		}
	}
	
    private void showPayloadGenerator() {
        PayloadGeneratorDialog paygen = new PayloadGeneratorDialog(this);
        /** set positive button**/
        paygen.setGenerateListener("Generate", new PayloadGeneratorDialog.OnGenerateListener() {
                @Override
                public void onGenerate(String payloadGenerated) {
                    edPayload.setText(payloadGenerated);
                }
            });
        /** set negative button**/
        paygen.setCancelListener("Cancel", new PayloadGeneratorDialog.OnCancelListener() {
                @Override
                public void onCancelListener() {

                }
            });
        /** set neutral button**/
        paygen.show();
    }

    private void darkModes() {
        final boolean isNightMode = new Settings(this).getModoNoturno().equals("on");
        if (isNightMode){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            new Settings(this).setModoNoturno("off");
            recreate();
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            new Settings(this).setModoNoturno("on");
            recreate();
        }

    }

    @Override
    public void onBackPressed() {
        if (mDrawerPanel.getDrawerLayout().isDrawerOpen(GravityCompat.START)) {
            mDrawerPanel.getDrawerLayout().closeDrawers();
        } else {
            // mostra opção para sair
            showExitDialog();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        imgFavorite.setChecked(prefs.getBoolean("SavedSetup", false));
        setupSpinner.setSelection(prefs.getInt("SavedPos",0));
        if (imgFavorite.isChecked()) {
            int i = setupSpinner.getSelectedItemPosition();
            if (i == 7) {
                if (prefs.getBoolean("isMsg", false)) {
                    messLay.setVisibility(View.VISIBLE);
                    tvMess.setText(Html.fromHtml(prefs.getString("Mess", "").replace("\n", "<br/>")));
                }
            }
        }
        if (!mTimerEnabled) {
            resumeTime();
        }if (config.time()){
        loadAd();
        }
        saveTime();
        SkStatus.addStateListener(this);
        if (adsBannerView != null) {
            adsBannerView.resume();
        }
        mAppUpdateManager.getAppUpdateInfo().addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>(){
                @Override
                public void onSuccess(AppUpdateInfo result) {
                    if(result.updateAvailability() == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS){
                        try {
                            mAppUpdateManager.startUpdateFlowForResult(result, AppUpdateType.IMMEDIATE, MainActivity.this, RC_APP_UPDATE);
                        } catch (IntentSender.SendIntentException e) {}}
                }
            });
    }

    @Override
    protected void onPause() {
        super.onPause();
        SkStatus.removeStateListener(this);
       // doSaveData();
        //   loadServerData();
        if (adsBannerView != null) {
            adsBannerView.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mActivityReceiver);
        if (adsBannerView != null) {
            adsBannerView.destroy();
        }
    }

    public void Changelogs() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.notif, null);
        MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(this);
        alertDialogBuilder.setView(inflate);
        TextView title = inflate.findViewById(R.id.notiftext1);
        TextView ms = inflate.findViewById(R.id.confimsg);
        Button ok = inflate.findViewById(R.id.appButton1);
        title.setText("Release Notes!");
        ms.setText(this.config.getNote());
        ok.setText("Hide");
        final AlertDialog alert = alertDialogBuilder.create();
        alert.setCanceledOnTouchOutside(false);
        alert.getWindow().setGravity(Gravity.CENTER);
        ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View p1){
                    alert.dismiss();
                }
            });
        alert.show();
    }

    private void showExitDialog() {
        MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(this);
        alertDialogBuilder.setMessage("Are you sure you want to exit?");
        alertDialogBuilder.setNegativeButton("Minimize", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    Intent startMain = new Intent(Intent.ACTION_MAIN);
                    startMain.addCategory(Intent.CATEGORY_HOME);
                    startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(startMain);
                }
            });
        alertDialogBuilder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(0);
                    if (TunnelUtils.isActiveVpn(MainActivity.this)) {
                        Utils.exitAll(MainActivity.this);
                    }
                }
            });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public void Changelogs1() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.notif, null);
        MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(this);
        alertDialogBuilder.setView(inflate);
        TextView title = inflate.findViewById(R.id.notiftext1);
        TextView ms = inflate.findViewById(R.id.confimsg);
        Button ok = inflate.findViewById(R.id.appButton1);
        title.setText("Announcement!");
        ms.setText(this.config.geNote1());
        ok.setText("Hide");
        final AlertDialog alert = alertDialogBuilder.create();
        alert.setCanceledOnTouchOutside(false);
        alert.getWindow().setGravity(Gravity.CENTER);
        ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View p1){
                    alert.dismiss();
                }
            });
        alert.show();
    }

    private void restart_app() {
        finish();
        loadServer();
        loadNetworks();
        startActivity(new Intent(this,LauncherActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION));
        config_ver_txt.setText(config.getVersion());
    }

    private void updateConfig(final boolean isOnCreate) {
		new ConfigUpdate(this, new ConfigUpdate.OnUpdateListener() {
			@Override
			public void onUpdateListener(String result) {
				try {
					if (!result.contains("Error on getting data")) {
						String json_data = AESCrypt.decrypt(config.PASSWORD, result);
						if (isNewVersion(json_data)) {
                        
							letUpdate(result);
						} else {
							if (!isOnCreate) {
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start(isOnCreate);
	}


    int status = 0 ;
    ProgressDialog pd;
    byte[] YourDataA;
    private void letUpdate(final String result) {
    YourDataA = result.getBytes();
    pd = new ProgressDialog(this, R.style.AppCompatAlertDialogStyle);
    pd.setMax(100);
    pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    pd.getWindow().setBackgroundDrawableResource(R.drawable.background_tek);
    pd.getWindow().getAttributes().windowAnimations = R.style.dialog;

    pd.show();

    new Thread(new Runnable() {
        @Override
        public void run() {
        TelephonyManager telephonyManager = ((TelephonyManager) getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE));
            try {
                // Simulate progress
                for (status = 0; status <= 100; status++) {
                    Thread.sleep(15);
                    runOnUiThread(() -> pd.setProgress(status));
                }

                // Step 1: Decrypt the result if encrypted
                String decryptedResult;
                try {
                    decryptedResult = AESCrypt.decrypt(config.PASSWORD, result);
                } catch (Exception e) {
                    throw new Exception("Failed to decrypt the result: " + e.getMessage());
                }

                // Step 2: Parse JSON and filter the Networks array
                JSONObject resultJson = new JSONObject(decryptedResult);
                JSONArray networksArray = resultJson.optJSONArray("Networks");
                String appVersion = resultJson.getString("AppVersion");
                String force = resultJson.getString("fUpdate");
                sp.edit().putString("AppVersion", appVersion).apply();
                sp.edit().putString("fUpdate", force).apply();
                   

                if (networksArray != null) {
                    JSONArray filteredNetworks = new JSONArray();
                    for (int i = 0; i < networksArray.length(); i++) {
                        JSONObject networkObj = networksArray.getJSONObject(i);
                        String flag = networkObj.optString("FLAG", "");
                        String mCountry = telephonyManager.getSimCountryIso();

                        // Filter by FLAG
                        if ("all".equals(flag) || mCountry.equals(flag)) {
                            filteredNetworks.put(networkObj);
                        }
                    }

                    // Replace Networks with the filtered array
                    resultJson.put("Networks", filteredNetworks);
                }

                // Step 3: Re-encrypt the JSON
                String encryptedResult;
                try {
                    encryptedResult = AESCrypt.encrypt(config.PASSWORD, resultJson.toString());
                } catch (Exception e) {
                    throw new Exception("Failed to encrypt the result: " + e.getMessage());
                }

                // Step 4: Save the encrypted JSON
                File file = new File(getFilesDir(), "Config.json");
                try (OutputStream out = new FileOutputStream(file)) {
                    out.write(encryptedResult.getBytes());
                }

                // Success: Dismiss dialog and restart app
                runOnUiThread(() -> {
                    pd.dismiss();
                    Toast.makeText(MainActivity.this, "Update successful!", Toast.LENGTH_SHORT).show();
                    restart_app();
                });
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("letUpdate", "Error occurred: " + e.getMessage());

                runOnUiThread(() -> {
                    pd.dismiss();
                    Toast.makeText(MainActivity.this, "An error occurred: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });
            }
        }
    }).start();
}

    private void newUpdateDialog(final String result) throws JSONException, GeneralSecurityException{
        String json_data = AESCrypt.decrypt(config.PASSWORD, result);
        String notes = new JSONObject(json_data).getString("ReleaseNotes");
        String version = new JSONObject(json_data).getString("Version");
        View inflate = LayoutInflater.from(this).inflate(R.layout.notif, null);
        MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(this);
        alertDialogBuilder.setView(inflate);

        TextView title = inflate.findViewById(R.id.notiftext1);
        TextView ms = inflate.findViewById(R.id.confimsg);
        Button ok = inflate.findViewById(R.id.appButton1);
        title.setText("New Update Available");
        ms.setText(Html.fromHtml("Config Version: "+version+"<br><br>")+notes);
        ok.setText("Apply & Restart");
        final AlertDialog alert = alertDialogBuilder.create();
        alert.setCanceledOnTouchOutside(false);
        alert.getWindow().setGravity(Gravity.CENTER);
        alert.show();
        ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View p1){
                    try {
                        letUpdate(result);
                       // showInterstitial();
                        alert.dismiss();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            });
        alert.show();

    }

    private boolean isNewVersion(String result) {
        try {
            String current = config.getVersion();
            String update = new JSONObject(result).getString("Version");
            return config.versionCompare(update, current);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void noUpdateDialog() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.notif, null);
        MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(this);
        alertDialogBuilder.setView(inflate);
        TextView title = inflate.findViewById(R.id.notiftext1);
        TextView ms = inflate.findViewById(R.id.confimsg);
        Button ok = inflate.findViewById(R.id.appButton1);
        title.setText("No Update Available!");
        ms.setText("Please Try Again Later");
        ok.setText("Hide");
        final AlertDialog alert = alertDialogBuilder.create();
        alert.setCanceledOnTouchOutside(false);
        alert.getWindow().setGravity(Gravity.CENTER);
        ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View p1){
                    alert.dismiss();
                }
            });
        alert.show();
    }

    private void errorUpdateDialog(String error) {
        MaterialAlertDialogBuilder dialogo = new MaterialAlertDialogBuilder(this);
        dialogo.setMessage("Error on update")
            .setPositiveButton("Ok", null)
            .create().show();
    }


    public void loadAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this, "ca-app-pub-8221353512185856/5759680489", adRequest,new InterstitialAdLoadCallback() {
                @Override
                public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                    MainActivity.this.interstitialAd = interstitialAd;
                    interstitialAd.setFullScreenContentCallback(
                        new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                MainActivity.this.interstitialAd = null;
                                Toast.makeText(MainActivity.this,"Thank you for supporting the app !! 💙", Toast.LENGTH_LONG).show();
                                //Log.d("TAG", "The ad was dismissed.");
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                MainActivity.this.interstitialAd = null;
                                //Log.d("TAG", "The ad failed to show.");
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                //Log.d("TAG", "The ad was shown.");
                            }
                        });
                }

                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    interstitialAd = null;
                }
            });
    }

    private void showInterstitial() {
        if (config.time()){
        if (interstitialAd != null) {
            interstitialAd.show(this);
        } else {
            loadAd();
        }
        }
    }


    public static void updateMainViews(Context context) {
        Intent updateView = new Intent(UPDATE_VIEWS);
        LocalBroadcastManager.getInstance(context).sendBroadcast(updateView);
    }


    private void start() {
        if (saved_ads_time == 0) {
            long millisInput = 1800 * 1000;
            setTime(millisInput);
        }
        if (!mTimerRunning) {
            startTimer();
        }
    }

    private void stop() {
        if (mTimerRunning) {
            pauseTimer();
        }
    }

    private void pauseTimer() {
        if (mCountDownTimer != null) {
    mCountDownTimer.cancel();
} else {
    Log.e("MainActivity", "CountDownTimer is null in pauseTimer");
}
        mTimerRunning = false;
        mConnected = false;
    }

    private void updateCountDownText() {
        long toDays = TimeUnit.MILLISECONDS.toDays(mTimeLeftInMillis);
        long toMillis = TimeUnit.DAYS.toMillis(toDays);
        long toHours = TimeUnit.MILLISECONDS.toHours(mTimeLeftInMillis - toMillis);
        long toMillis2 = TimeUnit.HOURS.toMillis(toHours);
        long toMinutes = TimeUnit.MILLISECONDS.toMinutes((mTimeLeftInMillis - toMillis) - toMillis2);
        long toSeconds = TimeUnit.MILLISECONDS.toSeconds(((mTimeLeftInMillis - toMillis) - toMillis2) - TimeUnit.MINUTES.toMillis(toMinutes));
        String timeLeftFormatted;
        timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d:%02d:%02d", toDays, toHours, toMinutes, toSeconds);
        mTextViewCountDown.setText(timeLeftFormatted);
    }

    private void addTime(long time) {
        setTime(time);
        if (mTimerRunning) {
            pauseTimer();
        }
        startTimer();
    }

    private void setTime(long milliseconds) {
        saved_ads_time = mTimeLeftInMillis + milliseconds;
        mTimeLeftInMillis = saved_ads_time;
        updateCountDownText();

    }

    private void saveTime() {
        SharedPreferences saved_current_time = getSharedPreferences("time", Context.MODE_PRIVATE);
        SharedPreferences.Editor time_edit = saved_current_time.edit();
        time_edit.putLong("SAVED_TIME", mTimeLeftInMillis);
        time_edit.apply();
    }

    private void resumeTime() {
        SharedPreferences time = getSharedPreferences("time", Context.MODE_PRIVATE);
        long saved_time = time.getLong("SAVED_TIME", 0);
        setTime(saved_time);
        String state = SkStatus.getLastState();
        if (SkStatus.SSH_CONECTADO.equals(state)) {
            if (!mTimerRunning) {
                startTimer();
            }
        }
        mTimerEnabled = true;
    }

    private void startTimer() {
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                saveTime();
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                pauseTimer();
                saved_ads_time = 0;
                final int ems = prefs.getInt(Settings.TUNNELTYPE_KEY, 0);
                UDPThread tek = new UDPThread(MainActivity.this);
                if (ems == Settings.bTUNNEL_TYPE_UDP){
                    tek.stopVudp();
                }
                Intent stopVPN = new Intent(SocksHttpService.TUNNEL_SSH_STOP_SERVICE);
                LocalBroadcastManager.getInstance(MainActivity.this).sendBroadcast(stopVPN);
                MainActivity.toast(getApplicationContext(), R.color.red, "Time Expire! Click Add + Time to renew acces!");
            }

        }.start();
        mTimerRunning = true;
        mConnected = true;
    }

    /**
     * Rewarded Setup
     */
    private void startGame() {
        if (rewardedAd != null && !isLoading) {
            loadRewardedAd();
        }
        createTimer();
        gamePaused = false;
        gameOver = false;
    }


    private void createTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        countDownTimer = new CountDownTimer(MainActivity.COUNTER_TIME * 1000, 50) {
            @Override
            public void onTick(long millisUnitFinished) {
                timeRemaining = ((millisUnitFinished / 1000) + 1);
                retryButton.setText("Requesting  " + timeRemaining);
            }

            @Override
            public void onFinish() {
                if (rewardedAd != null) {
                    reward();
                }
                retryButton.setText("Request Ads");
                gameOver = true;
            }
        };
        countDownTimer.start();
    }

    private void reward() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.notif2, null);
        MaterialAlertDialogBuilder alertDialogBuilder = new MaterialAlertDialogBuilder(this);
        alertDialogBuilder.setView(inflate);
        TextView title = inflate.findViewById(R.id.notiftext1);
        final TextView ms = inflate.findViewById(R.id.confimsg);
        final TextView ok = inflate.findViewById(R.id.appButton1);
        TextView cancel = inflate.findViewById(R.id.appButton2);
        title.setText("Reward Ads Loaded!");
        ms.setText("Watch to earn time!");
        ok.setText("Watch Ads");
        cancel.setText("Close");
        final AlertDialog alert = alertDialogBuilder.create();
        alert.setCanceledOnTouchOutside(false);
        alert.getWindow().setGravity(Gravity.CENTER);
        alert.show();
        ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View p1){
                    showRewardedVideo();
                    alert.dismiss();
                }
            });

        cancel.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View p1) {
                    alert.dismiss();
                }
            });
        alert.show();
    }

    private void showRewardedVideo() {
        if(rewardedAd == null) {
            return;
        }
        rewardedAd.setFullScreenContentCallback(
            new FullScreenContentCallback() {
                @Override
                public void onAdShowedFullScreenContent() {

                }
                @Override
                public void onAdFailedToShowFullScreenContent(AdError adError) {
                    rewardedAd = null;

                }
                @Override
                public void onAdDismissedFullScreenContent() {
                    rewardedAd = null;
                }
            });

        Activity activityContext = MainActivity.this;
        rewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                @Override
                public void onUserEarnedReward(RewardItem rewardItem) {
                    Toast.makeText(MainActivity.this, "+2hr added to your time", Toast.LENGTH_SHORT).show();
                    addTime(7200000);
                    alert.dismiss();
                }
            });
    }

    private void loadRewardedAd() {
        if (rewardedAd == null) {
            MainActivity.this.isLoading = true;
            AdRequest adRequest = new AdRequest.Builder().build();
            RewardedAd.load(this,"ca-app-pub-8221353512185856/5750161953", adRequest,new RewardedAdLoadCallback() {
                    @Override
                    public void onAdFailedToLoad( LoadAdError loadAdError) {
                        rewardedAd = null;
                        Toast.makeText(MainActivity.this, "Failed to load ADS", Toast.LENGTH_SHORT).show();
                        MainActivity.this.isLoading = false;
                    }

                    @Override
                    public void onAdLoaded(RewardedAd rewardedAd) {
                        MainActivity.this.rewardedAd = rewardedAd;
                        MainActivity.this.isLoading = false;
                        Toast.makeText(MainActivity.this, "ADS is loaded", Toast.LENGTH_SHORT).show();
                    }
                });
        }}


    public static void toast(Context contxt, int color, String string){
        LayoutInflater inflater = (LayoutInflater) contxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflate = inflater.inflate(R.layout.toast, (ViewGroup) null );
        LinearLayout ll1 = new LinearLayout(contxt);
        Toast llIl = Toast.makeText(contxt,Html.fromHtml(""),Toast.LENGTH_LONG);
        final TextView text1 = (TextView)inflate.findViewById(R.id.textqt);
        final ImageView img = (ImageView)inflate.findViewById(R.id.img);
        final RelativeLayout toastlayout = (RelativeLayout)inflate.findViewById(R.id.toastlayout);
        GradientDrawable var1 = new GradientDrawable();
        var1.setColor(contxt.getResources().getColor(color));
        if (color == R.color.red){
            img.setBackgroundResource(R.drawable.ic_info);

        } else if (color == R.color.colorPrimary){
            img.setBackgroundResource(R.drawable.err);

        } else if (color == R.color.green){
            img.setBackgroundResource(R.drawable.cnt);

        } else {
            img.setBackgroundResource(R.drawable.err);

        }
        var1.setCornerRadius((float)50);
        var1.setOrientation(Orientation.RIGHT_LEFT);
        var1.setStroke(0, Color.parseColor("#ffffff"));
        text1.setText(Html.fromHtml(string));
        ll1.setBackgroundDrawable(var1);
        ll1.addView(inflate);
        llIl.setView(ll1);
        llIl.show();

    }

}
