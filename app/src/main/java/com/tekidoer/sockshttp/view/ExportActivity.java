package com.tekidoer.sockshttp.view;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import com.tekidoer.sockshttp.activities.BaseActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.textfield.TextInputLayout;
import com.tekidoer.sockshttp.util.AESCrypt;
import com.tekidoer.sockshttp.util.ConfigUtil;
import com.tekidoer.sockshttp.util.RandomString;
import com.tekidoer.ultrasshservice.config.Settings;
import androidx.appcompat.app.AlertDialog.Builder;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import com.smkultratun.pro.R;
import java.io.File;
import android.app.AlertDialog;
import java.io.FileOutputStream;
import org.json.JSONObject;
import android.widget.TextView;
import java.util.Calendar;
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.content.DialogInterface;
import java.util.concurrent.atomic.AtomicReference;
import android.util.Log;
import java.text.DateFormat;
import java.util.Date;
import android.widget.LinearLayout;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.gms.tasks.OnCompleteListener;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.UploadTask;
import com.tekidoer.sockshttp.util.Utils;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

public class ExportActivity extends BaseActivity {
    
    private EditText etFilename;
    private LinearLayout messageLayout;
    private EditText etMessage;
    private Button btnExport,btnExportCloud;
    private CheckBox ckAddMessage;
    private CheckBox ckLock;
    private ConfigUtil mConfig;
    private SharedPreferences sp;
	private AdView mAdView;
    private CheckBox ex_check;
    private long expire_date;
    private ProgressDialog pd, pdup;

    private EditText pass_txt;

    private CheckBox pass_check;

    private CheckBox deviceid_check;
    private String deviceid;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export);
		MobileAds.initialize(this);
        mAdView = (AdView) findViewById(R.id.adView3);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
		int permissionCheck = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
		if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
		}
        
        sp = new Settings(this).getPrefsPrivate();
        new File(Environment.getExternalStorageDirectory() + "/Download/").mkdirs();
        mConfig = new ConfigUtil(this);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        
        etFilename = (EditText) findViewById(R.id.file_name);
        messageLayout = (LinearLayout) findViewById(R.id.messageLayout);
        messageLayout.setVisibility(View.GONE);
        etMessage = (EditText) findViewById(R.id.message);
        this.ex_check = (CheckBox) findViewById(R.id.expire_check);
        this.ex_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    if (ex_check.isChecked()) {
                        setValidateDate();
                    } else {
                        ex_check.setText("Expire Date");
                    }
                }
            });
        this.pass_txt = (EditText) findViewById(R.id.etPass);
        this.pass_check = (CheckBox) findViewById(R.id.pass_check);
        this.pass_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    if (pass_check.isChecked()) {
                        ((LinearLayout)findViewById(R.id.llPass)).setVisibility(View.VISIBLE);
                    } else {
                        ((LinearLayout)findViewById(R.id.llPass)).setVisibility(View.GONE);
                    }
                }
            });
        this.deviceid_check = (CheckBox) findViewById(R.id.hardware_id_check);
        this.deviceid_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    if (deviceid_check.isChecked()) {
                        setDeviceid();
                    }
                }
            });
            
        ckAddMessage = (CheckBox) findViewById(R.id.message_check);
        ckAddMessage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton p1, boolean p3) {
                    sp.edit().putBoolean("isTrue", p3).apply();
                    if (p3) {
                        messageLayout.setVisibility(View.VISIBLE);
                        etMessage.setVisibility(View.VISIBLE);
                    } else {
                        messageLayout.setVisibility(View.GONE);
                        etMessage.setVisibility(View.GONE);
                    }
                }
            });
        ckLock = (CheckBox) findViewById(R.id.ckLock);
        ckLock.setChecked(true);
        ckLock.setEnabled(false);
        btnExport = (Button) findViewById(R.id.export);
        btnExport.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View p1) {
                    if (etFilename.getText().toString().isEmpty()) {
                        ((TextInputLayout) findViewById(R.id.file_name_lay)).setError("Invalid Name");
                        return;
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(ExportActivity.this);
                    builder.setTitle("Choose Export Option");
                    View dialogView = getLayoutInflater().inflate(R.layout.dialog_export_options, null);
                    RadioGroup rgExportOptions = dialogView.findViewById(R.id.rgExportOptions);
                    RadioButton rbExportFile = dialogView.findViewById(R.id.rbExportFile);
                    RadioButton rbExportClipboard = dialogView.findViewById(R.id.rbExportClipboard);
                    builder.setView(dialogView);

                    builder.setPositiveButton("Export", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    String sName = etFilename.getText().toString();
                                    boolean isAddMessage = sp.getBoolean("isTrue", false);
                                    String sMessage = etMessage.getText().toString();
                                    String sPayload = sp.getString("SavedHTTP", "");
                                    String sSNI = sp.getString("SavedSSL", "");
                                    String sSSL = sp.getString("SavedHTTP + SSL", "");
                                    String ssPayload = sp.getString("SavedSSL + HTTP", "");
                                    String sUser = sp.getString("SavedUSER", "");
                                    String pPass = sp.getString("SavedPASS", "");
                                    String sBug = sp.getString("SavedBUG", "");
                                    String sUdp = sp.getString("SavedUDP", "");
                                    String obfs = sp.getString("SavedOBFS", "");
                                    String auth = sp.getString("SavedAUTH", "");
                                    String dnsC = sp.getString("SavedCHAVE", "");
                                    String dnsS = sp.getString("SavedSERKEY", "");
                                    String dnsD = sp.getString("SavedDNS", "");
                                    String v2 = sp.getString("SavedV2Ray", "");
                                    int tunType = sp.getInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_DIRECT);

                                    JSONObject obj = new JSONObject();
                                    obj.put("CPayload", sPayload);
                                    obj.put("Bug", sSNI);
                                    obj.put("Payload", sSSL);
                                    obj.put("SNI", ssPayload);
                                    obj.put("ServerUser", sUser);
                                    obj.put("ServerPass", pPass);
                                    obj.put("ServerIP", sBug);
                                    obj.put("UdpServer", sUdp);
                                    obj.put("Obfs", obfs);
                                    obj.put("Auth", auth);
                                    obj.put("chaveKey", dnsC);
                                    obj.put("serverNameKey", dnsS);
                                    obj.put("dnsKey", dnsD);
                                    obj.put("V2Ray", v2);
                                    obj.put("DeviceID", deviceid_check.isChecked() ? deviceid : "");
                                    obj.put("isPassON", pass_check.isChecked());
                                    obj.put("MyPass", pass_check.isChecked() ? pass_txt.getText().toString() : "");
                                    obj.put("TunnelType", tunType);
                                    obj.put("isMsg", isAddMessage);
                                    obj.put("Message", sMessage);
                                    obj.put("ExpireDate", expire_date);
                                    String data = AESCrypt.encrypt(new String(new byte[]{115,109,107,115,97,121,115,111}), obj.toString());
                                    if (rbExportFile.isChecked()) {
                                        StringBuffer sb = new StringBuffer();
                                        File path = new File(sb.append(Environment.getExternalStorageDirectory().getAbsolutePath()).append("/Download").toString());
                                        export(path, sName, data);
                                    } else if (rbExportClipboard.isChecked()) {
                                        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                                        ClipData clip = ClipData.newPlainText("Exported Data", data);
                                        clipboard.setPrimaryClip(clip);
                                        Toast.makeText(getApplicationContext(), "Copied to clipboard", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }

                                } catch (Exception e) {
                                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                    builder.create().show();
                }
            });
        
        btnExportCloud = (Button) findViewById(R.id.export_cloud_btn);
        btnExportCloud.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View p1) {
                    pdup = new ProgressDialog(ExportActivity.this);
			pdup.setMessage("Please wait while loading");
		       	pdup.setTitle("Uploading to cloud");
			pdup.setCancelable(false);
		       	pdup.show();
                    try {
                        String sName = etFilename.getText().toString();
                        boolean isAddMessage = sp.getBoolean("isTrue", false);
                        String sMessage = etMessage.getText().toString();
                        String sPayload = sp.getString("SavedHTTP", "");
                        String sSNI = sp.getString("SavedSSL", "");
						String sSSL = sp.getString("SavedHTTP + SSL", "");
                        String ssPayload = sp.getString("SavedSSL + HTTP", "");
						String sUser = sp.getString("SavedUSER", "");
                        String pPass = sp.getString("SavedPASS", "");
						String sBug = sp.getString("SavedBUG", "");
						String sUdp = sp.getString("SavedUDP", "");
                        String obfs = sp.getString("SavedOBFS", "");
                        String auth = sp.getString("SavedAUTH", "");
                        
						String dnsC = sp.getString("SavedCHAVE", "");
						String dnsS = sp.getString("SavedSERKEY", "");
						String dnsD = sp.getString("SavedDNS", "");
                        String v2 = sp.getString("SavedV2Ray", "");
						int tunType = sp.getInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_DIRECT);
                        
                        JSONObject obj = new JSONObject();
                        obj.put("CPayload", sPayload);
                        obj.put("Bug", sSNI);
						obj.put("Payload", sSSL);
                        obj.put("SNI", ssPayload);
						obj.put("ServerUser", sUser);
                        obj.put("ServerPass", pPass);
						obj.put("ServerIP", sBug);
                        
						obj.put("UdpServer", sUdp);
                        obj.put("Obfs", obfs);
						obj.put("Auth", auth);
                        
						obj.put("chaveKey", dnsC);
						obj.put("serverNameKey", dnsS);
						obj.put("dnsKey", dnsD);
                        
                        obj.put("V2Ray", v2);
                        
                        obj.put("DeviceID", deviceid_check.isChecked() ? deviceid : "");
						obj.put("isPassON", pass_check.isChecked());
                        obj.put("MyPass", pass_check.isChecked() ? pass_txt.getText().toString() : "");
                        obj.put("TunnelType", tunType);
                        obj.put("isMsg", isAddMessage);
                        obj.put("Message", sMessage);
                        obj.put("ExpireDate", expire_date);
                        String data = AESCrypt.encrypt(new String(new byte[]{115,109,107,115,97,121,115,111}), obj.toString());
                        addData(data.toString());
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), 0).show();
                    }
                }
            });
    }
    
    @SuppressWarnings("deprecation")
	private void addData(String isi) {
		String Name = RandomString.gentoken(10);
		String extension = ".sut";
		StorageReference ref = FirebaseStorage.getInstance().getReference();
		ref.child(Name + extension).putBytes(isi.getBytes())
				.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
					@Override
					public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
						if (pdup != null)
							pdup.dismiss();
						new AlertDialog.Builder(ExportActivity.this).setMessage(Utils.fromHtml("Token :  " + Name))
								.setPositiveButton("Copy", (dialog, which) -> {
									Utils.copyToClipboard(ExportActivity.this, Name);
								}).show();
					}
				});
	}

    void export(File directory, String fileName, String content) {
        try {
            File fileToSave = new File(directory, fileName+".sut");
            FileOutputStream fos = new FileOutputStream(fileToSave);
            String sl = "/";
            fos.write(content.getBytes());
            String saveNot = "Successfully Saved to "+directory+sl+fileToSave.getName();
            Toast.makeText(this, saveNot, Toast.LENGTH_SHORT).show();
            fos.close();
            finish();
        }catch (Exception e) {
            Toast.makeText(this, e.getMessage(),0).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    
    void setDeviceid() {
        View inflate = getLayoutInflater().inflate(R.layout.input_text, null);
        final EditText editText = (EditText) inflate.findViewById(R.id.input);
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setMessage("Enter ID");
        builder.setCancelable(false);
        builder.setView(inflate);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialogInterface, int i) {
                    if (editText.getText().toString().isEmpty()) {
                        Toast.makeText(ExportActivity.this, "ID Cannot be Empty!", 1).show();
                        deviceid_check.setChecked(false);
                        return;
                    }
                    deviceid = editText.getText().toString();
                }
            });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    deviceid_check.setChecked(false);
                }
            });
        builder.create().show();
    }

    
    /**
     * Validade
     */

   // private long expire_date = 0;

    private void setValidateDate() {

        // Get Current Date
        Calendar c = Calendar.getInstance();
        final long time_hoje = c.getTimeInMillis();
        c.setTimeInMillis(time_hoje+(1000*60*60*24));

        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        expire_date = c.getTimeInMillis();

        final DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker p1, int year, int monthOfYear, int dayOfMonth) {
                    Calendar c = Calendar.getInstance();
                    c.set(year, monthOfYear, dayOfMonth);
                    expire_date = c.getTimeInMillis();
                }
            },
            mYear, mMonth, mDay);

        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
            new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog2, int which) {
                    DateFormat df = DateFormat.getDateInstance();
                    DatePicker date = dialog.getDatePicker();

                    Calendar c = Calendar.getInstance();
                    c.set(date.getYear(), date.getMonth(), date.getDayOfMonth());

                    expire_date = c.getTimeInMillis();

                    if (expire_date < time_hoje) {
                        expire_date = 0;

                        Toast.makeText(getApplicationContext(), R.string.error_date_selected_invalid, Toast.LENGTH_SHORT).show();
                        if (ex_check != null)
                            ex_check.setChecked(false);
                    } else {
                        long dias = ((expire_date-time_hoje)/1000/60/60/24);
                      //  if (validadeText != null) {
                           // validadeText.setVisibility(View.VISIBLE);
                            ex_check.setText("Expiry: "+String.format("%s (%s)", dias, df.format(expire_date)));
                      //  }
                    }
                }
            }
        );

        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCEL",
            new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    expire_date = 0;

                    if (ex_check != null) {
                        ex_check.setChecked(false);
                    }
                }
            }
        );

        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface v1) {
                    expire_date = 0;
                    if (ex_check != null) {
                        ex_check.setChecked(false);
                    }
                }
            });
        dialog.show();
	}
    
}
