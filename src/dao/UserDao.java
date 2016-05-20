package dao;

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
				cursor.getString(cursor.getColumnIndex(DataBaseHelper.Users.PASSWORD	))
				);
		return model;
	}
	
	public void fechar() {
		databaseHelper.close();
		database = null;
	}
}
