package biz.webgate.sqs.db;

import java.lang.reflect.Method;
import java.util.ArrayList;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import biz.webgate.definition.Definition;
import biz.webgate.definition.FieldDefinition;

public class SQLiteViewAll {

	public static ArrayList<Object> getAllDoc(Object objc, Definition curDef) {
		
		ArrayList<FieldDefinition> curFielList = curDef.getFields();
		
		ArrayList<Object> curList = new ArrayList<Object>();
		curList.clear();
		
		SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase((curDef.getDatabaseName()), null);
		Cursor cursor = db.rawQuery("select * from "+ curDef.getTableName(), null);
	
		if (cursor.getCount() != 0) {
			if (cursor.moveToFirst()) {
				do {
					
					try {
					Object objectcur = objc.getClass().newInstance();
					for (int i = 0; i < (curFielList.size()); i++) {
								curFielList.get(i).getBinder().processSQLite2Java(objectcur, curFielList.get(i).getLayoutField(), curFielList.get(i).getSQLiteField(), cursor);
					}
					@SuppressWarnings("unused")
					Method m = objc.getClass().getMethod("getBetrag");
					curList.add(objectcur);
					}
					catch (Exception e) {						
					}


				} while (cursor.moveToNext());
			}
		}
		cursor.close();
		db.close();
		return curList;
	}

}
