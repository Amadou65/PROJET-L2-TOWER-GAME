package game.listchooser.util;


import java.io.InputStream;
import java.util.Scanner;

/**
 * A utility class for entering strings or integers on standard input
 */

public class Input {
	
	private static InputStream currentInput = System.in;
	private static Scanner scanner = new Scanner(System.in);

	public Input() {
	}
	
	/**
	 * Allows entry of a string on standard input
	 * 
	 * @return the string entered
	 */
	public static String readString() {
		return sharedScanner().next();
	}

	
	/**
	 * Allows entry of an int on standard input
	 * 
	 * @return the int entered
	 */
	public static int readInt() throws java.io.IOException {
		try {
			return sharedScanner().nextInt();
		} catch (Exception e) {
			if (sharedScanner().hasNext()) {
				sharedScanner().next();
			}
			throw new java.io.IOException();
		}
	}
	
	private static Scanner sharedScanner() {
		if (currentInput != System.in) {
			currentInput = System.in;
			scanner = new Scanner(System.in);
		}
		return scanner;
	}
	
} // Input

//
//
//import java.util.Scanner;
//
///**
// * Une classe utilitaire pour la saisie de cha�nes ou d'entiers sur l'entr�e
// * standard.
// */
//
//public class Input {
//	private static Scanner scanner = new Scanner(System.in);
//
//	/**
//	 * permet la saisie d'une chaîne sur l'entrée standard
//	 * 
//	 * @return la chaîne saisie
//	 */
//	public static String readString() {
//		return Input.scanner.next();
//	}
//
//	/**
//	 * permet la saisie d'un entier sur l'entrée standard
//	 * 
//	 * @return l'entier saisi
//	 */
//	public static int readInt() throws java.io.IOException {
//		try {
//			return Input.scanner.nextInt();
//		} catch (Exception e) {
//			Input.scanner.skip(".*");
//			throw new java.io.IOException();
//		}
//	}	
//	
//	// pour le test
//	public static void main(String[] args) {
//		try {
//			System.out.print(" chaine : ? ");
//			String chaineLue = Input.readString();
//			System.out.println("lue  => " + chaineLue);
//			System.out.print(" int : ? ");
//			int intLu = Input.readInt();
//			System.out.println("lue  => " + intLu);
//		} catch (java.io.IOException e) {
//		}
//	}
//} // Input
