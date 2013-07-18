package biz.webgate.sqs.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import biz.webgate.definition.Definition;

public class SQLiteDBTBCheckandCreate {

	public static SQLiteDatabase db = null;
	
	public static void checkandcreate(Object objc, Definition curDef) {
		db = SQLiteDatabase.openOrCreateDatabase((curDef.getDatabaseName()), null);
		if(!checkifTBexist(curDef.getTableName())) {
			SQLitePrepareTable.createDBTable(db, curDef);
		}
		db.close();
	}

	public static boolean checkifTBexist(String TableName) {		
				
				@SuppressWarnings("unused")
				Cursor rs = null;
				try {
					rs = db.rawQuery("SELECT * FROM " + TableName + " WHERE 1=0", null);
					return true;
				} catch (Exception e2) {					
					return false;
				}
	}
}
