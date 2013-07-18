package biz.webgate.sqs.binder;

import java.lang.reflect.Method;

import android.database.Cursor;

public class StringBinder implements IBinder<String>{

	private static StringBinder m_Binder;

	public static IBinder<String> getInstance() {
		if (m_Binder == null) {
			m_Binder = new StringBinder();
		}
		return m_Binder;
	}
	
	public void processSQLite2Java(Object objc, String SQLiteField, String JavaField, Cursor cursor) {
		String strValue = cursor.getString(cursor.getColumnIndex(SQLiteField));
		try {
			Method mt = objc.getClass().getMethod("set" +JavaField, String.class);			
			mt.invoke(objc, strValue);
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		
	}

	public String processJava2SQLite(Object objc, String JavaField) {
		try {
			Method m = objc.getClass().getMethod("get" + JavaField);
			return (String) m.invoke(objc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
