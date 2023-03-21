package controllers;

import com.conversor.convesor_jevs.Conversion;
import com.conversor.convesor_jevs.FactorConversion;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

public class Conversiones {

    public interface ConversionFuncional {
        float convertir(float valor, float tasaCambio);
    }
    public void botonConvertir(ComboBox selConvReq, ObservableList data, TextField inUser, TextArea outUser, ConversionFuncional conversionFuncional){

        try {
            //Obtener la tasa de cambio
            int indice = selConvReq.getSelectionModel().getSelectedIndex();
            FactorConversion tasaCambio = (FactorConversion) data.get(indice);

            //String tasaCambioStr = tasaCambio.getTasaCambio();
            float tasaCambioFloat = Float.parseFloat(tasaCambio.getFactorConversion());

            //Obtener el número ingresado por el usuario
            float valor = Float.parseFloat(inUser.getText());

            //Analizando si la entrada del usuario es un número negativo
            if(valor < 0){
                throw new NumberFormatException();
            }
            Conversion conversion = new Conversion();

            //Poner formato de moneda
            String resultado = conversion.formatoMoneda(conversionFuncional.convertir(valor, tasaCambioFloat));
            outUser.setText(resultado);
        }catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alerta de advertencia");
            alert.setHeaderText("Por favor ingrese un valor valido en el campo vacio!");
            alert.setContentText("¡Solo se aceptan valores numéricos positivos!");
            alert.showAndWait();
            inUser.clear();
        }

    }
}
