package dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import model.Task;
import model.Task;

public class TaskDao {
	private DataBaseHelper databaseHelper;
	private SQLiteDatabase database;

	public TaskDao(Context context) {
		databaseHelper = new DataBaseHelper(context);
	}
	
	private SQLiteDatabase getDatabase() {
		if (database == null ){
			database = databaseHelper.getWritableDatabase();
		}
		return database;
	}
	
	private Task createTask(Cursor cursor) {
		Task model = new Task(
				cursor.getInt(cursor.getColumnIndex(DataBaseHelper.Tasks._ID)),
				cursor.getString(cursor.getColumnIndex(DataBaseHelper.Tasks.TASK)),
				cursor.getString(cursor.getColumnIndex(DataBaseHelper.Tasks.DATE_CREATE)),
				cursor.getString(cursor.getColumnIndex(DataBaseHelper.Tasks.DATE_COMPLETED))
				);
		return model;
	}
	
	public List<Task> listTask() {
		Cursor cursor = getDatabase().query(DataBaseHelper.Tasks.TABELA,
						DataBaseHelper.Tasks.COLUNAS, null, null, null, null, null);
		
		List<Task> tasks = new ArrayList<Task>();
		
		while (cursor.moveToNext()) {
			Task model = createTask(cursor);
			tasks.add(model);
		}
		cursor.close();
		return tasks;
	}
	
	public long saveTask(Task task) {
		ContentValues values = new ContentValues();
		values.put(DataBaseHelper.Tasks.TASK, task.getTask());
		
		if(task.get_id() != null){
			return getDatabase().update(DataBaseHelper.Tasks.TABELA, values, 
					"_id = ?", new String[]{task.get_id().toString()});
		}
		return getDatabase().insert(DataBaseHelper.Tasks.TABELA, null, values);
	}
	
	public boolean removeTak(int id) {
		return getDatabase().delete(DataBaseHelper.Tasks.TABELA, "_id = ?", 
				new String[]{Integer.toString(id)}) > 0;
	}
	
	public Task searchTaskById(int id) {
		Cursor cursor = getDatabase().query(DataBaseHelper.Tasks.TABELA,
				DataBaseHelper.Tasks.COLUNAS, "_id = ?", 
				new String[]{Integer.toString(id)}, null, null, null);
		
		if (cursor.moveToNext()) {
			Task model = createTask(cursor);
			cursor.close();
			return model;
		}
		return null;
	}
	public void fechar() {
		databaseHelper.close();
		database = null;
	}

}
