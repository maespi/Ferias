package com.example.ferias.Ferias;


import com.example.ferias.Empresas.Empresa;

import java.util.Map;

public class Feria {
    private String name;
    private Map<String, Empresa> Empresas;

    public Feria() {
    }

    public Feria(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Empresa> getEmpresas() {
        return Empresas;
    }

    public void setEmpresas(Map<String, Empresa> empresas) {
        Empresas = empresas;
    }
}
