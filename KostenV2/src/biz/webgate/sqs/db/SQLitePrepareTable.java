package biz.webgate.sqs.db;

import android.database.sqlite.SQLiteDatabase;
import biz.webgate.definition.Definition;

public class SQLitePrepareTable {

	
	public static void createDBTable(SQLiteDatabase db, Definition curDef) {
		db.execSQL(createTableStr(curDef));
	}
	
	
	public static String createTableStr(Definition curDef) {
		String tableStr= "CREATE TABLE IF NOT EXISTS "+ curDef.getTableName() + " (" + curDef.getPrimaryField() +" "+ curDef.getPrimaryType()+" PRIMARY KEY , " + getAllFields(curDef) +")";
		getAllFields(curDef);		
		return tableStr;
	}
	
	public static String getAllFields(Definition curDef) {
		String tballfield = "";
		String cleantablefield = "";	
		for (int i = 0; i < curDef.getFields().size(); i++) {
			if(!(curDef.getFields().get(i).getSQLiteField()).equals(curDef.getPrimaryField())) {			
				String SQLField = curDef.getFields().get(i).getSQLiteField();
				String SQLType = curDef.getFields().get(i).getSQLiteType();
				tballfield += (SQLField +" "+SQLType+", " );
			}
		}
		cleantablefield = tballfield.substring(0,(tballfield.length()-2));
		return cleantablefield;
		
	}
}
