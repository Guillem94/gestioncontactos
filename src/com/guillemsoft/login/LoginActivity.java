package com.guillemsoft.login;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private Button entrarButton;
	private Button cancelarButton;
	private EditText loginEditText;
	private EditText passwordEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		entrarButton = (Button) findViewById(R.id.entrarButton);
		cancelarButton = (Button) findViewById(R.id.cancelarButton);
		loginEditText = (EditText) findViewById(R.id.loginEditText);
		passwordEditText = (EditText) findViewById(R.id.passwordEditText);
		cancelarButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		entrarButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String login = loginEditText.getText().toString();
				String password = passwordEditText.getText().toString();
				if (login.equals("abc") && password.equals("123")) {
					
					Intent intent = new Intent(getApplicationContext(),
							MenuActivity.class);
					startActivity(intent);
				} else {
					Toast toast=Toast.makeText(getApplicationContext(), "Credenciales incorrectos", Toast.LENGTH_LONG);
					toast.show();
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
