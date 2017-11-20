package in.nikita.busroute.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MethodUtils {
	private static ProgressDialog progressDialog;
	public static Handler handler;
	public static void showToast(Context mContext, String message) {
		Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
	}
	public static void LogMessage(String classname, String message) {
		Log.d(classname, "   " + message);
	}

	public static void SystemOutPrintln(String message) {
		System.out.println("---------------------"+message);
	}
	public static void showProgressDialog(AppCompatActivity activity , String message){
		progressDialog = new ProgressDialog(activity);
		progressDialog.setCancelable(false);
		progressDialog.setCanceledOnTouchOutside(false);
		progressDialog.setMessage(message);
		progressDialog.show();
	}
	public static void cancelProgressData(){
		if(progressDialog != null) {
			progressDialog.dismiss();
		}
	}

	public static void showSnack(boolean isConnected, AppCompatActivity context) {
		String message;
		int color;
		if (!isConnected) {
			message = " Internet connection not available! Please check and try again.";
			color = Color.WHITE;
			Snackbar snackbar = Snackbar
					.make(context.findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);

			View sbView = snackbar.getView();
			TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
			textView.setTextColor(color);
			snackbar.show();
		}
	}
	public static void showSnackAct(boolean isConnected, Activity context, TextView view) {
		String message;
		int color;
		if (!isConnected) {
			message = "Internet connection not available! Please check and try again.";
			color = Color.WHITE;
			view.setText(message);
			view.setTextColor(color);
			view.setVisibility(View.VISIBLE);
		}
	}
	public static void showSnack(boolean isConnected , Activity activity) {
		String message;
		int color;
		if (!isConnected) {
			message = "Internet connection not available! Please check and try again.";
			color = Color.WHITE;
			Snackbar snackbar = Snackbar.make(activity.findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);

			View sbView = snackbar.getView();
			TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
			textView.setTextColor(color);
			snackbar.show();
		}
	}

	public static void StrMethod(){
		handler = new Handler(){
			public void handleMessage(Message msg) {
			}
		};
	}
}
