package com.guillemsoft.login;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class ContactosDAOSQLLite extends SQLiteOpenHelper {
	private static ContactosDAOSQLLite instance=null;
	
	private static String createSQL = "create table Contactos"
			+ "(id Integer primary key," + "nombre VARCHAR NULL,"
			+ "apellidos VARCHAR NULL," + "direccion VARCHAR NULL,"
			+ "telefono VARCHAR NULL," + "email VARCHAR NULL, imagen BLOB NULL )";	
	private static String guardarSQL= "insert into Contactos(nombre,apellidos,direccion,telefono,email,imagen) Values (?,?,?,?,?,?)";
	private static String eliminarSQL="Delete from Contactos where id = ?";
	private static String actualizarSQL="Update Contactos set nombre=?,apellidos=?,direccion=?,telefono=?,email=?,imagen=? Where id=?";
	private static String recuperarContactosSQL="select * from Contactos";
	private static String recuperarContactoSQL="select * from Contactos where id=?";

	public static ContactosDAOSQLLite getInstance(Context context){
		if (instance==null){
			instance= new ContactosDAOSQLLite(context, "gestorcontactos", null, 2);
		}
		return instance;
	}
	
	private ContactosDAOSQLLite(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(createSQL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//db.execSQL("DROP table Contactos");
		//db.execSQL(createSQL);
		db.execSQL("Alter table contactos add column imagen BLOB Null");
	}
	
	public void guardarContacto(Contacto contacto){
		SQLiteDatabase db = getWritableDatabase();
		 SQLiteStatement insertStmt = db.compileStatement(guardarSQL);
		 insertStmt.clearBindings();
		 insertStmt.bindString(1,contacto.getNombre());
		 insertStmt.bindString(2,contacto.getApellidos());
		 insertStmt.bindString(3,contacto.getDireccion());
		 insertStmt.bindString(4,contacto.getTelefono());
		 insertStmt.bindString(5,contacto.getEmail());
		 insertStmt.bindBlob(6,contacto.getImagen());
		 insertStmt.executeInsert();
		 db.close();		
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
		cursor.moveToFirst();
		Contacto contacto=new  Contacto();
		contacto.setId(cursor.getInt(cursor.getColumnIndex("id")));
		contacto.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
		contacto.setApellidos(cursor.getString(cursor.getColumnIndex("apellidos")));
		contacto.setTelefono(cursor.getString(cursor.getColumnIndex("telefono")));
		contacto.setEmail(cursor.getString(cursor.getColumnIndex("email")));
		contacto.setDireccion(cursor.getString(cursor.getColumnIndex("direccion")));
		contacto.setImagen(cursor.getBlob(cursor.getColumnIndex("imagen")));
		contactos.add(contacto);
		return contacto; 
	}
	public void actualizarContacto(Contacto contacto){
		SQLiteDatabase db = getWritableDatabase();
		 SQLiteStatement updateStmt = db.compileStatement(actualizarSQL);
		 updateStmt.clearBindings();
		 updateStmt.bindString(1,contacto.getNombre());
		 updateStmt.bindString(2,contacto.getApellidos());
		 updateStmt.bindString(3,contacto.getDireccion());
		 updateStmt.bindString(4,contacto.getTelefono());
		 updateStmt.bindString(5,contacto.getEmail());
		 updateStmt.bindBlob(6,contacto.getImagen());
		 updateStmt.bindLong(7, contacto.getId());
		 updateStmt.executeInsert();
	}
	public void eliminarContacto(Integer id){
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL(eliminarSQL, new String[]{""+id});
	}
	
	

}
