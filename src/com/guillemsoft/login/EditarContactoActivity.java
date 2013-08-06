package com.guillemsoft.login;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditarContactoActivity extends Activity {
	public Integer id;
private EditText nombreEditText;
private EditText apellidosEditText;
private EditText direccionEditText;
private EditText telefonoEditText;
private EditText emailEditText;
private TextView direcciontTextView;
private TextView telefonoTextView;
private TextView emailTextView;
private Button guardarButton;	
private Button eliminarButton;
private Button cancelarButton;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editar_contacto);
		nombreEditText=(EditText) findViewById(R.id.nombreEditText);
		apellidosEditText=(EditText) findViewById(R.id.apellidosEditText);
		direccionEditText=(EditText) findViewById(R.id.direccionEditText);
		telefonoEditText=(EditText) findViewById(R.id.telefonoEditText);
		emailEditText=(EditText) findViewById(R.id.emailEditText);
		direcciontTextView=(TextView) findViewById(R.id.direccionTextView);
		telefonoTextView=(TextView) findViewById(R.id.telefonoTextView);
		emailTextView=(TextView) findViewById(R.id.emailTextView);
		guardarButton=(Button) findViewById(R.id.guardarButton);
		cancelarButton=(Button) findViewById(R.id.cancelarEditarButton);
		eliminarButton=(Button) findViewById(R.id.eliminarButton);
		
		Intent intent=getIntent();
		id=intent.getIntExtra("contactoid", 0);
		ContactosDAOSQLLite contactosDAO=ContactosDAOSQLLite.getInstance(getApplicationContext());
		Contacto contacto=contactosDAO.recuperarContacto(id);
		
		nombreEditText.setText(contacto.getNombre());
		apellidosEditText.setText(contacto.getApellidos());
		direccionEditText.setText(contacto.getDireccion());
		telefonoEditText.setText(contacto.getTelefono());
		emailEditText.setText(contacto.getEmail());
		cancelarButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {				
				finish();
			}
		});
		
		guardarButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				actualizarContacto();
				setResult(4);
				finish();
			}
		});
		eliminarButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				eliminarContacto();
				setResult(4);
				finish();
			}
		});
		telefonoTextView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String telefono= telefonoEditText.getText().toString();
				String url="tel:"+telefono;
				
			Intent intent=new Intent(Intent.ACTION_CALL, Uri.parse(url));
			
			
			startActivity(intent);
			}
		});
		emailTextView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String email=emailEditText.getText().toString();
				Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse("mailto:"+email));
				startActivity(intent);
			
			}
		});
		direcciontTextView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String direccion=direccionEditText.getText().toString();
				String uri="geo:0,0?q="+direccion;
				Intent intent=new Intent(Intent.ACTION_VIEW,Uri.parse(uri));
				startActivity(intent);
			}
		});
	}
	
	
	private void actualizarContacto(){
		String nombre=nombreEditText.getText().toString();
		String apellidos=apellidosEditText.getText().toString();
		String direccion=direccionEditText.getText().toString();
		String telefono=telefonoEditText.getText().toString();
		String email=emailEditText.getText().toString();
		if(nombre.equals("") || apellidos.equals("")){
			Toast toast=Toast.makeText(getApplicationContext(), "Los campos nombre y apellidos no pueden estar vacios", Toast.LENGTH_LONG);
			toast.show();
			
		}
		ContactosDAOSQLLite contactosDAO=ContactosDAOSQLLite.getInstance(getApplicationContext());
		Contacto contacto=new Contacto();
		contacto.setId(id);
		contacto.setNombre(nombre);
		contacto.setApellidos(apellidos);
		contacto.setDireccion(direccion);
		contacto.setTelefono(telefono);
		contacto.setEmail(email);
		contactosDAO.actualizarContacto(contacto);
	}
	private void eliminarContacto(){
    ContactosDAOSQLLite contactosDAO=ContactosDAOSQLLite.getInstance(getApplicationContext());
	contactosDAO.eliminarContacto(id);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.editar_contacto, menu);
		return true;
	}

}
