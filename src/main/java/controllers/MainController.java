package controllers;

import com.conversor.convesor_jevs.Conversion;
import com.conversor.convesor_jevs.Main;
import com.conversor.convesor_jevs.FactorConversion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    //TABLA CON LAS TASAS DE CAMBIO
    @FXML
    private TableView tbTasas;
    @FXML
    private TableColumn<FactorConversion, String> tbNombreMoneda;
    @FXML
    private TableColumn<FactorConversion, String> tbTasaConversion;
    @FXML
    private Button btnExit;
    @FXML
    private TabPane menuConv;
    @FXML
    private AnchorPane Bienvenida;
    @FXML
    private ComboBox selectMoneda;
    @FXML
    private ComboBox selectMoneda2;
    @FXML
    private TextField inUserM;
    @FXML
    private TextArea outUserM;
    @FXML
    private TextField inUserM2;
    @FXML
    private TextArea outUserM2;
    @FXML
    private Button btnConvAlma;

    private ObservableList<FactorConversion> data;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Tabla de tasas de cambio
        tbNombreMoneda.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
        tbTasaConversion.setCellValueFactory(new PropertyValueFactory<>("FactorConversion"));

        data = FXCollections.observableArrayList(
                new FactorConversion("Dollar Americano", "4878.78"),
                new FactorConversion("Euro", "5144.80"),
                new FactorConversion("Libra Esterlina", "5796.47"),
                new FactorConversion("Yen", "35.47"),
                new FactorConversion("Won", "3.69")
        );
        tbTasas.setItems(data);

        //No permite el ordenamiento por columnas
        tbTasas.setSortPolicy(null);

        //Inicializar activando el msj de bienvenida y desactivando el panel de conversión moneda
        Bienvenida.setVisible(true);
        menuConv.setVisible(false);


        //Rellenando las cajas de selección de moneda

        // Crear una nueva lista para almacenar solo los tipos de monedas
        List<String> opcionesMoneda = new ArrayList<>();

        // Recorrer cada elemento en el ArrayList y agregar solo los tipos de monedas a la nueva lista
        for (FactorConversion moneda : data) {
            opcionesMoneda.add(moneda.getNombre());
        }

        // Llenar el ChoiceBox con los tipos de monedas
        selectMoneda.setItems(FXCollections.observableArrayList(opcionesMoneda));
        selectMoneda2.setItems(FXCollections.observableArrayList(opcionesMoneda));

        // Seleccionar el primer elemento en el ChoiceBox
        selectMoneda.getSelectionModel().selectFirst();
        selectMoneda2.getSelectionModel().selectFirst();
    }

    //BOTÓN EXIT APLICACIÓN
    @FXML
    void CloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) btnExit.getScene().getWindow();
        stage.close();
    }

    //BOTÓN CONVERSOR DIVISAS
    @FXML
    void MostrarMenuConv(ActionEvent event) {
        Bienvenida.setVisible(false);
        menuConv.setVisible(true);
    }

    //BOTÓN CONVERTIR DIVISA Pesos a Extranjera

    @FXML
    void Convertir(ActionEvent event) {

        Conversiones resultado = new Conversiones();
        resultado.botonConvertir(selectMoneda, data, inUserM, outUserM, (valor, tasaCambio) -> {
            Conversion conversion = new Conversion();
            return conversion.conversionEstandar(valor, tasaCambio);
        });
    }

    //BOTÓN CONVERTIR DIVISA Extranjera a Pesos

    @FXML
    void Convertir2(ActionEvent event) {
        Conversiones resultado = new Conversiones();
        resultado.botonConvertir(selectMoneda2, data, inUserM2, outUserM2, (valor, tasaCambio) -> {
            Conversion conversion = new Conversion();
            return conversion.conversionInversa(valor, tasaCambio);
        });

    }
    //BOTÓN PASAR A VENTANA DE CONVERSIÓN UNIDADES DE ALMACENAMIENTO
    @FXML
    void handleButtonClick(ActionEvent event) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("convAl.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            /* Si quiero reemplazar la ventana que se encuentra abierta por esta ventana
            Se debe obtener la ventana activa y reemplazarla con este código*/
            //Stage stage = (Stage) btnConvAlma.getScene().getWindow();

            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);

            // Mostrar la nueva escena en la ventana principal
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}