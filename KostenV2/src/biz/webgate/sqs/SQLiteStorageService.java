package biz.webgate.sqs;



import java.util.ArrayList;
import java.util.Hashtable;

import biz.webgate.definition.Definition;
import biz.webgate.definition.PrepareDefinition;
import biz.webgate.sqs.annotation.SQLiteStore;
import biz.webgate.sqs.db.SQLiteSaveRecord;
import biz.webgate.sqs.db.SQLiteDBTBCheckandCreate;
import biz.webgate.sqs.db.SQLiteDeleteRecord;
import biz.webgate.sqs.db.SQLiteDeleteTable;
import biz.webgate.sqs.db.SQLiteViewAll;

public class SQLiteStorageService{

	private static SQLiteStorageService m_Service;
	private Hashtable<String, Definition> m_Definition = new Hashtable<String, Definition>();
	
	
	private SQLiteStorageService() {
		
	}
	
	public static SQLiteStorageService getInstance() {
		if (m_Service == null) {
			m_Service = new SQLiteStorageService();
		}
		return m_Service;
	}
	
	
	public boolean saveCurrentObject(Object objc) {
		if (objc.getClass().isAnnotationPresent(SQLiteStore.class)) {
			if(!m_Definition.containsKey(objc.getClass().getCanonicalName())) {
				prepareDef(objc);
			}
			Definition curDef = m_Definition.get(objc.getClass().getCanonicalName());
			SQLiteDBTBCheckandCreate.checkandcreate(objc, curDef);
			SQLiteSaveRecord.createSQLRecord(objc, curDef);
			}
		
		return false;
	}

	public ArrayList<?> getAllDocs(Object objc) {
		
		if(!m_Definition.containsKey(objc.getClass().getCanonicalName())) {
			prepareDef(objc);
		}		
		Definition curDef = m_Definition.get(objc.getClass().getCanonicalName());
		SQLiteDBTBCheckandCreate.checkandcreate(objc, curDef);		
		return SQLiteViewAll.getAllDoc(objc, curDef);
	}
	
	public boolean deleteTable(Object objc) {
		if(!m_Definition.containsKey(objc.getClass().getCanonicalName())) {
			prepareDef(objc);
		}		
		Definition curDef = m_Definition.get(objc.getClass().getCanonicalName());
		SQLiteDBTBCheckandCreate.checkandcreate(objc, curDef);
		return SQLiteDeleteTable.deleteTable(curDef);
	}
	
	public void deleteRecord(Object objc, String ID) {
		if(!m_Definition.containsKey(objc.getClass().getCanonicalName())) {
			prepareDef(objc);
		}		
		Definition curDef = m_Definition.get(objc.getClass().getCanonicalName());
		SQLiteDeleteRecord.DeleteCurRecord(curDef, ID);
	}
	
	
	private void prepareDef(Object objc) {
		PrepareDefinition curPreparedDef = new PrepareDefinition();
		m_Definition.put(objc.getClass().getCanonicalName(), curPreparedDef.PrepareCurDefinition(objc));
	}
	
	
		
		
		
		
	
	
	
			
}














