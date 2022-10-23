package dad.calculadora;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Implementaci�n de la l�gica de una calculadora.
 * @author Fran Vargas
 */
public class Modelo {
	
	public static final StringProperty IGUAL = new SimpleStringProperty("="); 
	public static final StringProperty SUMAR = new SimpleStringProperty("+"); 
	public static final StringProperty RESTAR = new SimpleStringProperty("-"); 
	public static final StringProperty DIVIDIR = new SimpleStringProperty("/"); 
	public static final StringProperty MULTIPLICAR = new SimpleStringProperty("*");
	
	private static final StringProperty COMA = new SimpleStringProperty("."); 
	
	private DoubleProperty operando = new SimpleDoubleProperty();
	private StringProperty operador;
	private Boolean nuevoOperando;
	private StringProperty pantalla = new SimpleStringProperty();
	
	public Modelo() {
		pantalla.set("");
		borrar();
	}
	
	/**
	 * Devuelve el contenido de la pantalla de la calculadora.
	 * @return Cadena de texto con el contenido de la pantalla de la calculdora.
	 */
	public StringProperty getPantalla() {
		return pantalla;
	}

	/**
	 * Inicializa por completo la calculadora, borrando la informaci�n que tiene memorizada y la pantalla.
	 */
	public void borrar() {
		operando = new SimpleDoubleProperty(0.0);
		operador = IGUAL;
		borrarTodo();
	}
	
	/**
	 * Borra lo que hay en la pantalla (el �ltimo operando introducido).
	 */
	public void borrarTodo() {
		nuevoOperando = true;
		pantalla.set("");;
	}
	
	/**
	 * Indica a la calculadora que realice la operaci�n indicada. 
	 * @param operador Operaci�n a realizar; usar una constante: IGUAL, SUMAR, RESTAR, MULTIPLCIAR, DIVIDIR.
	 */
	public void operar(char operador) {
		nuevoOperando = true;
		double operando2 = Double.parseDouble(pantalla.get());
		switch (this.operador.get().charAt(0)) {
			case '+': operando.set((operando.add(operando2)).get()); break;
			case '-': operando.set((operando.subtract(operando2)).get()); break;
			case '*': operando.set((operando.multiply(operando2)).get()); break;
			case '/': operando.set((operando.divide(operando2)).get()); break;
			case '=': operando.set(operando2); break;
		}
		this.operador.set("" + operador);
		pantalla.set("" + operando.get());
	}
	
	/**
	 * Inserta una coma en el operando actual (pantalla).
	 */
	public void insertarComa() {
		if (!pantalla.get().contains("" + COMA)) {
			pantalla.set(pantalla.get() + COMA.get());
		}
	}
	
	/**
	 * Inserta un d�gito en el operando actual (pantalla).
	 * @param digito Digito a introducir en la pantalla.
	 */
	public void insertar(char digito) {
		if (digito >= '0' && digito <= '9') {
			if (nuevoOperando) {
				nuevoOperando = false;
				pantalla.set("");
			}
			pantalla.set(pantalla.get() + digito);
		} else if (COMA.get().equals("" + digito)) {
			insertarComa();
		}
	}

	public StringProperty pantallaProperty() {
		return this.pantalla;
	}

}
