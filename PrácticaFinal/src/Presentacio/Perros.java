package Presentacio;

public class Perros {
	@Override
	public String toString() {
		String cadena = "";
		if (vacunado)
		cadena= "Nombre: " + nombre + "\n Raza: " + raza + "\n Sexo:" + sexo + "\n Vacunado: vacunado\n Estado:"
				+ estado + "";
		else cadena ="Nombre: " + nombre + "\n Raza: " + raza + "\n Sexo:" + sexo + "\n Vacunado: No vacunado\n Estado:"
				+ estado + "";
		return cadena;
	}
	private String nombre;
	private String raza;
	private String sexo;
	private Boolean vacunado;
	private String estado;
	public Perros(String nombre, String raza, String sexo, Boolean vacunado, String estado){
		this.nombre= nombre;
		this.raza = raza;
		this.sexo = sexo;
		this.vacunado = vacunado;
		this.estado = estado;
	}
	public Perros() {
		// TODO Auto-generated constructor stub
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRaza() {
		return raza;
	}
	public void setRaza(String raza) {
		this.raza = raza;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Boolean getVacunado() {
		return vacunado;
	}
	public void setVacunado(Boolean vacunado) {
		this.vacunado = vacunado;
	}
	
	public String toStringFichero(){
		String cadena = null;
		if (vacunado)
		cadena= nombre + "\n" + raza + "\n" + sexo + "\ntrue\n" 
				+ estado;
		else cadena =nombre + "\n" + raza + "\n" + sexo + "\nfalse\n" 
				+ estado;
		return cadena;
	}
}
