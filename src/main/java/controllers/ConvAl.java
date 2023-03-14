package controllers;

import com.conversor.convesor_jevs.Conversion;
import com.conversor.convesor_jevs.FactorConversion;
import com.conversor.convesor_jevs.LlenadoMatriz;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ConvAl extends MainController implements Initializable {

    @FXML
    private TableView tbAlmacenamiento;
    @FXML
    private TableColumn<FactorConversion, String> columnaNombre;
    @FXML
    private TableColumn<FactorConversion, String> columnaEquivalencia;
    @FXML
    private ComboBox selectAlmacenamiento;
    @FXML
    private TextField inUserA;

    //Inicializando arreglo para factor de conversión
    private ObservableList<FactorConversion> datos = FXCollections.observableArrayList();

    //Inicializando arreglo para el llenado de la tabla con las equivalencias
    private ObservableList<LlenadoMatriz> datosRes = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Arreglo con el factor de conversión

        datos.add(new FactorConversion("Byte", "1"));
        datos.add(new FactorConversion("Kilobyte", "1024"));
        datos.add(new FactorConversion("Megabyte", "1048576"));
        datos.add(new FactorConversion("Gigabyte", "1073741824"));
        datos.add(new FactorConversion("Terabyte", "1099511627776"));

        //llenado de combobox, entrada de usuario tipo de unidad
        List<String> opcionesAlmacenamiento = new ArrayList<>();

        for (FactorConversion nombre : datos) {
            opcionesAlmacenamiento.add(nombre.getNombre());
        }
        selectAlmacenamiento.setItems(FXCollections.observableArrayList(opcionesAlmacenamiento));
        selectAlmacenamiento.getSelectionModel().selectFirst();
    }

    @FXML
    void ConvertirAl(ActionEvent event){
    //CONVERSION
        try{
            //limpiar la tabla cada que el usuario presiona el botón
            tbAlmacenamiento.getItems().clear();

            //Convertir la entrada del usuario a bytes
            int indice = selectAlmacenamiento.getSelectionModel().getSelectedIndex();
            FactorConversion factorConversion = (FactorConversion) datos.get(indice);
            float factorConversionFloat = Float.parseFloat(factorConversion.getFactorConversion());
            float valor = Float.parseFloat(inUserA.getText());
            Conversion bytes = new Conversion();
            float resBytes = bytes.conversionInversa(valor, factorConversionFloat);

            //llenar la tabla convirtiendo de bytes a todas las unidades disponibles
            for (int i=1; i<=5; i++){
                FactorConversion factorConvAux = datos.get(i-1);
                float factorConversionFloatAux = Float.parseFloat(factorConvAux.getFactorConversion());
                String nombreAux = factorConvAux.getNombre();
                Conversion aux = new Conversion();
                float resultado = aux.conversionEstandar(resBytes,factorConversionFloatAux);
                datosRes.add(i-1,new LlenadoMatriz(resultado, nombreAux));
            }

            columnaNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
            columnaEquivalencia.setCellValueFactory(new PropertyValueFactory<>("resultadoConversion"));

            tbAlmacenamiento.setItems(datosRes);
        //catch para enviar mensaje de alerta si el usuario No introduce valor admitido
        }catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerta de advertencia");
            alert.setHeaderText("Por favor ingrese un valor valido en el campo vacio!");
            alert.setContentText("¡Solo se aceptan valores numéricos!");
            alert.showAndWait();
        }
    }
}
