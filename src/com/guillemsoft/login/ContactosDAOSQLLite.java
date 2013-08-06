package com.guillemsoft.login;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactosDAOSQLLite extends SQLiteOpenHelper {
	private static String createSQL = "create table Contactos"
			+ "(id Integer primary key," + "nombre VARCHAR NULL,"
			+ "apellidos VARCHAR NULL," + "direccion VARCHAR NULL,"
			+ "telefono VARCHAR NULL," + "email VARCHAR NULL)";
	
	private static String guardarSQL= "insert into Contactos(nombre,apellidos,direccion,telefono,email) Values (?,?,?,?,?)";
	private static String eliminarSQL="Delete from Contactos where id = ?";
	private static String actualizarSQL="Update Contactos set nombre=?,apellidos=?,direccion=?,telefono=?,email=? Where id=?";
	private static String recuperarContactosSQL="select * from Contactos";
	private static String recuperarContactoSQL="select * from Contactos where id=?";

	public ContactosDAOSQLLite(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(createSQL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(createSQL);
	}
	
	public void guardarContacto(Contacto contacto){
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL(guardarSQL, new String[]{contacto.getNombre(),contacto.getApellidos(),contacto.getDireccion(),contacto.getTelefono(),contacto.getEmail()});
	}
	public List<Contacto> recuperarContactos(){
		List<Contacto> contactos=new ArrayList<Contacto>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor=db.rawQuery(recuperarContactosSQL, new String[]{});
		while(cursor.moveToNext()){
			Contacto contacto=new  Contacto();
			contacto.setId(cursor.getInt(cursor.getColumnIndex("id")));
			contacto.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
			contacto.setApellidos(cursor.getString(cursor.getColumnIndex("apellidos")));
			contacto.setTelefono(cursor.getString(cursor.getColumnIndex("telefono")));
			contacto.setEmail(cursor.getString(cursor.getColumnIndex("email")));
			contacto.setDireccion(cursor.getString(cursor.getColumnIndex("direccion")));
			contactos.add(contacto);
		}
		return contactos;
	}
	public Contacto recuperarContacto(Integer id){
		List<Contacto> contactos=new ArrayList<Contacto>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor=db.rawQuery(recuperarContactoSQL, new String[]{""+id});
		Contacto contacto=new  Contacto();
		contacto.setId(cursor.getInt(cursor.getColumnIndex("id")));
		contacto.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
		contacto.setApellidos(cursor.getString(cursor.getColumnIndex("apellidos")));
		contacto.setTelefono(cursor.getString(cursor.getColumnIndex("telefono")));
		contacto.setEmail(cursor.getString(cursor.getColumnIndex("email")));
		contacto.setDireccion(cursor.getString(cursor.getColumnIndex("direccion")));
		contactos.add(contacto);
		return contacto;
	}
	public void actualizarContacto(Contacto contacto){
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL(actualizarSQL, new String[]{contacto.getNombre(),contacto.getApellidos(),contacto.getDireccion(),contacto.getTelefono(),contacto.getEmail(),""+contacto.getId()});
	}
	public void eliminarContacto(Integer id){
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL(eliminarSQL, new String[]{""+id});
	}
	
	

}
