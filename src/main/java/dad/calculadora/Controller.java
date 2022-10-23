package dad.calculadora;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

    private Modelo model = new Modelo();

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

        salida.textProperty().bindBidirectional(model.getPantalla());
        
        for(Button boton : botonesNumeros) {
            boton.setOnAction(this::onPulsarNumeroAction);
        }

        for(Button boton : botonesOperaciones) {
            boton.setOnAction(this::onPulsarOperador);
        }
        
        coma.setOnAction(e -> { model.insertarComa(); });

        borrar.setOnAction(e -> { model.borrar(); });

        borrarTodo.setOnAction(e -> { model.borrarTodo(); });
    
    }

    public GridPane getView() {
        return this.view;
    }

    private void onPulsarNumeroAction(ActionEvent e) {

        model.insertar(((Button) e.getSource()).getText().charAt(0));

    }

    private void onPulsarOperador(ActionEvent e) {
        model.operar(((Button) e.getSource()).getText().charAt(0));
    }

}
