package com.example.sales.control;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import dao.UserDao;
import util.Mensage;

public class LoginActivity extends Activity {
	private EditText edtUser, edtPassword;
	private UserDao helper;
	private CheckBox ckbConected;
	
	private static final String MANTER_CONECTADO = "manter_conectado";
	private static final String PREFERENCE_NAME = "LoginActivityPreferences";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		edtUser = (EditText)findViewById(R.id.login_edtUser);
		edtPassword = (EditText)findViewById(R.id.login_edtPassword);
		ckbConected = (CheckBox)findViewById(R.id.login_ckbConected);
		
		helper = new UserDao(this);
		
		SharedPreferences preferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
		boolean conectado = preferences.getBoolean(MANTER_CONECTADO, false);
		
		if (conectado) {
			CallMainActivity();
		}
	}
	
	public void login(View view) {
		String user = edtUser.getText().toString();
		String password = edtPassword.getText().toString();
		
		boolean validate = true;
		
		if (user == null || user.equals("")) {
			validate = false;
			edtUser.setError(getString(R.string.login_validateUser));
		}
		
		if (password == null || password.equals("")) {
			validate = false;
			edtPassword.setError(getString(R.string.login_validatePasword));
		}
		
		if (validate) {
			//login
			if(helper.login(user, password)){
				if (ckbConected.isChecked()) {
					SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE);
					SharedPreferences.Editor editor = sharedPreferences.edit();
					
					editor.putBoolean(MANTER_CONECTADO, true);
					editor.commit();
				}
				CallMainActivity();
			}else{
				//Error Mensage
				Mensage.Msg(this, getString(R.string.msg_login_wrong));
			}
		}
	}
	
	private void CallMainActivity() {
		startActivity(new Intent(this, MainActivity.class));
		finish();
	}


}
