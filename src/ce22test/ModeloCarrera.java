/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ce22test;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marco
 */
public class ModeloCarrera extends Conexion{
    public ArrayList<Carrera> lista = new ArrayList<>();
    public Carrera carrera = new Carrera();
    private final String tabla = "carreras";
    
    public ArrayList<Carrera> consulta() {
        try {
            this.lista.clear();
            String query = "SELECT * FROM " + this.tabla;
            this.ps = this.conectar().prepareStatement(query);
            this.rs = this.ps.executeQuery();
            
            while(rs.next()) {
                Carrera carreraTemporal = new Carrera(this.rs.getInt("id"), this.rs.getString("nombre"));
                this.lista.add(carreraTemporal);
            }
            this.cnx.close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCarrera.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.lista;
    }
    
    public ArrayList<Carrera> buscar(String nombre) {
        try {
            this.lista.clear();
            String query = "SELECT * FROM " + this.tabla + " WHERE nombre LIKE ?";
            this.ps = this.conectar().prepareStatement(query);
            this.ps.setString(1, "%" + nombre + "%");
            
            this.rs = this.ps.executeQuery();
            
            while(rs.next()) {
                Carrera carreraTemporal = new Carrera(this.rs.getInt("id"), this.rs.getString("nombre"));
                this.lista.add(carreraTemporal);
            }
            this.cnx.close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCarrera.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.lista;
    }
    
    public Carrera crear(String nombre) {
        try {
            this.carrera.setId(0);
            this.carrera.setNombre("");
            String query = "INSERT INTO " + this.tabla + " (nombre) VALUES (?) ";
            this.ps = this.conectar().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            this.ps.setString(1, nombre);
            
            if(this.ps.executeUpdate() > 0) {
                this.carrera.setNombre(nombre);
                
                this.rs = this.ps.getGeneratedKeys();
                if (this.rs.next()) {
                    this.carrera.setId(this.rs.getInt(1));
                }
            }
            this.cnx.close();
        } catch (Exception ex) { 
            Logger.getLogger(ModeloCarrera.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.carrera;
    }
    
    public Carrera actualizar(int id, String nombre) {
        try {
            this.carrera.setId(0);
            this.carrera.setNombre("");
            String query = "UPDATE " + this.tabla + " SET nombre = ? WHERE id = ?";
            this.ps = this.conectar().prepareStatement(query);
            this.ps.setString(1, nombre);
            this.ps.setInt(2, id);
            
            if(this.ps.executeUpdate() > 0) {
                this.carrera.setNombre(nombre);
                this.carrera.setId(id);
            }
            this.cnx.close();
        } catch (Exception ex) {
            Logger.getLogger(ModeloCarrera.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.carrera;
    }
    
    public boolean eliminar(int id) {
        boolean correcto = false;
        try {
            String query = "DELETE FROM " + this.tabla + " WHERE id = ?";
            this.ps = this.conectar().prepareStatement(query);
            this.ps.setInt(1, id);
            
            correcto = this.ps.executeUpdate() > 0;
            this.cnx.close();
        } catch (Exception ex) {
            Logger.getLogger(ModeloCarrera.class.getName()).log(Level.SEVERE, null, ex);
        }
        return correcto;
    }
    
    public boolean existe(int id) {
        boolean correcto = false;
        try {
            String query = "SELECT 1 FROM " + this.tabla + " WHERE id = ?;";
            this.ps = this.conectar().prepareStatement(query);
            this.ps.setInt(1, id);
            
            this.rs = this.ps.executeQuery();
            
            if(rs.next()) {
                correcto = true;
            }
            this.cnx.close();
        } catch (Exception ex) {
            Logger.getLogger(ModeloCarrera.class.getName()).log(Level.SEVERE, null, ex);
        }
        return correcto;
    }
}
