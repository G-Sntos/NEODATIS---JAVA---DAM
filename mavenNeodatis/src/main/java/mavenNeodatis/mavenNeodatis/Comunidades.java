package mavenNeodatis.mavenNeodatis;

public class Comunidades {
	private String nombre,calle,municipio,ciudad;
	private int numero,cuentaVecinos;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
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
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getCuentaVecinos() {
		return cuentaVecinos;
	}
	public void setCuentaVecinos(int cuentaVecinos) {
		this.cuentaVecinos = cuentaVecinos;
	}
	public Comunidades(String nombre, String calle, String municipio, String ciudad, int numero, int cuentaVecinos) {
		super();
		this.nombre = nombre;
		this.calle = calle;
		this.municipio = municipio;
		this.ciudad = ciudad;
		this.numero = numero;
		this.cuentaVecinos = cuentaVecinos;
	}
	@Override
	public String toString() {
		return "Comunidades [nombre=" + nombre + ", calle=" + calle + ", municipio=" + municipio + ", ciudad=" + ciudad
				+ ", numero=" + numero + ", cuentaVecinos=" + cuentaVecinos + "]";
	}
	public Comunidades() {
		super();
	}
	
}
