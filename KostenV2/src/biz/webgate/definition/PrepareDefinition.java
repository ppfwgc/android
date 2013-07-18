package biz.webgate.definition;

import java.lang.reflect.Field;
import java.util.ArrayList;

import biz.webgate.definition.FieldDefinition;
import biz.webgate.sqs.annotation.SQLiteEntity;
import biz.webgate.sqs.annotation.SQLiteStore;

public class PrepareDefinition {

// add all Store & Field Annotation	and Variable Name to a Definition Class
	
	public Definition PrepareCurDefinition(Object objc) {
		
		SQLiteStore sqls = objc.getClass().getAnnotation(SQLiteStore.class);
		ArrayList<FieldDefinition> curFieldDef = new ArrayList<FieldDefinition>();
		Definition curDefinition = new Definition();
		
		curDefinition.setPrimaryKeyCol(sqls.PrimaryKeyCol());
		curDefinition.setTableName(sqls.TableName());
		curDefinition.setDatabaseName(sqls.DatabaseName());
		curDefinition.setPrimaryField(sqls.PrimaryKeyCol());
		curDefinition.setPrimaryType(sqls.PrimaryKeyType());
		
		for (Field fldCurrent : objc.getClass().getDeclaredFields()) 
			if(fldCurrent.isAnnotationPresent(SQLiteEntity.class)) {			
				FieldDefinition curDef = new FieldDefinition(buildCleanFieldName(sqls, fldCurrent.getName()), 
						fldCurrent.getAnnotation(SQLiteEntity.class).FieldName(), 
						fldCurrent.getAnnotation(SQLiteEntity.class).FieldType(),  
						FieldDefinition.getBinder(fldCurrent.getType()));
				curFieldDef.add(curDef);
				curDef = null;
			}
		curDefinition.setFields(curFieldDef);
		return curDefinition;
	}
		
		
		private String buildCleanFieldName(SQLiteStore sqls, String fldName) {
			String fldNameClean = fldName;
			if (sqls.JavaFieldPrefix().length() > 0) {
				fldNameClean = fldNameClean.substring(sqls.JavaFieldPrefix().length());
			}
			if (sqls.JavaFieldPostFix().length() > 0) {
				fldNameClean = fldNameClean.substring(0, (fldNameClean.length() - sqls.JavaFieldPostFix().length()));
			}
			return fldNameClean;
		}	
}
