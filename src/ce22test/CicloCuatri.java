/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ce22test;

/**
 *
 * @author marco
 */
public class CicloCuatri {
    public int id;
    public int idCiclo;
    public int idCuatri;
    public Ciclo ciclo;
    public Cuatrimestre cuatri;

    public CicloCuatri() {
        this.id = 0;
        this.idCiclo = 0;
        this.idCuatri = 0;
        this.ciclo = new Ciclo();
        this.cuatri = new Cuatrimestre();
    }

    public CicloCuatri(int id, int idCiclo, int idCuatri) {
        this.id = id;
        this.idCiclo = idCiclo;
        this.idCuatri = idCuatri;
        this.ciclo = new Ciclo();
        this.cuatri = new Cuatrimestre();
    }

    public CicloCuatri(int id, int idCiclo, int idCuatri, Ciclo ciclo, Cuatrimestre cuatri) {
        this.id = id;
        this.idCiclo = idCiclo;
        this.idCuatri = idCuatri;
        this.ciclo = ciclo;
        this.cuatri = cuatri;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCiclo() {
        return idCiclo;
    }

    public void setIdCiclo(int idCiclo) {
        this.idCiclo = idCiclo;
    }

    public int getIdCuatri() {
        return idCuatri;
    }

    public void setIdCuatri(int idCuatri) {
        this.idCuatri = idCuatri;
    }

    public Ciclo getCiclo() {
        return ciclo;
    }

    public void setCiclo(Ciclo ciclo) {
        this.ciclo = ciclo;
    }

    public Cuatrimestre getCuatri() {
        return cuatri;
    }

    public void setCuatri(Cuatrimestre cuatri) {
        this.cuatri = cuatri;
    }
}
