package paquete;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PanelMostrarDepto extends JPanel{
	private JLabel titulo;
	public JButton volver_menu,mostrar_lista;
	public JLabel n_depto,nombre,cantidad_trabajadores;
	public PanelMostrarDepto() {
		setLayout(null);
		setBackground(Color.orange);
		labels();
		botones();
		
	}
	private void labels() {
		titulo = new JLabel("Mostrar Informacion de los departamentos");
		titulo.setBounds(180,20,450,40);
		titulo.setFont(new Font("Helvetica",Font.PLAIN,22));
		
		n_depto = new JLabel("N° Departamento");
		n_depto.setBounds(180, 70,200,40);
		
		nombre = new JLabel("Nombre");
		nombre.setBounds(350,70,200,40);
		
		cantidad_trabajadores = new JLabel("Cantidad Trabajadores");
		cantidad_trabajadores.setBounds(460,70,200,40);
		
		add(titulo);
		add(n_depto);
		add(nombre);
		add(cantidad_trabajadores);
	}
	private void botones() {
		mostrar_lista = new JButton("Mostrar Lista \nde departamentos");
		mostrar_lista.setBounds(150,350,200,30);
		mostrar_lista.setFont(new Font("Helvetica",Font.PLAIN,11));
		volver_menu = new JButton("Volver al menu Principal");
		volver_menu.setBounds(450,350,200,30);
		volver_menu.setFont(new Font("Helvetica",Font.PLAIN,11));
	
		
		
		
		add(volver_menu);
		add(mostrar_lista);
		
	}
}
