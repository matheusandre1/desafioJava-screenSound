package com.example.screenSound.models;

public enum Tipo {
    Solo("solo"),
    Dupla("dupla"),
    Banda("banda");

    private String tipo;

    Tipo(String tipo)
    {
        this.tipo = tipo;
    }


    public static Tipo fromString(String text) {
        for (Tipo tipo : Tipo.values()) {
            if (tipo.tipo.equalsIgnoreCase(text)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Nenhum Tipo encontrado para a string fornecida: " + text);
    }
}

