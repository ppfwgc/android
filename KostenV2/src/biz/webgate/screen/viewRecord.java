package biz.webgate.screen;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import biz.webgate.model.kosten;
import biz.webgate.sqs.SQLiteStorageService;

public class viewRecord extends Activity implements OnClickListener{

	public int ContentMenuPosition; 
	public ArrayList<kosten> kostenliste;
	private ListView listViewKosten;
	private ImageButton btn_add;
	private static final String [] TitleList= new String[] {"Brot", "Mittagessen"};
	private AutoCompleteTextView txtTitle;
	public ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);		
		setContentView(R.layout.viewallrecord);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.window_title);
		registerForContextMenu(findViewById(R.id.listViewKosten));

		listViewKosten = (ListView) findViewById(R.id.listViewKosten);
		btn_add = (ImageButton) findViewById(R.id.btn_add);
		btn_add.setOnClickListener((OnClickListener) this);		
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void onResume() {
		super.onResume();	
		kosten kosten = new kosten();
		kostenliste = (ArrayList<biz.webgate.model.kosten>) SQLiteStorageService.getInstance().getAllDocs(kosten);
		listViewKosten.setAdapter(new ListAdapter(this));
	}
	
	public class ListAdapter extends BaseAdapter {
		LayoutInflater inflater;
		ViewHolder viewHolder;

		public ListAdapter(Context context) {
			// TODO Auto-generated constructor stub
			inflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return kostenliste.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if (convertView == null) {

				convertView = inflater.inflate(R.layout.viewrecord_row, null);
				viewHolder = new ViewHolder();

				viewHolder.txt_Betrag = (TextView) convertView
						.findViewById(R.id.textBetrag);
				viewHolder.txt_Title = (TextView) convertView
						.findViewById(R.id.textTitle);
				viewHolder.txt_Kategorie = (TextView) convertView
						.findViewById(R.id.textKategorie);
				viewHolder.txt_Datum = (TextView) convertView
						.findViewById(R.id.textDatum);				
				convertView.setTag(viewHolder);

			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			String ID = kostenliste.get(0).getID();
			if ( ID !=null) {
			}
			viewHolder.txt_Betrag.setText(kostenliste.get(position).getBetrag().trim());
			viewHolder.txt_Title.setText(kostenliste.get(position).getTitle().trim());
			viewHolder.txt_Kategorie.setText(kostenliste.get(position).getKategorie().trim());
			viewHolder.txt_Datum.setText(kostenliste.get(position).getDatum().trim());
			return convertView;
		}
	}
	
	private class ViewHolder {
		TextView txt_Betrag;
		TextView txt_Title;
		TextView txt_Kategorie;
		TextView txt_Datum;
	}
	
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {		 
	      super.onCreateContextMenu(menu, v, menuInfo);
	      AdapterContextMenuInfo aInfo = (AdapterContextMenuInfo) menuInfo;
	      ContentMenuPosition = (Integer) listViewKosten.getAdapter().getItem(aInfo.position);	      
	      menu.setHeaderTitle("Options");
	      menu.add(1, 1, 1, "Edit");
	      menu.add(1, 2, 2, "Delete");
	}
	//Delete Alert Box
	@Override
	public boolean onContextItemSelected(MenuItem item) {
			
			switch (item.getItemId()) {
				case 1:
					kosten editkosten = new kosten();
					editkosten.setID(kostenliste.get(ContentMenuPosition).getID());
					editkosten.setBetrag(kostenliste.get(ContentMenuPosition).getBetrag());
					editkosten.setDatum(kostenliste.get(ContentMenuPosition).getDatum());
					editkosten.setKategorie(kostenliste.get(ContentMenuPosition).getKategorie());
					editkosten.setTitle(kostenliste.get(ContentMenuPosition).getTitle());
					
					break;
				case 2:
					AlertDialog.Builder alertbox = new AlertDialog.Builder(viewRecord.this);
					alertbox.setCancelable(true);
					alertbox.setMessage("Bisch Sicher ?");
					alertbox.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface arg0, int arg1) {
								kosten delkosten = new kosten();
								SQLiteStorageService.getInstance().deleteRecord(delkosten, kostenliste.get(ContentMenuPosition).getID());								
								viewRecord.this.onResume();
								Toast.makeText(getApplicationContext(),"Record Deleted...",Toast.LENGTH_SHORT).show();
							}
					});					
					alertbox.setNegativeButton("No",new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface arg0, int arg1) {
								}
							});
					alertbox.show();
					
					break;
			}
	return false;
		  
	}
	//Open add Record Dialog Box
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
			case R.id.btn_add:
				final Dialog addDialog= new Dialog(viewRecord.this);
				addDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				addDialog.setContentView(R.layout.addrecord);
				addDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
				addDialog.show();			
				
				final EditText txtBetrag = (EditText) addDialog.findViewById(R.id.txtBetrag);
				final EditText txtKategorie = (EditText) addDialog.findViewById(R.id.txtKategorie);

				txtTitle = (AutoCompleteTextView) addDialog.findViewById(R.id.txtTitle);
				adapter = new ArrayAdapter<String>(this, R.layout.autocompletelist, TitleList);
				txtTitle.setAdapter(adapter);
								
				
				Button dialogButtonCancel = (Button) addDialog.findViewById(R.id.btn_cancel);
				dialogButtonCancel.setOnClickListener(new OnClickListener() {	
					@Override
					public void onClick(View arg0) {
						addDialog.dismiss();
						
					}
				});
		    
				Button dialogButtonSave = (Button) addDialog.findViewById(R.id.btn_save);
				dialogButtonSave.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg1) {
						

						if (txtTitle.getText().toString().equals("")
								|| txtBetrag.getText().toString().equals("")
								|| txtKategorie.getText().toString().equals("")) {								
							Toast.makeText(getApplicationContext(), "Please add values..",
									Toast.LENGTH_LONG).show();
						} 
						else {		
							kosten curKN = new kosten();
							curKN.setTitle((txtTitle.getText().toString()));
							curKN.setBetrag(txtBetrag.getText().toString()+ " Sfr");
							curKN.setKategorie(txtKategorie.getText().toString());
							curKN.setID(UUID.randomUUID().toString());
							curKN.setDatum(curDate());
							SQLiteStorageService.getInstance().saveCurrentObject(curKN);							
							addDialog.dismiss();
							viewRecord.this.onResume();
							Toast.makeText(getApplicationContext(), "Record Added successfully.",
									Toast.LENGTH_LONG).show();
						}
					}
				});
			
				
			}
	}
	//Set current Date on new Record	
	@SuppressLint("SimpleDateFormat")
	public static String curDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		   Calendar curC = Calendar.getInstance();
		    return (dateFormat.format(curC.getTime()));
	}



}



