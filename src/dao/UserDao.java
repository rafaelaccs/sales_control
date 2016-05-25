package dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import model.User;

public class UserDao {
	private DataBaseHelper databaseHelper;
	private SQLiteDatabase database;

	public UserDao(Context context) {
		databaseHelper = new DataBaseHelper(context);
	}
	
	private SQLiteDatabase getDatabase() {
		if (database == null ){
			database = databaseHelper.getWritableDatabase();
		}
		return database;
	}
	
	private User createUser(Cursor cursor) {
		User model = new User(
				cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Users._ID)),
				cursor.getString(cursor.getColumnIndex(DataBaseHelper.Users.NAME)),
				cursor.getString(cursor.getColumnIndex(DataBaseHelper.Users.LOGIN)),
				cursor.getString(cursor.getColumnIndex(DataBaseHelper.Users.PASSWORD)),
				cursor.getString(cursor.getColumnIndex(DataBaseHelper.Users.CREATED_AT))
				);
		return model;
	}
	
	public List<User> listUser() {
		Cursor cursor = getDatabase().query(DataBaseHelper.Users.TABELA,
						DataBaseHelper.Users.COLUNAS, null, null, null, null, null);
		
		List<User> users = new ArrayList<User>();
		
		while (cursor.moveToNext()) {
			User model = createUser(cursor);
			users.add(model);
		}
		cursor.close();
		return users;
	}
	
	public long saveUser(User user) {
		ContentValues values = new ContentValues();
		values.put(DataBaseHelper.Users.NAME, user.getName());
		values.put(DataBaseHelper.Users.LOGIN, user.getLogin());
		values.put(DataBaseHelper.Users.PASSWORD, user.getPassword());
		values.put(DataBaseHelper.Users.CREATED_AT, user.getCreated_at());
		
		if(user.get_id() != null){
			return getDatabase().update(DataBaseHelper.Users.TABELA, values, 
					"_id = ?", new String[]{user.get_id().toString()});
		}
		return getDatabase().insert(DataBaseHelper.Users.TABELA, null, values);
	}
	
	public boolean removeUser(int id) {
		return getDatabase().delete(DataBaseHelper.Users.TABELA, "_id = ?", 
				new String[]{Integer.toString(id)}) > 0;
	}
	
	public User searchUserById(int id) {
		Cursor cursor = getDatabase().query(DataBaseHelper.Users.TABELA,
				DataBaseHelper.Users.COLUNAS, "_id = ?", 
				new String[]{Integer.toString(id)}, null, null, null);
		
		if (cursor.moveToNext()) {
			User model = createUser(cursor);
			cursor.close();
			return model;
		}
		return null;
	}
	
	public boolean login(String user, String password) {
		Cursor cursor = getDatabase().query(DataBaseHelper.Users.TABELA, 
				null, "LOGIN= ? AND PASSWORD = ?", new String[]{user, password}, 
				null, null, null);
		
		if (cursor.moveToFirst()) {
			return true;
		}
		return false;
	}
	public void fechar() {
		databaseHelper.close();
		database = null;
	}
}
