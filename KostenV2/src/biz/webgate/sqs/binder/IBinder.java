package biz.webgate.sqs.binder;

import android.database.Cursor;


	public interface IBinder<T> {
		
		public void processSQLite2Java(Object objc, String SQLiteField, String JavaField, Cursor cursor);
		public String processJava2SQLite(Object objc, String JavaField);
		
	}

