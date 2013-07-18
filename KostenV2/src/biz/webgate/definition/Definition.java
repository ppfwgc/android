package biz.webgate.definition;

import java.util.ArrayList;

public class Definition {

	private String m_DatabaseName;
	private String m_TableName;
	private String m_PrimaryField;
	private String m_PrimaryType;
	private String m_PrimaryKeyCol;
	private ArrayList<FieldDefinition> m_Fields;
	
	public String getDatabaseName() {
		return m_DatabaseName;
	}
	public void setDatabaseName(String databaseName) {
		m_DatabaseName = databaseName;
	}
	public String getTableName() {
		return m_TableName;
	}
	public void setTableName(String tableName) {
		m_TableName = tableName;
	}
	public ArrayList<FieldDefinition> getFields() {
		return m_Fields;
	}
	public void setFields(ArrayList<FieldDefinition> fields) {
		m_Fields = fields;
	}
	public String getPrimaryField() {
		return m_PrimaryField;
	}
	public void setPrimaryField(String primaryField) {
		m_PrimaryField = primaryField;
	}
	public String getPrimaryType() {
		return m_PrimaryType;
	}
	public void setPrimaryType(String primaryType) {
		m_PrimaryType = primaryType;
	}
	public String getPrimaryKeyCol() {
		return m_PrimaryKeyCol;
	}
	public void setPrimaryKeyCol(String primaryKeyCol) {
		m_PrimaryKeyCol = primaryKeyCol;
	}
	
	
	
}
