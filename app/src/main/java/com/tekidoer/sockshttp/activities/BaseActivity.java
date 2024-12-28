package com.tekidoer.sockshttp.activities;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import com.tekidoer.sockshttp.preference.LocaleHelper;
import com.tekidoer.ultrasshservice.config.Settings;
import static android.content.pm.PackageManager.GET_META_DATA;
import android.app.AlertDialog;
import android.content.DialogInterface;
import com.tekidoer.sockshttp.util.Utils;
import com.smkultratun.pro.R;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.widget.Toast;

public abstract class BaseActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
        if (!(((String) getPackageManager().getApplicationLabel(getApplicationInfo())).equals("SMK ULTRA TUN") && getPackageName().equals("com.smkultratun.pro"))) {
            AlertDialog.Builder builder = new AlertDialog.Builder(BaseActivity.this);
            builder.setTitle("OPPS APP MODIFIED");
            builder.setMessage("Please install the original application version")
                .setCancelable(false)
                .setPositiveButton(R.string.ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Utils.exitAll(BaseActivity.this);
                    }
                });
            builder.show();
		}
		setModoNoturnoLocal();	
		resetTitles();
        tekidoer_sign_check(this);
	}
	
	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(LocaleHelper.setLocale(base));
	}
	
    protected void tekidoer_sign_check(Context cont) {
        if (!(getSign().equals(Utils.my_sign) || getSign().equals(Utils.ps_sign))) {
            finish();
            Utils.exitAll(BaseActivity.this);
        }
    }

    public String getSign() {
        StringBuilder str = new StringBuilder();
        try {
            PackageInfo pi = getPackageManager().getPackageInfo(getPackageName(), getPackageManager().GET_SIGNATURES);
            for (Signature signs: pi.signatures) {
                str.append(signs.toCharsString());
            }
        } catch (Exception e) {}
        return str.toString();
    }
    
	public void setModoNoturnoLocal() {
		boolean is = new Settings(this).getModoNoturno().equals("on");
		int night_mode = is ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO;
		getDelegate().setLocalNightMode(night_mode);
	}
	
	protected void resetTitles() {
		try {
			ActivityInfo info = getPackageManager().getActivityInfo(getComponentName(), GET_META_DATA);
			if (info.labelRes != 0) {
				setTitle(info.labelRes);
			}
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
	}
}
