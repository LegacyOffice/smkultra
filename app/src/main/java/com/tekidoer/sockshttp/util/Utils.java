package com.tekidoer.sockshttp.util;

import android.content.pm.PackageInfo;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.NetworkInfo;
import android.net.ConnectivityManager;
import android.annotation.SuppressLint;
import android.text.Spanned;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import android.view.inputmethod.InputMethodManager;
import android.app.Activity;
import android.view.WindowManager;
import android.content.Intent;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.os.Build;
import com.tekidoer.ultrasshservice.SocksHttpService;
import java.io.InputStream;
import java.io.Reader;
import android.content.ComponentName;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import com.smkultratun.pro.R;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import android.text.SpannableString;
import android.text.Html;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Build.VERSION;

public class Utils {
    
    public static String my_sign = "3082036c30820254a0030201020204404a62a0300d06092a864886f70d01010d0500304f31093007060355040613003109300706035504081300310930070603550407130031093007060355040a130031163014060355040b130d54656b69646f6572204465767331093007060355040313003020170d3233303730363031323735365a180f33303231313130363031323735365a304f31093007060355040613003109300706035504081300310930070603550407130031093007060355040a130031163014060355040b130d54656b69646f65722044657673310930070603550403130030820122300d06092a864886f70d01010105000382010f003082010a02820101008aa4f42ffaf1762de367ebeaf6778013c76aebf25daca86a5d2ed07fa71e21467e5481b337d559831dec600f041223e4b9c42478b5e777facb16127379c78e3895419f596d2118ac1a3557b87c9e93c3d771678b417c9ac14ed6297fe4988077eec49e23f8f3011f1562d1f3ed9901ed76b158a0b0a34c2ef490295759ea4370d01143be484e1668fb56ea4641356cc3aa6bd5f63c118a85d7178e9203998f42715c6aa6bf530fbb64a790a5d8d8abb8387cdb381e61f2a8041e9547e3f34d34f4b6dfd32547da8fc336f6df26eba5bde616192bccb11bf944cd51d3c42053140d8c625c325a41c938f85a1d99a3f1d0ec196383f9d752356c541d7dda78f81d0203010001a34e304c302b0603551d1004243022800f32303233303730363031323735365a810f33303231313130363031323735365a301d0603551d0e04160414862bce8d99d5a23ac3a37dd7defb47646ec61ff5300d06092a864886f70d01010d050003820101004ae4622b32b4c669f1cedc796051b428f26df952df3f08c23cc69cc7727edd8ee1ba14b37c731980c45043f3b51c8138592ebd0393214c8d3c0bb28bbf351eaf6c9c14946bc126a788ff3a2947f29ea899666b8c9dedd600c1c6f991e20e58b71ec92d8b82177229d2d43a264ff891669d9d1b5530a3e9748f124f2e76226d14370a04560e101d11d8647e3e4a0deaa3dbd4aa387373224ca0ee4c86afb1772619605ce5e6522e0cf491e02d9f4ce9bafd1209618cdb7471314da50b4f56af433960ad77fa635133b68ab43c2727c64d0c169679a2f59832549e3c16a2ef4672307aab7d0222b6de38a4b6310b60ffa57f697617eeff2d837e34c33d7699f5f4";
    public static String ps_sign = "3082031f3082020702060193173198bb300d06092a864886f70d01010d05003052310b300906035504030c02554731143012060355040a0c0b53696d6f6e205065746572310f300d06035504070c064c757765726f310f300d06035504080c064b6173616e61310b30090603550406130255473020170d3234313131303137343930355a180f32313233313131303137343930355a3052310b300906035504030c02554731143012060355040a0c0b53696d6f6e205065746572310f300d06035504070c064c757765726f310f300d06035504080c064b6173616e61310b300906035504061302554730820122300d06092a864886f70d01010105000382010f003082010a02820101009a803c903df208bd324011e127546409fd06117d323b41bd1d6aa8c58049ce84eb1c399a0fbfdef342bab8896ffb41f88e7c087389cde8849010e73855d11303bca08fe20b1aedf8a114b8cfb123684e4e4e2e841dc9ea5fe171adcc432e9961883a72d40f1d9e640ada1961d5a792e5d5ae1940ca75fc4613a8b2ac8d557c97500e14d3967583b8bff8bd53b2756dd809623662b34161cdf8c4544a5e616cefeadf14c8ea827aae3e9e123d6f3c9465b9f837bbbbd2626aed0cb1057bc3e37139b56647cc7444791bff683fc9a32cbaf8971c86e7aec99bee5c696fec2fcc7172816fdafc8034fd22d0d20a25f823d77dc3a9c4f31f2fccbd8787285b2d49b30203010001300d06092a864886f70d01010d050003820101004a7ca588fbb1d7c6c72dfa393d965545941a70001f7d64ccd40d359bc9b009b2a697fce78513ab154aa68bcde181e5bb1b5c975c0e24533bc7f02477f45e15a951fe591c2793061272bf6f8fd74e63a48ddf0bb7e324b669d3cd3fd36890ef5095f8580ef37bec6c472fcf3df0105254e3d59ca6479d7242aa37ba2c5b0deb4d94ecb1fb84b10610fadc2f3b86376a6c179a53213a9c74e7d9f70c009a8b563877c247b3358911e58eb6c241035b9dd41d4c01ce269269c7b0b9bbdf6b8ab7964a2978d3d4178e3497f18079c9471313d71d125256d5e9f9889121b3c4fa4884a9eb24deeb76fa0e2a7d2d3270c63ed01a9732143112e4371efdcd3317364d6e";
    
 
    public static final String md5(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : digest) {
                String toHexString = Integer.toHexString(b & 255);
                while (toHexString.length() < 2) {
                    toHexString = new StringBuffer().append("0").append(toHexString).toString();
                }
                stringBuilder.append(toHexString);
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            return (String) null;
        }
    }
    
    public static String getHWID() {
        return md5(new StringBuffer().append(new StringBuffer().append(new StringBuffer().append(new StringBuffer().append(new StringBuffer().append(new StringBuffer().append(new StringBuffer().append(Build.SERIAL).append(Build.BOARD.length() % 5).toString()).append(Build.BRAND.length() % 5).toString()).append(Build.DEVICE.length() % 5).toString()).append(Build.MANUFACTURER.length() % 5).toString()).append(Build.MODEL.length() % 5).toString()).append(Build.PRODUCT.length() % 5).toString()).append(Build.HARDWARE).toString()).toUpperCase(Locale.getDefault());
    }
    
   public static void copyToClipboard(Context context, String str) {
		if (VERSION.SDK_INT >= 11) {
			((ClipboardManager) context.getSystemService("clipboard"))
					.setPrimaryClip(ClipData.newPlainText("log", str));
		} else {
			((android.text.ClipboardManager) context.getSystemService("clipboard")).setText(str);
		}
	}
	
	public static PackageInfo getAppInfo(Context context){
		try {
			return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
		} catch (PackageManager.NameNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String readFromAssets(Context context, String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open(filename)));

        // do reading, usually loop until end of file reading
        StringBuilder sb = new StringBuilder();
        String mLine = reader.readLine();
        while (mLine != null) {
            sb.append(mLine + '\n'); // process line
            mLine = reader.readLine();
        }
        reader.close();
        return sb.toString();
    }
	public static String readStream(InputStream in) {
        StringBuilder sb = new StringBuilder();
        try {
            Reader reader = new BufferedReader(new InputStreamReader(in));
            char[] buff = new char[1024];
            while (true) {
                int read = reader.read(buff, 0, buff.length);
                if (read <= 0) {
                    break;
                }
                sb.append(buff, 0, read);
            }
        } catch (Exception e) {

        }
        return sb.toString();
    }
	public static void hideKeyboard(Activity activity) {
		InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
		
        if (activity.getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (activity.getCurrentFocus() != null)
                inputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
					0);
        }
    }
	
	public static void restart_app(Activity act) {
		PackageManager packageManager = act.getPackageManager();
		Intent intent = packageManager.getLaunchIntentForPackage(act.getPackageName());
		assert intent != null;
		ComponentName componentName = intent.getComponent();
		Intent mainIntent = Intent.makeRestartActivityTask(componentName);
		act.startActivity(mainIntent);
		System.exit(0);
	}
	
	public static void exitAll(Activity activity) {
		Intent stopTunnel = new Intent(SocksHttpService.TUNNEL_SSH_STOP_SERVICE);
		LocalBroadcastManager.getInstance(activity).sendBroadcast(stopTunnel);
		if (Build.VERSION.SDK_INT >= 16) {
			activity.finishAffinity();
		}
		System.exit(0);
	}
    
   @SuppressWarnings("deprecation")
	public static Spanned fromHtml(String html) {
		if (html == null) {
			return new SpannableString("");
		} else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			return Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
		} else {
			return Html.fromHtml(html);
		}
	}
	
	public static void showExitDialog(final Activity a) {
		AlertDialog dialog = new AlertDialog.Builder(a).create();
		dialog.setTitle(a.getString(R.string.attention));
		dialog.setMessage(a.getString(R.string.alert_exit));
		dialog.setButton(DialogInterface.BUTTON_POSITIVE, a.getString(R.string.exit), new DialogInterface.OnClickListener() {

			    @Override
				public void onClick(DialogInterface dialog, int which){
					Utils.exitAll(a);
				}
			});
		dialog.setButton(DialogInterface.BUTTON_NEGATIVE, a.getString(R.string.minimize), new DialogInterface.OnClickListener() {

			    @Override
				public void onClick(DialogInterface dialog, int which) {
					Intent startMain = new Intent(Intent.ACTION_MAIN);
					startMain.addCategory(Intent.CATEGORY_HOME);
					startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					a.startActivity(startMain);
				}
			});
		dialog.show();
	}
}
