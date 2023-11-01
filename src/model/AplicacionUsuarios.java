package model;

import gui.VentanaBorrarUsuario;
import gui.VentanaCambiarContraseña;
import gui.VentanaCrearUsuario;
import gui.VentanaInicioSesion;
import gui.VentanaMenuUsuario;
import gui.VentanaVerUsuario;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.io.*;

public class AplicacionUsuarios {

	private final String RUTA_FICHERO = "./ProyectoJJose.json";
	private VentanaInicioSesion ventanaInicioSesion;
	private VentanaCrearUsuario ventanaCrearUsuario;
	private VentanaMenuUsuario ventanaMenuUsuario;
	private VentanaVerUsuario ventanaVerUsuario;
	private VentanaCambiarContraseña ventanaCambiarContraseña;
	private VentanaBorrarUsuario ventanaBorrarUsuario;
	private File jJoseFile;

	private void crearFicheroJson() {

			/* preparamos para crear el archivo Json */
			this.jJoseFile =new File(RUTA_FICHERO);
			if(jJoseFile.exists()){
				System.out.println("El archivo ya existe");
			}else System.out.println("Archivo creado");
	}

	private org.json.JSONArray obtenerUsuariosJson() {
		org.json.JSONArray listaUsuariosDelJJose;
		JSONParser jsonParser=new JSONParser();
		try(FileReader reader=new FileReader(jJoseFile)){
			Object obj=jsonParser.parse(reader);
			listaUsuariosDelJJose=(org.json.JSONArray) obj;

				/*
			for(Object usurio: listaUsuariosDelJJose){
				System.out.println(usurio);
			}

				 */

		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return listaUsuariosDelJJose;
	}

	private int obtenerPosicionUsuario(String nombreUsuario, JSONArray usuarios) {
		for(int i=0; i<usuarios.size(); i++){
			JSONObject usuarioDelArray=(JSONObject) usuarios.get(i);

			if(usuarioDelArray.get("nombre").equals(nombreUsuario))
				return i;
		}
		return -1;
	}

	private JSONObject obtenerUsuarioJson(String nombreUsuario) {
		org.json.JSONArray listaUsuariosDelJJose=obtenerUsuariosJson();

			for(int i=0; i<listaUsuariosDelJJose.length(); i++){
				JSONObject usuarioDelArray=(JSONObject) listaUsuariosDelJJose.get(i);
				if(usuarioDelArray.get("nombre").equals(nombreUsuario)){
					return usuarioDelArray;
				}
			}
			return null;

	}

		public void ejecutar() {
		ventanaInicioSesion=new VentanaInicioSesion(this);
		ventanaInicioSesion.setVisible(true);

		while(ventanaInicioSesion.isVisible()){
			Thread.yield();
		}

	}

	public void iniciarSesion(String nombreUsuario, String contraseñaUsuario) {
		crearFicheroJson();
		JSONObject object= obtenerUsuarioJson(nombreUsuario);
		String nombre=object.get("nombre").toString();
		String pass=object.get("contraseña").toString();
		if(nombre.equals(nombreUsuario) && pass==contraseñaUsuario){
			VentanaMenuUsuario ventanaMenuUsuario1=new VentanaMenuUsuario(this,nombreUsuario);
			ventanaMenuUsuario1.setVisible(true);
		}

	}

	public void cerrarSesion() {
		if(ventanaInicioSesion==null){
			JOptionPane.showConfirmDialog(null,"No existes, mi pana.","Error?",JOptionPane.ERROR_MESSAGE);
		JOptionPane.showMessageDialog(null,"Se cerró la cosita esta de la sesion","CerrarSestion?",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void crearUsuario(String nombre, String contraseña, String edad, String correo) {
			crearFicheroJson();
			//Aqui como que solo ponemos lo que vamos a escribir. Pero todavía no se seleccionó el documento en si.
			JSONObject jJose=new JSONObject();
		jJose.put("nombre", nombre);
		jJose.put("contraseña", contraseña);
		jJose.put("edad",edad);
		jJose.put("correo",correo);

		try {
			FileWriter escritor = new FileWriter(jJoseFile);

			escritor.write(jJose.toJSONString());
			escritor.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}


	}

	public void cambiarContraseña(String nombreUsuario, String nuevaContraseña) {
		org.json.JSONArray listaUsuariosDelJJose=obtenerUsuariosJson();
		for(int i=0;i<listaUsuariosDelJJose.length();i++){
			JSONObject obj= (JSONObject) listaUsuariosDelJJose.get(i);
			if(obj.get("nombre").equals(nombreUsuario)){
				obj.put("contraseña",nuevaContraseña);
			}

		}
	}

	public void borrarUsuario(String nombreUsuario) {
		org.json.JSONArray listaUsuariosDelJJose=obtenerUsuariosJson();
		for(int i=0;i<listaUsuariosDelJJose.length();i++){
			JSONObject obj= (JSONObject) listaUsuariosDelJJose.get(i);
			if(obj.get("nombre").equals(nombreUsuario)){
				listaUsuariosDelJJose.remove(i);
			}

		}
	}

	public void mostrarVentanaCrearUsuario() {
		VentanaCrearUsuario ventanaCrearUsuario1=new VentanaCrearUsuario(this);

		ventanaCrearUsuario1.setVisible(true);
	}

	public void mostrarVentanaVerUsuario(String nombreUsuario) {
		JSONObject object=obtenerUsuarioJson(nombreUsuario);
		String edad= object.get("edad").toString();
		String correo= object.get("correo").toString();

		VentanaVerUsuario ventanaVerUsuario1=new VentanaVerUsuario(this,nombreUsuario,edad,correo);
		ventanaVerUsuario1.setVisible(true);
	}

	public void mostrarVentanaCambiarContraseña(String nombreUsuario) {
		VentanaCambiarContraseña ventanaCambiarContraseña1= new VentanaCambiarContraseña(this,nombreUsuario);
		ventanaCambiarContraseña1.setVisible(true);

	}

	public void mostrarVentanaBorrarUsuario(String nombreUsuario) {
		VentanaBorrarUsuario ventanaBorrarUsuario1=new VentanaBorrarUsuario(this,nombreUsuario);
		ventanaBorrarUsuario1.setVisible(true);

	}


}
