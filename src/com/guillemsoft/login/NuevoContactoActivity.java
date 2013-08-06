package com.guillemsoft.login;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class NuevoContactoActivity extends Activity {
private EditText nombreEditText;
private EditText apellidosEditText;
private EditText direccionEditText;
private EditText telefonoEditText;
private EditText emailEditText;
private Button guardarButton;
private Button cancelarButton;
private ImageButton fotoButton;

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
		fotoButton=(ImageButton) findViewById(R.id.fotoButton);
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
		
		fotoButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mostrarSeleccionImagen();
				
				
				
			}
		});
	}

	protected void mostrarSeleccionImagen() {
		Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Seleccione la fuente para la imagen").
		setItems(R.array.imagenes,  new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				if(which==0){
					Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
					intent.setType("image/*");
					startActivityForResult(intent, 1);
				}else{
					Intent intent=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
					startActivityForResult(intent, 1);
					
				}
				
			}
		});
		AlertDialog dialogo = builder.create();
		dialogo.show();
		
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
			
			ContactosDAOSQLLite contactosDAO=ContactosDAOSQLLite.getInstance(getApplicationContext());
			contactosDAO.guardarContacto(contacto);
			finish();
			
			
		}
	}
	

}
