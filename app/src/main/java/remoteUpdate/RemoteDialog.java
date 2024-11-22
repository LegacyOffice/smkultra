package remoteUpdate;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.demontunnel.project.R;


public class RemoteDialog extends Dialog {
    private Context ctx;
    private FirebaseRemoteConfig remoteConfig;

    public RemoteDialog(Context context, FirebaseRemoteConfig remoteConfig) {
        super(context);
        this.ctx = context;
        this.remoteConfig = remoteConfig;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.remote_dialog);
        setCancelable(false);
        TextView txtTitle = findViewById(R.id.remoteDialogTitle);
        TextView txtBody = findViewById(R.id.remotemessageBody);
        AppCompatButton remoteYes = findViewById(R.id.remoteBtnYes), remoteNo = findViewById(R.id.remoteBtnNo);
        txtTitle.setText(remoteConfig.getString(RemoteUtil.TITLE));
        txtBody.setText(remoteConfig.getString(RemoteUtil.WHATNEW));
        if (remoteConfig.getBoolean(RemoteUtil.ISFORCE)) {
            remoteNo.setVisibility(View.GONE);
            setCancelable(false);
        } else {
            remoteNo.setVisibility(View.VISIBLE);

        }
        remoteNo.setOnClickListener((v) ->
        {
            dismiss();
        });
        remoteYes.setOnClickListener((v) ->
        {
            Uri uri = Uri.parse(remoteConfig.getString(RemoteUtil.UpdateUrl));
            Intent i = new Intent(Intent.ACTION_VIEW, uri);
            ctx.startActivity(i);
        });

    }

    @Override
    public void onBackPressed() {
        if (remoteConfig.getBoolean(RemoteUtil.ISFORCE)) {
            ((Activity) ctx).finish();
        }
    }
}
