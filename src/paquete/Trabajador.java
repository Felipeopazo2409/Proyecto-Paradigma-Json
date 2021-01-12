package paquete;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Trabajador {
	private String nombre;
	private String apellido_paterno;
	private String apellido_materno;
	private int rut;
	private String tipo_contrato;
	private int salario;
	private  String fecha_de_nacimiento;
	private String departamento;
	public Trabajador(String nombre,String a_paterno,String a_materno,int rut,String fecha_nacimiento,String tipo_contrato,int salario,String departamento) {
		this.nombre = nombre;
		this.apellido_paterno = a_paterno;
		this.apellido_materno = a_materno;
		this.rut = rut;
		this.tipo_contrato = tipo_contrato;
		this.salario = salario;
		this.fecha_de_nacimiento = fecha_nacimiento;
		this.departamento = departamento;
	}
	public void setNombre (String nombre) {
		this.nombre = nombre;
	}
	public String getNombre() {
		return nombre;
	}
	public void setAPaterno(String apellido) {
		this.apellido_paterno = apellido;
	}
	public String getAPaterno() {
		return apellido_paterno;
	}
	public void setAMaterno(String apellido) {
		this.apellido_materno = apellido;
	}
	public String getAMaterno() {
		return apellido_materno;
	}
	public void setRut(int rut) {
		this.rut = rut;
	}
	public int getRut() {
		return rut;
	}
	public void setContrato(String contrato) {
		this.tipo_contrato = contrato;
	}
	public String getContrato() {
		return tipo_contrato;
	}
	public void setFecha(String fecha) {
		this.fecha_de_nacimiento = fecha;
	}
	
	public String getFecha() {
		return fecha_de_nacimiento;
	}
	public void setSalario(int salario) {
		this.salario = salario;
	}
	public int getSalario() {
		return salario;
	}
	public void setDepartamento(String Departamento) {
		this.departamento = Departamento;
	}
	public String getDepartamento() {
		return departamento;
	}
	
	public Integer getIndexByRut(JSONArray arr, Integer rut) {
		if(arr != null) {
			for(int i=0; i < arr.length(); i++) {
				/*
				if(arr.getJSONArray(i).getBoolean(index) == rut ) {				
					return (JSONObject) lista_trabajadores.get("rut");
				}
				*/
			}
		}
		return null;
	}
	
	
	public JSONObject getTrabajadorByRut(JSONArray lista_trabajadores, Integer rut) {
		
		System.out.println("getTrabajadorByRut");
		for(int i=0; i<lista_trabajadores.length(); i++) {
			System.out.println("lista_trabajadores.getJSONObject(i).get(\"rut\"): " + lista_trabajadores.getJSONObject(i).get("rut"));
			if(lista_trabajadores.getJSONObject(i).get("rut") == rut ) {				
				return (JSONObject) lista_trabajadores.getJSONObject(i).get("rut");
			}
		}
		
		return null;
	}
	
	public JSONArray deleteTrabajadorByRut(JSONArray lista_trabajadores, Integer rut) {
		if(lista_trabajadores != null) {
			for(int i=0; i<lista_trabajadores.length(); i++) {
				if(lista_trabajadores.getJSONObject(i).get("rut") == rut ) {				
					lista_trabajadores.getJSONObject(i).remove("rut");
				}
			}
			return lista_trabajadores;
		}
		return null;
	}
	
	
}
