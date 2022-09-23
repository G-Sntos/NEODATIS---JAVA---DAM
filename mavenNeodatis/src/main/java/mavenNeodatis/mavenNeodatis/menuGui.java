package mavenNeodatis.mavenNeodatis;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.InputMismatchException;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ODBRuntimeException;

public class menuGui extends JFrame{
	//variables para recibir input
	String dni,nombre,apellido,municipio,ciudad,calle,numReserva;
	int edad,numero,numMunicipio,numVecino,hora,dia,mes;
	
	//buttones subMenu
	JButton button1= new JButton();
	JButton button2= new JButton();
	JButton button3= new JButton();
	JButton button4= new JButton();
	JButton button5= new JButton();
	JButton button6= new JButton();
	JButton button7= new JButton();
	//buttones menuPrincipal
	JButton button1Menu= new JButton();
	JButton button2Menu= new JButton();
	JButton button3Menu= new JButton();
	JButton button4Menu= new JButton();
	JButton button5Menu= new JButton();
	JButton button6Menu= new JButton();
	
	JMenu menu;
	JMenuBar menuBar;
	JMenuItem menuItem1;
	
	
	Image icon = Toolkit.getDefaultToolkit().getImage("iconJava.png"); 
	configuracionesNeoDatis x = new configuracionesNeoDatis();
	public menuGui() {
		//Base frame del menu
		//hecho mi propio icono, el icono de java  me parece muy raro la verdad.
		  
		this.setIconImage(icon);
		this.setLayout(new FlowLayout(FlowLayout.CENTER,20,20)); 
		this.setSize(480, 300);
		this.setTitle("PRÁCTICA 1.- NEODATIS - PISTA");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.getContentPane().setBackground(new Color(250, 245, 228));
		menuBar = new JMenuBar();// iniciar barra de menu
		menu = new JMenu("Fichero");
		menuItem1 = new JMenuItem("RE-CREAR Fichero NEODATIS");
		
		menu.add(menuItem1);
		menuBar.add(menu);
		
		button1Menu.setFocusable(false);
		button2Menu.setFocusable(false);
		button3Menu.setFocusable(false);
		button4Menu.setFocusable(false);
		button5Menu.setFocusable(false);
		button6Menu.setFocusable(false);
		//tamaño buttones
		button1Menu.setPreferredSize(new Dimension(200,50));
		button2Menu.setPreferredSize(new Dimension(200,50));
		button3Menu.setPreferredSize(new Dimension(200,50));
		button4Menu.setPreferredSize(new Dimension(200,50));
		button5Menu.setPreferredSize(new Dimension(200,50));
		button6Menu.setPreferredSize(new Dimension(200,50));
		//texto buttones
		button1Menu.setText("Insertar");
		button2Menu.setText("Listado");
		button3Menu.setText("Modificar");
		button4Menu.setText("Eliminar");
		button5Menu.setText("Consultas");
		button6Menu.setText("Salir");
		//añadir buttones al frame
		this.add(button1Menu);
		this.add(button2Menu);
		this.add(button3Menu);
		this.add(button4Menu);
		this.add(button5Menu);
		this.add(button6Menu);
		this.setJMenuBar(menuBar);
		//acciones de cada button
		menuItem1.addActionListener(h->{ // borrar el antiguo fichero y hacer uno nuevo
			File neoDatis = new File("NEODATIS.neodatis"); 
			neoDatis.delete();
			ODB odb = ODBFactory.open("NEODATIS.neodatis");
			odb.close();
		});
		
		button1Menu.addActionListener(e->{
			insertarDatos();
			this.dispose();
		
		});
		button2Menu.addActionListener(e->{
			listadoDatos();
			this.dispose();
		});
		button3Menu.addActionListener(e->{
			modificarDatos();
			this.dispose();
		});
		button4Menu.addActionListener(e->{
			eliminarDatos();
			this.dispose();
		});
		button5Menu.addActionListener(e->{
			consultaDeInteres();
			this.dispose();
		});
		button6Menu.addActionListener(e->{
			System.exit(0);
		});
		//para que muestra el frame
		this.setVisible(true);
	}
	
	private void consultaDeInteres() {
		JFrame consultas =  new JFrame();
		consultas.setIconImage(icon);
		consultas.setLayout(new FlowLayout(FlowLayout.CENTER,20,20)); 
		consultas.setSize(400, 380);
		consultas.setTitle("Pista-Neodatis");
		consultas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		consultas.setResizable(false);
		consultas.getContentPane().setBackground(new Color(233, 213, 218));
		//montar buttones
		button1.setFocusable(false);
		button2.setFocusable(false);
		button3.setFocusable(false);
		button4.setFocusable(false);
		button5.setFocusable(false);
		button6.setFocusable(false);
		//tamaño buttones
		button1.setPreferredSize(new Dimension(240,30));
		button2.setPreferredSize(new Dimension(240,30));
		button3.setPreferredSize(new Dimension(240,30));
		button4.setPreferredSize(new Dimension(240,30));
		button5.setPreferredSize(new Dimension(240,30));
		button6.setPreferredSize(new Dimension(240,30));
		//texto buttones
		button1.setText("Comunidad con más Vecino");
		button2.setText("Jugadores de Municipio");
		button3.setText("Reserva por Dia");
		button4.setText("Comunidad por Cuenta Vecino");
		button5.setText("Jugador ordenado por Edad");
		button6.setText("Volver");
		//añadir al frame
		consultas.add(button1);
		consultas.add(button2);
		consultas.add(button3);
		consultas.add(button4);
		consultas.add(button5);
		consultas.add(button6);
		consultas.setVisible(true);
		button1.addActionListener(j->{
			x.comunidadVecino();
		});	
		button2.addActionListener(j->{
			try {	
				 municipio =  JOptionPane.showInputDialog("Municipio: ");
				}catch (InputMismatchException | NumberFormatException | NullPointerException | ODBRuntimeException b) {
					consultas.dispose();
				}
			x.jugadoresPorMunicipio(municipio);
		});		
		button3.addActionListener(j->{
			try {
				 dia= Integer.valueOf(JOptionPane.showInputDialog("Dia: "));				
				}catch (InputMismatchException | NumberFormatException | NullPointerException | ODBRuntimeException b) {
					consultas.dispose();
				}
			x.reservaPorDia(dia);
		});		
		button4.addActionListener(j->{
			try {
				 numero =  Integer.valueOf(JOptionPane.showInputDialog("Cuenta Vecino: "));
				}catch (InputMismatchException | NumberFormatException | NullPointerException | ODBRuntimeException b) {
					consultas.dispose();
				}
			x.comunidadPorCuentaVecino(numVecino);
		});		
		button5.addActionListener(j->{
			try {
				 edad =  Integer.valueOf(JOptionPane.showInputDialog("Edad: "));
				}catch (InputMismatchException | NumberFormatException | NullPointerException | ODBRuntimeException b) {
					consultas.dispose();
				}
			x.jugadoresPorEdad(edad);
		});		
		button6.addActionListener(j->{
			consultas.dispose();
			menuGui menuPrincipal = new menuGui();
		});		
	}
	private void eliminarDatos() {
		JFrame eliminarDatos = new JFrame();
		eliminarDatos.setIconImage(icon);
		eliminarDatos.setLayout(new FlowLayout(FlowLayout.CENTER,20,20)); 
		eliminarDatos.setSize(400, 250);
		eliminarDatos.setTitle("Pista-Neodatis");
		eliminarDatos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		eliminarDatos.setResizable(false);
		eliminarDatos.getContentPane().setBackground(new Color(233, 213, 218));
		//montar buttones
		button1.setFocusable(false);
		button2.setFocusable(false);
		button3.setFocusable(false);
		button4.setFocusable(false);
		//tamaño buttones
		button1.setPreferredSize(new Dimension(240,30));
		button2.setPreferredSize(new Dimension(240,30));
		button3.setPreferredSize(new Dimension(240,30));
		button4.setPreferredSize(new Dimension(240,30));
		//texto buttones
		button1.setText("Eliminar Jugadores");
		button2.setText("Eliminar Comunidades");
		button3.setText("Eliminar Reserva");
		button4.setText("Volver");
		//añadir al frame
		eliminarDatos.add(button1);
		eliminarDatos.add(button2);
		eliminarDatos.add(button3);
		eliminarDatos.add(button4);
		
		button1.addActionListener(g->{
			try {
				dni = JOptionPane.showInputDialog("DNI: ");
				}catch (InputMismatchException | NumberFormatException | NullPointerException | ODBRuntimeException b) {
					
					eliminarDatos.dispose();
				}
			x.configBorrarJugador(dni);
			});
		button2.addActionListener(g->{
			try {
				nombre = JOptionPane.showInputDialog("Nombre: ");
				}catch (InputMismatchException | NumberFormatException | NullPointerException | ODBRuntimeException b) {
					
					eliminarDatos.dispose();
				}
			x.configBorrarComunidad(nombre);
		
		});
		button3.addActionListener(g->{
			try {
				numReserva = JOptionPane.showInputDialog("Nombre Reserva: ");
				}catch (InputMismatchException | NumberFormatException | NullPointerException | ODBRuntimeException b) {
				
					eliminarDatos.dispose();
				}
			x.configBorrarReserva(numReserva);
		});
		button4.addActionListener(g->{
			eliminarDatos.dispose();
			menuGui menuPrincipal = new menuGui();
			
		});
		
		eliminarDatos.setVisible(true);
		
		
	}
	private void modificarDatos() {
		JFrame modificarDatos = new JFrame();
		modificarDatos.setIconImage(icon);
		modificarDatos.setLayout(new FlowLayout(FlowLayout.CENTER,20,20)); 
		modificarDatos.setSize(400, 250);
		modificarDatos.setTitle("Pista-Neodatis");
		modificarDatos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		modificarDatos.setResizable(false);
		modificarDatos.getContentPane().setBackground(new Color(233, 213, 218));
		//montar buttones
		button1.setFocusable(false);
		button2.setFocusable(false);
		button3.setFocusable(false);
		button4.setFocusable(false);
		//tamaño buttones
		button1.setPreferredSize(new Dimension(240,30));
		button2.setPreferredSize(new Dimension(240,30));
		button3.setPreferredSize(new Dimension(240,30));
		button4.setPreferredSize(new Dimension(240,30));
		//texto buttones
		button1.setText("Modificar Jugador");
		button2.setText("Modificar Comunidad");
		button3.setText("ModificarReserva");
		button4.setText("Volver");
		//añadir al frame
		modificarDatos.add(button1);
		modificarDatos.add(button2);
		modificarDatos.add(button3);
		modificarDatos.add(button4);
		
		button1.addActionListener(g->{
			try {
				dni = JOptionPane.showInputDialog("DNI: ");
				nombre = JOptionPane.showInputDialog("Nombre: ");
 				apellido = JOptionPane.showInputDialog("Apellido: ");
				municipio = JOptionPane.showInputDialog("Municipio: ");
				ciudad = JOptionPane.showInputDialog("Ciudad: ");
				edad = Integer.valueOf(JOptionPane.showInputDialog("Edad "));
				}catch (InputMismatchException | NumberFormatException | NullPointerException | ODBRuntimeException b) {
					
					modificarDatos.dispose();
				}
			x.configModificarJugador(dni, nombre, apellido, municipio, ciudad, edad);
			});
		button2.addActionListener(g->{
			try {
				nombre = JOptionPane.showInputDialog("Nombre: ");
				calle = JOptionPane.showInputDialog("Calle: ");
				municipio = JOptionPane.showInputDialog("Municipio: ");
				ciudad = JOptionPane.showInputDialog("Ciudad: ");
				edad = Integer.valueOf(JOptionPane.showInputDialog("Numero"));
				numVecino = Integer.valueOf(JOptionPane.showInputDialog("Cuenta Vecino"));
				}catch (InputMismatchException | NumberFormatException | NullPointerException | ODBRuntimeException b) {
					
					modificarDatos.dispose();
				}
			x.configModificarComunidad(nombre, calle, municipio, ciudad, numero, numVecino);
		
		});
		button3.addActionListener(g->{
			Jugadores[] listaJugadores;
			try {
				numReserva = JOptionPane.showInputDialog("Nombre Reserva: ");
				 hora= Integer.valueOf(JOptionPane.showInputDialog("Hora: "));
				 dia= Integer.valueOf(JOptionPane.showInputDialog("Dia: "));
				 mes = Integer.valueOf(JOptionPane.showInputDialog("Mes: "));
				 municipio =  JOptionPane.showInputDialog("Municipio: ");
				 numero =  Integer.valueOf(JOptionPane.showInputDialog("Numero Jugadores: "));
				}catch (InputMismatchException | NumberFormatException | NullPointerException | ODBRuntimeException b) {
				
					modificarDatos.dispose();
				}
			listaJugadores= new Jugadores[numero];
			for(int i=0; i<numero; i++) {
				dni = JOptionPane.showInputDialog("Dni Jugador: ");
				Jugadores nuevoJugador = new Jugadores();
				nuevoJugador.setDni(dni);
				listaJugadores[i] = nuevoJugador;
			}
			Comunidades comunidad = new Comunidades();
			comunidad.setMunicipio(municipio);
			try {
				x.configModificarReserva(numReserva, hora, dia, mes, comunidad, listaJugadores);
			} catch (Exception e) {
				modificarDatos.dispose();

			}
		});
		button4.addActionListener(g->{
			modificarDatos.dispose();
			menuGui menuPrincipal = new menuGui();
		});
		
		modificarDatos.setVisible(true);
		
		
	}
	private void listadoDatos() {
		JFrame listadoDatos = new JFrame();
		listadoDatos.setIconImage(icon);
		listadoDatos.setLayout(new FlowLayout(FlowLayout.CENTER,20,20)); 
		listadoDatos.setSize(400, 250);
		listadoDatos.setTitle("Pista-Neodatis");
		listadoDatos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		listadoDatos.setResizable(false);
		listadoDatos.getContentPane().setBackground(new Color(233, 213, 218));
		//montar buttones
		button1.setFocusable(false);
		button2.setFocusable(false);
		button3.setFocusable(false);
		button4.setFocusable(false);
		//tamaño buttones
		button1.setPreferredSize(new Dimension(240,30));
		button2.setPreferredSize(new Dimension(240,30));
		button3.setPreferredSize(new Dimension(240,30));
		button4.setPreferredSize(new Dimension(240,30));
		//texto buttones
		button1.setText("Mostrar Jugadores");
		button2.setText("Mostrar Comunidades");
		button3.setText("Mostrar Reserva");
		button4.setText("Volver");
		//añadir al frame
		listadoDatos.add(button1);
		listadoDatos.add(button2);
		listadoDatos.add(button3);
		listadoDatos.add(button4);
		
		button1.addActionListener(g->{
			x.configListadoJugador();

		});
		button2.addActionListener(g->{
			x.configListadoComunidades();
		});
		button3.addActionListener(g->{
			x.configListadoReservas();
		});
		button4.addActionListener(g->{
			listadoDatos.dispose();
			menuGui menuPrincipal = new menuGui();
			
		});
		
		listadoDatos.setVisible(true);
		
	}
	private void insertarDatos() {
		JFrame insertarDatos = new JFrame();
		insertarDatos.setIconImage(icon);
		insertarDatos.setLayout(new FlowLayout(FlowLayout.CENTER,20,20)); 
		insertarDatos.setSize(400, 250);
		insertarDatos.setTitle("Pista-Neodatis");
		insertarDatos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		insertarDatos.setResizable(false);
		insertarDatos.getContentPane().setBackground(new Color(105, 182, 188));
		//montar buttones
		button1.setFocusable(false);
		button2.setFocusable(false);
		button3.setFocusable(false);
		button4.setFocusable(false);
		//tamaño buttones
		button1.setPreferredSize(new Dimension(240,30));
		button2.setPreferredSize(new Dimension(240,30));
		button3.setPreferredSize(new Dimension(240,30));
		button4.setPreferredSize(new Dimension(240,30));
		//texto buttones
		button1.setText("Insertar Jugadores");
		button2.setText("Insertar Comunidades");
		button3.setText("Insertar Reserva");
		button4.setText("Volver");
		//añadir al frame
		insertarDatos.add(button1);
		insertarDatos.add(button2);
		insertarDatos.add(button3);
		insertarDatos.add(button4);
		button2.addActionListener(f->{
			try {
				
				nombre = JOptionPane.showInputDialog("Nombre Comunidad: ");
 				calle = JOptionPane.showInputDialog("Calle: ");
				municipio = JOptionPane.showInputDialog("Municipio: ");
				ciudad = JOptionPane.showInputDialog("Ciudad: ");
				edad = Integer.valueOf(JOptionPane.showInputDialog("Numero"));
				numVecino = Integer.valueOf(JOptionPane.showInputDialog("Cuenta Vecino"));
			}catch (InputMismatchException | NumberFormatException | NullPointerException | ODBRuntimeException b) {
				
				insertarDatos.dispose();
				}
			if(nombre == null || calle == null || municipio == null || ciudad == null || edad == 0 || numVecino == 0) {
				JOptionPane.showMessageDialog(null, "Error","Neodatis-Pista", JOptionPane.WARNING_MESSAGE);
			}else {
				try {
					x.configInsertarComunidad(nombre, calle, municipio, ciudad, numero, numVecino);
				} catch (Exception e) {
					e.printStackTrace();
					insertarDatos.dispose();

				}
			}
			
			
		});
		button1.addActionListener(f->{
			try {
				dni = JOptionPane.showInputDialog("DNI: ");
				nombre = JOptionPane.showInputDialog("Nombre: ");
 				apellido = JOptionPane.showInputDialog("Apellido: ");
				municipio = JOptionPane.showInputDialog("Municipio: ");
				ciudad = JOptionPane.showInputDialog("Ciudad: ");
				edad = Integer.valueOf(JOptionPane.showInputDialog("Edad "));
			}catch (InputMismatchException | NumberFormatException | NullPointerException| ODBRuntimeException b) {
		
				insertarDatos.dispose();
				}
			if(dni == null || nombre== null || municipio == null || apellido== null || edad == 0 || ciudad == null) {
				JOptionPane.showMessageDialog(null, "Error","Neodatis-Pista", JOptionPane.WARNING_MESSAGE);
			}else {
				try {
					x.configInsertarJugador(dni, nombre, apellido, municipio, ciudad, edad);
				} catch (Exception e) {
					e.printStackTrace();
					insertarDatos.dispose();

				}
			}
			
					
		});
		
		button3.addActionListener(f->{
			Jugadores[] listaJugadores;
			try {
			 numReserva= JOptionPane.showInputDialog("Nombre Reserva: ");
			 hora= Integer.valueOf(JOptionPane.showInputDialog("Hora: "));
			 dia= Integer.valueOf(JOptionPane.showInputDialog("Dia: "));
			 mes = Integer.valueOf(JOptionPane.showInputDialog("Mes: "));
			 municipio =  JOptionPane.showInputDialog("Municipio: ");
			 numero =  Integer.valueOf(JOptionPane.showInputDialog("Numero Jugadores: "));
			
			}catch (InputMismatchException | NumberFormatException | NullPointerException | ODBRuntimeException b) {
		
				insertarDatos.dispose();
				}
			listaJugadores= new Jugadores[numero];
			for(int i=0; i<numero; i++) {
				dni = JOptionPane.showInputDialog("Dni Jugador: ");
				Jugadores nuevoJugador = new Jugadores();
				nuevoJugador.setDni(dni);
				listaJugadores[i] = nuevoJugador;
			}
			Comunidades comunidad = new Comunidades();
			comunidad.setMunicipio(municipio);
			try {
				x.configInsertarReserva(numReserva, hora, dia, mes, comunidad, listaJugadores);
			} catch (Exception e) {
		
				e.printStackTrace();
				insertarDatos.dispose();

			}
			
		});
		button4.addActionListener(f->{
			insertarDatos.dispose();
			menuGui menuPrincipal = new menuGui();
		});
		insertarDatos.setVisible(true);
		
	}
}
