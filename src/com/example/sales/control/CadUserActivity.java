package com.example.sales.control;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import dao.UserDao;
import model.User;
import util.Mensagem;

public class CadUserActivity extends Activity {
	private EditText edtName, edtLogin, edtPassword;
	private UserDao userDao;
	private User user;
	private int idUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cad_user);
		
		userDao = new UserDao(this);
		
		edtName = 		(EditText)	findViewById(R.id.user_edtName);
		edtLogin = 		(EditText)	findViewById(R.id.user_edtLogin);
		edtPassword = 	(EditText)	findViewById(R.id.user_edtPassword);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		userDao.fechar();
		super.onDestroy();
	}
	
	private void register() {
		boolean validate = true;
		
		String name = edtName.getText().toString();
		String login = edtLogin.getText().toString();
		String password = edtPassword.getText().toString();
		
		if (name == null || name.equals("")) {
			validate = false;
			edtName.setError(getString(R.string.campo_origatorio));
		}
		
		if (login == null || login.equals("")) {
			validate = false;
			edtLogin.setError(getString(R.string.campo_origatorio));
		}
		
		if (password == null || password.equals("")) {
			validate = false;
			edtPassword.setError(getString(R.string.campo_origatorio));
		}
		
		if (validate) {
			user = new User();
			user.setName(name);
			user.setLogin(login);
			user.setPassword(password);
		}
		
		//IF Update
		if (idUser > 0) {
			user.set_id(idUser);
		}
		
		long result = userDao.saveUser(user);
		if (result != -1) {
			if (idUser > 0) {
				Mensagem.Msg(this, getString(R.string.message_update));
			}else{
				Mensagem.Msg(this, getString(R.string.message_register));
			}
			finish();
			startActivity(new Intent(this, MainActivity.class));
		}else{
			Mensagem.Msg(this, getString(R.string.message_erro));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		
		if(idUser > 0){
			menu.findItem(R.id.action_menu_exclude).setVisible(true);
		}
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		
		switch (id) {
			case R.id.action_menu_save:
				this.register();
				break;
			case R.id.action_menu_exit:
				finish();
				startActivity(new Intent(this, MainActivity.class));
				break;
		}
		return super.onOptionsItemSelected(item);
	}
}
