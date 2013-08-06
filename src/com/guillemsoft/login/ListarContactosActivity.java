package com.guillemsoft.login;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class ListarContactosActivity extends Activity {
	private EditText filtroEditText;
	private LinearLayout contactosLinearLayout;
	private Button salirButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listar_contactos);
		filtroEditText = (EditText) findViewById(R.id.filtroEditText);
		contactosLinearLayout = (LinearLayout) findViewById(R.id.contactosLinearLayout);
		salirButton = (Button) findViewById(R.id.salirButton);
		salirButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	   refrescarContactos();
	}
	
	public void refrescarContactos(){
		contactosLinearLayout.removeAllViewsInLayout();
		ContactosDAOSQLLite contactosDAO=ContactosDAOSQLLite.getInstance(getApplicationContext());
		List<Contacto> contactos = contactosDAO.recuperarContactos();
		for (int i = 0; i < contactos.size(); i++) {
			final Contacto contacto = contactos.get(i);
			Button contactoButton = new Button(getApplicationContext());
			String nombre = contacto.getNombre();
			contactoButton.setText(nombre);
			contactosLinearLayout.addView(contactoButton);
			contactoButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(getApplicationContext(),
							EditarContactoActivity.class);
					Integer id = contacto.getId();
					intent.putExtra("contactoid", id);
					startActivityForResult(intent, 1);
				}
			});

		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			super.onActivityResult(requestCode, resultCode, data);
			System.out.println("refresco contactos:"+resultCode);
			if(resultCode==4){
				refrescarContactos();			
					
			}
			
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.listar_contactos, menu);
		return true;
	}

}
