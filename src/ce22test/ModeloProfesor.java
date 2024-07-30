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
public class ModeloProfesor extends Conexion{
    public ArrayList<Profesor> lista = new ArrayList<>();
    public Profesor profesor = new Profesor();
    private final String tabla = "profesores";
    
    public ArrayList<Profesor> consulta() {
        try {
            this.lista.clear();
            String query = "SELECT * FROM " + this.tabla;
            this.ps = this.conectar().prepareStatement(query);
            this.rs = this.ps.executeQuery();
            
            while(rs.next()) {
                Profesor profTemp = new Profesor(this.rs.getInt("id"), this.rs.getString("nombre"));
                this.lista.add(profTemp);
            }
            this.cnx.close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloProfesor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.lista;
    }
    
    public ArrayList<Profesor> buscar(String nombre) {
        try {
            this.lista.clear();
            String query = "SELECT * FROM " + this.tabla + " WHERE nombre LIKE ?";
            this.ps = this.conectar().prepareStatement(query);
            this.ps.setString(1, "%" + nombre + "%");
            
            this.rs = this.ps.executeQuery();
            
            while(rs.next()) {
                Profesor profTemp = new Profesor(this.rs.getInt("id"), this.rs.getString("nombre"));
                this.lista.add(profTemp);
            }
            this.cnx.close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloProfesor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.lista;
    }
    
    public Profesor crear(String nombre) {
        try {
            this.profesor.setId(0);
            this.profesor.setNombre("");
            String query = "INSERT INTO " + this.tabla + " (nombre) VALUES (?) ";
            this.ps = this.conectar().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            this.ps.setString(1, nombre);
            
            if(this.ps.executeUpdate() > 0) {
                this.profesor.setNombre(nombre);
                
                this.rs = this.ps.getGeneratedKeys();
                if (this.rs.next()) {
                    this.profesor.setId(this.rs.getInt(1));
                }
            }
            this.cnx.close();
        } catch (Exception ex) { 
            Logger.getLogger(ModeloProfesor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.profesor;
    }
    
    public Profesor actualizar(int id, String nombre) {
        try {
            this.profesor.setId(0);
            this.profesor.setNombre("");
            String query = "UPDATE " + this.tabla + " SET nombre = ? WHERE id = ?";
            this.ps = this.conectar().prepareStatement(query);
            this.ps.setString(1, nombre);
            this.ps.setInt(2, id);
            
            if(this.ps.executeUpdate() > 0) {
                this.profesor.setNombre(nombre);
                this.profesor.setId(id);
            }
            this.cnx.close();
        } catch (Exception ex) {
            Logger.getLogger(ModeloProfesor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.profesor;
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
            Logger.getLogger(ModeloProfesor.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ModeloProfesor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return correcto;
    }
}
