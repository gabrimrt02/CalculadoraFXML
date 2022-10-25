package dad.calculadora;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class Controller implements Initializable {

    @FXML
    private GridPane view;

    @FXML
    private TextField salida;

    @FXML
    private Button numero9, numero8, numero7, numero6, numero5, numero4, numero3, numero2, numero1, numero0;

    @FXML
    private Button coma, sumar, restar, multiplicar, dividir, igual, borrar, borrarTodo;

    private Model model = new Model();

    // Atributos del controlador
    private Boolean nuevoOperando;

    private DoubleProperty operando = new SimpleDoubleProperty(0.0);
    private StringProperty operador = new SimpleStringProperty();

    private final char COMA = '.';
    private final char SUMA = '+';
    private final char RESTA = '-';
    private final char MULTIPLICAR = '*';
    private final char DIVIDIR = '/';
    private final char IGUAL = '=';

    // Constructor que inicia el loader de XML
    public Controller() throws IOException {

        // CARGAR EL CONTROLLER
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/view.fxml"));
        loader.setController(this);
        loader.load();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Button[] botonesNumeros = {
                numero0, numero1, numero2, numero3, numero4,
                numero5, numero6, numero7, numero8, numero9
        };

        Button[] botonesOperaciones = {
                sumar, restar, multiplicar, dividir, igual
        };

        // Bind
        salida.textProperty().bindBidirectional(model.pantallaProperty());

        for (Button boton : botonesNumeros) {
            boton.setOnAction(this::onPulsarNumeroAction);
        }

        for (Button boton : botonesOperaciones) {
            boton.setOnAction(this::onPulsarOperador);
        }

        coma.setOnAction(e -> {
            insertarComa();
        });

        borrar.setOnAction(e -> {
            borrar();
        });

        borrarTodo.setOnAction(e -> {
            borrarTodo();
        });

        // Inicio de la pantalla
        model.setPantalla("");
        borrar();

    }

    public GridPane getView() {
        return this.view;
    }

    private void onPulsarNumeroAction(ActionEvent e) {

        insertar(((Button) e.getSource()).getText().charAt(0));

    }

    private void onPulsarOperador(ActionEvent e) {
        operar(((Button) e.getSource()).getText().charAt(0));
    }

    private void borrar() {
        operando.set(0.0);
        operador.set("" + IGUAL);
        borrarTodo();
    }

    private void borrarTodo() {
        nuevoOperando = true;
        model.setPantalla("");
    }

    private void operar(char operador) {
        nuevoOperando = true;
        double operando2 = Double.parseDouble(model.getPantalla());
        switch (this.operador.get().charAt(0)) {
            case SUMA:
                operando.set((operando.add(operando2)).get());
                break;
            case RESTA:
                operando.set((operando.subtract(operando2)).get());
                break;
            case MULTIPLICAR:
                operando.set((operando.multiply(operando2)).get());
                break;
            case DIVIDIR:
                operando.set((operando.divide(operando2)).get());
                break;
            case IGUAL:
                operando.set(operando2);
                break;
        }
        this.operador.set("" + operador);
        model.setPantalla("" + operando.get());
    }

    private void insertarComa() {
        if (!model.getPantalla().contains("" + COMA)) {
            model.setPantalla(model.getPantalla() + COMA);
        }
    }

    private void insertar(char digito) {
        if (digito >= '0' && digito <= '9') {
            if (nuevoOperando) {
                nuevoOperando = false;
                model.setPantalla("");
            }
            model.setPantalla(model.getPantalla() + digito);
        } else if (digito == COMA) {
            insertarComa();
        }
    }
}
