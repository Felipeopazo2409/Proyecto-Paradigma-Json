package paquete;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import org.json.JSONArray;
import org.json.simple.parser.ParseException;

public class FlujoPrograma {
	public VentanaPrincipal ventana_main;
	public VentanaAdministrador ventana_admin;
	public VentanaTrabajador ventana_trabajadores;
	public VentanaInformacion ventana_informacion_general;
	
	JSONArray trabajadores = new JSONArray();
	JSONArray departamentos = new JSONArray();
	
	public FlujoPrograma() throws IOException, ParseException {
		CrearVentanas();
		funcionalidades_botones_ventana_main();
		funcionalidades_botones_ventana_admin(); 
		funcionalidades_botones_ventana_trabajadores();
		funcionalidades_botones_ventana_informacion();
		
		
	}

	
	public void CrearVentanas() throws IOException, ParseException {
	    
		ventana_main = new VentanaPrincipal();
		ventana_admin = new VentanaAdministrador();
		ventana_trabajadores = new VentanaTrabajador();
		int cantidad_depto = ventana_admin.getCantidadDepartamentos();
		 int cantidad_trabajadores = ventana_trabajadores.getcantidad_trabajadores();
		ventana_informacion_general = new VentanaInformacion(cantidad_depto,cantidad_trabajadores);
		
	   
	
	}
	
	public void funcionalidades_botones_ventana_main() {
		ventana_main.menu.boton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_main.setVisible(false);
				ventana_admin.setVisible(true);
				ventana_trabajadores.setVisible(false);
				ventana_informacion_general.setVisible(false);
			}
		});
		
		ventana_main.menu.boton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_main.setVisible(false);
				ventana_admin.setVisible(false);
				ventana_trabajadores.setVisible(true);
				ventana_informacion_general.setVisible(false);
			}
		});
		
		ventana_main.menu.boton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_main.setVisible(false);
				ventana_admin.setVisible(false);
				ventana_trabajadores.setVisible(false);
				ventana_informacion_general.setVisible(true);
			}
		});
		
		ventana_main.menu.boton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
	}
	
	public void funcionalidades_botones_ventana_admin() {
		ventana_admin.admin.volver_menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_main.setVisible(true);
				ventana_admin.setVisible(false);
			}
		});
	}
	
	public void funcionalidades_botones_ventana_trabajadores() {
	
		ventana_trabajadores.panel.volver_menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_main.setVisible(true);
				ventana_admin.setVisible(false);
				ventana_trabajadores.setVisible(false);
			}
		});
	}
	
	public void funcionalidades_botones_ventana_informacion() {
		 ventana_informacion_general.panel_informacion.boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_main.setVisible(true);
				ventana_admin.setVisible(false);
				ventana_trabajadores.setVisible(false);
				ventana_informacion_general.setVisible(false);
			}
		 });
	}
}
