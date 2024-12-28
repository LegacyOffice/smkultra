package com.tekidoer.sockshttp;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Process;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    private static final String LINE_SEPARATOR = "\n";
    private final Activity myContext;
    public static final String CONTACT_MESSAGE = "Please Contact Jay Flores";

    public ExceptionHandler(Activity activity) {
        this.myContext = activity;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable th) {
        Writer stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("************ APPLICATION ERROR ************\n\n");
        stringBuilder.append(stringWriter.toString());
        stringBuilder.append("\n************ DEVICE INFORMATION ***********\n");
        stringBuilder.append("Brand: ").append(Build.BRAND).append("\n");
        stringBuilder.append("Device: ").append(Build.DEVICE).append("\n");
        stringBuilder.append("Model: ").append(Build.MODEL).append("\n");
        stringBuilder.append("Id: ").append(Build.ID).append("\n");
        stringBuilder.append("Product: ").append(Build.PRODUCT).append("\n");
        stringBuilder.append("\n************ FIRMWARE ************\n");
        stringBuilder.append("SDK: ").append(Build.VERSION.SDK_INT).append("\n");
        stringBuilder.append("Release: ").append(Build.VERSION.RELEASE).append("\n");
        stringBuilder.append("Incremental: ").append(Build.VERSION.INCREMENTAL).append("\n");
        stringBuilder.append(CONTACT_MESSAGE).append("\n");

        // Copy error details to clipboard
        copyToClipboard(stringBuilder.toString());

        try {
            Intent intent = new Intent(this.myContext, errors.class);
            intent.putExtra("error", stringBuilder.toString());
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            this.myContext.startActivity(intent);
        } catch (Exception e) {
            // Handle any exceptions while launching the error activity
            e.printStackTrace();
        } finally {
            // Ensure the app is terminated
            Process.killProcess(Process.myPid());
            System.exit(10);
        }
    }

    private void copyToClipboard(String errorDetails) {
        try {
            ClipboardManager clipboard = (ClipboardManager) myContext.getSystemService(Context.CLIPBOARD_SERVICE);
            if (clipboard != null) {
                ClipData clip = ClipData.newPlainText("Error Details", errorDetails);
                clipboard.setPrimaryClip(clip);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
