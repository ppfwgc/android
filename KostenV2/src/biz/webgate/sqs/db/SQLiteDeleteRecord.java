package biz.webgate.sqs.db;

import android.database.sqlite.SQLiteDatabase;
import biz.webgate.definition.Definition;

public class SQLiteDeleteRecord {
	
	public static boolean DeleteCurRecord(Definition curDef, String ID) {
		SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase((curDef.getDatabaseName()), null);
		db.execSQL("delete from "+curDef.getTableName()+" where " +curDef.getPrimaryKeyCol()+"='"+ID+"'");
		db.close();
		return true;
	}
}

