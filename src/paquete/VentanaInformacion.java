package paquete;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class VentanaInformacion extends JFrame{
	public PanelInformacionEmpresa panel_informacion;
	private JScrollPane scrollPaneles;

	public VentanaInformacion(int cantidad_departamentos,int cantidad_trabajadores) {
		setSize(780,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(false);
		setLocationRelativeTo(null);
		cargarComponentes(cantidad_departamentos,cantidad_trabajadores);
	
	}
	public void cargarComponentes(int cantidad_departamentos,int cantidad_trabajadores) {
		panel_informacion = new PanelInformacionEmpresa(cantidad_departamentos,cantidad_trabajadores);
		scrollPaneles = new JScrollPane();
		scrollPaneles.setBounds(1,1,779,500);
		add(scrollPaneles);
		scrollPaneles.setViewportView(panel_informacion);
		
	}
	

}
