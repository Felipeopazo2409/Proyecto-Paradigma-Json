package paquete;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;

public class VentanaTrabajador extends JFrame {
	
	public PanelTrabajador panel;
	private JScrollPane scrollpane;
	public PanelInsertarTrabajador panelInsertarTrabajador;
	public PanelModificarTrabajador panelModificarTrabajador;
	public PanelEliminarTrabajador panelEliminarTrabajador;
	private PanelConsultarDatos panelConsultarTrabajador;
	private PanelLiquidacionSueldo panelLiquidacionTrabajador;
	public Trabajador trabajador;
	//public ArrayList<Trabajador> lista_trabajadores = new ArrayList();
	public JSONObject lista_trabajadores = new JSONObject();
	
	public JSONArray arreglo;
	public int pos;
	public int pos_eliminar;
	public int cont = 0;
	public int largo = 0;
	
	public VentanaTrabajador() throws IOException, ParseException {
		panel = new PanelTrabajador();
		
		arreglo = new JSONArray();
		setSize(780, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(false);
		setLocationRelativeTo(null);
		Inicializar();
		navegacion();
		volver_atras();
		insertarDatos();
		modificar_informacion();
		eliminar_trabajador();
		consultar_datos();
		
		
	}
	private void Inicializar() {
		scrollpane = new JScrollPane();
		scrollpane.setBounds(1, 1, 779, 500);
		scrollpane.setViewportView(panel);
		add(scrollpane);
	}
	
	private void navegacion() {//Navegamos por los diferentes paneles
		// Instancia de paneles
		panelInsertarTrabajador = new PanelInsertarTrabajador();
		panelModificarTrabajador = new PanelModificarTrabajador();
		panelEliminarTrabajador = new PanelEliminarTrabajador();
		panelConsultarTrabajador = new PanelConsultarDatos();
		panelLiquidacionTrabajador = new PanelLiquidacionSueldo();
	
		// Eventos de los botones
		
		// pinchar boton para ir a panel ingresar un trabajador
		panel.ingresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollpane.setViewportView(panelInsertarTrabajador);
			}
		});

		// Pinchar Boton para ir a panel modificar un trabajador
		panel.modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollpane.setViewportView(panelModificarTrabajador);

			}
		});

		// Pinchar Boton para ir a panel Eliminar un trabajador
		panel.eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollpane.setViewportView(panelEliminarTrabajador);
			}
		});
		// Pinchar Boton para ir a panel Consultar Datos de un trabajador
		panel.consultar_datos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollpane.setViewportView(panelConsultarTrabajador);
			}
		});
		// Pinchar Boton para ir a panel Generar Liquidacion de sueldo
		panel.generar_liquidacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollpane.setViewportView(panelLiquidacionTrabajador);
			}
		});
	}

	private void volver_atras() {//Eventos para llegar al menú principal
		panelInsertarTrabajador.cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollpane.setViewportView(panel);
			}
		});

		panelModificarTrabajador.cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollpane.setViewportView(panel);
			}
		});

		panelEliminarTrabajador.cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollpane.setViewportView(panel);
			}
		});
		panelConsultarTrabajador.volver_menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollpane.setViewportView(panel);
			}
		});
		panelLiquidacionTrabajador.volver_menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollpane.setViewportView(panel);
			}
		});

	}

	private void insertarDatos() {

		panelInsertarTrabajador.guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarTrabajadores();
				panelInsertarTrabajador.campoNombre.setText("");
				panelInsertarTrabajador.campoApellidoPaterno.setText("");
				panelInsertarTrabajador.campoApellidoMaterno.setText("");
				panelInsertarTrabajador.campoNacimiento.setText("");
				panelInsertarTrabajador.campoRut.setText("");
				panelInsertarTrabajador.campoSalario.setText("");
				panelInsertarTrabajador.campoDepartamento.setText("");
				JOptionPane.showMessageDialog(null, "Trabajador ingresado Exitosamente");
			}
		});

	}

	private void insertarTrabajadores()  {
		int rut;
		int salario;
		String nombre = panelInsertarTrabajador.campoNombre.getText();
		String apellidoP = panelInsertarTrabajador.campoApellidoPaterno.getText();
		String apellidoM = panelInsertarTrabajador.campoApellidoMaterno.getText();
		String obtener_rut = panelInsertarTrabajador.campoRut.getText();
		String fecha = panelInsertarTrabajador.campoNacimiento.getText();
		String tipo_contrato = panelInsertarTrabajador.contrato.getSelectedItem().toString();
		String obtener_salario = panelInsertarTrabajador.campoSalario.getText();
		String departamento = panelInsertarTrabajador.campoDepartamento.getText();
		rut = Integer.parseInt(obtener_rut);
		salario = Integer.parseInt(obtener_salario);
		trabajador = new Trabajador(nombre, apellidoP, apellidoM, rut, fecha, tipo_contrato, salario, departamento);
		lista_trabajadores.put("trabajadores", trabajador);
		
		arreglo.put(trabajador);

		
		FileUtil.write(arreglo);
		
	}

	private void modificar_informacion() {
		
		panelModificarTrabajador.buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String obtener_rut = panelModificarTrabajador.campoRut.getText();
				int rut = Integer.parseInt(obtener_rut);
				
				try {
					arreglo = FileUtil.read();
				} catch (IOException | ParseException e1) {
					// TODO Bloque catch generado automáticamente
					e1.printStackTrace();
				}
				
				JSONObject trab = trabajador.getTrabajadorByRut(arreglo, rut);
				
				if (trab != null) {
					panelModificarTrabajador.campoNombre.setText(trab.get("nombre").toString());
					panelModificarTrabajador.campoApellidoPaterno.setText(trab.get("nombre").toString());
					panelModificarTrabajador.campoApellidoMaterno.setText(trab.get("nombre").toString());
					panelModificarTrabajador.campoNacimiento.setText(trab.get("nombre").toString());
					panelModificarTrabajador.campo_contrato.setText(trab.get("nombre").toString());
					panelModificarTrabajador.campoSalario.setText(trab.get("nombre").toString());
					panelModificarTrabajador.campoDepartamento.setText(trab.get("nombre").toString());
			
					JOptionPane.showMessageDialog(null,"Trabajador Encontrado exitosamente");
				}else {
					JOptionPane.showMessageDialog(null,"Error al encontrar trabajador,Intente Nuevamente");
				}
			}
			
		});
		
		//Guardamos la información del trabajador modificado
		panelModificarTrabajador.guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Obtengo los valores de los campos TextField
				String nombre = panelModificarTrabajador.campoNombre.getText();
				String apellidoP = panelModificarTrabajador.campoApellidoPaterno.getText();
				String apellidoM = panelModificarTrabajador.campoApellidoMaterno.getText();
				String obtener_rut = panelModificarTrabajador.campoRut.getText();
				String fecha = panelModificarTrabajador.campoNacimiento.getText();
				String tipo_contrato = panelModificarTrabajador.campo_contrato.getText();
				String obtener_salario = panelModificarTrabajador.campoSalario.getText();
				String departamento = panelModificarTrabajador.campoDepartamento.getText();
				int rut = Integer.parseInt(obtener_rut);
				int salario = Integer.parseInt(obtener_salario);
				
				
				try {
					arreglo = FileUtil.read();
				} catch (IOException | ParseException e1) {
					// TODO Bloque catch generado automáticamente si falla al leer el archivo
					e1.printStackTrace();
				}
				
				trabajador.deleteTrabajadorByRut(arreglo, rut);
				
				trabajador = new Trabajador(nombre, apellidoP, apellidoM, rut, fecha, tipo_contrato, salario, departamento);
				//lista_trabajadores.put("trabajadores", trabajador);
				
				arreglo.put(trabajador);
				FileUtil.write(arreglo);
				
				//Mensaje De alerta
				panelModificarTrabajador.campoNombre.setText("");
				panelModificarTrabajador.campoApellidoPaterno.setText("");
				panelModificarTrabajador.campoApellidoMaterno.setText("");
				panelModificarTrabajador.campoNacimiento.setText("");
				panelModificarTrabajador.campo_contrato.setText("");
				panelModificarTrabajador.campoSalario.setText("");
				panelModificarTrabajador.campoDepartamento.setText("");
				JOptionPane.showMessageDialog(null, "Trabajador Modificado exitosamente");
				
			}
		});
		
		
	}
	private void eliminar_trabajador() {
		panelEliminarTrabajador.buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String obtener_rut = panelEliminarTrabajador.campo_rut.getText();
				int rut = Integer.parseInt(obtener_rut);
				
				try {
					arreglo = FileUtil.read();
				} catch (IOException | ParseException e1) {
					// TODO Bloque catch generado automáticamente Si falla al leer el archivo
					e1.printStackTrace();
				}
				
				arreglo = trabajador.deleteTrabajadorByRut(arreglo, rut);
				
				//arreglo.remove();
				FileUtil.write(arreglo);
				
				JSONObject trab = trabajador.getTrabajadorByRut(arreglo, rut);
				
				if(trab != null) {
					panelEliminarTrabajador.campoNombre.setText(trab.get("nombre").toString());
					panelEliminarTrabajador.campoApellidoPaterno.setText(trab.get("nombre").toString());
					panelEliminarTrabajador.campoApellidoMaterno.setText(trab.get("nombre").toString());
					panelEliminarTrabajador.campoNacimiento.setText(trab.get("nombre").toString());
					panelEliminarTrabajador.campo_contrato.setText(trab.get("nombre").toString());
					panelEliminarTrabajador.camposalario.setText(trab.get("nombre").toString());
					panelEliminarTrabajador.campodepartamento.setText(trab.get("nombre").toString());
				
					JOptionPane.showMessageDialog(null, "Se ha encontrado el trabajador");
				}else {
					JOptionPane.showMessageDialog(null, "El trabajador no existe");
				}
					
			}
		});
		
		panelEliminarTrabajador.eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//lista_trabajadores.remove(pos_eliminar);
				panelEliminarTrabajador.campoNombre.setText("");
				panelEliminarTrabajador.campoApellidoPaterno.setText("");
				panelEliminarTrabajador.campoApellidoMaterno.setText("");
				panelEliminarTrabajador.campoNacimiento.setText("");
				panelEliminarTrabajador.campo_contrato.setText("");
				panelEliminarTrabajador.camposalario.setText("");
				panelEliminarTrabajador.campodepartamento.setText("");
				JOptionPane.showMessageDialog(null,"Trabajador eliminado exitosamente");
				
				FileUtil.write(arreglo);
			}
		});
		
	}
		
	private void consultar_datos() {
		
		panelConsultarTrabajador.buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String obtener_rut = panelConsultarTrabajador.campo_rut.getText();
				int rut = Integer.parseInt(obtener_rut);
				
				try {
					arreglo = FileUtil.read();
				} catch (IOException | ParseException e1) {
					// TODO Bloque catch generado automáticamente
					e1.printStackTrace();
				}
				
				System.out.println("arreglo: " + arreglo);
				System.out.println("rut: " + rut);
				
				//JSONObject trab = trabajador.getTrabajadorByRut(arreglo, rut);
				
				JSONObject trab = null;
				for(int i=0; i<arreglo.length(); i++) {
					if( ((JSONObject) arreglo.getJSONObject(i).get("rut")) == rut ) {				
						trab = (JSONObject) arreglo.getJSONObject(i).get("rut");
					}
				}
				
				
				if (trab != null) {
					
					panelConsultarTrabajador.campoNombre.setText(trab.get("nombre").toString());
					panelConsultarTrabajador.campoNombre.setEditable(false);
					
					panelConsultarTrabajador.campoApellidoPaterno.setText(trab.get("nombre").toString());
					panelConsultarTrabajador.campoApellidoPaterno.setEditable(false);
					
					panelConsultarTrabajador.campoApellidoMaterno.setText(trab.get("nombre").toString());
					panelConsultarTrabajador.campoApellidoMaterno.setEditable(false);
					
					panelConsultarTrabajador.campoNacimiento.setText(trab.get("nombre").toString());
					panelConsultarTrabajador.campoNacimiento.setEditable(false);
					
					panelConsultarTrabajador.campo_contrato.setText(trab.get("nombre").toString());
					panelConsultarTrabajador.campo_contrato.setEditable(false);
					
					panelConsultarTrabajador.camposalario.setText(trab.get("nombre").toString());
					panelConsultarTrabajador.camposalario.setEditable(false);
					
					panelConsultarTrabajador.campodepartamento.setText(trab.get("nombre").toString());
					panelConsultarTrabajador.campodepartamento.setEditable(false);
					
					JOptionPane.showMessageDialog(null,"Trabajador Encontrado exitosamente");
				}else {
					JOptionPane.showMessageDialog(null,"Error al encontrar trabajador,Intente Nuevamente");
				}
			}
		});
		
		}
	
		public int getcantidad_trabajadores() {
			return largo;
		}
			
	}
	

	
