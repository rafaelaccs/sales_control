package dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper{
	
	private static final String BANCO_DADOS = "tasks";
	private static final int VERSAO = 1;

	public DataBaseHelper(Context context) {
		super(context, BANCO_DADOS, null, VERSAO);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		//Table Users
		db.execSQL("CREATE TABLE USERS(_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "NAME TEXT NOT NULL, LOGIN TEXT NOT NULL, PASSWORD TEXT NOT NULL)");
		
		//Table Tasks
		db.execSQL("CREATE TABLE TASKS(_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "TASKS TEXT NOT NULL, DATE_CREATE DATETIME DEFAULT CURRENT_TIMESTAMP, "
				+ "DATE_COMPLETED DATETIME)");
		
		//Register a User
		db.execSQL("INSERT INTO USERS(NAME, LOGIN, PASSWORD) VALUES('Admin', 'admin', '123')");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	public static class Users{
		public static final String TABELA = "users";
		public static final String _ID = "_id";
		public static final String NAME = "name";
		public static final String LOGIN = "login";
		public static final String PASSWORD = "password";
		
		public static final String [] COLUNAS = new String [] {
				_ID, NAME, LOGIN, PASSWORD
		};
	}
	
	public static class Tasks{
		public static final String TABELA = "tasks";
		public static final String _ID = "_id";
		public static final String TASK = "task";
		public static final String DATE_CREATE = "date_create";
		public static final String DATE_COMPLETED = "date_completed";
		
		public static final String [] COLUNAS = new String [] {
				_ID, TASK, DATE_CREATE, DATE_COMPLETED
		};
	}

}
