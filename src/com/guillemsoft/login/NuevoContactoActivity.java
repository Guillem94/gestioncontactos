package com.guillemsoft.login;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NuevoContactoActivity extends Activity {
private EditText nombreEditText;
private EditText apellidosEditText;
private EditText direccionEditText;
private EditText telefonoEditText;
private EditText emailEditText;
private Button guardarButton;
private Button cancelarButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nuevo_contacto);
		nombreEditText= (EditText) findViewById(R.id.NombreEditText);
		apellidosEditText=(EditText) findViewById(R.id.ApellidosEditText);
		direccionEditText=(EditText) findViewById(R.id.DireccionEditText);
		telefonoEditText=(EditText) findViewById(R.id.TelefonoEditText);
		emailEditText=(EditText) findViewById(R.id.EmailEditText);
		guardarButton=(Button) findViewById(R.id.GuardarButton);
		cancelarButton=(Button) findViewById(R.id.CancelarButton);
		cancelarButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				
			}
		});
		
		guardarButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				guardarContacto();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nuevo_contacto, menu);
		return true;
	}
	
	private void guardarContacto(){
		String nombre=nombreEditText.getText().toString();
		String apellidos=apellidosEditText.getText().toString();
		String direccion=direccionEditText.getText().toString();
		String telefono=telefonoEditText.getText().toString();
		String email=emailEditText.getText().toString();
		if(nombre.equals("") || apellidos.equals("") || direccion.equals("") || telefono.equals("") || email.equals("") ){
			Toast toast=Toast.makeText(getApplicationContext(), "No se puede guardar el contacto introduzca todos los datos", Toast.LENGTH_LONG);
			toast.show();
		}else{
			Contacto contacto=new Contacto();
			contacto.setNombre(nombre);
			contacto.setApellidos(apellidos);
			contacto.setDireccion(direccion);
			contacto.setTelefono(telefono);
			contacto.setEmail(email);
			
			ContactosDAO contactosDAO=new ContactosDAO();
			contactosDAO.guardarContacto(contacto);
			finish();
			
			
		}
	}
	

}
