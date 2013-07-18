package biz.webgate.model;

import biz.webgate.sqs.annotation.SQLiteEntity;
import biz.webgate.sqs.annotation.SQLiteStore;

@SQLiteStore(DatabaseName="//data/data/biz.webgate.screen/kostenverwaltung", TableName="Kosten",PrimaryKeyCol="ID",PrimaryKeyType="TEXT",Version="1")

public class kosten {

	@SQLiteEntity(FieldName="ID", FieldType="TEXT")
	private String m_ID;
	
	@SQLiteEntity(FieldName="Title", FieldType="TEXT")
	private String m_Title;

	@SQLiteEntity(FieldName="Betrag", FieldType="TEXT")
	private String m_Betrag;
	
	@SQLiteEntity(FieldName="Kategorie", FieldType="TEXT")
	private String m_Kategorie;

	@SQLiteEntity(FieldName="Datum", FieldType="TEXT")
	private String m_Datum;
	
	public String getID() {
		return m_ID;
	}

	public void setID(String iD) {
		m_ID = iD;
	}

	public String getTitle() {
		return m_Title;
	}

	public void setTitle(String title) {
		m_Title = title;
	}

	public String getBetrag() {
		return m_Betrag;
	}

	public void setBetrag(String betrag) {
		m_Betrag = betrag;
	}

	public String getKategorie() {
		return m_Kategorie;
	}

	public void setKategorie(String kategorie) {
		m_Kategorie = kategorie;
	}

	public String getDatum() {
		return m_Datum;
	}

	public void setDatum(String datum) {
		m_Datum = datum;
	}
	
}

