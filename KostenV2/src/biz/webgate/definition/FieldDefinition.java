package biz.webgate.definition;

import biz.webgate.sqs.binder.*;


public class FieldDefinition {

	public static IBinder<String> STRING = StringBinder.getInstance(); 
	
	
	private String m_LayoutField;
	private String m_SQLiteField;
	private String m_SQLiteType;
	private IBinder<?> m_Binder;
	
	public FieldDefinition(String layoutField, String sQLiteField, String sQLiteType, IBinder<?> curBinder) {
		m_LayoutField = layoutField; 
		m_SQLiteField = sQLiteField;
		m_SQLiteType = sQLiteType;
		m_Binder = curBinder;
	}


	public String getLayoutField() {
		return m_LayoutField;
	}


	public void setLayoutField(String layoutField) {
		m_LayoutField = layoutField;
	}


	public String getSQLiteField() {
		return m_SQLiteField;
	}


	public void setSQLiteField(String sQLiteField) {
		m_SQLiteField = sQLiteField;
	}


	public String getSQLiteType() {
		return m_SQLiteType;
	}


	public void setSQLiteType(String sQLiteType) {
		m_SQLiteType = sQLiteType;
	}

	

	public IBinder<?> getBinder() {
		return m_Binder;
	}


	public void setBinder(IBinder<?> binder) {
		m_Binder = binder;
	}


	public static IBinder<?> getBinder(Class<?> clcurrent) {
		if (clcurrent.equals(String.class)) {
			return STRING;
		}
		
		return null;
	}



	
}
