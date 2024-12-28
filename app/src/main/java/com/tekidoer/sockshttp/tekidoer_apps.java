package com.tekidoer.sockshttp;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.appopen.AppOpenAd;
import com.google.android.gms.ads.appopen.AppOpenAd.AppOpenAdLoadCallback;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.tekidoer.ultrasshservice.SocksHttpCore;
import com.tekidoer.ultrasshservice.config.Settings;
import com.tekidoer.ultrasshservice.util.TekidProtect;
import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexFile;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Enumeration;
import io.michaelrocks.paranoid.Obfuscate;

@Obfuscate
public class tekidoer_apps extends Application implements ActivityLifecycleCallbacks, LifecycleObserver {

	public static final String TAG = tekidoer_apps.class.getSimpleName();
	public static final String PREFS_GERAL = "tekidoer_devs";	
	private AppOpenAdManager appOpenAdManager;
	private Activity currentActivity;
	private static tekidoer_apps mApp;

    public static Settings mConfig;
    public static SharedPreferences sp;
    private Context ctx;
	
	@Override
	public void onCreate() {
		super.onCreate();
		mApp = this;
        mConfig = new Settings(this);
        ctx = this;
        sp = mConfig.getPrefsPrivate();
		FirebaseAnalytics.getInstance(this);
        MobileAds.initialize(this);
		SocksHttpCore.init(this);
		this.registerActivityLifecycleCallbacks(this);
		ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
		appOpenAdManager = new AppOpenAdManager();
		TekidProtect.init(this);
		setModoNoturno(this);
        
        try {
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
                            if (ctx instanceof Activity) {
                                ((Activity) ctx).finishAndRemoveTask();
                                System.exit(0);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {}
	}
	
	@OnLifecycleEvent(Event.ON_START)
	protected void onMoveToForeground() {
		// Show the ad (if available) when the app moves to foreground.
		appOpenAdManager.showAdIfAvailable(currentActivity);
	}

	/** ActivityLifecycleCallback methods. */
	@Override
	public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {}

	@Override
	public void onActivityStarted(@NonNull Activity activity) {
		// An ad activity is started when an ad is showing, which could be AdActivity class from Google
		// SDK or another activity class implemented by a third party mediation partner. Updating the
		// currentActivity only when an ad is not showing will ensure it is not an ad activity, but the
		// one that shows the ad.
		if (!appOpenAdManager.isShowingAd) {
			currentActivity = activity;
		}
	}

	@Override
	public void onActivityResumed(@NonNull Activity activity) {}

	@Override
	public void onActivityPaused(@NonNull Activity activity) {}

	@Override
	public void onActivityStopped(@NonNull Activity activity) {}

	@Override
	public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {}

	@Override
	public void onActivityDestroyed(@NonNull Activity activity) {}

	/**
	 * Shows an app open ad.
	 *
	 * @param activity the activity that shows the app open ad
	 * @param onShowAdCompleteListener the listener to be notified when an app open ad is complete
	 */
	public void showAdIfAvailable(
		@NonNull Activity activity,
		@NonNull OnShowAdCompleteListener onShowAdCompleteListener) {
		// We wrap the showAdIfAvailable to enforce that other classes only interact with MyApplication
		// class.
		appOpenAdManager.showAdIfAvailable(activity, onShowAdCompleteListener);
	}

	/**
	 * Interface definition for a callback to be invoked when an app open ad is complete
	 * (i.e. dismissed or fails to show).
	 */
	public interface OnShowAdCompleteListener {
		void onShowAdComplete();
	}

	/** Inner class that loads and shows app open ads. */
	private class AppOpenAdManager {

		private static final String LOG_TAG = "AppOpenAdManager";
		private static final String AD_UNIT_ID = "ca-app-pub-8221353512185856/9556117976";

		private AppOpenAd appOpenAd = null;
		private boolean isLoadingAd = false;
		private boolean isShowingAd = false;

		/** Keep track of the time an app open ad is loaded to ensure you don't show an expired ad. */
		private long loadTime = 0;

		/** Constructor. */
		public AppOpenAdManager() {}

		/**
		 * Load an ad.
		 *
		 * @param context the context of the activity that loads the ad
		 */
		private void loadAd(Context context) {
			// Do not load ad if there is an unused ad or one is already loading.
			if (isLoadingAd || isAdAvailable()) {
				return;
			}

			isLoadingAd = true;
			AdManagerAdRequest request = new AdManagerAdRequest.Builder().build();
			AppOpenAd.load(
				context,
				AD_UNIT_ID,
				request,
				AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT,
				new AppOpenAdLoadCallback() {
					/**
					 * Called when an app open ad has loaded.
					 *
					 * @param ad the loaded app open ad.
					 */
					@Override
					public void onAdLoaded(AppOpenAd ad) {
						appOpenAd = ad;
						isLoadingAd = false;
						loadTime = (new Date()).getTime();

						//Log.d(LOG_TAG, "onAdLoaded.");
						//Toast.makeText(MyApplication.this, "onAdLoaded", Toast.LENGTH_SHORT).show();
					}

					/**
					 * Called when an app open ad has failed to load.
					 *
					 * @param loadAdError the error.
					 */
					@Override
					public void onAdFailedToLoad(LoadAdError loadAdError) {
						isLoadingAd = false;
						//Log.d(LOG_TAG, "onAdFailedToLoad: " + loadAdError.getMessage());
						//Toast.makeText(MyApplication.this, "onAdFailedToLoad", Toast.LENGTH_SHORT).show();
					}
				});
		}

		/** Check if ad was loaded more than n hours ago. */
		private boolean wasLoadTimeLessThanNHoursAgo(long numHours) {
			long dateDifference = (new Date()).getTime() - loadTime;
			long numMilliSecondsPerHour = 3600000;
			return (dateDifference < (numMilliSecondsPerHour * numHours));
		}

		/** Check if ad exists and can be shown. */
		private boolean isAdAvailable() {
			// Ad references in the app open beta will time out after four hours, but this time limit
			// may change in future beta versions. For details, see:
			// https://support.google.com/admob/answer/9341964?hl=en
			return appOpenAd != null && wasLoadTimeLessThanNHoursAgo(4);
		}

		/**
		 * Show the ad if one isn't already showing.
		 *
		 * @param activity the activity that shows the app open ad
		 */
		private void showAdIfAvailable(@NonNull final Activity activity) {
			showAdIfAvailable(
				activity,
				new OnShowAdCompleteListener() {
					@Override
					public void onShowAdComplete() {
						// Empty because the user will go back to the activity that shows the ad.
					}
				});
		}

		/**
		 * Show the ad if one isn't already showing.
		 *
		 * @param activity the activity that shows the app open ad
		 * @param onShowAdCompleteListener the listener to be notified when an app open ad is complete
		 */
		private void showAdIfAvailable(
			@NonNull final Activity activity,
			@NonNull OnShowAdCompleteListener onShowAdCompleteListener) {
			// If the app open ad is already showing, do not show the ad again.
			if (isShowingAd) {
				//Log.d(LOG_TAG, "The app open ad is already showing.");
				return;
			}

			// If the app open ad is not available yet, invoke the callback then load the ad.
			if (!isAdAvailable()) {
				//Log.d(LOG_TAG, "The app open ad is not ready yet.");
				onShowAdCompleteListener.onShowAdComplete();
				loadAd(activity);
				return;
			}

			Log.d(LOG_TAG, "Will show ad.");

			appOpenAd.setFullScreenContentCallback(
				new FullScreenContentCallback() {
					/** Called when full screen content is dismissed. */
					@Override
					public void onAdDismissedFullScreenContent() {
						// Set the reference to null so isAdAvailable() returns false.
						appOpenAd = null;
						isShowingAd = false;

						//Log.d(LOG_TAG, "onAdDismissedFullScreenContent.");
						//Toast.makeText(activity, "onAdDismissedFullScreenContent", Toast.LENGTH_SHORT).show();

						onShowAdCompleteListener.onShowAdComplete();
						loadAd(activity);
					}

					/** Called when fullscreen content failed to show. */
					@Override
					public void onAdFailedToShowFullScreenContent(AdError adError) {
						appOpenAd = null;
						isShowingAd = false;

						//Log.d(LOG_TAG, "onAdFailedToShowFullScreenContent: " + adError.getMessage());
						//Toast.makeText(activity, "onAdFailedToShowFullScreenContent", Toast.LENGTH_SHORT).show();

						onShowAdCompleteListener.onShowAdComplete();
						loadAd(activity);
					}

					/** Called when fullscreen content is shown. */
					@Override
					public void onAdShowedFullScreenContent() {
						//Log.d(LOG_TAG, "onAdShowedFullScreenContent.");
						//Toast.makeText(activity, "onAdShowedFullScreenContent", Toast.LENGTH_SHORT).show();
					}
				});

			isShowingAd = true;
			appOpenAd.show(activity);
		}
	}
	
	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		//LocaleHelper.setLocale(this);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		//LocaleHelper.setLocale(this);
	}
	
	private void setModoNoturno(Context context) {
		boolean is = new Settings(context)
			.getModoNoturno().equals("on");

		int night_mode = is ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO;
		AppCompatDelegate.setDefaultNightMode(night_mode);
	}
	
	public static tekidoer_apps getApp() {
		return mApp;
	}
    
    public static SharedPreferences getSharedPreferences()  {
        return sp;
    }
    
}
