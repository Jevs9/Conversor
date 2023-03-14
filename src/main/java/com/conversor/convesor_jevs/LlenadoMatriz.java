package com.conversor.convesor_jevs;
import javafx.application.Application;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.stage.Stage;

public class LlenadoMatriz extends Application {
    SimpleStringProperty nombre;
    SimpleFloatProperty resultadoConversion;

    public LlenadoMatriz(float resultadoConversion, String nombre) {
        this.nombre = new SimpleStringProperty(nombre);
        this.resultadoConversion = new SimpleFloatProperty(resultadoConversion);
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public float getResultadoConversion() {
        return resultadoConversion.get();
    }

    public void setResultadoConversion(float resultadoConversion) {
        this.resultadoConversion.set(resultadoConversion);
    }


    public SimpleStringProperty nombreProperty() {
        return nombre;
    }

    public SimpleFloatProperty resultadoConversionProperty() {
        return resultadoConversion;
    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}
