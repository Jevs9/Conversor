package com.conversor.convesor_jevs;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.stage.Stage;

public class FactorConversion extends Application {
    SimpleStringProperty nombre;
    SimpleStringProperty factorConversion;

    public FactorConversion(String nombre, String factorConversion) {
        this.nombre = new SimpleStringProperty(nombre);
        this.factorConversion = new SimpleStringProperty(factorConversion);
    }

    public String getNombre() {
        return nombre.get();
    }
    public SimpleStringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getFactorConversion() {
        return factorConversion.get();
    }

    public SimpleStringProperty factorConversionProperty() {
        return factorConversion;
    }

    public void setFactorConversion(String factorConversion) {
        this.factorConversion.set(factorConversion);
    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}
