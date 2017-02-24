package Presentacio;

import java.util.Calendar;
import java.util.Date;

public class Registrados {
	private String nombre;
	private String password;
	private String fecha;

	public Registrados(String nombre, String password, String fecha){
	this.nombre = nombre;
	this.password =password;
	this.fecha = fecha;
	
}

	public Registrados() {
	
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public String toString (){
		return nombre+"\n"+fecha;
	}
	public String toStringFichero(){
		return nombre+"\n"+password+"\n"+fecha;
	}
}
