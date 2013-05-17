package com.protodx.masterdex;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLengine extends SQLiteOpenHelper{
private static  String DB_PATH = "/data/data/com.protodx.masterdex/databases/";
private static  String DB_NAME = "nationaldex.sql";
private SQLiteDatabase database;
private final Context context;


public static final String Pokemon = "pokemon";
public static final int DBVersion = 3;
public static final String COLUMN_ID = "_id";
public static final String COLUMN_CONTENT = "Content";
public static final int CURRENT_VERSION = 2;

public SQLengine(Context context){
	super(context, DB_NAME,null,1);
	this.context = context;
}

public boolean createDataBase()throws IOException{
	boolean dbExist = checkDatabase();
	
	if(dbExist){
		//DO NOTHING - database already exist
		return false;
	}else{
		this.getReadableDatabase();
		try{
			copyDatabase();
		}catch(IOException e){throw new Error("Error copying database!");}
		return true;
	}
}

private boolean checkDatabase(){
	SQLiteDatabase database = null;
	try{
		String path = DB_PATH + DB_NAME;
		database = SQLiteDatabase.openDatabase(path, null, 0);
		if(!checkVersion()){
			return false;
		}
	}catch(SQLiteException e){/*Database does't exist yet*/}
	
	if(database != null){
		database.close();
	}
	
	//return false;
	return database != null;
}

private boolean checkVersion(){
	PokemonDAO dao = new PokemonDAO(context);
	int mVersion = 0;
	try {dao.open();
		mVersion = dao.getDBVersion();
	} catch (Exception e) {
		return false;
	}dao.close();
	if(mVersion == CURRENT_VERSION){
		return true;
	}else{
		return false;
	}
}

private void copyDatabase()throws IOException{
	InputStream IS = context.getAssets().open(DB_NAME);
	String path = DB_PATH + DB_NAME;
	OutputStream OS = new FileOutputStream(path);
	
	byte[] buffer = new byte[1024];
	int length;
	while((length = IS.read(buffer))>0){
		OS.write(buffer,0,length);
		System.out.write(buffer);
	}
	OS.flush();
	OS.close();
	IS.close();
}

public SQLiteDatabase openDatabase()throws SQLException{
	String path = DB_PATH + DB_NAME;
	database = SQLiteDatabase.openDatabase(path, null, 0);
	return database;
}

@Override
public synchronized void close(){
	if(database != null)
		database.close();
	super.close();
}

@Override
public void onCreate(SQLiteDatabase arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	// TODO Auto-generated method stub
	
}

}