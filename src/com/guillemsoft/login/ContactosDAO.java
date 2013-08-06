package com.guillemsoft.login;

import java.util.ArrayList;
import java.util.List;

public class ContactosDAO {
	private static List<Contacto> contactos=new ArrayList<Contacto>();
	private static int id=0;
	
	public ContactosDAO(){
	}
	
	
	public void guardarContacto(Contacto contacto) {
		contacto.setId(id);
		contactos.add(contacto);
		id++;
		System.out.println("El id asignado es "+id);
		System.out.println("El tamaño de la lista es "+contactos.size());
	}
	
	
	public List<Contacto> recuperarContactos(){
		return contactos;
	}
	
	
	public Contacto recuperarContacto(Integer id){
		for(int i=0;i<contactos.size();i++){
			Contacto contacto=contactos.get(i);
			Integer contactoid=contacto.getId();
			if(contactoid==id){
				return contacto;
			}
		}
		return null;
	}
	public void actualizarContacto(Contacto contacto){
		for(int i=0;i<contactos.size();i++){
			Contacto contacto2=contactos.get(i);
			Integer contacto2id=contacto2.getId();
			Integer contactoid=contacto.getId();
			if(contacto2id==contactoid){
				contactos.set(i, contacto);
				return;
			}
		}
	}
	public void eliminarContacto(Integer id){
		for(int i=0;i<contactos.size();i++){
			Contacto contacto=contactos.get(i);
			Integer contactoid=contacto.getId();
		    if(contactoid==id){
		    	contactos.remove(i);
		    	return;
		    }
		}
	}
}
