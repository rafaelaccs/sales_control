package com.example.sales.control;

import java.util.List;

import adapter.UserAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import dao.UserDao;
import model.User;

public class ListUsersActivity extends Activity {
	
	private ListView list;
	private List<User> userList;
	private UserAdapter userAdapter;
	private UserDao userDAO;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_users);
		
		userDAO 	= new UserDao(this);
		userList 	= userDAO.listUser();
		userAdapter = new UserAdapter(this, userList);
		
		list = (ListView)findViewById(R.id.listUsers);
		list.setAdapter(userAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_users, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
