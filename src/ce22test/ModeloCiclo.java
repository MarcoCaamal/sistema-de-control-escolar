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
public class ModeloCiclo extends Conexion{
    public ArrayList<Ciclo> lista = new ArrayList<>();
    public Ciclo ciclo = new Ciclo();
    private final String tabla = "ciclos";
    
    public ArrayList<Ciclo> consulta() {
        try {
            this.lista.clear();
            String query = "SELECT * FROM " + this.tabla;
            this.ps = this.conectar().prepareStatement(query);
            this.rs = this.ps.executeQuery();
            
            while(rs.next()) {
                Ciclo cicloTemporal = new Ciclo(this.rs.getInt("id"), this.rs.getString("nombre"));
                this.lista.add(cicloTemporal);
            }
            this.cnx.close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCiclo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.lista;
    }
    
    public ArrayList<Ciclo> buscar(String nombre) {
        try {
            this.lista.clear();
            String query = "SELECT * FROM " + this.tabla + " WHERE nombre LIKE ?";
            this.ps = this.conectar().prepareStatement(query);
            this.ps.setString(1, "%" + nombre + "%");
            
            this.rs = this.ps.executeQuery();
            
            while(rs.next()) {
                Ciclo cicloTemporal = new Ciclo(this.rs.getInt("id"), this.rs.getString("nombre"));
                this.lista.add(cicloTemporal);
            }
            this.cnx.close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCiclo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.lista;
    }
    
    public Ciclo crear(String nombre) {
        try {
            this.ciclo.setId(0);
            this.ciclo.setNombre("");
            String query = "INSERT INTO " + this.tabla + " (nombre) VALUES (?) ";
            this.ps = this.conectar().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            this.ps.setString(1, nombre);
            
            if(this.ps.executeUpdate() > 0) {
                this.ciclo.setNombre(nombre);
                
                this.rs = this.ps.getGeneratedKeys();
                if (this.rs.next()) {
                    this.ciclo.setId(this.rs.getInt(1));
                }
            }
            this.cnx.close();
        } catch (Exception ex) { 
            Logger.getLogger(ModeloCiclo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.ciclo;
    }
    
    public Ciclo actualizar(int id, String nombre) {
        try {
            this.ciclo.setId(0);
            this.ciclo.setNombre("");
            String query = "UPDATE " + this.tabla + " SET nombre = ? WHERE id = ?";
            this.ps = this.conectar().prepareStatement(query);
            this.ps.setString(1, nombre);
            this.ps.setInt(2, id);
            
            if(this.ps.executeUpdate() > 0) {
                this.ciclo.setNombre(nombre);
                this.ciclo.setId(id);
            }
            this.cnx.close();
        } catch (Exception ex) {
            Logger.getLogger(ModeloCiclo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.ciclo;
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
            Logger.getLogger(ModeloCiclo.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ModeloCiclo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return correcto;
    }
}
