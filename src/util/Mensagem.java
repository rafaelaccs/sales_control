package util;

import android.app.Activity;
import android.widget.Toast;

public class Mensagem {
	
	public static void Msg(Activity activity, String mensage) {
		Toast.makeText(activity, mensage, Toast.LENGTH_LONG).show();
	}
}
