package mavenNeodatis.mavenNeodatis;

public class Jugadores {
	private String dni,nombre,apellido,municipio,ciudad;
	private int edad;
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public Jugadores(String dni, String nombre, String apellido, String municipio, String ciudad, int edad) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.municipio = municipio;
		this.ciudad = ciudad;
		this.edad = edad;
	}
	public Jugadores() {
		super();
	}
	@Override
	public String toString() {
		return "Jugadores [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", municipio=" + municipio
				+ ", ciudad=" + ciudad + ", edad=" + edad + "]";
	}
	
}
