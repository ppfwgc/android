package biz.webgate.sqs.db;

import android.database.sqlite.SQLiteDatabase;
import biz.webgate.definition.Definition;

public class SQLiteDeleteTable {

	
	public static boolean deleteTable(Definition curDef) {
		try {
			
			SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase((curDef.getDatabaseName()), null);
			db.execSQL("DROP TABLE IF EXISTS "+ curDef.getTableName());
			db.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return false;
	}
}
