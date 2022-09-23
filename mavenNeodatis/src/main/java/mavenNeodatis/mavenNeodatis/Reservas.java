package mavenNeodatis.mavenNeodatis;

import java.util.Arrays;

public class Reservas {
	private int hora,dia,mes;
	private String numReserva;
	private Comunidades comunidad;
	private Jugadores[] listaJugadores;
	
	public Reservas() {
		super();
	}
	
	@Override
	public String toString() {
		return "Reservas [numReserva=" + numReserva + ", hora=" + hora + ", dia=" + dia + ", mes=" + mes
				+ ", comunidad=" + comunidad + ", listaJugadores=" + Arrays.toString(listaJugadores) + "]";
	}

	public Reservas(String numReserva, int hora, int dia,int mes, Comunidades comunidad, Jugadores[] listaJugadores) {
		super();
		this.numReserva = numReserva;
		this.hora = hora;
		this.dia = dia;
		this.comunidad = comunidad;
		this.listaJugadores = listaJugadores;
		this.mes =  mes;
	
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public String getNumReserva() {
		return numReserva;
	}
	public void setNumReserva(String numReserva) {
		this.numReserva = numReserva;
	}
	public int getHora() {
		return hora;
	}
	public void setHora(int hora) {
		this.hora = hora;
	}
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public Comunidades getComunidad() {
		return comunidad;
	}
	public void setComunidad(Comunidades comunidad) {
		this.comunidad = comunidad;
	}
	public Jugadores[] getListaJugadores() {
		return listaJugadores;
	}
	public void setListaJugadores(Jugadores[] listaJugadores) {
		this.listaJugadores = listaJugadores;
	}
}
