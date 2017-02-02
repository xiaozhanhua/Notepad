package com.example.Control;

import java.util.ArrayList;

import com.example.Bean.Notepad;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
	/*
	 * 用来对数据库进行操作
	 * 数据的增，删，改，查，都在这里实现
	 */
public class MyDB_operate {
	Context context;
	MyDB mydb;
	SQLiteDatabase myDatabase;
	/*
	 * 别的类实例化这个类的同时，创建数据库
	 */
	public MyDB_operate(Context context){
		this.context=context;
		mydb=new MyDB(context);
	}
	/*
	 * 得到填充ListView用的array数据，从数据库里查找后解析。MainActivity界面调用
	 */
	public ArrayList<Notepad> getArray(){
		ArrayList<Notepad> array=new ArrayList<Notepad>();
		ArrayList<Notepad> array1=new ArrayList<Notepad>();
		myDatabase=mydb.getWritableDatabase();
		Cursor cursor=myDatabase.rawQuery("select ids,title,times from mybook" , null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			int id=cursor.getInt(cursor.getColumnIndex("ids"));
			String title=cursor.getString(cursor.getColumnIndex("title"));
			String times=cursor.getString(cursor.getColumnIndex("times"));
			Notepad note=new Notepad(id, title, times);
			array.add(note);
			cursor.moveToNext();
		}
		myDatabase.close();
//		对数据库中存储的记事进行排序
		for (int i = array.size(); i >0; i--) {
			array1.add(array.get(i-1));
		}
		return array1;		
	}
	
	/*
	 * 返回可能要修改的数据，SecondActivity界面调用。
	 */
	public Notepad getTiandCon(int id){
		myDatabase=mydb.getWritableDatabase();
		Cursor cursor=myDatabase.rawQuery("select title,content from mybook where ids='"+id+"'" , null);
		cursor.moveToFirst();
		String title=cursor.getString(cursor.getColumnIndex("title"));
		String content=cursor.getString(cursor.getColumnIndex("content"));
		Notepad cun=new Notepad(title,content);
		myDatabase.close();
		return cun;
	}
	/*
	 * SecondActivity界面调用，用来修改记事
	 */
	public void toUpdate(Notepad note){
		myDatabase=mydb.getWritableDatabase();
		myDatabase.execSQL("update mybook set title='"+ note.getTitle()+"',times='"+note.getTimes()+"',content='"+note.getContent() +"' where ids='"+ note.getId()+"'");
		myDatabase.close();
	}
	/*
	 *SecondActivity界面调用，用来增加新的记事
	 */
	public void toInsert(Notepad cun){
		myDatabase=mydb.getWritableDatabase();
		myDatabase.execSQL("insert into mybook(title,content,times)values('"+ cun.getTitle()+"','"+cun.getContent()+"','"+cun.getTimes()+"')");
		myDatabase.close();
	}
	/*
	 * SecondActivity界面调用，长按点击后选择删除记事
	 */
	public void toDelete(int ids){
		myDatabase=mydb.getWritableDatabase();
		myDatabase.execSQL("delete  from mybook where ids="+ids+"");
		myDatabase.close();
	}
}
