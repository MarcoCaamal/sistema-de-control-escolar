/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ce22test;

/**
 *
 * @author marco
 */
public class Unidad {
    public int id;
    public String nombre;
    public int idMateria;
    public Materia materia;

    public Unidad() {
        this.id = 0;
        this.nombre = "";
        this.idMateria = 0;
        this.materia = new Materia();
    }

    public Unidad(int id, String nombre, int idMateria) {
        this.id = id;
        this.nombre = nombre;
        this.idMateria = idMateria;
        this.materia = new Materia();
    }

    public Unidad(int id, String nombre, int idMateria, Materia materia) {
        this.id = id;
        this.nombre = nombre;
        this.idMateria = idMateria;
        this.materia = materia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
}
