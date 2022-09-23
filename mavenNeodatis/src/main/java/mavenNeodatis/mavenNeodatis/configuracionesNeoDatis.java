package mavenNeodatis.mavenNeodatis;

import java.util.Arrays;

import javax.swing.JOptionPane;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;


public class configuracionesNeoDatis {
	final String db = "NEODATIS.neodatis";
	final String jugadoresClass = "Jugadores.class";
	final String comunidadesClass = "Comunidades.class";
	final String reservasClass = "Reservas.class";
	
	//config de insertar datos
	//abrir fichero neodatis
	//crear el objeto
	//usar el metodo.store()
	//cerrar fichero(ODBFactory)	
	@SuppressWarnings("unused")
	public void configInsertarJugador(String dni, String nombre, String apellido, String municipio, String ciudad, int edad) throws Exception{
		Jugadores nuevoJugador = new Jugadores(dni, nombre, apellido, municipio, ciudad, edad);
		ODB odb = ODBFactory.open(db);
		odb.store(nuevoJugador);
		if(odb!=null){
			
			JOptionPane.showMessageDialog(null, "Dato(s) insertado","Neodatis-Pista", JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, "Error","Neodatis-Pista", JOptionPane.WARNING_MESSAGE);
		}
		odb.close();
		
	}
	@SuppressWarnings("unused")
	public void configInsertarComunidad(String nombre, String calle, String municipio, String ciudad, int numero, int cuentaVecinos) throws Exception{
	Comunidades nuevoComunidad = new Comunidades(nombre, calle, municipio, ciudad, numero, cuentaVecinos);
	ODB odb = ODBFactory.open(db);
	odb.store(nuevoComunidad);
	if(odb!=null){

		JOptionPane.showMessageDialog(null, "Dato(s) insertado","Neodatis-Pista", JOptionPane.INFORMATION_MESSAGE);
	}else {
		JOptionPane.showMessageDialog(null, "Error","Neodatis-Pista", JOptionPane.WARNING_MESSAGE);
	}
	odb.close();
	
}
	@SuppressWarnings("unused")
	public void configInsertarReserva(String numReserva, int hora, int dia,int mes,Comunidades comunidad, Jugadores[] listaJugadores) throws Exception{
	Reservas nuevoReserva = new Reservas(numReserva, hora, dia, mes,comunidad, listaJugadores);
	boolean duplicado = false;
	ODB odb = ODBFactory.open(db);
	//Comprobacion al funcion de añadir reserva.
	//Recoge todo los objetos del class Reservas y le mete en una lista
	//Si la hora y dia del objeto a insertar es igual a una de lo que lleva la lista, saldrá un error en vez de insertar el dato.
	Objects<Reservas> lista = odb.getObjects(Reservas.class);
	while(lista.hasNext()){
		 if ((lista.next().getHora() == nuevoReserva.getHora()) && (lista.next().getDia() == nuevoReserva.getDia())) duplicado = true;
		 }
	
	if(duplicado) {
		JOptionPane.showMessageDialog(null, "Error Reserva ya Existe","Neodatis-Pista", JOptionPane.WARNING_MESSAGE);
		odb.close();
	}else {
		odb.store(nuevoReserva);
		odb.close();
	}
	
	
	
}
	//config de listados
	//abrir fichero
	//recoger objetos en una lista
	//mostrar lista por la consola
	//cerrar fichero(ODBFactory)
	public void configListadoJugador() {
		ODB odb = null;
		try{
		 odb = ODBFactory.open(db);
		 Objects<Jugadores> lista = odb.getObjects(Jugadores.class);
		 System.out.println(lista.size() + " Jugador" );
		while(lista.hasNext()){
		 System.out.println("\t: " + lista.next());
		 }
		}finally{
		 if(odb!=null){
		 odb.close();
		 }
		}

	}
	public void configListadoComunidades() {
		ODB odb = null;
		try{
		 odb = ODBFactory.open(db);
		 Objects<Comunidades> lista = odb.getObjects(Comunidades.class);
		 System.out.println(lista.size() + " Comunidad");
		while(lista.hasNext()){
		 System.out.println("\t: " + lista.next());
		 }
		}finally{
		 if(odb!=null){
		 odb.close();
		 }
		}

	}
	public void configListadoReservas() {
		ODB odb = null;
		try{
		 odb = ODBFactory.open(db);
		 Objects<Reservas> lista = odb.getObjects(Reservas.class);
		 System.out.println(lista.size() + " Reserva(s)" );
		// Mostrar los objetos
		while(lista.hasNext()){
		 System.out.println("\t: " + lista.next());
		 }
		}finally{
		 if(odb!=null){
		 odb.close();
		 }
		}

	}
	//config para borrar
	//abrir fichero
	//crear un query para identificar la fila
	//lo hice con su "primary key"
	//recoger el dato con el mismo "primary key"
	//borrar con el metodo de odb.
	//cerrar fichero(ODBFactory)
	public void configBorrarJugador(String dni) {
		ODB odb = ODBFactory.open(db);
		IQuery query = new CriteriaQuery(Jugadores.class, Where.like("dni", dni));
		Objects<Jugadores> lista = odb.getObjects(query);
		odb.delete(lista.getFirst());//get first, porque en teoria cada uno solo puede tener un DNI
		JOptionPane.showMessageDialog(null, "Dato(s) Borrado","Neodatis-Pista", JOptionPane.INFORMATION_MESSAGE);
		odb.close();
		
	}
	public void configBorrarComunidad(String nombre) {
		ODB odb = ODBFactory.open(db);
		IQuery query = new CriteriaQuery(Comunidades.class, Where.like("nombre", nombre));
		Objects<Comunidades> lista = odb.getObjects(query);
		odb.delete(lista.getFirst());//get first, porque en teoria cada uno solo puede tener un nombre de comunidad
		JOptionPane.showMessageDialog(null, "Dato(s) Borrado","Neodatis-Pista", JOptionPane.INFORMATION_MESSAGE);
		odb.close();
		
	}
	public void configBorrarReserva(String numReserva) {
		ODB odb = ODBFactory.open(db);
		IQuery query = new CriteriaQuery(Reservas.class, Where.like("numReserva", numReserva));
		Objects<Reservas> lista = odb.getObjects(query);
		odb.delete(lista.getFirst());//get first, porque en teoria cada uno solo puede tener un Nombre Reserva
		JOptionPane.showMessageDialog(null, "Dato(s) Borrado","Neodatis-Pista", JOptionPane.INFORMATION_MESSAGE);
		odb.close();
		
	}
	//config modificar Dato
	//abrir fichero
	//crear un query para identificar la fila
	//lo hice con su "primary key"
	//recoger el dato con el mismo "primary key"
	//crear un nuevo objeto para meter el objeto recogido
	//modificar datos
	//volver a guardar con store.
	//cerrar fichero(ODBFactory)
	public void configModificarJugador(String dni,String nombre, String apellido, String municipio, String ciudad, int edad) {
		ODB odb = ODBFactory.open(db);
		IQuery query = new CriteriaQuery(Jugadores.class, Where.like("dni", dni));
		Objects<Jugadores> lista = odb.getObjects(query);
		Jugadores modificado = new Jugadores();
		modificado=(lista.getFirst());//get first, porque en teoria cada uno solo puede tener un DNI
		modificado.setNombre(nombre);
		modificado.setApellido(apellido);
		modificado.setMunicipio(municipio);
		modificado.setCiudad(ciudad);
		modificado.setEdad(edad);
		odb.store(modificado);
		JOptionPane.showMessageDialog(null, "Dato(s) Modificado","Neodatis-Pista", JOptionPane.INFORMATION_MESSAGE);
		odb.close();
		
	}
	public void configModificarComunidad(String nombre,String calle, String municipio, String ciudad, int numero, int cuentaVecinos) {
		ODB odb = ODBFactory.open(db);
		IQuery query = new CriteriaQuery(Comunidades.class, Where.like("nombre", nombre));
		Objects<Comunidades> lista = odb.getObjects(query);
		Comunidades comunidad = new Comunidades();
		comunidad = lista.getFirst();//get first, porque en teoria cada uno solo puede tener un DNI
		comunidad.setCalle(calle);
		comunidad.setMunicipio(municipio);
		comunidad.setCiudad(ciudad);
		comunidad.setNumero(numero);
		comunidad.setCuentaVecinos(cuentaVecinos);
		odb.store(comunidad);
		JOptionPane.showMessageDialog(null, "Dato(s) Borrado","Neodatis-Pista", JOptionPane.INFORMATION_MESSAGE);
		odb.close();
		
	}
	public void configModificarReserva(String numReserva,int hora, int dia,int mes,Comunidades comunidad, Jugadores[] listaJugadores) {
		ODB odb = ODBFactory.open(db);
		IQuery query = new CriteriaQuery(Reservas.class, Where.like("numReserva", numReserva));
		Objects<Reservas> lista = odb.getObjects(query);
		Reservas reservaModificar = new Reservas();
		reservaModificar =lista.getFirst();//get first, porque en teoria cada uno solo puede tener un DNI
		reservaModificar.setHora(hora);
		reservaModificar.setDia(dia);
		reservaModificar.setMes(mes);
		reservaModificar.setComunidad(comunidad);
		reservaModificar.setListaJugadores(listaJugadores);
		odb.store(reservaModificar);
		JOptionPane.showMessageDialog(null, "Dato(s) Borrado","Neodatis-Pista", JOptionPane.INFORMATION_MESSAGE);
		odb.close();
		
	}
	//Config de las consultas especiales.
	
	//ComunidadVecino
	//Recoger la lista de todos las reservas
	//Meter el numero de vecino de cada comunidad en un array
	//sortear el array de menos a mas
	//Hacer un query que recoge comunidad que lleva ese numero de vecino
	//recoger toda las comunidades que coincide con el query
	//mostrar.
	public void comunidadVecino() {
		ODB odb = null;
		try{
		 odb = ODBFactory.open(db);
		 Objects<Comunidades> lista = odb.getObjects(Comunidades.class);
		 int[] comArray;
		 comArray = new int[lista.size()];
		 for(int i =0; i<lista.size(); i++) {
			 comArray[0]=lista.next().getCuentaVecinos();
		 }
		 Arrays.sort(comArray);
		
		IQuery query = new CriteriaQuery(Comunidades.class, Where.contain("cuentaVecinos", comArray[(lista.size()-1)]));
		Objects<Comunidades> resultado = odb.getObjects(query);
		while(resultado.hasNext()){
			 System.out.println("\t: " + lista.next());
			 }
		}finally{
		 if(odb!=null)odb.close();
		}
		
	}
	public void jugadoresPorMunicipio(String municipio) {
		ODB odb = ODBFactory.open(db);
		try{
		IQuery query = new CriteriaQuery(Jugadores.class, Where.like("municipio", municipio));
		 Objects<Jugadores> lista = odb.getObjects(query);
		while(lista.hasNext()){
			 System.out.println("\t: " + lista.next());
			 }
			}finally {
		odb.close();	
			}
	}
	public void reservaPorDia(int dia) {
		ODB odb = ODBFactory.open(db);
		try{
		IQuery query = new CriteriaQuery(Reservas.class, Where.contain("dia", dia));
		 Objects<Reservas> lista = odb.getObjects(query);
		 System.out.println(lista.size() + " Reserva(s)" );
		while(lista.hasNext()){
		 System.out.println("\t: " + lista.next());
		 }
		}finally{
		 odb.close();
		 }
		
	}
	public void comunidadPorCuentaVecino(int numVecino) {

		ODB odb = ODBFactory.open(db);
		try{
		IQuery query = new CriteriaQuery(Comunidades.class, Where.contain("cuentaVecinos", numVecino));
		 Objects<Reservas> lista = odb.getObjects(query);
		while(lista.hasNext()){
		 System.out.println("\t: " + lista.next());
		 }
		}finally{
		 odb.close();
		 }
		
	}
	public void jugadoresPorEdad(int edad) {
		ODB odb = ODBFactory.open(db);
		try{
		IQuery query = new CriteriaQuery(Jugadores.class, Where.contain("edad", edad));
		 Objects<Jugadores> lista = odb.getObjects(query);
		while(lista.hasNext()){
			 System.out.println("\t: " + lista.next());
			 }
			}finally {
		odb.close();	
			}
			 
	}
}
