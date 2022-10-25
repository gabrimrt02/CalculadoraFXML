package dad.calculadora;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Model {
    
    private StringProperty pantalla = new SimpleStringProperty();

    public StringProperty pantallaProperty() {
        return pantalla;
    }

    public String getPantalla() {
        return pantalla.get();
    }

    public void setPantalla(String texto) {
        pantalla.set(texto);
    }

}
