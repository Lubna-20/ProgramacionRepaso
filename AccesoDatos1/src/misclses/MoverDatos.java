package misclses;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;
import java.security.PrivateKey;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class MoverDatos {
	static ArrayList<Ciclos> listadoCiclos = new ArrayList<Ciclos>();

	public static void main(String[] args) {
		final String url = "jdbc:mysql://localhost:3306/augustobriga";
		final String usuario = "root";
		final String pws = "";

		// ESTABLECER CONEXION CON EL SERVIDOR DE BASE DE DATOS
		try (Connection conexion = DriverManager.getConnection(url, usuario, pws)) {
			cargarAlumnos(conexion);
		} catch (SQLException ex) {
			System.out.println("Error de Driver o permiso");
		}

	}

	private static void cargarAlumnos(Connection conexion) {
		String cadenaSql = "select * from CICLOS";
		if (conexion == null) {
			System.out.println("Error en la conexion de la BD");
		} else {
			try {
				PreparedStatement psStatement = conexion.prepareStatement(cadenaSql);
				ResultSet resultado = psStatement.executeQuery();
				// LA CONSULTA YA SE HA EJECUTADO
				// LOS DATOS YA ESTAN EN RESULTADO
				// NO ESTAN PROCESADOS
				while (resultado.next()) {
					String idAl = resultado.getString("idAlumno");
					String nombre = resultado.getString("nombre");
					String correo = resultado.getString("correo");
					String ciclo = resultado.getString("ciclo");
					String turno = resultado.getString("turno");
					String ies = resultado.getString("IES");
					Ciclos cc = new Ciclos(idAl, nombre, correo, ciclo, turno, ies);
					listadoCiclos.add(cc);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		mostrarContenidoLista();
		volcarBD_ficheroCSV();
		volcar_ficheroCSV_ficheroBinario();
		volcar_ficheroBIN_BD(conexion);

	}

	private static void volcar_ficheroBIN_BD(Connection conexion) {
		ArrayList<Ciclos> listaBIN = new ArrayList<Ciclos>();
		// LEER EL FICHERO DE ALUMNOS ALUMNOS.BIN
		FileInputStream fileInput;
		try {
			fileInput = new FileInputStream("alumnos.bin");
			BufferedInputStream bfinput = new BufferedInputStream(fileInput);
			DataInputStream ficheroBinario = new DataInputStream(bfinput);
			// LEER EL FICHERO BINARIO
			while (ficheroBinario.available() > 0) {
				try {
					String idalumno = ficheroBinario.readUTF();
					String nombre = ficheroBinario.readUTF();
					String correo = ficheroBinario.readUTF();
					String ciclo = ficheroBinario.readUTF();
					String turno = ficheroBinario.readUTF();
					String ies = ficheroBinario.readUTF();
					Ciclos ciclo2 = new Ciclos(idalumno, nombre, correo, ciclo, turno, ies);
					listaBIN.add(ciclo2);

				} catch (FileNotFoundException e) {
					System.out.println("Error al leer el fichero binario");
					e.printStackTrace();
				}

			}

		} catch (IOException e) {
			System.out.println("Error al leer el fichero binario");
			e.printStackTrace();
		}
		for (Ciclos ciclos : listaBIN) {
			String cadenasql = "insert into CICLOSAux values (?,?,?,?,?,?)";
			try {
				PreparedStatement psStatement = conexion.prepareStatement(cadenasql);
				psStatement.setString(1, ciclos.getIdalumno());
				psStatement.setString(2, ciclos.getNombre());
				psStatement.setString(3, ciclos.getCorreo());
				psStatement.setString(4, ciclos.getCiclo());
				psStatement.setString(5, ciclos.getTurno());
				psStatement.setString(6, ciclos.getIes());
				psStatement.executeUpdate();
			} catch (SQLException e) {
				System.out.println("Error al insertar en la tabla alumnosAux");
				e.printStackTrace();
			}
		}

	}

	private static void volcar_ficheroCSV_ficheroBinario() {
		try {
			// DECLARACION EN MODO LECTURA DEL FICHERO TEXTO/CSV
			FileReader fr = new FileReader("alumno.csv");
			BufferedReader fichero = new BufferedReader(fr);
			// DECLARACION EN MODO ESCRITURA DEL FICHERO BINARIO
			FileOutputStream fb = new FileOutputStream("alumnos.bin");
			// PERMITIR ESCRIBIR EN EL FICHERO BINARIO LOS DATOS
			// PRIMITIVOS : INT,STRING, DOUBLE... PARA MANTENER
			DataOutputStream ficheroBinario = new DataOutputStream(fb);
			String linea;
			try {
				while ((linea = fichero.readLine()) != null) {
					// POR CADA LINEA TENGO UN ARRAY DE SRING
					String[] campos = linea.split(";");
					Ciclos ciclo = new Ciclos(campos[0], campos[1], campos[2], campos[3], campos[4], campos[5]);
					ficheroBinario.writeUTF(ciclo.getIdalumno());
					ficheroBinario.writeUTF(ciclo.getNombre());
					ficheroBinario.writeUTF(ciclo.getCorreo());
					ficheroBinario.writeUTF(ciclo.getCiclo());
					ficheroBinario.writeUTF(ciclo.getTurno());
					ficheroBinario.writeUTF(ciclo.getIes());

				}
				ficheroBinario.close();
				fichero.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}

	}

	private static void volcarBD_ficheroCSV() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Telea el nombre/ruta del fichero: ");

		String rutaCSV = sc.next();
		try {
			// PERMITE ESCRIBIR CARACTERES EN UN FICHERO DE TEXTO
			FileWriter fw = new FileWriter(rutaCSV);
			// ESCRIBIR TEXTO EN UN FLUJO DE DATOS (ALMACENARLO EN BIFFER)
			BufferedWriter ficheroCSV = new BufferedWriter(fw);
			for (Ciclos ciclos : listadoCiclos) {
				ficheroCSV.write(ciclos.getIdalumno() + ";" + ciclos.getNombre() + ";" + ciclos.getCorreo() + ";"
						+ ciclos.getCiclo() + ";" + ciclos.getTurno() + ";" + ciclos.getIes() + "\n");
			}
			ficheroCSV.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void mostrarContenidoLista() {
		for (Ciclos ciclos : listadoCiclos) {
			System.out.println(ciclos.getNombre());

		}
	}

}