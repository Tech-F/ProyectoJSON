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

import java.io.File;
import java.io.FileWriter;

public class AplicacionUsuarios {

	private final String RUTA_FICHERO = "";
	private VentanaInicioSesion ventanaInicioSesion;
	private VentanaCrearUsuario ventanaCrearUsuario;
	private VentanaMenuUsuario ventanaMenuUsuario;
	private VentanaVerUsuario ventanaVerUsuario;
	private VentanaCambiarContraseña ventanaCambiarContraseña;
	private VentanaBorrarUsuario ventanaBorrarUsuario;

	private void crearFicheroJson() {

			/* preparamos para crear el archivo Json */
			File JJose=new File("./ProyectoJJose.json");
			if(JJose.exists()){
				System.out.println("El archivo ya existe");
			}else System.out.println("Archivo creado");
	}

	private JSONArray obtenerUsuariosJson() {

	}

	private int obtenerPosicionUsuario(String nombreUsuario, JSONArray usuarios) {

	}

	private JSONObject obtenerUsuarioJson(String nombreUsuario) {

	}

	public void ejecutar() {

	}

	public void iniciarSesion(String nombreUsuario, String contraseñaUsuario) {

	}

	public void cerrarSesion() {

	}

	public void crearUsuario(String nombre, String contraseña, String edad, String correo) {

			//Aqui como que solo ponemos lo que vamos a escribir. Pero todavía no se seleccionó el documento en si.
			JSONObject JJose=new JSONObject();

	}

	public void cambiarContraseña(String nombreUsuario, String nuevaContraseña) {

	}

	public void borrarUsuario(String nombreUsuario) {

	}

	public void mostrarVentanaCrearUsuario() {

	}

	public void mostrarVentanaVerUsuario(String nombreUsuario) {

	}

	public void mostrarVentanaCambiarContraseña(String nombreUsuario) {

	}

	public void mostrarVentanaBorrarUsuario(String nombreUsuario) {

	}


}
