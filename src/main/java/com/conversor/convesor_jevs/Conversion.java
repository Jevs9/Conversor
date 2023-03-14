package com.conversor.convesor_jevs;

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
}
