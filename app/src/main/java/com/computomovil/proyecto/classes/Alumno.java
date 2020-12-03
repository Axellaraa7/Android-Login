package com.computomovil.proyecto.classes;

import java.io.Serializable;

public class Alumno implements Serializable {
    private String name;
    private String fechaNac;
    private String numCuenta;
    private String carrera;
    private int id;

    public Alumno(String name,String fechaNac,String numCuenta,String carrera,int id){
        this.name=name;
        this.fechaNac=fechaNac;
        this.numCuenta=numCuenta;
        this.carrera=carrera;
        this.id=id;
    }

    public String getName(){
        return this.name;
    }

    public String getFechaNac() {
        return this.fechaNac;
    }

    public String getNumCuenta() {
        return this.numCuenta;
    }

    public String getCarrera() {
        return this.carrera;
    }

    public int getId(){
        return this.id;
    }
}
