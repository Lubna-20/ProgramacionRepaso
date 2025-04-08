package clases;

import java.util.Iterator;
import java.util.Scanner;

public class menuCadenas {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		int opcion =0;
		//menu : leer opciones con sc  y en bucle do-while
		

		do {
			System.out.println("Menu Cadenaas 1 --------------------------");
			System.out.println("1- Comprar dos cadenas ");
			System.out.println("2- Contar vocales ");
			System.out.println("3- Invertir una cadena ");
			System.out.println("4- contar Palabras ");
			System.out.println("5- Contar Apariciones Cadenas ");
			System.out.println("6- Sustituir una palabla por otra  ");
			System.out.println("7- Salir");
			System.out.println("Elegir opcion");
			if (sc.hasNext()) {
				opcion=sc.nextInt();
				sc.nextLine();
				switch (opcion ) {
				case 1: conprobarCadenas();
					break;
				case 2: contarVocales();
				break;
				case 3: invertirCadenas();
				break;
				case 4: contarPalabras();
				break;
				case 5: contarAparicionesCadenas();
				break;
				case 6: sustituirPalabras();
				break;
				case 7: System.out.println("Saliendo...");;
				break;
				default: System.out.println("Si es un numero pero no es valido");
				}
				
			} else {
				System.out.println("Error caracter no valido. Debes introducir un numero entero");
				sc.next();
				opcion=0;
			}
		} while(opcion!=7);
	}

	private static void sustituirPalabras() {
		// TODO Auto-generated method stub
		System.out.println("sustituirPalabras");
		
	}

	private static void contarAparicionesCadenas() {
		// TODO Auto-generated method stub
		System.out.println("contarAparicionesCadenas");
		
	}

	private static void contarPalabras() {
		// TODO Auto-generated method stub
		System.out.println("contarPalabras");
	}

	private static void invertirCadenas() {
		// TODO Auto-generated method stub
		System.out.println("invertirCadenas");
	}

	private static void contarVocales() {
		// TODO Auto-generated method stub
		System.out.println("contarVocales");
		String cadenaVocales="aeiouAEIOUáéíóúÁÉÍÓÚ";
		int contadorVocales=0;
		System.out.println("Teclea la cadena");
		String cadenaBuscar = sc.nextLine();
		char[] vectorCaracteres=cadenaBuscar.toCharArray();
		for(int i=0; i<vectorCaracteres.length;i++) {
			if (cadenaVocales.indexOf(vectorCaracteres[i])!=-1) {
				contadorVocales++;
			}
			
			/*for(char c:vectorCaracteres) {
				if (cadenaVocales.indexOf(c)!=-1) {
					contadorVocales++;
					
				}
				
			}*/
		}
		System.out.println("Total vocales " + contadorVocales);
		
				
	}

	private static void conprobarCadenas() {
		// TODO Auto-generated method stub
		System.out.println("conprobarCadenas");
		System.out.println("Teclea cadena 1");
		String cadena1 = sc.nextLine();
		System.out.println("Teclea cadena 2");
		String cadena2 = sc.nextLine();	
		if (cadena1.equalsIgnoreCase(cadena2)) {
			System.out.println(cadena1 + " es igual = " + cadena2);
		}else {
			System.out.println(cadena1 + " es distinta a  = " + cadena2);
		}
		
	}

}
