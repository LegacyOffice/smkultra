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
import com.demontunnel.project.R;
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
    public static String ps_sign = "3082058830820370a003020102021430f4c4e0cfac6a9a0ea3a8089ed93620e1c22223300d06092a864886f70d01010b05003074310b3009060355040613025553311330110603550408130a43616c69666f726e6961311630140603550407130d4d6f756e7461696e205669657731143012060355040a130b476f6f676c6520496e632e3110300e060355040b1307416e64726f69643110300e06035504031307416e64726f69643020170d3233303831373132323130395a180f32303533303831373132323130395a3074310b3009060355040613025553311330110603550408130a43616c69666f726e6961311630140603550407130d4d6f756e7461696e205669657731143012060355040a130b476f6f676c6520496e632e3110300e060355040b1307416e64726f69643110300e06035504031307416e64726f696430820222300d06092a864886f70d01010105000382020f003082020a02820201008d9aefdca50d616b367b225fa2b833161ddaf3af6e2889fdf5e1ac541b97e99af7d9facb7328a1730341d8ba941ea7a082ceeab13cc8b9e3aaf768dfd029172ab7807b2f542348fe8b1300c4d832034b26820266930ef85179b608b4104615d8d0a4ec08b5baf6d513403cf98c75b2dd6c922f17f979df66356bd03e2bf13a5336ee2695c3e4a300d4c38e8e78d590e40dfc63eadf1c3d72ed9531d7061d81a2a7a74477fc550232c783e6cf8241d2ee9086f5b0608039fcc69361560dfd4540af8852dadb86b1f0c94e3906ca24fb5622bbc9d0fb50cb1e3d14227478b726b5b635ec812ad14f09eee73b3c894ba2ddfbe1e758b7e31ac5ce9efefbcee824fa3a10120b8c160e86ba6f98b43b2f748b38aac245731f6008b1e0291f79a1f5e7be17a8109c78592c33065e1cbc0d408a228895b9d9cf23de5866bfedb3a2dc069b42da6719ea8e42310d188b4c3fed1034602fc36476ace6f79c803cf65caaa5f5c227de4cb8cd619b35c5a06a9726254f6c122eae167961f8579d87e679e19e8738d79e2b59006120fab9607ff055493dbde9ddcf9cb6d7707873724e38558997acf809b84bf6477f690b5a96bfb3c8afda0ee53439984a9f2e1e385c70f59ecf6cc0feaf19bf675196341cccf673ac476a1c701b26bdb86d8b3f3f7b763dc4d4e1e1dd58c7611f973384df4e08034a3a898b899f4a51d54b77e2fb9303a9110203010001a310300e300c0603551d13040530030101ff300d06092a864886f70d01010b0500038202010075b62049593c8e25625f4fcdf5baaf0852f045690d5bc0d4c72080f05258bc6c4d27f35c7ed1b08ba3dc33851df5e8988481323862c2dea07fe7e4f95e447edfa1717b1ce03ac67b9ec52cf7d9a81b2cdff97272f448733876c6fa51d00953cad4629817ea3a2b73a2dd416ac250e2064855dbe2b7c3d964630bfc8c6febdfcf885cd2caa67c3d1931b4de2a8eae12ef26d6220dfba27e4c099c1e161023332fd6a47470c2838526baa6438022f33123077144cc48112414cfc21c355420f539a2d19bbf3a19e6c68f07b539483a1116324b95023081a9f05b0127b28e147aa2c4ff5bfec89d608bdbb4834c1a6d65906f5bf37d2610302a331cb0215c4ae265db7628a12085a61f0973250c4f7e562d66ce6e70d4f1588020e0f1768eb9ecc47f6a566b08cfb56fbcaa993b23549c97fbaa6ea44158e20bdf1a9f57f00218680870aba07a9b7afe0cb62ab66f85be220fb5a97151e5b3fe25e74efb7a6197bb22354f34c4edd57a0d01e9279b97c6b81a2edb2159499e4a05c204efd311233d6f2d03429a730fa5774478eb8d250aac9812c17b7f908923ae6da2d0a27f41c3e0638fbe69db35ec85cbca7ab8877410a8924eec8efdf5cd568fe3ab55c8b2474d22dea98a5fd811f319c5f6e8adc54a721b147d7a1a48b759b33db4baf1ab5f4461719e55cd7bb26330cd813bbb479d529ce4e4a80fc3a2f08e91c42ebb18e4";
    
 
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
