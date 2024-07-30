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
public class ModeloCuatrimestre extends Conexion{
    public ArrayList<Cuatrimestre> lista = new ArrayList<>();
    public Cuatrimestre cuatrimestre = new Cuatrimestre();
    private final String tabla = "cuatrimestres";
    
    public ArrayList<Cuatrimestre> consulta() {
        try {
            this.lista.clear();
            String query = "SELECT * FROM " + this.tabla;
            this.ps = this.conectar().prepareStatement(query);
            this.rs = this.ps.executeQuery();
            
            while(rs.next()) {
                Cuatrimestre cuatriTemp = new Cuatrimestre(this.rs.getInt("id"), this.rs.getString("nombre"));
                this.lista.add(cuatriTemp);
            }
            this.cnx.close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCuatrimestre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.lista;
    }
    
    public ArrayList<Cuatrimestre> buscar(String nombre) {
        try {
            this.lista.clear();
            String query = "SELECT * FROM " + this.tabla + " WHERE nombre LIKE ?";
            this.ps = this.conectar().prepareStatement(query);
            this.ps.setString(1, "%" + nombre + "%");
            
            this.rs = this.ps.executeQuery();
            
            while(rs.next()) {
                Cuatrimestre cuatriTemp = new Cuatrimestre(this.rs.getInt("id"), this.rs.getString("nombre"));
                this.lista.add(cuatriTemp);
            }
            this.cnx.close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCuatrimestre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.lista;
    }
    
    public Cuatrimestre crear(String nombre) {
        try {
            this.cuatrimestre.setId(0);
            this.cuatrimestre.setNombre("");
            String query = "INSERT INTO " + this.tabla + " (nombre) VALUES (?) ";
            this.ps = this.conectar().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            this.ps.setString(1, nombre);
            
            if(this.ps.executeUpdate() > 0) {
                this.cuatrimestre.setNombre(nombre);
                
                this.rs = this.ps.getGeneratedKeys();
                if (this.rs.next()) {
                    this.cuatrimestre.setId(this.rs.getInt(1));
                }
            }
            this.cnx.close();
        } catch (Exception ex) { 
            Logger.getLogger(ModeloCuatrimestre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.cuatrimestre;
    }
    
    public Cuatrimestre actualizar(int id, String nombre) {
        try {
            this.cuatrimestre.setId(0);
            this.cuatrimestre.setNombre("");
            String query = "UPDATE " + this.tabla + " SET nombre = ? WHERE id = ?";
            this.ps = this.conectar().prepareStatement(query);
            this.ps.setString(1, nombre);
            this.ps.setInt(2, id);
            
            if(this.ps.executeUpdate() > 0) {
                this.cuatrimestre.setNombre(nombre);
                this.cuatrimestre.setId(id);
            }
            this.cnx.close();
        } catch (Exception ex) {
            Logger.getLogger(ModeloCuatrimestre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.cuatrimestre;
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
            Logger.getLogger(ModeloCuatrimestre.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ModeloCuatrimestre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return correcto;
    }
}
