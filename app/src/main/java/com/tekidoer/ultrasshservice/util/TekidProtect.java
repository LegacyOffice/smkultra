package com.tekidoer.ultrasshservice.util;

import android.app.Activity;
import android.content.Context;
import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexFile;
import java.lang.reflect.Field;
import java.util.Enumeration;
import com.demontunnel.project.*;
import io.michaelrocks.paranoid.Obfuscate;

@Obfuscate
public class TekidProtect {

	private static final String TAG = TekidProtect.class.getSimpleName();
	
	private static final String APP_BASE = "com.demontunnel.project";
	
	// Assinatura da Google Play
	//private static final String APP_SIGNATURE = "XbhYZ4Bz/9F4cWLIDMg0wl/+jl8=\n";

	private static TekidProtect mInstance;

	private Context mContext;
	
	public static void init(Context context) {
		if (mInstance == null) {
			mInstance = new TekidProtect(context);

			// This method will print your certificate signature to the logcat.
			//AndroidTamperingProtectionUtils.getCertificateSignature(context);
		}
	}

	private TekidProtect(Context context) {
		mContext = context;
	}
	
	public void simpleProtect() {
        try {
            if (!APP_BASE.equals(mContext.getPackageName().toLowerCase()) || !mContext.getString(R.string.app_name).toLowerCase().equals("demon vpn")) {
                if (mContext instanceof Activity) {
                    ((Activity) mContext).finishAndRemoveTask();
                    System.exit(0);
                }
            }
            
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            if (contextClassLoader != null && (contextClassLoader instanceof BaseDexClassLoader)) {
                Field pathListField = BaseDexClassLoader.class.getDeclaredField("pathList");
                pathListField.setAccessible(true);

                Object dexPathList = pathListField.get(contextClassLoader);
                Field dexElementsField = dexPathList.getClass().getDeclaredField("dexElements");
                dexElementsField.setAccessible(true);

                Object[] dexElements = (Object[]) dexElementsField.get(dexPathList);
                for (Object dexElement : dexElements) {
                    Field dexFileField = dexElement.getClass().getDeclaredField("dexFile");
                    dexFileField.setAccessible(true);
                    DexFile dexFile = (DexFile) dexFileField.get(dexElement);
                    Enumeration<String> entries = dexFile.entries();
                    while (entries.hasMoreElements()) {
                        String entry = entries.nextElement();
                        if (entry.startsWith("mt") || entry.startsWith("np")) {
                            if (mContext instanceof Activity) {
                                ((Activity) mContext).finishAndRemoveTask();
                                System.exit(0);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {}
	}

	public static void CharlieProtect() {
		if (mInstance == null) return;
			
		mInstance.simpleProtect();
		
		// ative apenas ao enviar pra PlayStore
		//mInstance.tamperProtect();
	}
}
