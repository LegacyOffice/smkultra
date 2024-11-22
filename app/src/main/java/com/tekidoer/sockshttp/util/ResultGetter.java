package com.tekidoer.sockshttp.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ResultGetter extends AsyncTask<String, String, String> {

	private Context context;
	private OnUpdateListener listener;
	private boolean isOnCreate;
	private boolean isDialog;
	private ProgressDialog progressDialog;
	private String what;
	
	public ResultGetter(Context context, OnUpdateListener listener) {
		this.context = context;
		this.listener = listener;
	}

	public void start(boolean isOnCreate, boolean isDialog, String what) {
		this.isOnCreate = isOnCreate;
		this.isDialog = isDialog;
		this.what = what;
		execute();
	}

	public interface OnUpdateListener {
		void onUpdateListener(String result);
	}

	@Override
	protected String doInBackground(String... strings) {
		try {
			StringBuilder sb = new StringBuilder();
			URL url = new URL(what);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String response;

			while ((response = br.readLine()) != null) {
				sb.append(response);
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "Error on getting data: " + e.getMessage();
		}
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		if (!isOnCreate) {
		    if (isDialog) {
    			progressDialog = new ProgressDialog(context);
    			progressDialog.setMessage("Please wait while loading");
    			progressDialog.setCancelable(true);
    			progressDialog.show();
			}
		}
	}

	@Override
	protected void onPostExecute(String s) {
		super.onPostExecute(s);		
		if (!isOnCreate && isDialog && progressDialog != null) {
			progressDialog.dismiss();
		}
		if (listener != null) {
			listener.onUpdateListener(s);
		}
	}
}