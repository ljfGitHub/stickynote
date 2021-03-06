package com.lijinfeng.stickynote.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.lijinfeng.stickynote.model.Note;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class NoteDao {
	
	private DBOpenHelper helper;
	private SQLiteDatabase db;
	
	public NoteDao (Context context) {
		helper = new DBOpenHelper(context);
	}
	
	public void addNote(Note note) {
		db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("title", note.getTitle());
		values.put("content", note.getContent());
		values.put("time", note.getTime());
		db.insert("t_note", null, values);
	}
	
	public void updateNote (Note note) {
		db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("title", note.getTitle());
		values.put("content", note.getContent());
		db.update("t_note", values, "id = ?", new String [] {String.valueOf(note.getId())});
	}
	
	public Note findNote (int id) {
		db = helper.getWritableDatabase();
		Cursor cursor = db.query("t_note",new String[]{"id","title","content","time"},"id = ?", new String [] {String.valueOf(id)},null,null,null);
		if(cursor.moveToNext()) {
			return new Note (cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
		}
		return null;
	}
	
	 public ArrayList<HashMap<String, Object>> getAllData() {  
		 db = helper.getWritableDatabase();
         Cursor cursor = db.rawQuery("select * from " + "t_note " +"order by id desc", null);  
         int columnsSize = cursor.getColumnCount();  
         ArrayList<HashMap<String, Object>> listData = new ArrayList<HashMap<String, Object>>();  
         // 获取表的内容  
         while (cursor.moveToNext()) {  
             HashMap<String, Object> map = new HashMap<String, Object>();  
             for (int i = 0; i < columnsSize; i++) {  
                 map.put("id", cursor.getString(0));  
                 map.put("title", cursor.getString(1));  
                 map.put("content", cursor.getString(2));  
                 map.put("time", cursor.getString(3));  
             }  
             listData.add(map);  
         }  
         return listData;
     }  
	 
	 /**
	  * 功能：根据时间段查询数据
	  * @param fromTime
	  * @return
	  */
	 public ArrayList<HashMap<String, Object>> getDataByCondition(String fromTime) {  
		 
		 SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");       
		 Date curDate = new Date(System.currentTimeMillis());//获取当前时间       
		 String toTime = formatter.format(curDate); 
		 
		 db = helper.getWritableDatabase();
         Cursor cursor = db.rawQuery("select * from t_note where time between '"+fromTime+"' and '"+toTime+"' order by id desc", null);  
         int columnsSize = cursor.getColumnCount();  
         ArrayList<HashMap<String, Object>> listData = new ArrayList<HashMap<String, Object>>();  
         // 获取表的内容  
         while (cursor.moveToNext()) {  
             HashMap<String, Object> map = new HashMap<String, Object>();  
             for (int i = 0; i < columnsSize; i++) {  
                 map.put("id", cursor.getString(0));  
                 map.put("title", cursor.getString(1));  
                 map.put("content", cursor.getString(2));  
                 map.put("time", cursor.getString(3));  
             }  
             listData.add(map);  
         }  
         return listData;
     }  
	 
	// 删除一条数据  
     public boolean delete(int id) {  
    	 db = helper.getWritableDatabase();
         String whereClause = "id=?";  
         String[] whereArgs = new String[] { String.valueOf(id) };  
         try {  
             db.delete("t_note", "id=?", new String[] { String.valueOf(id) });  
         } catch (SQLException e) {  
             return false;  
         }  
         return true;  
     }  
}
