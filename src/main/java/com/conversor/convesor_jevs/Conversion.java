package com.conversor.convesor_jevs;

import java.text.DecimalFormat;

public class Conversion {
    float valor;
    float factorMult;

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public float getFactorMult() {
        return factorMult;
    }

    public void setFactorMult(float factorMult) {
        this.factorMult = factorMult;
    }

    public float conversionEstandar(float valor, float factorMult){
        this.valor = valor;
        this.factorMult = factorMult;
        return getValor()/getFactorMult();
    }

    public float conversionInversa(float valor, float factorMult){
        this.valor = valor;
        this.factorMult = factorMult;
        return getValor()*getFactorMult();
    }

    public String formatoMoneda(float valor){
        DecimalFormat formatoMoneda = new DecimalFormat("#,##0.00");
        String resultadoFormato = formatoMoneda.format(valor);
        return resultadoFormato;
    }
    //Por implementar para poner signo de moneda
    public String formatoMoneda(float valor, String caracter){
        DecimalFormat formatoMoneda = new DecimalFormat(caracter + "#,##0.00");
        String resultadoFormato = formatoMoneda.format(valor);
        return resultadoFormato;
    }
}
