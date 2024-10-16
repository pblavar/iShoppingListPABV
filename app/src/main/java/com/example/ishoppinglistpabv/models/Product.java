package com.example.ishoppinglistpabv.models;

import java.io.Serializable;

public class Product implements Serializable {
    int id;
    String name;
    String description;
    boolean state;
    boolean lactose;
    boolean gluten;

    public Product() {

    }

    public Product(int id, String name, String description, boolean state, boolean lactose, boolean gluten) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.state = state;
        this.lactose = lactose;
        this.gluten = gluten;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean isLactose() {
        return lactose;
    }

    public void setLactose(boolean lactose) { this.lactose = lactose; }

    public boolean isGluten() {
        return gluten;
    }

    public void setGluten(boolean gluten) {
        this.gluten = gluten;
    }

    /**
     * Método para cambiar el fondo del elemento según tenga lactosa o gluten
     *
     * @return
     */
    public int hasLactoseOrGluten() {
        if (this.lactose && this.gluten) {
            // Si tiene ambos, combinamos los dos colores
            return 3;
        } else if (this.lactose) {
            // Si solo tiene lactosa
            return 1;
        } else if (this.gluten) {
            // Si solo tiene gluten
            return 2;
        } else {
            // Si no tiene ni lactosa ni gluten, color por defecto
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", state=" + state + '\'' +
                ", lactose=" + lactose + '\'' +
                ", gluten=" + gluten +
                '}';
    }
}
