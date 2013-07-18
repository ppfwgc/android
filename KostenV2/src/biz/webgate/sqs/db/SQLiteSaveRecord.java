package biz.webgate.sqs.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import biz.webgate.definition.Definition;
import biz.webgate.definition.FieldDefinition;


public class SQLiteSaveRecord {
	
	public static SQLiteDatabase db = null;
	
	public static boolean createSQLRecord(Object objc, Definition curDef) {
		
		ContentValues v = new ContentValues();
		SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase((curDef.getDatabaseName()), null);
		ArrayList<FieldDefinition> curFielList = curDef.getFields();
		
		for (int i = 0; i < (curFielList.size()); i++) {				
				v.put(curFielList.get(i).getSQLiteField(),
				curFielList.get(i).getBinder().processJava2SQLite(objc, curFielList.get(i).getLayoutField()));
		}
		
		db.insertOrThrow(curDef.getTableName(), null, v);
		db.close();
		return true;
	}

}

