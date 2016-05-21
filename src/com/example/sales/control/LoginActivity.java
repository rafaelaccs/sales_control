package com.example.sales.control;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import dao.UserDao;

public class LoginActivity extends Activity {
	private EditText edtUser, edtPassword;
	private UserDao helper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		edtUser = (EditText)findViewById(R.id.login_edtUser);
		edtPassword = (EditText)findViewById(R.id.login_edtPassword);
		
		helper = new UserDao(this);
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
				startActivity(new Intent(this, MainActivity.class));
				finish();
			};
		}
	}


}
