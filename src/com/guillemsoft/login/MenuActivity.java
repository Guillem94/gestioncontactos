package com.guillemsoft.login;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuActivity extends Activity {
private Button nuevoContactoButton;
private Button listarContactosButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		nuevoContactoButton= (Button) findViewById(R.id.NuevoContactoButton);
		listarContactosButton=(Button) findViewById(R.id.ListarContactosButton);
		nuevoContactoButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent (getApplicationContext(),NuevoContactoActivity.class);
				startActivity(intent);
			}
		});
		listarContactosButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent (getApplicationContext(),ListarContactosActivity.class);
				startActivity(intent);
			}
		});
		Contacto contacto=new Contacto();
	    contacto.setNombre("Guillem");		
	    contacto.setApellidos("Carrion");
		contacto.setDireccion("calle colon 5,Valencia");
		contacto.setEmail("prueba@hotmail.es");
	    contacto.setTelefono("966333222");
	    ContactosDAO contactosDAO=new ContactosDAO();
		contactosDAO.guardarContacto(contacto);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

}
