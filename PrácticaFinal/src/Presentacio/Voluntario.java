package Presentacio;

public class Voluntario {
	String nombre;
	int telefono;
	String dni;
	String email;
	String horario;
	String zona_asignada;
	
	
	public Voluntario(String nombre, int telefono, String dni,String horario,String email,String zona_asignada) {
		this.nombre = nombre;
		this.telefono = telefono;
		this.dni = dni;
		this.email =email;
		this.horario=horario;
		this.zona_asignada=zona_asignada;
	}
	public Voluntario() {
		// TODO Auto-generated constructor stub
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getTelefono() {
		return telefono;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getZona_asignada() {
		return zona_asignada;
	}
	public void setZona_asignada(String zona_asignada) {
		this.zona_asignada = zona_asignada;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	@Override
	public String toString() {
		return nombre + "\n" + telefono + "\n" + dni + "\n" + horario
				+ "\n" + email + "\n" + zona_asignada;
	}

}
